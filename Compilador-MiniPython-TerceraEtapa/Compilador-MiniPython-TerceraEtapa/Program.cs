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
        using var reader = new StreamReader(request.Body);
        var body = await reader.ReadToEndAsync();
        var json = JsonSerializer.Deserialize<Dictionary<string, string>>(body);
        var code = json?["code"] ?? string.Empty;

        var lexer = new MiniPythonLexer(CharStreams.fromString(code));
        var parserInstance = new MiParser(lexer);

        parserInstance.ParseAndAnalyze(); 

        var errors = parserInstance.GetErrors();
        var symbolTableContent = parserInstance.GetSymbolTableContent();

        if (errors.Count > 0)
        {
            return Results.BadRequest(new
            {
                error = "Parsing failed",
                details = errors,
                symbolTable = symbolTableContent
            });
        }

        return Results.Ok(new 
        { 
            message = "Parsing completed successfully.",
            symbolTable = symbolTableContent 
        });
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