public class q6 {
	public String s;
	public int indx=0;
	public char token;
	q6(String s) {
		this.s=s;
	}
	public void read_next_token() {
		this.token=s.charAt(indx);
		indx=indx+1;
	}
	public boolean S() {
		int savep=indx;
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
		if(T() && Ed()) {
			return true;
		}
		else {
			indx=savep;
			return false;
		}
	}
	public boolean Ed() {
		int savep=indx;
		read_next_token();
		if(token=='+' && T() && Ed()) {
			return true;
		}
		indx=savep;
		read_next_token();
		if(token=='-' && T() && Ed()) {
			return true;
		}
		indx=savep;
		return true;
	}
	public boolean T() {
		int savep=indx;
		if(F() && Td())
			return true;
		else {
			indx=savep;
			return false;
		}
	}
	public boolean Td() {
		int savep=indx;
		read_next_token();
		if(token=='*'||token=='/') {
			savep=indx;
			if(F() && Td())
				return true;
			else {
				indx=savep;
				return false;
			}
		}
		indx=savep;
		return true;
	}
	public boolean F() {
		int savep=indx;
		read_next_token();
		char c[]= {'a','b','0','1','2','3','4','5','6','7','8','9'};
		for(int i=0;i<c.length;i++) {
			if(c[i]==token)
				return true;
		}
		indx=savep;
		return false;
	}
}
