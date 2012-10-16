// Parser.java -- the implementation of class Parser
//
// Defines
//
//   class Parser;
//
// Parses the language
//
//   exp  ->  ( rest
//         |  #f
//         |  #t
//         |  ' exp
//         |  integer_constant
//         |  string_constant
//         |  identifier
//    rest -> )
//         |  exp+ [. exp] )
//
// and builds a parse tree.  Lists of the form (rest) are further
// `parsed' into regular lists and special forms in the constructor
// for the parse tree node class Cons.  See Cons.parseList() for
// more information.
//
// The parser is implemented as an LL(0) recursive descent parser.
// I.e., parseExp() expects that the first token of an exp has not
// been read yet.  If parseRest() reads the first token of an exp
// before calling parseExp(), that token must be put back so that
// it can be reread by parseExp() or an alternative version of
// parseExp() must be called.
//
// If EOF is reached (i.e., if the scanner returns a NULL) token,
// the parser returns a NULL tree.  In case of a parse error, the
// parser discards the offending token (which probably was a DOT
// or an RPAREN) and attempts to continue parsing with the next token.

class Parser {
  private Scanner scanner;

  private Nil nil = new Nil();
  private BooleanLit trueLit=new BooleanLit(true), falseLit=new BooleanLit(false);
  
  public Parser(Scanner s) { scanner = s; }
  
  public Node parseExp() {
	  Node exp = null;
	  Token t= scanner.getNextToken();
	  if (t == null) { //EOF
		  exp = null;
	  } else if (t.getType() == TokenType.LPAREN) {
		  exp = parseRest();
	  } else if (t.getType() == TokenType.FALSE) {
		  exp = falseLit;
	  } else if (t.getType() == TokenType.TRUE) {
		  exp = trueLit;
	  } else if (t.getType() == TokenType.QUOTE) {
		  exp = new Cons(new Ident("'"), new Cons(parseExp(), null));
	  } else if (t.getType() == TokenType.INT) {
		  exp = new IntLit(t.getIntVal());
	  } else if (t.getType() == TokenType.STRING) {
		  exp = new StrLit(t.getStrVal());
	  } else if (t.getType() == TokenType.IDENT) {
		  exp = new Ident(t.getName());
	  } else if (t.getType() == TokenType.RPAREN) {
		  System.out.println("Unexpected Token: )");
		  exp = parseExp();
	  } else if (t.getType() == TokenType.DOT) {
		  System.out.println("Unexpected Token: .");
		  exp = parseExp();
	  } else { //Generic Parsing Error
		  System.out.println("Unexpected Token of Type: " + t.getType());
	  }
	  
    return exp;
  }
  
  protected Node parseRest() {
	    // TODO: write code for parsing rest
	      Token t= scanner.getNextToken();
	      Node exp= null;
	      if(t == null) {
	          exp=null;
	      } else if (t.getType() == TokenType.RPAREN){
	          return null;
	      } else if (t.getType() == TokenType.DOT){
	          //lookahead
	          t=scanner.getNextToken();
	          if(t.getType() != TokenType.RPAREN){
	              scanner.putTokenBack(t);
	              exp = new Cons(parseExp(), null);
	          } else {
	              System.out.println("unexpected: ')'");
	              exp=parseExp();
	          }
	      } else { //exp
	    	  scanner.putTokenBack(t);
	          exp= new Cons(parseExp(), parseRest());
	      }
	   
	    return exp;
	  }
  
  // TODO: Add any additional methods you might need.
};
