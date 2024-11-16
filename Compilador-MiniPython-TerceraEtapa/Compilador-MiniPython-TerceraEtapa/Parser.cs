using Antlr4.Runtime;
using Antlr4.Runtime.Tree;
using System.Collections.Generic;
using System.IO;

namespace MiniPython
{
    public class MiParser
    {
        private readonly MiniPythonParser _parser;
        private readonly CustomErrorListener _errorListener;

        public IParseTree ParseTree { get; private set; }

        public MiParser(MiniPythonLexer lexer)
        {
            var tokens = new CommonTokenStream(lexer);
            _parser = new MiniPythonParser(tokens);
            _errorListener = new CustomErrorListener();
            _parser.RemoveErrorListeners();
            _parser.AddErrorListener(_errorListener);
        }

        public void ParseAndAnalyze()
        {
            ParseTree = _parser.program();

            if (_errorListener.HasErrors)
                return;
        }

        public List<ErrorInfo> GetErrors()
        {
            return _errorListener.Errors;
        }

        public List<string> GetSymbolTableContent()
        {
            return new List<string>();
        }
    }

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
            Errors.Add(new ErrorInfo
            {
                Line = line,
                Column = charPositionInLine,
                Message = $"Error de sintaxis: {msg}"
            });
            Console.WriteLine($"Error de sintaxis en línea {line}, posición {charPositionInLine}: {msg}");
        }
    }
}