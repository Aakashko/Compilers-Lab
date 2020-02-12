import java.io.*;
class Main{
	public static void main(String args[]) throws IOException {
		q1 lex=new q1(new FileReader("input.txt");
		Token token=lex.yylex();
		while(token.text !=null) {
			token=lex.yylex();
		}
	}
}
class Token{
	String text;
	Token(String t) {
		text=t;
	}
}
%%
%public 
%class q1
%type void

%type Token
%eofval{
return new Token(null);
%eofval}

digit=[0-9]
letter=[a-zA-z]
special=[!@$%^*()_+]
whitespace=[\t\n\r]

%%
whitespace {System.out.print("");}
main {System.out.println("MAIN,"+yytext());}
\{ {System.out.println("LPAREN,"+yytext());}
\} {System.out.println("RPAREN,"+yytext());}
\( {System.out.println("LBRACE,"+yytext());}
\) {System.out.println("RBRACE,"+yytext());}
{letter}({letter}|{digit})* {System.out.println("ID,"+yytext());}
{digit}+ {System.out.println("NUM,"+yytext());}
\"({letter}|{digit}|{special})*\" {System.out.println("STR,"+yytext());}
{digit}+\.{digit}+ {System.out.println("REAL,"+yytext());}
if {System.out.println("IF+yytext());}
while {System.out.println("WHILE,"+yytext());}
switch {System.out.println("SWITCH"+yytext());}
case {System.out.println("CASE,"+yytext());}
break {System.out.println("BREAK,"+yytext());}
printf {System.out.println("PRINTF,"+yytext());}
scanf {System.out.println("SCANF,"+yytext());}
return {System.out.println("RETURN,+yytext());}
int {System.out.println("INT,"+yytext());}
float {System.out.println("FLOAT,"+yytext());}
char {System.out.println("CHAR,"+yytext());}
\/*
