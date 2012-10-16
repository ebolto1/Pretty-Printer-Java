class StrLit extends Node {
  private String strVal;

  public StrLit(String s) { strVal = s; }

  public void print(int n) {

    System.out.print("\"" + strVal + "\"");
  }
  
  public String getVal(){
	  return strVal;
  }
  
  @Override
  public boolean isString(){
	  return true;
  }
}