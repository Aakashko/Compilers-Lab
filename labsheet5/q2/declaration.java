import java_cup.runtime.Symbol; 
import java_cup.runtime.Scanner; 


class Yylex implements java_cup.runtime.Scanner {
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

	Yylex (java.io.Reader reader) {
		this ();
		if (null == reader) {
			throw (new Error("Error: Bad input stream initializer."));
		}
		yy_reader = new java.io.BufferedReader(reader);
	}

	Yylex (java.io.InputStream instream) {
		this ();
		if (null == instream) {
			throw (new Error("Error: Bad input stream initializer."));
		}
		yy_reader = new java.io.BufferedReader(new java.io.InputStreamReader(instream));
	}

	private Yylex () {
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
		/* 21 */ YY_NOT_ACCEPT,
		/* 22 */ YY_NO_ANCHOR,
		/* 23 */ YY_NOT_ACCEPT,
		/* 24 */ YY_NO_ANCHOR,
		/* 25 */ YY_NOT_ACCEPT,
		/* 26 */ YY_NO_ANCHOR,
		/* 27 */ YY_NOT_ACCEPT,
		/* 28 */ YY_NO_ANCHOR,
		/* 29 */ YY_NOT_ACCEPT,
		/* 30 */ YY_NO_ANCHOR,
		/* 31 */ YY_NO_ANCHOR,
		/* 32 */ YY_NO_ANCHOR,
		/* 33 */ YY_NO_ANCHOR,
		/* 34 */ YY_NO_ANCHOR,
		/* 35 */ YY_NO_ANCHOR,
		/* 36 */ YY_NO_ANCHOR,
		/* 37 */ YY_NO_ANCHOR,
		/* 38 */ YY_NO_ANCHOR,
		/* 39 */ YY_NO_ANCHOR,
		/* 40 */ YY_NO_ANCHOR,
		/* 41 */ YY_NO_ANCHOR,
		/* 42 */ YY_NO_ANCHOR
	};
	private int yy_cmap[] = unpackFromString(1,130,
"0:10,28,0:21,4,8,7,0,8:2,0:2,8:4,3,0,6,0,5:10,0,2,0,1,0:2,8:12,15,8,13,8:6," +
"14,8:5,11,8,12,8:3,20,29,27,29:2,24,23,29,16,29:2,25,29,17,26,29:2,21,22,18" +
",29,19,29:4,9,0,10,0:2,30:2")[0];

	private int yy_rmap[] = unpackFromString(1,43,
"0,1:4,2,1:4,3,1:3,4,5:2,1,5:3,6,7,8,9,4,10,11,12,13,14,15,16,17,18,19,20,21" +
",5,22,23,24,25")[0];

	private int yy_nxt[][] = unpackFromString(26,31,
"-1,1,2,3,4,5,-1,21,-1,6,7,8,9,23,-1:2,10,38:2,39,38:2,40,38,41,38:2,42,11,3" +
"8,12,-1:36,5,25,-1:29,38,-1:10,38,22,38:10,-1,38,-1:6,14,-1:30,38,-1:10,38:" +
"12,-1,38,-1:6,21,-1,13,21,-1:2,21:17,-1,21,-1:6,38,-1:10,38:2,15,38:9,-1,38" +
",-1:15,27,-1:21,38,-1:10,38:5,16,38:6,-1,38,-1:6,38,-1:10,38:5,31,38:6,-1,3" +
"8,-1:16,29,-1:20,38,-1:10,38:10,32,38,-1,38,-1:16,17,-1:20,38,-1:10,38,33,3" +
"8:10,-1,38,-1:6,38,-1:10,34,38:11,-1,38,-1:6,38,-1:10,38:4,35,38:7,-1,38,-1" +
":6,38,-1:10,38:6,36,38:5,-1,38,-1:6,38,-1:10,38,37,38:10,-1,38,-1:6,38,-1:1" +
"0,38:2,18,38:9,-1,38,-1:6,38,-1:10,38:2,19,38:9,-1,38,-1:6,38,-1:10,38:7,20" +
",38:4,-1,38,-1:6,38,-1:10,38:4,24,38:7,-1,38,-1:6,38,-1:10,38:2,26,38:9,-1," +
"38,-1:6,38,-1:10,38:9,28,38:2,-1,38,-1:6,38,-1:10,38:10,30,38,-1,38,-1");

	public java_cup.runtime.Symbol next_token ()
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
 
return new Symbol(sym.EOFILE);
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
						{System.out.println("LA "+yytext());return new Symbol(sym.EQUAL);}
					case -2:
						break;
					case 2:
						{System.out.println("LA "+yytext());return new Symbol(sym.SEMI);}
					case -3:
						break;
					case 3:
						{System.out.println("LA "+yytext());return new Symbol(sym.COMMA);}
					case -4:
						break;
					case 4:
						{System.out.println("LA "+yytext());return new Symbol(sym.SPACE);}
					case -5:
						break;
					case 5:
						{System.out.println("LA "+yytext());return new Symbol(sym.INTVAL,new  Integer(yytext()));}
					case -6:
						break;
					case 6:
						{System.out.println("LA "+yytext());return new Symbol(sym.OC);}
					case -7:
						break;
					case 7:
						{System.out.println("LA "+yytext());return new Symbol(sym.CC);}
					case -8:
						break;
					case 8:
						{System.out.println("LA "+yytext());return new Symbol(sym.OS);}
					case -9:
						break;
					case 9:
						{System.out.println("LA "+yytext());return new Symbol(sym.CS);}
					case -10:
						break;
					case 10:
						{System.out.println("LA "+yytext());
return new Symbol(sym.ID,new String(yytext()));}
					case -11:
						break;
					case 11:
						{System.out.println("LA "+yytext());return new Symbol(sym.NEWL);}
					case -12:
						break;
					case 12:
						
					case -13:
						break;
					case 13:
						{System.out.println("LA "+yytext());return new Symbol(sym.STRVAL,new String(yytext()));}
					case -14:
						break;
					case 14:
						{System.out.println("LA "+yytext());return new Symbol(sym.FLOATVAL,new Float(yytext()));}
					case -15:
						break;
					case 15:
						{System.out.println("LA "+yytext());return new Symbol(sym.INT);}
					case -16:
						break;
					case 16:
						{System.out.println("LA "+yytext());return new Symbol(sym.VAR);}
					case -17:
						break;
					case 17:
						{System.out.println("LA "+yytext()); return new Symbol(sym.NULLVAL,null);}
					case -18:
						break;
					case 18:
						{System.out.println("LA "+yytext()); return new Symbol(sym.FLOAT);}
					case -19:
						break;
					case 19:
						{System.out.println("LA "+yytext()); return new Symbol(sym.CONST);}
					case -20:
						break;
					case 20:
						{System.out.println("LA "+yytext());return new Symbol(sym.STRING);}
					case -21:
						break;
					case 22:
						{System.out.println("LA "+yytext());
return new Symbol(sym.ID,new String(yytext()));}
					case -22:
						break;
					case 24:
						{System.out.println("LA "+yytext());
return new Symbol(sym.ID,new String(yytext()));}
					case -23:
						break;
					case 26:
						{System.out.println("LA "+yytext());
return new Symbol(sym.ID,new String(yytext()));}
					case -24:
						break;
					case 28:
						{System.out.println("LA "+yytext());
return new Symbol(sym.ID,new String(yytext()));}
					case -25:
						break;
					case 30:
						{System.out.println("LA "+yytext());
return new Symbol(sym.ID,new String(yytext()));}
					case -26:
						break;
					case 31:
						{System.out.println("LA "+yytext());
return new Symbol(sym.ID,new String(yytext()));}
					case -27:
						break;
					case 32:
						{System.out.println("LA "+yytext());
return new Symbol(sym.ID,new String(yytext()));}
					case -28:
						break;
					case 33:
						{System.out.println("LA "+yytext());
return new Symbol(sym.ID,new String(yytext()));}
					case -29:
						break;
					case 34:
						{System.out.println("LA "+yytext());
return new Symbol(sym.ID,new String(yytext()));}
					case -30:
						break;
					case 35:
						{System.out.println("LA "+yytext());
return new Symbol(sym.ID,new String(yytext()));}
					case -31:
						break;
					case 36:
						{System.out.println("LA "+yytext());
return new Symbol(sym.ID,new String(yytext()));}
					case -32:
						break;
					case 37:
						{System.out.println("LA "+yytext());
return new Symbol(sym.ID,new String(yytext()));}
					case -33:
						break;
					case 38:
						{System.out.println("LA "+yytext());
return new Symbol(sym.ID,new String(yytext()));}
					case -34:
						break;
					case 39:
						{System.out.println("LA "+yytext());
return new Symbol(sym.ID,new String(yytext()));}
					case -35:
						break;
					case 40:
						{System.out.println("LA "+yytext());
return new Symbol(sym.ID,new String(yytext()));}
					case -36:
						break;
					case 41:
						{System.out.println("LA "+yytext());
return new Symbol(sym.ID,new String(yytext()));}
					case -37:
						break;
					case 42:
						{System.out.println("LA "+yytext());
return new Symbol(sym.ID,new String(yytext()));}
					case -38:
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
