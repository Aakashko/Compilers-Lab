public class Driver {
public static void main(String[] args) {
	q1 qa=new q1("((a,a),(a))$");
	if(qa.S()) {
		qa.read_next_token();
		if(qa.token=='$')
			System.out.println("q1 Accepted");
		else
			System.out.println("q1 Not Accepted");
	}
	else {
		System.out.println("q1 Not Accepted");
	}
	
	q2 qb=new q2("(()())$");
	if(qb.S()) {
		qb.read_next_token();
		if(qb.token=='$')
			System.out.println("q2 Accepted");
		else
			System.out.println("q2 Not Accepted");
	}
	else {
		System.out.println("q2 Not Accepted");
	}
	
	q3 qc=new q3("((a,a),(a,a))$");
	if(qc.S()) {
		qc.read_next_token();
		if(qc.token=='$')
			System.out.println("q3 Accepted");
		else
			System.out.println("q3 Not Accepted");
	}
	else {
		System.out.println("q3 Not Accepted");
	}
	
	q4 qd=new q4("abababa,$");
	if(qd.S()) {
		qd.read_next_token();
		if(qd.token=='$')
			System.out.println("q4 Accepted");
		else
			System.out.println("q4 Not Accepted");
	}
	else {
		System.out.println("q4 Not Accepted");
	}
	
	q5 qe=new q5("id+(id*id)$");
	if(qe.E()) {
		qe.read_next_token();
		if(qe.token=='$')
			System.out.println("q5 Accepted");
		else {
			System.out.println("q5 Not Accepted");
		}
	}
	else {
		System.out.println("q5 Not Accepted");
	}
	
	q6 qf=new q6("a+b*b+1$");
	if(qf.S()) {
		qf.read_next_token();
		if(qf.token=='$')
			System.out.println("q6 Accepted");
		else {
			System.out.println("q6 Not Accepted");
		}
	}
	else {
		System.out.println("q6 Not Accepted");
	}
	
	q7 qg=new q7("ans=(a**b)/(d*c)$");
	if(qg.A()) {
		qg.read_next_token();
		if(qg.token=='$')
			System.out.println("q7 Accepted");
		else {
			System.out.println("q7 Not Accepted");
		}
	}
	else {
		System.out.println("q7 Not Accepted");
	}
	q8 qh=new q8("id=num;id(num)$");
	if(qh.L()) {
		qh.read_next_token();
		if(qh.token=='$')
			System.out.println("q8 Accepted");
		else {
			System.out.println("q8 Not Accepted");
		}
	}
	else {
		System.out.println("q8 Not Accepted");
	}
}
}
