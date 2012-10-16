class IntLit extends Node {
  private int intVal;

  public IntLit(int i) { intVal = i; }

  public void print(int n) {
    System.out.print(intVal);
  }

  public int getVal(){
	  return intVal;
  }
  
  @Override
  public boolean isNumber(){
	  return true;
  }
}