public class q4 {
	public String s;
	public int indx=0;
	public char token;
	q4(String s) {
		this.s=s;
	}
	public void read_next_token() {
		this.token=s.charAt(indx);
		indx=indx+1;
	}
	public boolean S() {
		//System.out.println("S");
		int savep=indx;
		if(A() && B()) {
			savep=indx;
			read_next_token();
			//System.out.println(token);
			if(token=='a') {
				savep=indx;
				read_next_token();
				//System.out.println(token);
				if(token==',')
					return true;
				else {
					savep=indx;
					return false;
				}
			}
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
	public boolean A() {
		//System.out.println("A");
		int savep=indx;
		if(B() && B()) {
			return true;
		}
		indx=savep;
		read_next_token();
		//System.out.println(token);
		if(token=='b')
			return true;
		else {
			indx=savep;
			return false;
		}
	}
	public boolean B() {
		//System.out.println("B");
		int savep=indx;
		read_next_token();
		//System.out.println(token);
		if(token=='a') {
			savep=indx;
			read_next_token();
			//System.out.println(token);
			if(token=='b') {
				return true;
			}
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
}
