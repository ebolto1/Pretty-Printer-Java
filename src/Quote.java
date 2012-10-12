import java.io.*;

class Quote extends Special {
	private Cons list = null;
	
	public Quote(Cons list) {
		this.list = list;
	}

    void print(Node t, int n, boolean p) {
    	System.out.println("QUOTE");
    }
}
