using System.Diagnostics;
using System.IO;
using System.Text.Json;
using Microsoft.AspNetCore.Builder;
using Microsoft.Extensions.DependencyInjection;
using Microsoft.Extensions.Hosting;
using Antlr4.Runtime;
using CodeGen;
using MiniPython;

var builder = WebApplication.CreateBuilder(args);

builder.Services.AddCors(options =>
{
    options.AddDefaultPolicy(builder =>
    {
        builder.AllowAnyOrigin()
            .AllowAnyMethod()
            .AllowAnyHeader();
    });
});

builder.Services.AddEndpointsApiExplorer();
builder.Services.AddSwaggerGen();

var app = builder.Build();

if (app.Environment.IsDevelopment())
{
    app.UseSwagger();
    app.UseSwaggerUI();
}

app.UseCors();

app.MapPost("/parse", async (HttpRequest request) =>
{
    try
    {
        // Leer el contenido de la solicitud (código fuente en JSON)
        using var reader = new StreamReader(request.Body);
        var body = await reader.ReadToEndAsync();
        var json = JsonSerializer.Deserialize<Dictionary<string, string>>(body);

        if (json == null)
        {
            Console.WriteLine("El cuerpo de la solicitud no contiene un JSON válido.");
            return Results.BadRequest("El cuerpo de la solicitud no contiene un JSON válido.");
        }

        var code = json.ContainsKey("code") ? json["code"] : string.Empty;

        if (string.IsNullOrWhiteSpace(code))
        {
            Console.WriteLine("No se proporcionó código fuente en el cuerpo de la solicitud.");
            return Results.BadRequest("No se proporcionó código fuente en el cuerpo de la solicitud.");
        }

        // Crear el lexer y parser usando el código fuente proporcionado
        var lexer = new MiniPythonLexer(CharStreams.fromString(code));
        var parserInstance = new MiParser(lexer);

        // Iniciar el análisis sintáctico
        parserInstance.ParseAndAnalyze();

        if (parserInstance.ParseTree == null)
        {
            Console.WriteLine("Error: ParseTree es nulo después de la generación.");
            return Results.Problem("Error al generar el árbol de análisis (ParseTree es nulo).");
        }

        // Obtener errores sintácticos y tabla de símbolos
        var errors = parserInstance.GetErrors();
        var symbolTableContent = parserInstance.GetSymbolTableContent();

        if (errors.Count > 0)
        {
            Console.WriteLine("Errores de parsing encontrados:");
            foreach (var error in errors)
            {
                Console.WriteLine($"Línea {error.Line}, Columna {error.Column}: {error.Message}");
            }

            return Results.BadRequest(new
            {
                error = "Parsing failed",
                details = errors,
                symbolTable = symbolTableContent
            });
        }

        // Generación de bytecode
        var codeGen = new CodeGeneration();
        try
        {
            codeGen.Visit(parserInstance.ParseTree);
        }
        catch (Exception visitEx)
        {
            Console.WriteLine("Error durante la visita del árbol de análisis: " + visitEx.Message);
            return Results.Problem("Error durante la generación del bytecode.");
        }

        var bytecode = codeGen.ToString();

        // Escribir el bytecode en un archivo llamado "bytecode.txt" en la raíz del proyecto
        var filePath = Path.Combine(Directory.GetCurrentDirectory(), "bytecode.txt");
        try
        {
            await File.WriteAllTextAsync(filePath, bytecode);
            Console.WriteLine($"Archivo bytecode.txt generado en: {filePath}");
        }
        catch (Exception writeEx)
        {
            Console.WriteLine("Error al escribir el archivo de bytecode: " + writeEx.Message);
            return Results.Problem("Error al escribir el archivo de bytecode.");
        }

        // Ejecutar MiniPY.exe con el archivo bytecode.txt como argumento
        string output = string.Empty;
        string errorOutput = string.Empty;

        try
        {
            var process = new Process
            {
                StartInfo = new ProcessStartInfo
                {
                    FileName = "MiniPY.exe",
                    Arguments = filePath,  
                    RedirectStandardOutput = true,
                    RedirectStandardError = true,
                    UseShellExecute = false,
                    CreateNoWindow = true
                }
            };

            process.Start();

            // Leer la salida de la ejecución
            output = await process.StandardOutput.ReadToEndAsync();
            errorOutput = await process.StandardError.ReadToEndAsync();
            process.WaitForExit();
        }
        catch (Exception ex)
        {
            Console.WriteLine("Error al ejecutar MiniPY.exe: " + ex.Message);
            return Results.Problem("Error al ejecutar MiniPY.exe.");
        }

        // Retornar el mensaje de éxito, tabla de símbolos, bytecode, y salida de MiniPY.exe
        return Results.Ok(new
        {
            message = "Parsing, code generation, and execution completed successfully.",
            symbolTable = symbolTableContent,
            bytecode = bytecode,         // Incluye el bytecode en la respuesta
            output = output,             // Incluye la salida de MiniPY.exe en la respuesta
            errorOutput = errorOutput    // Incluye errores, si existen
        });
    }
    catch (Exception ex)
    {
        var errorResponse = new
        {
            error = ex.Message,
            details = ex.StackTrace
        };
        Console.WriteLine("Error durante el procesamiento: " + ex.Message);
        return Results.Problem(JsonSerializer.Serialize(errorResponse));
    }
});

app.Run();
