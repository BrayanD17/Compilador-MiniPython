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
        // private readonly SemanticAnalyzer _semanticAnalyzer; // Análisis semántico comentado

        public MiParser(MiniPythonLexer lexer)
        {
            var tokens = new CommonTokenStream(lexer);
            _parser = new MiniPythonParser(tokens);
            _errorListener = new CustomErrorListener();
            _parser.RemoveErrorListeners();
            _parser.AddErrorListener(_errorListener);

            // _semanticAnalyzer = new SemanticAnalyzer(); // Inicialización del análisis semántico comentada
        }

        public void ParseAndAnalyze()
        {
            var tree = _parser.program();

            if (_errorListener.HasErrors)
                return;

            // _semanticAnalyzer.Analyze(tree); // Ejecución del análisis semántico comentada

            // foreach (var error in _semanticAnalyzer.GetSemanticErrors()) // Agregado de errores semánticos comentado
            // {
            //     _errorListener.Errors.Add(error);
            // }
        }

        public List<ErrorInfo> GetErrors()
        {
            return _errorListener.Errors;
        }

        public List<string> GetSymbolTableContent()
        {
            // return _semanticAnalyzer.GetSymbolTableContent(); // Obtención de contenido de la tabla de símbolos comentada
            return new List<string>(); // Retornar lista vacía para evitar errores
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
        }
    }
}
