import java.io.*;
class Main{
	public static void main(String args[]) throws IOException{
		q2 lex = new q2(new FileReader("input.txt"));
		Token token = lex.yylex();
		while(token.text !=null){
			token = lex.yylex();
		}
	}
}
class Token{
	String text;
	Token(String t){
		text=t;
	}
}


public class q2 {
	private final int YY_BUFFER_SIZE = 512;
	private final int YY_F = -1;
	private final int YY_NO_STATE = -1;
	private final int YY_NOT_ACCEPT = 0;
	private final int YY_START = 1;
	private final int YY_END = 2;
	private final int YY_NO_ANCHOR = 4;
	private final int YY_BOL = 128;
	private final int YY_EOF = 129;
	private java.io.BufferedReader yy_reader;
	private int yy_buffer_index;
	private int yy_buffer_read;
	private int yy_buffer_start;
	private int yy_buffer_end;
	private char yy_buffer[];
	private boolean yy_at_bol;
	private int yy_lexical_state;

	public q2 (java.io.Reader reader) {
		this ();
		if (null == reader) {
			throw (new Error("Error: Bad input stream initializer."));
		}
		yy_reader = new java.io.BufferedReader(reader);
	}

	public q2 (java.io.InputStream instream) {
		this ();
		if (null == instream) {
			throw (new Error("Error: Bad input stream initializer."));
		}
		yy_reader = new java.io.BufferedReader(new java.io.InputStreamReader(instream));
	}

	private q2 () {
		yy_buffer = new char[YY_BUFFER_SIZE];
		yy_buffer_read = 0;
		yy_buffer_index = 0;
		yy_buffer_start = 0;
		yy_buffer_end = 0;
		yy_at_bol = true;
		yy_lexical_state = YYINITIAL;
	}

	private boolean yy_eof_done = false;
	private final int YYINITIAL = 0;
	private final int yy_state_dtrans[] = {
		0
	};
	private void yybegin (int state) {
		yy_lexical_state = state;
	}
	private int yy_advance ()
		throws java.io.IOException {
		int next_read;
		int i;
		int j;

		if (yy_buffer_index < yy_buffer_read) {
			return yy_buffer[yy_buffer_index++];
		}

		if (0 != yy_buffer_start) {
			i = yy_buffer_start;
			j = 0;
			while (i < yy_buffer_read) {
				yy_buffer[j] = yy_buffer[i];
				++i;
				++j;
			}
			yy_buffer_end = yy_buffer_end - yy_buffer_start;
			yy_buffer_start = 0;
			yy_buffer_read = j;
			yy_buffer_index = j;
			next_read = yy_reader.read(yy_buffer,
					yy_buffer_read,
					yy_buffer.length - yy_buffer_read);
			if (-1 == next_read) {
				return YY_EOF;
			}
			yy_buffer_read = yy_buffer_read + next_read;
		}

		while (yy_buffer_index >= yy_buffer_read) {
			if (yy_buffer_index >= yy_buffer.length) {
				yy_buffer = yy_double(yy_buffer);
			}
			next_read = yy_reader.read(yy_buffer,
					yy_buffer_read,
					yy_buffer.length - yy_buffer_read);
			if (-1 == next_read) {
				return YY_EOF;
			}
			yy_buffer_read = yy_buffer_read + next_read;
		}
		return yy_buffer[yy_buffer_index++];
	}
	private void yy_move_end () {
		if (yy_buffer_end > yy_buffer_start &&
		    '\n' == yy_buffer[yy_buffer_end-1])
			yy_buffer_end--;
		if (yy_buffer_end > yy_buffer_start &&
		    '\r' == yy_buffer[yy_buffer_end-1])
			yy_buffer_end--;
	}
	private boolean yy_last_was_cr=false;
	private void yy_mark_start () {
		yy_buffer_start = yy_buffer_index;
	}
	private void yy_mark_end () {
		yy_buffer_end = yy_buffer_index;
	}
	private void yy_to_mark () {
		yy_buffer_index = yy_buffer_end;
		yy_at_bol = (yy_buffer_end > yy_buffer_start) &&
		            ('\r' == yy_buffer[yy_buffer_end-1] ||
		             '\n' == yy_buffer[yy_buffer_end-1] ||
		             2028/*LS*/ == yy_buffer[yy_buffer_end-1] ||
		             2029/*PS*/ == yy_buffer[yy_buffer_end-1]);
	}
	private java.lang.String yytext () {
		return (new java.lang.String(yy_buffer,
			yy_buffer_start,
			yy_buffer_end - yy_buffer_start));
	}
	private int yylength () {
		return yy_buffer_end - yy_buffer_start;
	}
	private char[] yy_double (char buf[]) {
		int i;
		char newbuf[];
		newbuf = new char[2*buf.length];
		for (i = 0; i < buf.length; ++i) {
			newbuf[i] = buf[i];
		}
		return newbuf;
	}
	private final int YY_E_INTERNAL = 0;
	private final int YY_E_MATCH = 1;
	private java.lang.String yy_error_string[] = {
		"Error: Internal error.\n",
		"Error: Unmatched input.\n"
	};
	private void yy_error (int code,boolean fatal) {
		java.lang.System.out.print(yy_error_string[code]);
		java.lang.System.out.flush();
		if (fatal) {
			throw new Error("Fatal Error.\n");
		}
	}
	private int[][] unpackFromString(int size1, int size2, String st) {
		int colonIndex = -1;
		String lengthString;
		int sequenceLength = 0;
		int sequenceInteger = 0;

		int commaIndex;
		String workString;

		int res[][] = new int[size1][size2];
		for (int i= 0; i < size1; i++) {
			for (int j= 0; j < size2; j++) {
				if (sequenceLength != 0) {
					res[i][j] = sequenceInteger;
					sequenceLength--;
					continue;
				}
				commaIndex = st.indexOf(',');
				workString = (commaIndex==-1) ? st :
					st.substring(0, commaIndex);
				st = st.substring(commaIndex+1);
				colonIndex = workString.indexOf(':');
				if (colonIndex == -1) {
					res[i][j]=Integer.parseInt(workString);
					continue;
				}
				lengthString =
					workString.substring(colonIndex+1);
				sequenceLength=Integer.parseInt(lengthString);
				workString=workString.substring(0,colonIndex);
				sequenceInteger=Integer.parseInt(workString);
				res[i][j] = sequenceInteger;
				sequenceLength--;
			}
		}
		return res;
	}
	private int yy_acpt[] = {
		/* 0 */ YY_NOT_ACCEPT,
		/* 1 */ YY_NO_ANCHOR,
		/* 2 */ YY_NO_ANCHOR,
		/* 3 */ YY_NO_ANCHOR,
		/* 4 */ YY_NO_ANCHOR,
		/* 5 */ YY_NO_ANCHOR,
		/* 6 */ YY_NO_ANCHOR,
		/* 7 */ YY_NO_ANCHOR,
		/* 8 */ YY_NO_ANCHOR,
		/* 9 */ YY_NO_ANCHOR,
		/* 10 */ YY_NO_ANCHOR,
		/* 11 */ YY_NO_ANCHOR,
		/* 12 */ YY_NO_ANCHOR,
		/* 13 */ YY_NO_ANCHOR,
		/* 14 */ YY_NO_ANCHOR,
		/* 15 */ YY_NO_ANCHOR,
		/* 16 */ YY_NO_ANCHOR,
		/* 17 */ YY_NO_ANCHOR,
		/* 18 */ YY_NO_ANCHOR,
		/* 19 */ YY_NO_ANCHOR,
		/* 20 */ YY_NO_ANCHOR,
		/* 21 */ YY_NO_ANCHOR,
		/* 22 */ YY_NO_ANCHOR,
		/* 23 */ YY_NO_ANCHOR,
		/* 24 */ YY_NOT_ACCEPT,
		/* 25 */ YY_NO_ANCHOR,
		/* 26 */ YY_NO_ANCHOR,
		/* 27 */ YY_NOT_ACCEPT,
		/* 28 */ YY_NO_ANCHOR,
		/* 29 */ YY_NOT_ACCEPT,
		/* 30 */ YY_NO_ANCHOR,
		/* 31 */ YY_NOT_ACCEPT,
		/* 32 */ YY_NO_ANCHOR,
		/* 33 */ YY_NOT_ACCEPT,
		/* 34 */ YY_NO_ANCHOR,
		/* 35 */ YY_NO_ANCHOR,
		/* 36 */ YY_NO_ANCHOR,
		/* 37 */ YY_NO_ANCHOR,
		/* 38 */ YY_NO_ANCHOR,
		/* 39 */ YY_NO_ANCHOR,
		/* 40 */ YY_NO_ANCHOR,
		/* 41 */ YY_NO_ANCHOR,
		/* 42 */ YY_NO_ANCHOR,
		/* 43 */ YY_NO_ANCHOR,
		/* 44 */ YY_NO_ANCHOR,
		/* 45 */ YY_NO_ANCHOR,
		/* 46 */ YY_NO_ANCHOR,
		/* 47 */ YY_NO_ANCHOR,
		/* 48 */ YY_NO_ANCHOR,
		/* 49 */ YY_NO_ANCHOR,
		/* 50 */ YY_NO_ANCHOR,
		/* 51 */ YY_NO_ANCHOR,
		/* 52 */ YY_NO_ANCHOR,
		/* 53 */ YY_NO_ANCHOR,
		/* 54 */ YY_NO_ANCHOR,
		/* 55 */ YY_NO_ANCHOR,
		/* 56 */ YY_NO_ANCHOR,
		/* 57 */ YY_NO_ANCHOR,
		/* 58 */ YY_NO_ANCHOR,
		/* 59 */ YY_NO_ANCHOR,
		/* 60 */ YY_NO_ANCHOR,
		/* 61 */ YY_NO_ANCHOR,
		/* 62 */ YY_NO_ANCHOR,
		/* 63 */ YY_NO_ANCHOR,
		/* 64 */ YY_NO_ANCHOR,
		/* 65 */ YY_NO_ANCHOR,
		/* 66 */ YY_NO_ANCHOR,
		/* 67 */ YY_NO_ANCHOR,
		/* 68 */ YY_NO_ANCHOR,
		/* 69 */ YY_NO_ANCHOR,
		/* 70 */ YY_NO_ANCHOR,
		/* 71 */ YY_NO_ANCHOR,
		/* 72 */ YY_NO_ANCHOR,
		/* 73 */ YY_NO_ANCHOR,
		/* 74 */ YY_NO_ANCHOR,
		/* 75 */ YY_NO_ANCHOR,
		/* 76 */ YY_NO_ANCHOR
	};
	private int yy_cmap[] = unpackFromString(1,130,
"0:10,25,0:21,36,28,19,22:3,26,0,33,34,23:2,35,24,0,24,21:10,0,35,30,29,30,0" +
",22,20:26,0:3,22:2,0,8,20,9,20,5,15,14,2,3,20:2,16,10,11,17,7,20,13,6,4,18," +
"12,1,20:3,31,27,32,0:2,37:2")[0];

	private int yy_rmap[] = unpackFromString(1,77,
"0,1,2:2,3:2,2:7,4,2,4:9,5,6,2,7,8,9,10,11,12,3,13,14,15,16,17,18,19,20,21,2" +
"2,23,24,25,26,27,28,29,30,31,32,33,34,35,36,37,38,39,4,40,41,42,43,44,45,46" +
",47,48,49,50,4,51,52,53")[0];

	private int yy_nxt[][] = unpackFromString(54,38,
"-1,1,73,25,56,57,75,73:2,58,59,73,43,73:2,44,73,76,73,24,73,-1:2,2:2,3,27,2" +
"9,31,4,5,6,7,8,9,10,11,12,-1,73,60,73:16,-1,73,61,-1:83,26,-1:9,73:18,-1,73" +
",61,-1:17,24:18,14,24:4,-1:2,24,-1,24,-1:4,24:2,-1:4,73:10,62,73:3,13,73:3," +
"-1,73,61,-1:42,26,-1:12,73:12,15,73:5,-1,73,61,-1:43,26,-1:11,73:12,16,73:5" +
",-1,73,61,-1:45,33,-1:9,73:10,17,73:7,-1,73,61,-1:17,73:4,18,73:13,-1,73,61" +
",-1:17,73:12,19,73:5,-1,73,61,-1:17,73:10,15,73:7,-1,73,61,-1:17,73:4,20,73" +
":13,-1,73,61,-1:17,73:3,21,73:14,-1,73,61,-1:17,73:3,19,73:14,-1,73,61,-1:1" +
"7,73:13,19,73:4,-1,73,61,-1:17,73:3,22,73:14,-1,73,61,-1:17,73:4,23,73:13,-" +
"1,73,61,-1:17,73:7,28,73:10,-1,73,61,-1:17,73:15,64,30,73,-1,73,61,-1:17,73" +
":4,32,73:13,-1,73,61,-1:17,73:5,34,73:12,-1,73,61,-1:17,73:7,35,73:10,-1,73" +
",61,-1:17,73:2,36,73:15,-1,73,61,-1:17,73:3,74,73:11,37,73:2,-1,73,61,-1:17" +
",73:17,38,-1,73,61,-1:17,73:7,39,73:10,-1,73,61,-1:17,73:10,40,73:7,-1,73,6" +
"1,-1:17,73:17,41,-1,73,61,-1:17,73:4,35,73:13,-1,73,61,-1:17,73:8,42,73:9,-" +
"1,73,61,-1:17,73,45,73:16,-1,73,61,-1:17,73:15,46,73:2,-1,73,61,-1:17,73,47" +
",73:16,-1,73,61,-1:17,73:7,48,73:10,-1,73,61,-1:17,73:2,49,73:15,-1,73,61,-" +
"1:17,73:3,66,73:2,50,73:11,-1,73,61,-1:17,73:12,67,73:5,-1,73,61,-1:17,73:1" +
"6,51,73,-1,73,61,-1:17,73:3,68,73:14,-1,73,61,-1:17,73:4,69,73:13,-1,73,61," +
"-1:17,73:2,52,73:15,-1,73,61,-1:17,73:6,53,73:11,-1,73,61,-1:17,73:13,54,73" +
":4,-1,73,61,-1:17,73:5,71,73:12,-1,73,61,-1:17,73:6,72,73:11,-1,73,61,-1:17" +
",73:7,55,73:10,-1,73,61,-1:17,73:4,70,73:13,-1,73,61,-1:17,73:3,63,73:14,-1" +
",73,61,-1:17,73:17,65,-1,73,61,-1:16");

	public Token yylex ()
		throws java.io.IOException {
		int yy_lookahead;
		int yy_anchor = YY_NO_ANCHOR;
		int yy_state = yy_state_dtrans[yy_lexical_state];
		int yy_next_state = YY_NO_STATE;
		int yy_last_accept_state = YY_NO_STATE;
		boolean yy_initial = true;
		int yy_this_accept;

		yy_mark_start();
		yy_this_accept = yy_acpt[yy_state];
		if (YY_NOT_ACCEPT != yy_this_accept) {
			yy_last_accept_state = yy_state;
			yy_mark_end();
		}
		while (true) {
			if (yy_initial && yy_at_bol) yy_lookahead = YY_BOL;
			else yy_lookahead = yy_advance();
			yy_next_state = YY_F;
			yy_next_state = yy_nxt[yy_rmap[yy_state]][yy_cmap[yy_lookahead]];
			if (YY_EOF == yy_lookahead && true == yy_initial) {

return new Token(null);
			}
			if (YY_F != yy_next_state) {
				yy_state = yy_next_state;
				yy_initial = false;
				yy_this_accept = yy_acpt[yy_state];
				if (YY_NOT_ACCEPT != yy_this_accept) {
					yy_last_accept_state = yy_state;
					yy_mark_end();
				}
			}
			else {
				if (YY_NO_STATE == yy_last_accept_state) {
					throw (new Error("Lexical Error: Unmatched Input."));
				}
				else {
					yy_anchor = yy_acpt[yy_last_accept_state];
					if (0 != (YY_END & yy_anchor)) {
						yy_move_end();
					}
					yy_to_mark();
					switch (yy_last_accept_state) {
					case 1:
						{System.out.println("Identifier "+yytext());}
					case -2:
						break;
					case 2:
						{System.out.println("Arithmetic Operators "+yytext());}
					case -3:
						break;
					case 3:
						{System.out.print("");}
					case -4:
						break;
					case 4:
						{System.out.println("Assigment Operator "+yytext());}
					case -5:
						break;
					case 5:
						{System.out.println("Logical Operators "+yytext());}
					case -6:
						break;
					case 6:
						{System.out.println("Left Curly Braces "+yytext());}
					case -7:
						break;
					case 7:
						{System.out.println("Right Curly Braces "+yytext());}
					case -8:
						break;
					case 8:
						{System.out.println("Left Parenthesis "+yytext());}
					case -9:
						break;
					case 9:
						{System.out.println("Right Parenthesis "+yytext());}
					case -10:
						break;
					case 10:
						{System.out.print("");}
					case -11:
						break;
					case 11:
						{System.out.print("");}
					case -12:
						break;
					case 12:
						
					case -13:
						break;
					case 13:
						{System.out.println("If "+yytext());}
					case -14:
						break;
					case 14:
						{System.out.println("String "+yytext());}
					case -15:
						break;
					case 15:
						{System.out.println("Keyword "+yytext());}
					case -16:
						break;
					case 16:
						{System.out.println("for "+yytext());}
					case -17:
						break;
					case 17:
						{System.out.println("then "+yytext());}
					case -18:
						break;
					case 18:
						{System.out.println("else "+yytext());}
					case -19:
						break;
					case 19:
						{System.out.println("Data types "+yytext());}
					case -20:
						break;
					case 20:
						{System.out.println("while "+yytext());}
					case -21:
						break;
					case 21:
						{System.out.println("Input Function "+yytext());}
					case -22:
						break;
					case 22:
						{System.out.println("Output Function "+yytext());}
					case -23:
						break;
					case 23:
						{System.out.print("");}
					case -24:
						break;
					case 25:
						{System.out.println("Identifier "+yytext());}
					case -25:
						break;
					case 26:
						{System.out.println("Logical Operators "+yytext());}
					case -26:
						break;
					case 28:
						{System.out.println("Identifier "+yytext());}
					case -27:
						break;
					case 30:
						{System.out.println("Identifier "+yytext());}
					case -28:
						break;
					case 32:
						{System.out.println("Identifier "+yytext());}
					case -29:
						break;
					case 34:
						{System.out.println("Identifier "+yytext());}
					case -30:
						break;
					case 35:
						{System.out.println("Identifier "+yytext());}
					case -31:
						break;
					case 36:
						{System.out.println("Identifier "+yytext());}
					case -32:
						break;
					case 37:
						{System.out.println("Identifier "+yytext());}
					case -33:
						break;
					case 38:
						{System.out.println("Identifier "+yytext());}
					case -34:
						break;
					case 39:
						{System.out.println("Identifier "+yytext());}
					case -35:
						break;
					case 40:
						{System.out.println("Identifier "+yytext());}
					case -36:
						break;
					case 41:
						{System.out.println("Identifier "+yytext());}
					case -37:
						break;
					case 42:
						{System.out.println("Identifier "+yytext());}
					case -38:
						break;
					case 43:
						{System.out.println("Identifier "+yytext());}
					case -39:
						break;
					case 44:
						{System.out.println("Identifier "+yytext());}
					case -40:
						break;
					case 45:
						{System.out.println("Identifier "+yytext());}
					case -41:
						break;
					case 46:
						{System.out.println("Identifier "+yytext());}
					case -42:
						break;
					case 47:
						{System.out.println("Identifier "+yytext());}
					case -43:
						break;
					case 48:
						{System.out.println("Identifier "+yytext());}
					case -44:
						break;
					case 49:
						{System.out.println("Identifier "+yytext());}
					case -45:
						break;
					case 50:
						{System.out.println("Identifier "+yytext());}
					case -46:
						break;
					case 51:
						{System.out.println("Identifier "+yytext());}
					case -47:
						break;
					case 52:
						{System.out.println("Identifier "+yytext());}
					case -48:
						break;
					case 53:
						{System.out.println("Identifier "+yytext());}
					case -49:
						break;
					case 54:
						{System.out.println("Identifier "+yytext());}
					case -50:
						break;
					case 55:
						{System.out.println("Identifier "+yytext());}
					case -51:
						break;
					case 56:
						{System.out.println("Identifier "+yytext());}
					case -52:
						break;
					case 57:
						{System.out.println("Identifier "+yytext());}
					case -53:
						break;
					case 58:
						{System.out.println("Identifier "+yytext());}
					case -54:
						break;
					case 59:
						{System.out.println("Identifier "+yytext());}
					case -55:
						break;
					case 60:
						{System.out.println("Identifier "+yytext());}
					case -56:
						break;
					case 61:
						{System.out.println("Identifier "+yytext());}
					case -57:
						break;
					case 62:
						{System.out.println("Identifier "+yytext());}
					case -58:
						break;
					case 63:
						{System.out.println("Identifier "+yytext());}
					case -59:
						break;
					case 64:
						{System.out.println("Identifier "+yytext());}
					case -60:
						break;
					case 65:
						{System.out.println("Identifier "+yytext());}
					case -61:
						break;
					case 66:
						{System.out.println("Identifier "+yytext());}
					case -62:
						break;
					case 67:
						{System.out.println("Identifier "+yytext());}
					case -63:
						break;
					case 68:
						{System.out.println("Identifier "+yytext());}
					case -64:
						break;
					case 69:
						{System.out.println("Identifier "+yytext());}
					case -65:
						break;
					case 70:
						{System.out.println("Identifier "+yytext());}
					case -66:
						break;
					case 71:
						{System.out.println("Identifier "+yytext());}
					case -67:
						break;
					case 72:
						{System.out.println("Identifier "+yytext());}
					case -68:
						break;
					case 73:
						{System.out.println("Identifier "+yytext());}
					case -69:
						break;
					case 74:
						{System.out.println("Identifier "+yytext());}
					case -70:
						break;
					case 75:
						{System.out.println("Identifier "+yytext());}
					case -71:
						break;
					case 76:
						{System.out.println("Identifier "+yytext());}
					case -72:
						break;
					default:
						yy_error(YY_E_INTERNAL,false);
					case -1:
					}
					yy_initial = true;
					yy_state = yy_state_dtrans[yy_lexical_state];
					yy_next_state = YY_NO_STATE;
					yy_last_accept_state = YY_NO_STATE;
					yy_mark_start();
					yy_this_accept = yy_acpt[yy_state];
					if (YY_NOT_ACCEPT != yy_this_accept) {
						yy_last_accept_state = yy_state;
						yy_mark_end();
					}
				}
			}
		}
	}
}
