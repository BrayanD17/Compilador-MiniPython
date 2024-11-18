parser grammar MiniPythonParser;

options {
    tokenVocab = MiniPythonLexer;
}

program: statement* EOF;

statement
    : defStatement
    | ifStatement
    | returnStatement
    | printStatement
    | whileStatement
    | assignStatement
    | forStatement
    | functionCallStatement;

defStatement: DEF IDENTIFIER LPAREN argList? RPAREN DOSPUNTOS NEWLINE sequence;
argList: IDENTIFIER (COMMA IDENTIFIER)*;

ifStatement: IF logicalExpression DOSPUNTOS NEWLINE sequence (ELSE DOSPUNTOS NEWLINE sequence)?;
whileStatement: WHILE logicalExpression DOSPUNTOS NEWLINE sequence;
returnStatement: RETURN expression? NEWLINE;
forStatement: FOR expression IN expressionList DOSPUNTOS NEWLINE sequence;

printStatement: PRINT LPAREN (expression (COMMA expression)*)? RPAREN NEWLINE?;

assignStatement
    : simpleAssignStatement
    | listAssignStatement;

simpleAssignStatement: IDENTIFIER ASSIGN expression NEWLINE;
listAssignStatement: IDENTIFIER LBRACKET expression RBRACKET ASSIGN expression NEWLINE;

functionCallStatement: IDENTIFIER LPAREN expressionList RPAREN NEWLINE?;

sequence: INDENT statement+ DEDENT;

logicalExpression: comparison ((AND | OR) comparison)*;
comparison: additionExpression (LT | GT | LE | GE | EQ) additionExpression;
expression: additionExpression;

additionExpression: multiplicationExpression ((PLUS | MINUS) multiplicationExpression)*;
multiplicationExpression: elementExpression ((MULT | DIV | MOD) elementExpression)*;

elementExpression: primitiveExpression (LBRACKET expression RBRACKET)?;

expressionList: (expression (COMMA expression)*)?;

primitiveExpression
    : LPAREN expression RPAREN                                      #primitiveExpressionparenthesisExprAST
    | LEN LPAREN expression RPAREN                                  #primitiveExpressionlenAST
    | listExpression                                                #primitiveExpressionlistAST
    | (PLUS | MINUS)? (INTEGER | FLOAT | CHARCONST | STRING)        #primitiveExpressionliteralAST
    | IDENTIFIER (LPAREN expressionList RPAREN)?                    #primitiveExpressionidentifierListAST
    ;

listExpression: LBRACKET expressionList RBRACKET;
