using System;
using System.Collections.Generic;
using System.Text;
using System.Linq;
using Antlr4.Runtime;
using Antlr4.Runtime.Tree;

namespace CodeGen
{
    public class CodeGeneration : MiniPythonParserBaseVisitor<object>
    {
        private class Instruction
        {
            public string Instr { get; set; }
            public string Value { get; set; } // Hacer que Value sea modificable

            public Instruction(string instr, string value)
            {
                Instr = instr;
                Value = value;
            }

            public Instruction(string instr)
            {
                Instr = instr;
            }

            public override string ToString()
            {
                return Value != null ? $"{Instr} {Value}" : Instr;
            }
        }

        private readonly List<Instruction> bytecode;
        private readonly HashSet<string> localVariables = new HashSet<string>();
        private readonly HashSet<string> globalVariables = new HashSet<string>();
        private int currentLevel = 0;

        public CodeGeneration()
        {
            bytecode = new List<Instruction>();
        }

        public override object VisitProgram(MiniPythonParser.ProgramContext context)
        {
            currentLevel = 0; 
            string lastDefinedFunction = null;

            foreach (var stmt in context.statement())
            {
                // Revisa si el contexto contiene una declaración de función
                if (stmt is MiniPythonParser.StatementContext statementContext &&
                    statementContext.defStatement() != null)
                {
                    lastDefinedFunction = statementContext.defStatement().IDENTIFIER().GetText();
                }

                Visit(stmt); // Visita cada declaración en el programa
            }

            // Si no hay ninguna llamada explícita, invoca automáticamente la última función definida
            if (lastDefinedFunction != null && !bytecode.Any(instr => instr.Instr == "CALL_FUNCTION"))
            {
                bytecode.Add(new Instruction("LOAD_GLOBAL", lastDefinedFunction));
                bytecode.Add(new Instruction("CALL_FUNCTION", "0")); // Suponemos sin argumentos para la invocación automática
            }

            bytecode.Add(new Instruction("END"));
            return null;
        }
        
        public override object VisitStatement(MiniPythonParser.StatementContext context)
        {
            return base.VisitStatement(context);
        }

        public override object VisitAssignStatement(MiniPythonParser.AssignStatementContext context)
        {
            var varName = context.IDENTIFIER().GetText();
            var scopedVarName = currentLevel == 0 ? varName : $"{varName}_{currentLevel}";

            if (context.LBRACKET() != null && context.RBRACKET() != null)
            {
                bytecode.Add(new Instruction(currentLevel == 0 ? "LOAD_GLOBAL" : "LOAD_FAST", scopedVarName));
                Visit(context.expression(0)); // Índice
                Visit(context.expression(1)); // Valor
                bytecode.Add(new Instruction("STORE_SUBSCR"));
            }
            else
            {
                if (currentLevel == 0 && !globalVariables.Contains(scopedVarName))
                {
                    bytecode.Add(new Instruction("PUSH_GLOBAL", scopedVarName));
                    globalVariables.Add(scopedVarName);
                }
                else if (!localVariables.Contains(scopedVarName))
                {
                    bytecode.Add(new Instruction("PUSH_LOCAL", scopedVarName));
                    localVariables.Add(scopedVarName);
                }

                VisitExpressionHandlingGlobals(context.expression(0));

                bytecode.Add(new Instruction(currentLevel == 0 ? "STORE_GLOBAL" : "STORE_FAST", scopedVarName));
            }

            return null;
        }

        private void VisitExpressionHandlingGlobals(ParserRuleContext context)
        {
            if (context is MiniPythonParser.ElementExpressionContext elementExpr &&
                elementExpr.primitiveExpression() is MiniPythonParser.PrimitiveExpressionidentifierListASTContext identifierExpr)
            {
                var identifier = identifierExpr.IDENTIFIER().GetText();

                // Verificar si la variable es global
                if (globalVariables.Contains(identifier))
                {
                    bytecode.Add(new Instruction("LOAD_GLOBAL", identifier));
                }
                else if (localVariables.Contains($"{identifier}_{currentLevel}"))
                {
                    // Si no es global, verificar si es local
                    bytecode.Add(new Instruction("LOAD_FAST", $"{identifier}_{currentLevel}"));
                }
                else
                {
                    // Generar advertencia si no está declarada
                    Console.WriteLine($"Advertencia: la variable '{identifier}' no está declarada.");
                }
            }
            else
            {
                // Procesar expresiones complejas o no identificadas
                Visit(context);
            }
        }

        public override object VisitListExpression(MiniPythonParser.ListExpressionContext context)
        {
            var expressions = context.expressionList()?.expression().ToList();
            var numElements = expressions?.Count ?? 0;

            if (expressions != null)
            {
                foreach (var expr in expressions)
                {
                    Visit(expr); // Cargar cada elemento en la pila
                }
            }

            // Crear una lista con los elementos en la pila
            bytecode.Add(new Instruction("BUILD_LIST", numElements.ToString()));
            return null;
        }

        public override object VisitIfStatement(MiniPythonParser.IfStatementContext context)
        {
            Visit(context.logicalExpression());

            bytecode.Add(new Instruction("JUMP_IF_FALSE", ""));
            var jumpIfFalseIndex = bytecode.Count - 1;

            Visit(context.sequence(0));

            bytecode.Add(new Instruction("JUMP_ABSOLUTE", ""));
            var jumpAbsoluteIndex = bytecode.Count - 1;

            bytecode.ElementAt(jumpIfFalseIndex).Value = bytecode.Count.ToString();

            if (context.sequence().Length > 1)
            {
                Visit(context.sequence(1));
            }

            bytecode.ElementAt(jumpAbsoluteIndex).Value = bytecode.Count.ToString();

            return null;
        }
        
        public override object VisitWhileStatement(MiniPythonParser.WhileStatementContext context)
        {
            var start = bytecode.Count;

            Visit(context.logicalExpression());

            bytecode.Add(new Instruction("JUMP_IF_FALSE", ""));
            var exitJumpIndex = bytecode.Count - 1;

            Visit(context.sequence());

            bytecode.Add(new Instruction("JUMP_ABSOLUTE", start.ToString()));

            bytecode.ElementAt(exitJumpIndex).Value = bytecode.Count.ToString();

            return null;
        }

        public override object VisitDefStatement(MiniPythonParser.DefStatementContext context)
        {
            var methodName = currentLevel == 0 ? context.IDENTIFIER().GetText() : $"{context.IDENTIFIER().GetText()}_{currentLevel}";
            bytecode.Add(new Instruction("DEF", methodName));
    
            currentLevel++;
            if (context.argList() != null)
            {
                Visit(context.argList());
            }
    
            Visit(context.sequence());
            localVariables.Clear();

            // Solo agrega RETURN si hay un RETURN_VALUE en el bytecode o si es un método en contexto no global.
            bool hasReturnValue = bytecode.Any(instr => instr.Instr == "RETURN_VALUE");
            if (hasReturnValue || currentLevel > 1) 
            {
                bytecode.Add(new Instruction("RETURN"));
            }
            currentLevel--;
            return null;
        }

        public override object VisitReturnStatement(MiniPythonParser.ReturnStatementContext context)
        {
            if (context.expression() != null)
            {
                Visit(context.expression());
                bytecode.Add(new Instruction("RETURN_VALUE"));
            }
            else
            {
                bytecode.Add(new Instruction("RETURN"));
            }
            return null;
        }
        
        public override object VisitPrintStatement(MiniPythonParser.PrintStatementContext context)
        {
            foreach (var expr in context.expression())
            {
                Visit(expr);
            }

            bytecode.Add(new Instruction("LOAD_GLOBAL", "print"));  // Usa -1 para `print`
            bytecode.Add(new Instruction("CALL_FUNCTION", context.expression().Length.ToString()));
            return null;
        }
        
        public override object VisitFunctionCallStatement(MiniPythonParser.FunctionCallStatementContext context)
        {
            var numArgs = 0;
            foreach (var expr in context.expressionList().expression())
            {
                Visit(expr);
                numArgs++;
            }
            bytecode.Add(new Instruction("LOAD_GLOBAL", context.IDENTIFIER().GetText()));
            bytecode.Add(new Instruction("CALL_FUNCTION", numArgs.ToString()));
            return null;
        }
        
        public override object VisitExpressionList(MiniPythonParser.ExpressionListContext context)
        {
            if (context.expression() != null)
            {
                foreach (var expr in context.expression())
                {
                    VisitExpressionHandlingGlobals(expr); // Manejar expresiones globales
                }
            }
            return context.expression()?.Count() ?? 0; // Devuelve la cantidad de expresiones evaluadas
        }

        public override object VisitExpression(MiniPythonParser.ExpressionContext context)
        {
            return Visit(context.additionExpression());
        }

        public override object VisitAdditionExpression(MiniPythonParser.AdditionExpressionContext context)
        {
            // Procesa la primera expresión
            VisitExpressionHandlingGlobals(context.multiplicationExpression(0));

            // Procesa las siguientes expresiones y las operaciones
            for (var i = 1; i < context.multiplicationExpression().Length; i++)
            {
                VisitExpressionHandlingGlobals(context.multiplicationExpression(i));

                if (context.PLUS(i - 1) != null)
                {
                    bytecode.Add(new Instruction("BINARY_ADD"));
                }
                else if (context.MINUS(i - 1) != null)
                {
                    bytecode.Add(new Instruction("BINARY_SUBTRACT"));
                }
            }

            return null;
        }

        public override object VisitMultiplicationExpression(MiniPythonParser.MultiplicationExpressionContext context)
        {
            Visit(context.elementExpression(0));
            for (var i = 1; i < context.elementExpression().Length; i++)
            {
                Visit(context.elementExpression(i));
                if (context.MULT(i - 1) != null)
                {
                    bytecode.Add(new Instruction("BINARY_MULTIPLY"));
                }
                else if (context.DIV(i - 1) != null)
                {
                    bytecode.Add(new Instruction("BINARY_DIVIDE"));
                }
                else if (context.MOD(i - 1) != null)
                {
                    bytecode.Add(new Instruction("BINARY_MODULO"));
                }
            }
            return null;
        }

        public override object VisitPrimitiveExpressionidentifierListAST(MiniPythonParser.PrimitiveExpressionidentifierListASTContext context)
        {
            var varName = context.IDENTIFIER().GetText();
    
            if (varName == "print")
            {
                Console.WriteLine("Generando bytecode para `print`");

                if (context.expressionList() != null)
                {
                    var numArgs = 0;
                    foreach (var expr in context.expressionList().expression())
                    {
                        Visit(expr);
                        numArgs++;
                    }
                    bytecode.Add(new Instruction("LOAD_GLOBAL", "print"));
                    bytecode.Add(new Instruction("CALL_FUNCTION", numArgs.ToString()));
                }
            }
            else
            {
                // Procede normalmente con el sufijo si no es `print`
                var scopedVarName = $"{varName}_{currentLevel}";

                if (context.expressionList() != null)
                {
                    var numArgs = 0;
                    foreach (var expr in context.expressionList().expression())
                    {
                        Visit(expr);
                        numArgs++;
                    }

                    // Usa LOAD_GLOBAL para otras funciones y llamadas externas
                    bytecode.Add(new Instruction("LOAD_GLOBAL", scopedVarName));
                    bytecode.Add(new Instruction("CALL_FUNCTION", numArgs.ToString()));
                }
                else
                {
                    // Verificar si la variable es global o local
                    if (globalVariables.Contains(scopedVarName))
                    {
                        bytecode.Add(new Instruction("LOAD_GLOBAL", scopedVarName));
                    }
                    else if (localVariables.Contains(scopedVarName))
                    {
                        bytecode.Add(new Instruction("LOAD_FAST", scopedVarName));
                    }
                    else
                    {
                        Console.WriteLine($"Advertencia: la variable '{scopedVarName}' no está declarada.");
                    }
                }
            }

            return null;
        }

        public override object VisitPrimitiveExpressionliteralAST(MiniPythonParser.PrimitiveExpressionliteralASTContext context)
        {
            bytecode.Add(new Instruction("LOAD_CONST", context.GetText()));
            return null;
        }

        public override object VisitLogicalExpression(MiniPythonParser.LogicalExpressionContext context)
        {
            Visit(context.comparison(0));
            for (var i = 1; i < context.comparison().Length; i++)
            {
                Visit(context.comparison(i));
                if (context.AND(i - 1) != null)
                {
                    bytecode.Add(new Instruction("BINARY_AND"));
                }
                else if (context.OR(i - 1) != null)
                {
                    bytecode.Add(new Instruction("BINARY_OR"));
                }
            }
            return null;
        }


        public override object VisitComparison(MiniPythonParser.ComparisonContext context)
        {
            Visit(context.additionExpression(1));
            Visit(context.additionExpression(0));

            if (context.LT() != null)
            {
                bytecode.Add(new Instruction("COMPARE_OP", "<"));
            }
            else if (context.GT() != null)
            {
                bytecode.Add(new Instruction("COMPARE_OP", ">"));
            }
            else if (context.LE() != null)
            {
                bytecode.Add(new Instruction("COMPARE_OP", "<="));
            }
            else if (context.GE() != null)
            {
                bytecode.Add(new Instruction("COMPARE_OP", ">="));
            }
            else if (context.EQ() != null)
            {
                bytecode.Add(new Instruction("COMPARE_OP", "=="));
            }

            return null;
        }

        public override object VisitElementExpression(MiniPythonParser.ElementExpressionContext context)
        {
            if (context.LBRACKET() != null && context.RBRACKET() != null)
            {
                Visit(context.primitiveExpression()); // Cargar la referencia del array
                Visit(context.expression()); // Cargar el índice en la pila
                bytecode.Add(new Instruction("BINARY_SUBSCR")); // Cargar el valor del índice
            }
            else
            {
                Visit(context.primitiveExpression());
            }
            return null;
        }

        public override string ToString()
        {
            var sb = new StringBuilder();
            var count = 0;
            foreach (var instr in bytecode)
            {
                sb.AppendLine($"{count++} {instr}");
            }
            return sb.ToString();
        }
    }
}
