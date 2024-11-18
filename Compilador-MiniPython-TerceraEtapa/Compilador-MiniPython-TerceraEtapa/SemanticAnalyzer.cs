using Antlr4.Runtime.Tree;
using System;
using System.Collections.Generic;

namespace MiniPython
{
    public class SemanticAnalyzer
    {
        private readonly SymbolTable _symbolTable = new SymbolTable();
        private readonly List<ErrorInfo> _semanticErrors = new List<ErrorInfo>();
        private bool isInGlobalScope = true;

        public List<ErrorInfo> GetSemanticErrors() => _semanticErrors;

        public void Analyze(IParseTree tree)
        {
            Console.WriteLine("Iniciando análisis semántico...");
            Visit(tree);
            Console.WriteLine("Análisis semántico finalizado.");
        }

        public List<string> GetSymbolTableContent()
        {
            var content = new List<string>();
            _symbolTable.ImprimirTablaSimbolos(content);
            return content;
        }

        private void Visit(IParseTree node)
        {
            switch (node)
            {
                case MiniPythonParser.DefStatementContext defContext:
                    HandleFunctionDefinition(defContext);
                    break;
                case MiniPythonParser.AssignStatementContext assignContext:
                    HandleAssignment(assignContext);
                    break;
                case MiniPythonParser.FunctionCallStatementContext callContext:
                    HandleFunctionCall(callContext);
                    break;
                case MiniPythonParser.PrintStatementContext printContext:
                    HandlePrintStatement(printContext);
                    break;
                case MiniPythonParser.IfStatementContext ifContext:
                    HandleIfStatement(ifContext);
                    break;
                case MiniPythonParser.WhileStatementContext whileContext:
                    HandleWhileStatement(whileContext);
                    break;
                case MiniPythonParser.ForStatementContext forContext:
                    HandleForStatement(forContext);
                    break;
                case MiniPythonParser.ReturnStatementContext returnContext:
                    HandleReturnStatement(returnContext);
                    break;
            }

            for (int i = 0; i < node.ChildCount; i++)
            {
                Visit(node.GetChild(i));
            }
        }

        private void HandleFunctionDefinition(MiniPythonParser.DefStatementContext defContext)
        {
            var functionName = defContext.IDENTIFIER().GetText();
            Console.WriteLine($"Definiendo función: {functionName}");
            var parameters = defContext.argList()?.IDENTIFIER();
            var paramList = new List<string>();

            if (_symbolTable.BuscarEnNivelActual(functionName) != null)
            {
                Console.WriteLine($"Error: La función '{functionName}' ya está definida en este nivel.");
                _semanticErrors.Add(new ErrorInfo
                {
                    Line = defContext.Start.Line,
                    Column = 0,
                    Message = $"Error: Método '{functionName}' ya está definido en este nivel."
                });
                return;
            }

            if (parameters != null)
            {
                foreach (var param in parameters)
                {
                    paramList.Add(param.GetText());
                }
            }
            _symbolTable.InsertarFuncion(functionName, "method", paramList);
            Console.WriteLine($"Función '{functionName}' con parámetros {string.Join(", ", paramList)} registrada en la tabla de símbolos.");

            isInGlobalScope = false;
            _symbolTable.OpenScope();

            foreach (var param in paramList)
            {
                _symbolTable.InsertarVariable(param, "parameter", false);
                Console.WriteLine($"Parámetro '{param}' registrado en el alcance de la función '{functionName}'.");
            }

            Visit(defContext.sequence());

            _symbolTable.CloseScope();
            isInGlobalScope = true;
            Console.WriteLine($"Alcance de la función '{functionName}' cerrado y variables locales eliminadas.");
        }

        private void HandleAssignment(MiniPythonParser.AssignStatementContext assignContext)
        {
            // Manejar asignación simple
            if (assignContext.simpleAssignStatement() != null)
            {
                var variableName = assignContext.simpleAssignStatement().IDENTIFIER().GetText();
                Console.WriteLine($"Asignando a la variable: {variableName}");

                if (assignContext.simpleAssignStatement().expression() == null)
                {
                    Console.WriteLine($"Error: La asignación a '{variableName}' no tiene una expresión válida.");
                    _semanticErrors.Add(new ErrorInfo
                    {
                        Line = assignContext.Start.Line,
                        Column = 0,
                        Message = $"Error: La asignación a '{variableName}' no tiene una expresión válida."
                    });
                    return;
                }

                // Registrar la variable en el alcance correspondiente
                if (isInGlobalScope)
                {
                    if (_symbolTable.BuscarEnNivelActual(variableName) == null)
                    {
                        Console.WriteLine($"Registrando nueva variable global '{variableName}'.");
                        _symbolTable.InsertarVariable(variableName, "global variable", false);
                    }
                }
                else
                {
                    if (_symbolTable.Buscar(variableName) == null)
                    {
                        Console.WriteLine($"Registrando nueva variable local '{variableName}' en el alcance actual.");
                        _symbolTable.InsertarVariable(variableName, "local variable", false);
                    }
                    else
                    {
                        Console.WriteLine($"Variable '{variableName}' ya existe en algún nivel, no se registra de nuevo.");
                    }
                }
            }
            // Manejar asignación a índice de lista
            else if (assignContext.listAssignStatement() != null)
            {
                var listName = assignContext.listAssignStatement().IDENTIFIER().GetText();
                Console.WriteLine($"Asignando a un índice de la lista: {listName}");

                if (assignContext.listAssignStatement().expression().Length < 2)
                {
                    Console.WriteLine($"Error: La asignación a un índice de '{listName}' no tiene una expresión válida.");
                    _semanticErrors.Add(new ErrorInfo
                    {
                        Line = assignContext.Start.Line,
                        Column = 0,
                        Message = $"Error: La asignación a un índice de '{listName}' no tiene una expresión válida."
                    });
                    return;
                }

                // Verificar si la lista está definida
                if (_symbolTable.Buscar(listName) == null)
                {
                    Console.WriteLine($"Error: La lista '{listName}' no está definida.");
                    _semanticErrors.Add(new ErrorInfo
                    {
                        Line = assignContext.Start.Line,
                        Column = 0,
                        Message = $"Error: La lista '{listName}' no está definida."
                    });
                    return;
                }

                Console.WriteLine($"Asignación válida al índice de la lista '{listName}'.");
            }
        }

        private void HandleFunctionCall(MiniPythonParser.FunctionCallStatementContext callContext)
        {
            var methodName = callContext.IDENTIFIER().GetText();
            var paramCount = callContext.expressionList()?.expression().Length ?? 0;
            Console.WriteLine($"Llamada a función: {methodName} con {paramCount} parámetros.");

            if (methodName == "print")
            {
                foreach (var expr in callContext.expressionList()?.expression() ?? Array.Empty<MiniPythonParser.ExpressionContext>())
                {
                    VisitExpression(expr, callContext.Start.Line);
                }
            }
            else
            {
                CheckMethodCallParameters(methodName, paramCount, callContext.Start.Line);
            }
        }

        private void HandlePrintStatement(MiniPythonParser.PrintStatementContext printContext)
        {
            Console.WriteLine("Procesando sentencia print con expresiones:");
            foreach (var expr in printContext.expression())
            {
                VisitExpression(expr, printContext.Start.Line);
            }
        }

        private void HandleIfStatement(MiniPythonParser.IfStatementContext ifContext)
        {
            Console.WriteLine($"Procesando sentencia 'if' en línea {ifContext.Start.Line}");
            Visit(ifContext.logicalExpression());
            Visit(ifContext.sequence(0));
            if (ifContext.sequence().Length > 1)
            {
                Visit(ifContext.sequence(1)); // Else
            }
        }

        private void HandleWhileStatement(MiniPythonParser.WhileStatementContext whileContext)
        {
            Console.WriteLine($"Procesando sentencia 'while' en línea {whileContext.Start.Line}");
            Visit(whileContext.logicalExpression());
            Visit(whileContext.sequence());
        }

        private void HandleForStatement(MiniPythonParser.ForStatementContext forContext)
        {
            Console.WriteLine($"Procesando sentencia 'for' en línea {forContext.Start.Line}");
            Visit(forContext.expression());
            Visit(forContext.expressionList());
            Visit(forContext.sequence());
        }

        private void HandleReturnStatement(MiniPythonParser.ReturnStatementContext returnContext)
        {
            Console.WriteLine($"Procesando sentencia 'return' en línea {returnContext.Start.Line}");
            if (returnContext.expression() != null)
            {
                Visit(returnContext.expression());
            }
        }

        private bool VisitExpression(MiniPythonParser.ExpressionContext exprContext, int line)
        {
            if (exprContext == null)
            {
                Console.WriteLine($"Error: Expresión no válida en la línea {line}.");
                _semanticErrors.Add(new ErrorInfo
                {
                    Line = line,
                    Column = 0,
                    Message = $"Error: Expresión no válida."
                });
                return false;
            }

            return true;
        }

        private void CheckMethodCallParameters(string methodName, int actualParamCount, int line)
        {
            var symbol = _symbolTable.Buscar(methodName);
            if (symbol is MethodInfo methodSymbol)
            {
                if (methodSymbol.Params.Count != actualParamCount)
                {
                    Console.WriteLine($"Error: El método '{methodName}' espera {methodSymbol.Params.Count} parámetros, pero se pasaron {actualParamCount} en línea {line}.");
                    _semanticErrors.Add(new ErrorInfo
                    {
                        Line = line,
                        Column = 0,
                        Message = $"Error: El método '{methodName}' espera {methodSymbol.Params.Count} parámetros, pero se pasaron {actualParamCount}."
                    });
                }
            }
        }
    }
}
