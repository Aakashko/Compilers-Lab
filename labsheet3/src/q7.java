public class q7 {
	public String s;
	public int indx=0;
	public char token;
	q7(String s) { 
		this.s=s;
	}
	public void read_next_token() {
		this.token=s.charAt(indx);
		indx=indx+1;
	}
	public boolean A() {
		int savep=indx;
		if(I()) {
			savep=indx;
			read_next_token();
			if(token=='=' && E()) {
				return true;
			}
			else {
				indx=savep;
				return false;
			}
		}
		indx=savep;
		if(E()) {
			return true;
		}
		else {
			indx=savep;
			return false;
		}
	}
	public boolean E() {
		int savep=indx;
		if(P() && O() && P()) {
			return true;
		}
		indx=savep;
		if(P()) {
			return true;
		}
		else {
			indx=savep;
			return false;
		}
	}
	public boolean O() {
		int savep=indx;
		read_next_token();
		if(token=='+' || token=='-' || token=='/')
			return true;
		indx=savep;
		read_next_token();
		if (token=='*' && Od()){
			return true;
		}
		else {
			indx=savep;
			return false;
		}
	}
	public boolean Od() {
		int savep=indx;
		read_next_token();
		if(token=='*')
			return true;
		indx=savep;
		return true;
	}
	public boolean P() {
		int savep=indx;
		if(I()) {
			return true;
		}
		indx=savep;
		if(L()) {
			return true;
		}
		indx=savep;
		if(U() && I()) {
			return true;
		}
		indx=savep;
		if(U() && L()) {
			return true;
		}
		indx=savep;
		read_next_token();
		if(token=='(' && E()) {
			savep=indx;
			read_next_token();
			if(token==')')
				return true;
			else {
				indx=savep;
				return false;
			}
		}
		else {
			indx=savep;
			return false;
		}
	}
	public boolean U() {
		int savep=indx;
		read_next_token();
		if(token=='+' || token=='-'||token=='!')
			return true;
		else {
			indx=savep;
			return false;
		}
	}
	public boolean I() {
		int savep=indx;
		if(C() && I())
			return true;
		indx=savep;
		if(C())
			return true;
		indx=savep;
		return false;
	}
	public boolean C() {
		int savep=indx;
		read_next_token();
		for(char x='a';x<='z';x++) { //
			//System.out.println("l-> "+x);
			if(x==token)
				return true;
		}
		indx=savep;
		return false;
	}
	public boolean L() {
		int savep=indx;
		if(D())
			return true;
		indx=savep;
		if(D() && L())
			return true;
		else {
			indx=savep;
			return false;
		}
	}
	public boolean D() {
		int savep=indx;
		read_next_token();
		for(char i='0';i<='9';i++) {
			if(i==token)
				return true;
		}
		indx=savep;
		return false;
	}
}
