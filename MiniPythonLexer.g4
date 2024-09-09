lexer grammar MiniPythonLexer;

// Keywords
IF      : 'if' ;
ELSE    : 'else' ;
PRINT   : 'print' ;
DEF     : 'def' ;
RETURN  : 'return' ;
WHILE   : 'while' ;
FOR     : 'for' ;
IN      : 'in' ;
LEN     : 'len' ;

// Symbols
PIZQ    : '(' ;
PDER    : ')' ;
DOSPUN  : ':' ;
ASIGN   : '=' ;
COMMA   : ',' ;
GT      : '>' ;
LT      : '<' ;
LBRACKET: '[' ;
RBRACKET: ']' ;
MUL     : '*' ;
DIV     : '/' ;
MOD     : '%' ;
SUM     : '+' ;
REST    : '-' ;
GE      : '>=' ;
LE      : '<=' ;
EQEQ    : '==' ;
NOTEQ   : '!=' ;

// Identifiers and literals
ID      : [a-zA-Z_] [a-zA-Z0-9_]* ;
NUM     : [0-9]+ ;
FLOAT   : [0-9]+ '.' [0-9]+ ;
STRING  : '"' (~["\\] | '\\')* '"' ;

// Whitespace and comments
WS             : [ \t]+ -> skip ;             // Ignorar espacios y tabulaciones
COMMENT        : '#' ~[\r\n]* -> skip ;       // Ignorar comentarios de una sola línea
BLOCK_COMMENT  : '"""' .*? '"""' -> skip ;    // Ignorar comentarios en bloque
NEWLINE        : [\r\n]+ ;                    // Capturar saltos de línea
