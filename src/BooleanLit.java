class BooleanLit extends Node {
  private boolean booleanVal;

  public BooleanLit(boolean b) {
    booleanVal = b;
  }

  public void print(int n) {    
    if (booleanVal) {
      System.out.print("#t");
    } else {
      System.out.print("#f");
    }
  }
  
  public boolean getVal(){
	  return booleanVal;
  }
  
  @Override
  public boolean isBoolean(){
	  return true;
  }
}