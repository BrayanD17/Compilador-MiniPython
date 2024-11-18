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
        
        private bool IsGlobalVariable(string identifier)
        {
            return globalVariables.Contains(identifier);
        }

        private void EnsureGlobalVariable(string identifier)
        {
            if (!globalVariables.Contains(identifier))
            {
                globalVariables.Add(identifier);
                bytecode.Add(new Instruction("PUSH_GLOBAL", identifier));
            }
        }


        public override object VisitProgram(MiniPythonParser.ProgramContext context)
        {
            currentLevel = 0;
            string lastDefinedFunction = null;

            foreach (var stmt in context.statement())
            {
                Visit(stmt);
                Console.WriteLine($"Variables globales: {string.Join(", ", globalVariables)}");
            }

            if (lastDefinedFunction != null && !bytecode.Any(instr => instr.Instr == "CALL_FUNCTION"))
            {
                bytecode.Add(new Instruction("LOAD_GLOBAL", lastDefinedFunction));
                bytecode.Add(new Instruction("CALL_FUNCTION", "0"));
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
            if (context.simpleAssignStatement() != null)
            {
                return VisitSimpleAssignStatement(context.simpleAssignStatement());
            }
            else if (context.listAssignStatement() != null)
            {
                return VisitListAssignStatement(context.listAssignStatement());
            }
            return null;
        }

        public override object VisitSimpleAssignStatement(MiniPythonParser.SimpleAssignStatementContext context)
        {
            var varName = context.IDENTIFIER().GetText();
            var scopedVarName = currentLevel == 0 ? varName : $"{varName}_{currentLevel}";

            if (currentLevel == 0)
            {
                EnsureGlobalVariable(varName);
                VisitExpressionHandlingGlobals(context.expression());
                bytecode.Add(new Instruction("STORE_GLOBAL", varName));
            }
            else
            {
                if (!localVariables.Contains(scopedVarName))
                {
                    bytecode.Add(new Instruction("PUSH_LOCAL", scopedVarName));
                    localVariables.Add(scopedVarName);
                }
                VisitExpressionHandlingGlobals(context.expression());
                bytecode.Add(new Instruction("STORE_FAST", scopedVarName));
            }

            return null;
        }

        public override object VisitListAssignStatement(MiniPythonParser.ListAssignStatementContext context)
        {
            var listName = context.IDENTIFIER().GetText();

            // Determinar si la lista es global o local
            if (globalVariables.Contains(listName))
            {
                bytecode.Add(new Instruction("LOAD_GLOBAL", listName)); // Cargar la lista global primero
            }
            else
            {
                bytecode.Add(new Instruction("LOAD_FAST", $"{listName}_{currentLevel}")); // Cargar la lista local primero
            }

            // Procesar el índice y el valor
            VisitExpressionHandlingGlobals(context.expression(0)); // Cargar el índice
            VisitExpressionHandlingGlobals(context.expression(1)); // Cargar el valor

            // Generar la instrucción para asignar el valor al índice
            bytecode.Add(new Instruction("STORE_SUBSCR"));

            return null;
        }

        private void PrintParseTree(IParseTree tree, string prefix = "")
        {
            if (tree == null)
            {
                Console.WriteLine($"{prefix}NULL");
                return;
            }

            Console.WriteLine($"{prefix}{tree.GetText()}");
            for (int i = 0; i < tree.ChildCount; i++)
            {
                PrintParseTree(tree.GetChild(i), prefix + "  ");
            }
        }

        private void VisitExpressionHandlingGlobals(ParserRuleContext context)
        {
            if (context is MiniPythonParser.PrimitiveExpressionidentifierListASTContext identifierExpr)
            {
                var identifier = identifierExpr.IDENTIFIER().GetText();

                if (globalVariables.Contains(identifier))
                {
                    bytecode.Add(new Instruction("LOAD_GLOBAL", identifier));
                }
                else if (localVariables.Contains($"{identifier}_{currentLevel}"))
                {
                    bytecode.Add(new Instruction("LOAD_FAST", $"{identifier}_{currentLevel}"));
                }
                else
                {
                    throw new InvalidOperationException($"Error: La variable '{identifier}' no está definida.");
                }
            }
            else
            {
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
                    VisitExpressionHandlingGlobals(expr); // Procesar cada elemento de la lista
                }
            }

            bytecode.Add(new Instruction("BUILD_LIST", numElements.ToString())); // Crear la lista
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

            bytecode.Add(new Instruction("LOAD_GLOBAL", "print"));  // Usa -1 para print
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
            // Procesar el primer término
            VisitExpressionHandlingGlobals(context.multiplicationExpression(0));

            // Procesar términos adicionales y operadores
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

            if (globalVariables.Contains(varName))
            {
                bytecode.Add(new Instruction("LOAD_GLOBAL", varName));
            }
            else if (localVariables.Contains($"{varName}_{currentLevel}"))
            {
                bytecode.Add(new Instruction("LOAD_FAST", $"{varName}_{currentLevel}"));
            }
            else
            {
                throw new InvalidOperationException($"Error: La variable '{varName}' no está definida.");
            }

            return null;
        }

        public override object VisitPrimitiveExpressionliteralAST(MiniPythonParser.PrimitiveExpressionliteralASTContext context)
        {
            var literalValue = context.GetText();
            bytecode.Add(new Instruction("LOAD_CONST", literalValue));
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
                if (context.primitiveExpression() == null || context.expression() == null)
                {
                    throw new InvalidOperationException("Error: Nodo nulo en acceso a índice de lista.");
                }

                Visit(context.primitiveExpression()); // Cargar la referencia del array
                Visit(context.expression()); // Cargar el índice en la pila
                bytecode.Add(new Instruction("BINARY_SUBSCR")); // Obtener el valor del índice
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