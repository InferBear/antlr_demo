grammar Calculator;

calc:
    expr;

expr:
       NUM                                  # number
   |    (PLUS|MINUS) expr                   # plusOrMinusExpr
   |   '(' expr ')'                         # parensExpr
   |   left=expr op=('*'|'/') right=expr    # infixExpr
   |   left=expr op=('+'|'-') right=expr    # infixExpr
   ;
NUM :   [0-9]+;
PLUS : '+';
MINUS: '-';
WS : [ \r\n]+ ->channel(HIDDEN);