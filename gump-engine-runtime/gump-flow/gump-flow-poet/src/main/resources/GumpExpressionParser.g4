parser grammar GumpExpressionParser;
@header {
    package org.junhui.gump.flow.poet.compiler.expression.grammar;
}

options { tokenVocab=GumpExpressionLexer; }


identifier
    : IDENTIFIER
    ;

emptyObject
    : EMPTY_OBJECT
    ;

primary
    : '(' expression ')'                    # BracketsExpr
    | identifier                            # IdentifierExpr
    | literal                               # LiteralExpr
    ;

expressionList
    : expression (',' expression)*
    ;

methodCall
    : identifier '(' expressionList? ')'
    ;

arrayLiteral
    : ('[' elementList ']')
    ;

elementList
    : ','* expression? (','+ expression)* ','*
    ;


literal
    : integerLiteral
    | floatLiteral
    | STRING_LITERAL
    | BOOL_LITERAL
    | NULL_LITERAL
    ;

integerLiteral
    : DECIMAL_LITERAL
    | HEX_LITERAL
    | OCT_LITERAL
    | BINARY_LITERAL
    ;

floatLiteral
    : FLOAT_LITERAL
    | HEX_FLOAT_LITERAL
    ;

expression
    : primary                                                       # PrimaryPointer
    | expression bop='.' identifier                                 # PointerChain
    | expression '[' expression ']'                                 # ArrayPointer
    | methodCall                                                    # MethodCallExpr
    | emptyObject                                                   # EmptyObjectExpr
    | arrayLiteral                                                  # ArrayLiteralExpr
    | expression postfix=('++' | '--')                              # SelfAddSubSuffix
    | prefix=('+'|'-'|'++'|'--') expression                         # SelfAddSubPrefix
    | prefix=('~'|'!') expression                                   # AntiExpr
    | expression bop=('*'|'/'|'%') expression                       # MulDivMod
    | expression bop=('+'|'-') expression                           # AddSub
    | expression bop=('<=' | '>=' | '>' | '<') expression           # Compare
    | expression bop=('==' | '!=') expression                       # Equal
    | expression bop='&' expression                                 # BitAnd
    | expression bop='^' expression                                 # BitXOR
    | expression bop='|' expression                                 # BitOr
    | expression bop='&&' expression                                # LogicAnd
    | expression bop='||' expression                                # LogicOr
    | <assoc=right> expression bop='?' expression ':' expression    # TernaryOperator
    ;