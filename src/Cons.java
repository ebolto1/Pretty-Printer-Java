class Cons extends Node {
	private Node car;
	private Node cdr;
	private Special form;

	// parseList() `parses' special forms, constructs an appropriate
	// object of a subclass of Special, and stores a pointer to that
	// object in variable form.  It would be possible to fully parse
	// special forms at this point.  Since this causes complications
	// when using (incorrect) programs as data, it is easiest to let
	// parseList only look at the car for selecting the appropriate
	// object from the Special hierarchy and to leave the rest of
	// parsing up to the interpreter.
	void parseList() { 
		if (car instanceof Ident) {
			Ident id = (Ident)car;
			String idName = id.getName();
			
			if (idName.equals("quote") || idName.equals("'")) {
				form = new Quote((Cons)cdr);
			} else if (idName.equals("lambda")) {
				form = new Lambda();
			} else if (idName.equals("begin")) {
				form = new Begin();
			} else if (idName.equals("if")) {
				form = new If();
			} else if (idName.equals("let")) {
				form = new Let();
			} else if (idName.equals("cond")) {
				form = new Cond();
			} else if (idName.equals("define")) {
				form = new Define();
			} else if (idName.equals("set!")) {
				form = new Set();
			} else {
				form = new Regular(idName);
			}
		}
	}
	// TODO: Add any helper functions for parseList as appropriate.

	public Cons(Node a, Node d) {
		car = a;
		cdr = d;
		parseList();
	}

	void print(int n) {
		form.print(this, n, false);
		if (cdr != null) cdr.print(n);
	}

	void print(int n, boolean p) {
		form.print(this, n, p);
	}
	
	//TODO: What the hell is a pair?
	@Override
	public boolean isPair() {
		return car != null && cdr != null;
	}
	@Override
	public Node getCar(){
		return car;
	}
	@Override
	public Node getCdr(){
		return cdr;
	}
	@Override
	public void setCar(Node a){
		car = a;;
	}
	@Override
	public void setCdr(Node d){
		car = d;
	}
}
