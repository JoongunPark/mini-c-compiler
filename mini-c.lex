import java_cup.runtime.*;

%%
%cup
%line
%char

%{
    private Symbol token(int kind) {
        return new Symbol(kind, yyline+1, yychar+1);
    }

    private Symbol token(int kind, Object value) {
        return new Symbol(kind, yyline+1, yychar+1, value);
    }

    private void yyline() {
        yychar = -1;
    }
%}

%%

"*"             { return token(sym.MULT); }
"/"             { return token(sym.DIV); }
"+"             { return token(sym.PLUS); }
"-"             { return token(sym.MINUS); }
"<"             { return token(sym.LT); }
">"             { return token(sym.GT); }
"<="            { return token(sym.LTE); }
">="            { return token(sym.GTE); }
"=="            { return token(sym.EQ); }
"!="            { return token(sym.NEQ); }

"="             { return token(sym.ASSIGN); }
"("             { return token(sym.LPAREN); }
")"             { return token(sym.RPAREN); }
"{"             { return token(sym.LBRACE); }
"}"             { return token(sym.RBRACE); }
"["             { return token(sym.LSBRACE); }
"]"             { return token(sym.RSBRACE); }
","             { return token(sym.COMMA); }
";"             { return token(sym.SEMI); }
":"             { return token(sym.COLON); }

"int"           { return token(sym.INT); }
"float"         { return token(sym.FLOAT); }
"return"        { return token(sym.RETURN); }
"while"         { return token(sym.WHILE); }
"do"            { return token(sym.DO); }
"for"           { return token(sym.FOR); }
"if"            { return token(sym.IF); }
"else"          { return token(sym.ELSE); }
"switch"        { return token(sym.SWITCH); }
"case"          { return token(sym.CASE); }
"default"       { return token(sym.DEFAULT); }
"break"         { return token(sym.BREAK); }

[A-Za-z][A-Za-z0-9_]* { return token(sym.ID, yytext()); }
[0-9]+\.[0-9]+  { return token(sym.FLOATNUM, new Float(yytext())); }
[0-9]+          { return token(sym.INTNUM, new Integer(yytext())); }
[\n\r]          { yyline(); }
[\ \t\f]         { /* ignore white space. */ }
. {  System.err.println("[Error]:"+yyline+":"+yychar+": Illegal character.");System.exit(0);}
