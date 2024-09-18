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

app.UseHttpsRedirection();

app.MapPost("/parse", async (HttpRequest request) =>
{
    try
    {

        using var reader = new StreamReader(request.Body);
        var body = await reader.ReadToEndAsync();
        var json = JsonSerializer.Deserialize<Dictionary<string, string>>(body);
        var code = json?["code"] ?? string.Empty;

        var lexer = new MiniPythonLexer(CharStreams.fromString(code));
        var parser = new MiniPythonParser(new CommonTokenStream(lexer));

        var errorListener = new CustomErrorListener();
        parser.AddErrorListener(errorListener);

        parser.program(); 
        if (errorListener.HasErrors)
        {
            return Results.BadRequest(new
            {
                error = "Parsing failed",
                details = errorListener.Errors.Select(err => new
                {
                    line = err.Line,
                    column = err.Column,
                    message = err.Message
                })
            });
        }
        return Results.Ok(new { message = "Parsing completed successfully." });
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
