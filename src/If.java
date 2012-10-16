class If extends Special {
	public If() { }

    void print(Node t, int n, boolean p) {
    	if(!p)
    		System.out.print("(");
    	System.out.print("if ");
    	if(t.getCdr()!=null){
    		t.getCdr().getCar().print(n+4, false);
    		System.out.println();
    		for(int i=0; i<n; i++)
	    		System.out.print(" ");
    		if(t.getCdr().getCdr()!=null)
        		printElements((Cons)t.getCdr().getCdr(), n+4, false);
    	}
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
		System.out.print("if");
		if(t.getCdr()!=null)
	    	printElements((Cons)t.getCdr(), 0, true);
		System.out.print(")");
	}
}
