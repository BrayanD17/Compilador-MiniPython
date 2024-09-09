using System.IO;
using System.Text.Json;
using Microsoft.AspNetCore.Builder;
using Microsoft.Extensions.DependencyInjection;
using Microsoft.Extensions.Hosting;
using Antlr4.Runtime;
using MiniPython; // Asegúrate de que este namespace coincida con el que usas

var builder = WebApplication.CreateBuilder(args);

// Agregar soporte para CORS
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

// Habilitar CORS
app.UseCors();

app.UseHttpsRedirection();

app.MapPost("/parse", async (HttpRequest request) =>
{
    try
    {
        using var reader = new StreamReader(request.Body);
        var body = await reader.ReadToEndAsync();
        var json = JsonSerializer.Deserialize<Dictionary<string, string>>(body);
        var code = json?["code"] ?? string.Empty;

        // Crear el lexer y el parser
        var lexer = new MiniPythonLexer(CharStreams.fromString(code));
        var parser = new MiniPythonParser(new CommonTokenStream(lexer));

        var errorListener = new CustomErrorListener();
        parser.RemoveErrorListeners(); // Remover los listeners existentes
        parser.AddErrorListener(errorListener);

        // Procesar el código
        parser.program(); // Asegúrate de llamar a la función de entrada de tu parser

        // Si hay errores, devolverlos
        if (errorListener.HasErrors)
        {
            // Separar los errores por saltos de línea
            var errorMessages = string.Join("\n", errorListener.Errors);
            return Results.BadRequest(new { error = "Parsing failed", details = errorMessages });
        }

        return Results.Ok("Parsing completed successfully.");
    }
    catch (Exception ex)
    {
        var errorResponse = new
        {
            error = ex.Message,
            details = ex.StackTrace
        };
        return Results.Problem(JsonSerializer.Serialize(errorResponse));
    }
});

app.Run();
