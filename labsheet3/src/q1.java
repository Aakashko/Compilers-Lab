public class q1 {
	public String s;
	public int indx=0;
	public char token;
	q1(String s) {
		this.s=s;
	}
	public void read_next_token() {
		this.token=s.charAt(indx);
		indx=indx+1;
	}
	public boolean S() {
		int savep=indx;
		read_next_token();
		if(token=='a'||token=='^')
			return true;
		else if(token=='(' && T()) {
			read_next_token();
			if(token==')')
				return true;
			else {
				indx=savep;
				//System.out.println("S 1"+indx+" "+s.charAt(indx));
				return false;
			}
		}
		else {
			indx=savep;
			//System.out.println("S 2"+indx+" "+s.charAt(indx));
			return false;
		}
	}
	public boolean T() {
		int savep=indx;
		if(S() && Td()) {
			return true;
		}
		else {
			indx=savep;
			//System.out.println("T "+indx+" "+s.charAt(indx));
			return false;
		}
	}
	public boolean Td() {
		int savep=indx;
		read_next_token();
		if(token==',' && S() && Td()) {
			return true;
		}
		indx=savep;
		return true;
	}
}
