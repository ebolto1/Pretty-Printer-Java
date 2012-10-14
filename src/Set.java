import java.io.*;

class Set extends Special {
 
	private Cons cons;
	
	
	public Set(Cons c) {
		this.cons=c;
	}

    void print(Node t, int n, boolean p) {
    	for(int i=0; i<n; i++)
    		System.out.println(" ");
    	if(!p)
    		System.out.print("(");
    	
    	
    	if (cons.getCar() instanceof Cons) {
			cons.getCar().print(n, false);	
    	}
    	else { 
    		cons.getCar().print(n, true);
    	}

    	
    	if (cons.getCdr() != null)
			System.out.print(" ");
    	
		
    	if (cons.getCdr() != null) {
			cons.getCdr().print(n, true);
		}
		else {
			System.out.print(")");		
		}
    }
    
    void printQuote(Node t, int n, boolean p){
    	print(t, n, p);
    }
}
