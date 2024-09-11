using Antlr4.Runtime;
using Antlr4.Runtime.Tree;
using System.Collections.Generic;
using System.IO;

namespace MiniPython
{
    public class MiParser
    {
        private readonly MiniPythonParser _parser;

        // Constructor
        public MiParser(MiniPythonLexer lexer)
        {
            var tokens = new CommonTokenStream(lexer);
            _parser = new MiniPythonParser(tokens);
            _parser.RemoveErrorListeners();
            _parser.AddErrorListener(new CustomErrorListener());
        }

        // Método para parsear
        public void Parse()
        {
            IParseTree tree = _parser.program();
            // Aquí puedes implementar la lógica para recorrer el árbol sintáctico
            // usando el visitor pattern o el listener pattern si lo necesitas.
        }
    }

    // Manejo de errores personalizado
    public class CustomErrorListener : BaseErrorListener
{
    public List<ErrorInfo> Errors { get; private set; } = new List<ErrorInfo>();

    public bool HasErrors => Errors.Count > 0;

    public override void SyntaxError(
        TextWriter output,
        IRecognizer recognizer,
        IToken offendingSymbol,
        int line,
        int charPositionInLine,
        string msg,
        RecognitionException e)
    {
        // Agrega el error a la lista
        Errors.Add(new ErrorInfo
        {
            Line = line,
            Column = charPositionInLine,
            Message = msg
        });
    }
}

public class ErrorInfo
{
    public int Line { get; set; }
    public int Column { get; set; }
    public string Message { get; set; }
}
}
