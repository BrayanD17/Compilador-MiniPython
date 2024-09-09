using Antlr4.Runtime;
using Antlr4.Runtime.Tree;

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
    public class CustomErrorListener : IAntlrErrorListener<IToken>
{
    public List<string> Errors { get; } = new List<string>();
    public bool HasErrors => Errors.Count > 0;

    public void SyntaxError(
        TextWriter output,
        IRecognizer recognizer,
        IToken offendingSymbol,
        int line,
        int charPositionInLine,
        string msg,
        RecognitionException e)
    {
        // Almacena el mensaje de error
        Errors.Add($"Error en la línea {line}:{charPositionInLine} - {msg}");
    }
}

}
