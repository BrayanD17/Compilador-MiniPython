using System.IO;
using System.Text.Json;
using Microsoft.AspNetCore.Builder;
using Microsoft.Extensions.DependencyInjection;
using Microsoft.Extensions.Hosting;
using Antlr4.Runtime;
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
        var code = json?["code"] ?? string.Empty;

        // Crear el lexer y parser usando el código fuente proporcionado
        var lexer = new MiniPythonLexer(CharStreams.fromString(code));
        var parserInstance = new MiParser(lexer);

        // Iniciar el análisis semántico y sintáctico
        parserInstance.ParseAndAnalyze();

        // Obtener errores semánticos y tabla de símbolos
        var errors = parserInstance.GetErrors();
        var symbolTableContent = parserInstance.GetSymbolTableContent();

        // Comprobar si hubo errores durante el análisis sintáctico o semántico
        if (errors.Count > 0)
        {
            // Si hay errores, devolver un estado de "BadRequest" con detalles
            return Results.BadRequest(new
            {
                error = "Parsing failed",
                details = errors,
                symbolTable = symbolTableContent
            });
        }

        // Si no hubo errores, devolver un mensaje de éxito y la tabla de símbolos generada
        return Results.Ok(new 
        { 
            message = "Parsing completed successfully.",
            symbolTable = symbolTableContent 
        });
    }
    catch (Exception ex)
    {
        // Manejo de excepciones en caso de que ocurra un error durante el análisis o procesamiento
        var errorResponse = new
        {
            error = ex.Message,
            details = ex.StackTrace
        };
        return Results.Problem(JsonSerializer.Serialize(errorResponse));
    }
});

app.Run();
