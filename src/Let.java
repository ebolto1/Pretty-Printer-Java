import java.io.*;

class Let extends Special {
 
private Cons cons;
	
	public Let( Cons c) {
		this.cons=c;
	}

    void print(Node t, int n, boolean p) {
    	for(int i=0; i<n; i++)
    		System.out.print(" ");
    	if(!p)
    		System.out.print("(");
    	System.out.println("let");
    	if(t.getCdr()!=null)
	    	printElements((Cons)t.getCdr(), n+4, false);
    	for(int i=0; i<n; i++)
    		System.out.print(" ");
    	System.out.print(")");
    }
    
    void printElements(Cons t, int n, boolean isQuote) {
    	if (isQuote) {
    		System.out.print(" ");
    		t.getCar().printQuote(n, false);
    	} else {
    		t.getCar().print(n);
    		System.out.println();
    	}
    	
    	if(t.getCdr()!=null)
    		printElements((Cons)t.getCdr(), n, isQuote);
    }

	@Override
	void printQuote(Node t, int n, boolean p) {
		if(!p)
			System.out.print("(");
		System.out.print("let");
		if(t.getCdr()!=null)
	    	printElements((Cons)t.getCdr(), 0, true);
		System.out.print(")");
	}
}
