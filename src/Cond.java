class Cond extends Special {
	public Cond() { }

	void print(Node t, int n, boolean p) {
    	if(!p)
    		System.out.print("(");
    	System.out.println("cond");
    	if(t.getCdr()!=null){
	    	printElements((Cons)t.getCdr(), n+4, false);
    	}
		for(int i=0; i<n; i++)
			System.out.print(" ");
    	System.out.println(")");
    }
    void printElements(Cons t, int n, boolean isQuote) {
    	if (isQuote) {
    		System.out.print(" ");
    		t.getCar().printQuote(n, false);
    	} else {
    		if (t.getCar() instanceof Cons)
    			for(int i=0; i<n; i++)
            		System.out.print(" ");
    		t.getCar().print(n);
    		System.out.println(); //This line dbl prints for nested special forms
    	}
    	
    	if(t.getCdr()!=null)
    		printElements((Cons)t.getCdr(), n, isQuote);
    }
	@Override
	void printQuote(Node t, int n, boolean p) {
		if(!p)
			System.out.print("(");
		System.out.print("cond ");
		if(t.getCdr()!=null)
	    	printElements((Cons)t.getCdr(), 0, true);
		System.out.print(")");
	}
}