public class q3 {
	public String s;
	public int indx=0;
	public char token;
	q3(String s) {
		this.s=s;
	}
	public void read_next_token() {
		this.token=s.charAt(indx);
		indx=indx+1;
	}
	public boolean S() {
		int savep=indx;
		read_next_token();
		if(token=='a')
			return true;
		else if(token=='(' && L()) {
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
	public boolean L() {
		int savep=indx;
		if(S() && Ld()) {
			return true;
		}
		else {
			indx=savep;
			//System.out.println("T "+indx+" "+s.charAt(indx));
			return false;
		}
	}
	public boolean Ld() {
		int savep=indx;
		read_next_token();
		if(token==',' && S() && Ld()) {
			return true;
		}
		indx=savep;
		return true;
	}
}
