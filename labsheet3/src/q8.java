public class q8 {
	public String s;
	public int indx=0;
	public char token;
	q8(String s) { 
		this.s=s;
	}
	public void read_next_token() {
		this.token=s.charAt(indx);
		indx=indx+1;
	}
	public boolean L() {
		int savep=indx;
		if(S() && Ld()) {
			return true;
		}
		else {
			indx=savep;
			return false;
		}
	}
	public boolean Ld() {
		int savep=indx;
		read_next_token();
		if(token==';' && S() && Ld()) {
			return true;
		}
		indx=savep;
		return true;
	}
	public boolean S() {
		int savep=indx;
		if(A())
			return true;
		indx=savep;
		if(C())
			return true;
		indx=savep;
		return false;
	}
	public boolean A() {
		int savep=indx;
		read_next_token();
		if(token=='i') {
			savep=indx;
			read_next_token();
			if(token=='d') {
				savep=indx;
				read_next_token();
				if(token=='=' && E())
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
		else {
			indx=savep;
			return false;
		}
	}
	public boolean C() {
		int savep=indx;
		read_next_token();
		if(token=='i') {
			savep=indx;
			read_next_token();
			if(token=='d') {
				savep=indx;
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
	public boolean E() {
		int savep=indx;
		read_next_token();
		if(token=='i') {
			savep=indx;
			read_next_token();
			if(token=='d')
				return true;
			else {
				indx=savep;
				return false;
			}
		}
		indx=savep;
		read_next_token();
		if(token=='n') {
			savep=indx;
			read_next_token();
			if(token=='u') {
				savep=indx;
				read_next_token();
				if(token=='m') {
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
		else {
			indx=savep;
			return false;
		}
	}
}
