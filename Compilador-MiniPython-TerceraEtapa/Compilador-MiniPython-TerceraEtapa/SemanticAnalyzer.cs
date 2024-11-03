using Antlr4.Runtime.Tree;
using System;
using System.Collections.Generic;

namespace MiniPython
{
    public class SemanticAnalyzer
    {
        private readonly SymbolTable _symbolTable = new SymbolTable();
        private readonly List<ErrorInfo> _semanticErrors = new List<ErrorInfo>();
        private bool isInGlobalScope = true; // Nueva variable para controlar el alcance global

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
            if (node is MiniPythonParser.DefStatementContext defContext)
            {
                HandleFunctionDefinition(defContext);
            }
            else if (node is MiniPythonParser.AssignStatementContext assignContext)
            {
                HandleAssignment(assignContext);
            }
            else if (node is MiniPythonParser.FunctionCallStatementContext callContext)
            {
                HandleFunctionCall(callContext);
            }
            else if (node is MiniPythonParser.PrintStatementContext printContext)
            {
                HandlePrintStatement(printContext);
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

            isInGlobalScope = false;  // Entramos en el alcance de una función (local)
            _symbolTable.OpenScope();

            foreach (var param in paramList)
            {
                _symbolTable.InsertarVariable(param, "parameter", false);
                Console.WriteLine($"Parámetro '{param}' registrado en el alcance de la función '{functionName}'.");
            }

            Visit(defContext.sequence());

            _symbolTable.CloseScope();
            isInGlobalScope = true;  // Volvemos al alcance global
            Console.WriteLine($"Alcance de la función '{functionName}' cerrado y variables locales eliminadas.");
        }

        private void HandleAssignment(MiniPythonParser.AssignStatementContext assignContext)
        {
            var variableName = assignContext.IDENTIFIER().GetText();
            Console.WriteLine($"Asignando a la variable: {variableName}");

            // Verificar las expresiones en el lado derecho de la asignación
            bool isAssignmentValid = VisitExpression(assignContext.expression(), assignContext.Start.Line);

            if (!isAssignmentValid)
            {
                Console.WriteLine($"Error: Asignación no válida a '{variableName}' debido a variables o métodos no definidos.");
                return;
            }

            if (isInGlobalScope) // Si estamos en el alcance global
            {
                if (_symbolTable.BuscarEnNivelActual(variableName) == null)
                {
                    Console.WriteLine($"Registrando nueva variable global '{variableName}'.");
                    _symbolTable.InsertarVariable(variableName, "global variable", false);
                }
            }
            else // Si estamos en el alcance de una función
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

        private void HandleFunctionCall(MiniPythonParser.FunctionCallStatementContext callContext)
        {
            var methodName = callContext.IDENTIFIER().GetText();
            var paramCount = callContext.expressionList()?.expression().Length ?? 0;
            Console.WriteLine($"Llamada a función: {methodName} con {paramCount} parámetros.");

            if (methodName == "print")
            {
                if (callContext.expressionList() != null)
                {
                    foreach (var expr in callContext.expressionList().expression())
                    {
                        VisitExpression(expr, callContext.Start.Line);
                    }
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

        private bool VisitExpression(MiniPythonParser.ExpressionContext exprContext, int line)
        {
            bool isExpressionValid = true;
            Console.WriteLine($"Analizando expresión en línea {line}...");

            foreach (var child in exprContext.children)
            {
                if (child is MiniPythonParser.ElementExpressionContext elemExprContext)
                {
                    foreach (var elemChild in elemExprContext.children)
                    {
                        if (elemChild is MiniPythonParser.PrimitiveExpressionContext primitiveExpr)
                        {
                            var terminalNode = primitiveExpr.GetChild(0) as ITerminalNode;
                            if (terminalNode != null)
                            {
                                var identifier = terminalNode.GetText();
                                if (!IsStringLiteral(identifier))
                                {
                                    bool identifierValid = CheckIdentifierUsage(identifier, line);
                                    if (!identifierValid)
                                    {
                                        isExpressionValid = false;
                                    }
                                }
                            }
                        }
                    }
                }
            }
            return isExpressionValid;
        }

        private bool IsStringLiteral(string text)
        {
            return (text.StartsWith("\"") && text.EndsWith("\"")) || (text.StartsWith("'") && text.EndsWith("'"));
        }

        private bool CheckIdentifierUsage(string identifier, int line)
        {
            var symbol = _symbolTable.Buscar(identifier);

            if (symbol == null)
            {
                Console.WriteLine($"Error: '{identifier}' no está definido en línea {line}.");
                _semanticErrors.Add(new ErrorInfo
                {
                    Line = line,
                    Column = 0,
                    Message = $"Error: '{identifier}' no está definido."
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
            else if (symbol == null)
            {
                Console.WriteLine($"Error: El método '{methodName}' no está definido en línea {line}.");
                _semanticErrors.Add(new ErrorInfo
                {
                    Line = line,
                    Column = 0,
                    Message = $"Error: El método '{methodName}' no está definido."
                });
            }
        }
    }
}
