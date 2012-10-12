// Scanner.java -- the implementation of class Scanner

import java.io.*;

class Scanner {
	private PushbackInputStream in;
	private byte[] buf = new byte[1000];
	private Token putback = null;

	public Scanner(InputStream i) { in = new PushbackInputStream(i); }

	public void putTokenBack(Token t) {
		putback = t;
	}
	
	public Token getNextToken() {
		if (putback != null) {
			Token tmp = putback;
			putback = null;
			return tmp;
		}
		
		int bite = -1;

		// It would be more efficient if we'd maintain our own input buffer
		// and read characters out of that buffer, but reading individual
		// characters from the input stream is easier.
		try {
			bite = in.read();
		} catch (IOException e) {
			System.err.println("We fail: " + e.getMessage());
		}

		//Skip whitespace and comments
		char cbite = (char)bite;
		if(isWhiteSpace(cbite))
			return getNextToken();
		if(cbite == ';')
		{
			try{
				while(in.read() != '\n');
			}catch(IOException e){
				System.err.println("WE FAIL:" + e.getMessage());
				bite = -1; 
			}
			return getNextToken();
		}
		if (bite == -1)
			return null;

		char ch = (char) bite;

		// Special characters
		if (ch == '\'')
			return new Token(Token.QUOTE);
		else if (ch == '(')
			return new Token(Token.LPAREN);
		else if (ch == ')')
			return new Token(Token.RPAREN);
		else if (ch == '.')
			// We ignore the special identifier `...'.
			return new Token(Token.DOT);

		// Boolean constants
		else if (ch == '#') {
			try {
				bite = in.read();
			} catch (IOException e) {
				System.err.println("We fail: " + e.getMessage());
			}

			if (bite == -1) {
				System.err.println("Unexpected EOF following #");
				return null;
			}
			ch = (char) bite;
			if (ch == 't')
				return new Token(Token.TRUE);
			else if (ch == 'f')
				return new Token(Token.FALSE);
			else {
				System.err.println("Illegal character '" + (char) ch + "' following #");
				return getNextToken();
			}
		}

		// String constants
		else if (ch == '"') {
			int i;
			for(i = 0; i<buf.length; i++)
			{
				try{
					ch = (char)in.read();
					if(ch == '\\')
					{
						ch = (char)in.read();
						switch(ch){
						case'\\':
							buf[i] = '\\';
							continue;
						case'\"':
							buf[i] = '\"';
							continue;
						case't':
							buf[i] = '\t';
							continue;
						case'n':
							buf[i] = '\n';
							continue;
						case'f':
							buf[i] = '\f';
							continue;
						default:
							throw new IllegalArgumentException("Invalid escape sequence");
						}
					}
					if(ch == '\"')
						break;
					buf[i] = (byte)ch;   			   			
				}
				catch(IOException e){
					System.err.println("WE FAIL:" + e.getMessage());    	
				}
				catch(IllegalArgumentException e)
				{
					System.err.println("Something went wrong: " + e.getMessage());
				}	
			}

			byte[] str = new byte[i];	
			for(int j = 0; j <i; j++)
			{
				str[j] = buf[j]; 
			}
			return new StrToken(new String(str));
		}

		// Integer constants
		else if (ch >= '0' && ch <= '9') {
			int j=0;
			try{	
				for(j=0; j<buf.length; j++){
					byte i = (byte) (ch - '0');
					buf[j]=(byte)ch;
					bite= in.read();
					ch= (char) bite;
					if(ch >= '0' && ch <= '9')
						continue;
					else if(isWhiteSpace(ch) || !isExtendedAlpha(ch)){
						in.unread((byte)ch);
						break;
					}else {
						throw new IllegalArgumentException("invalid integer; expected digit, received \'" +ch +"\'");
					}
				}
			} catch (IOException e){
				System.out.println("WE FAIL: ");
				e.printStackTrace();
			} catch (IllegalArgumentException e){
				System.out.println("something went wrong: " +e.getMessage());
			}
			
			
			byte[] temp = new byte[j+1];
			
			for(int i=0; i<=j; i++){
				temp[i]=buf[i];
			}
			int k=Integer.MAX_VALUE;
			try{
				k=Integer.parseInt(new String(temp));
			} catch (NumberFormatException n) {
				System.out.println("Integer to big for int type: "+ new String(temp)+"\n Reset to Integer.MAX_VALUE");
			}
			
			
			return new IntToken(k);
		}

		//Identifiers
		else if (isExtendedAlpha(ch)) {
			try {
				int i;
				for (i = 0; i < buf.length; i++)
				{
					buf[i] = (byte)ch;
					bite = in.read();
					ch= (char)bite;
					if ( isExtendedAlpha(ch) || (ch >= '0' && ch <= '9') )
						continue;
					else
					{
						in.unread((int)ch); // put the character after the identifier back into the input
						break;
					}
				}

				byte[] id = new byte[i+1];	
				for(int j = 0; j <= i; j++)
				{
					id[j] = buf[j]; 
				}
				String idStr = new String(id);
				return new IdentToken(idStr.toLowerCase());
			}
			catch(IOException e) {
				System.err.println("WE FAIL:" + e.getMessage());    
				return getNextToken();
			}
		}

		// Illegal character
		else {
			System.err.println("Illegal input character '" + (char) ch + '\'');
			return getNextToken();
		}
	};
	private boolean isExtendedAlpha(char ch){
		return (ch >= 'A' && ch <= 'Z') || (ch >= 'a' && ch <= 'z') || (ch >= '<' && ch <= '@' 
				|| ch == '!' ||ch == '%' ||ch == '&' ||ch == '*' ||ch == '+' ||ch == '-' ||ch == '.' ||ch == '/' 
				|| ch == ':' ||ch == '^' ||ch == '_' ||ch == '~' );
				
	}
	private boolean isWhiteSpace(char ch){
		return ch == ' ' || ch == '\t' || ch == '\n' || ch == '\r' || ch == '\f';
	}
}
