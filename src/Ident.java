class Ident extends Node {
  private String name;

  public Ident(String n) { name = n; }
  
  public void print(int n, boolean parenPrintedLast){
	  if(parenPrintedLast)
		print(n);
	  else print(n); 
  }

  public void print(int n) {
    System.out.print(name);
  }
  
  @Override
  public boolean isSymbol(){
	  return true;
  }
  
  public String getName() {
	  return name;
  }
}