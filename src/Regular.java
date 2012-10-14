import java.io.*;

class Regular extends Special {
	private String idName;
	private Cons cons;
	
	
	public Regular(String idName, Cons c) {
		this.idName = idName;
		this.cons=c;
		
	}
	
    void print(Node t, int n, boolean p) {
    	System.out.print("REGULAR:[");
    	if (cons.getCar() instanceof Cons)
			cons.getCar().print(n);
    	else 
    		cons.getCar().print(n);
		if (cons.getCdr() != null) cons.getCdr().print(n);
		
		System.out.print("]");
    }
    
    public Cons getCons(){
    	return cons;
    }
}