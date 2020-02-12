public class q2 {
	public String s;
	public int indx=0;
	public char token;
	q2(String s) {
		this.s=s;
	}
	public void read_next_token() {
		this.token=s.charAt(indx);
		indx=indx+1;
	}
	public boolean S() {
		int savep=indx;
		read_next_token();
		if(token=='(' && S()) {
			savep=indx;
			read_next_token();
			if(token==')' && S())
				return true;
			else {
				indx=savep;
				return false;
			}
		}
		indx=savep;
		return true;
	}
}
