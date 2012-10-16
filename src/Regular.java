class Regular extends Special {
	private Cons cons;
	
	public Regular( Cons c) {
		this.cons=c;	
	}
	
    void print(Node t, int n, boolean p) {
    	if(!p)
    		System.out.print("(");
    	
    	
    	if (cons.getCar() instanceof Cons) {
			cons.getCar().print(0, false);	
    	}
    	else { 
    		cons.getCar().print(0, true);
    	}

    	
    	if (cons.getCdr() != null)
			System.out.print(" ");
    	
		
    	if (cons.getCdr() != null) {
			cons.getCdr().print(0, true);
		}
		else {
			System.out.print(")");		
		}
    }
    
    public void printQuote(Node t, int n, boolean p){
    	print(t, n, p);
    }
    
    public Cons getCons(){
    	return cons;
    }
}