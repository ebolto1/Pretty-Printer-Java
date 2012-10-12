import java.io.*;

class Regular extends Special {
	private String idName;
	
	public Regular(String idName) {
		this.idName = idName;
	}
	
    void print(Node t, int n, boolean p) {
    	System.out.println("REGULAR[" + idName + "]");
    }
}