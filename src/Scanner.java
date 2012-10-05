// Scanner.java -- the implementation of class Scanner

import java.io.*;

class Scanner {
  private PushbackInputStream in;
  private byte[] buf = new byte[1000];

  public Scanner(InputStream i) { in = new PushbackInputStream(i); }
    
  public Token getNextToken() {
    int bite = -1;
	
    // It would be more efficient if we'd maintain our own input buffer
    // and read characters out of that buffer, but reading individual
    // characters from the input stream is easier.
    try {
      bite = in.read();
    } catch (IOException e) {
      System.err.println("We fail: " + e.getMessage());
    }

    // TODO: skip white space and comments
    char cbite = (char)bite;
	if(cbite == ' ' || cbite == '\t' || cbite == '\n' || cbite == '\r' || cbite == '\f')
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
      // TODO: scan a string into the buffer variable buf -NEED FIX
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
      int i = ch - '0';
      // TODO: scan the number and convert it to an integer
      
      // put the character after the integer back into the input
      // in->putback(ch);
      return new IntToken(i);
    }

    // Identifiers
    else if (ch >= 'A' && ch <= 'Z'
	     /* or ch is some other valid first character for an identifier */) {
      // TODO: scan an identifier into the buffer

      // put the character after the identifier back into the input
      // in->putback(ch);
      return new IdentToken(buf.toString());
    }

    // Illegal character
    else {
      System.err.println("Illegal input character '" + (char) ch + '\'');
      return getNextToken();
    }
  };
}
