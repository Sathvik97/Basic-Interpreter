// RECURSIVE DESCENT PARSING TECHNIQUE
class Parser{
   private boolean isAtEnd(){
     return peek().type == EOF;
   }

  private Token peek(){
        return tokens.get(current);
    }

   private Token previous(){
        return tokens.get(current-1);
   }
    private Token advance(){
        if(!isAtEnd()){
            current++;
        }
        return previous();
    }
    //The check() method returns true if the current token is of the given type.
    // Unlike match(), it never consumes the token, it only looks at it.
    private boolean check(TokenType type){
        if(peek().type == type && !isAtEnd()){
            return true;
        }
        return false;
    }

    //match function with varargs(variable arguments)
    //This checks to see if the current token has any of the given types.
    // If so, it consumes the token and returns true.
    // Otherwise, it returns false and leaves the current token alone.
    // The match() method is defined in terms of two more fundamental operations.
    private boolean match(TokenType...types){
        for(TokenType type:types){
            if(check(type)){
                advance();
                return true;
            }
        }
        return false;
    }


    // We store the list of tokens and use current to point
    // to the next token eagerly waiting to be parsed.
    private int current = 0;
    private final List<Token> tokens;

    Parser(List<Token> tokens){//receives the tokens as a list
        this.tokens = tokens;
    }

    //expression-----------------------
    private Expr expression() throws Throwable {
        return equality();
    }
    //-----------------------------------


    //------------------equality---------------------------
    private Expr equality(){//comparing equality
        Expr expr = comparison();

        while(match(BANG_EQUAL,EQUAL_EQUAL)){
            Token operator = previous();
            Expr right = comparison();
            expr = new Expr.Binary(expr,operator,right);
        }
        return expr;
    }
    //------------------------------------


    //----------------comparison--------------------------
    private Expr comparison(){
        Expr expr = term();

        while(match(GREATER, GREATER_EQUAL,LESS,LESS_EQUAL)){
            Token operator = previous();
            Expr right = term();
            expr = new Expr.Binary(expr,operator,right);
        }
        return expr;
    }
    //-------------------------------------



    //------------------term---------------------------------
    private Expr term(){
        Expr expr = factor();

        while(match(PLUS,MINUS)){
            Token operator = previous();
            Expr right = factor();
           expr = new  Expr.Binary(expr,operator,right);
        }
        return expr;
    }
    //---------------------------------------

    //-----------------factor--------------------
    private Expr factor() {
        Expr expr = unary();

        while(match(SLASH,STAR)){
            Token operator = previous();
            Expr right = unary();
            expr = new Expr.Binary(expr,operator,right);
        }
        return expr;
    }
    //----------------------------------------


    //----------------unary-------------------
    private Expr unary(){
        if (match(BANG, MINUS)) {
            Token operator = previous();
            Expr right = unary();
            return new Expr.Unary(operator, right);
        }

        return primary();
    }
    //-----------------------------------------

    //---------------primary-------------------
    private Expr primary() {
        if (match(FALSE)) return new Expr.Literal(false);
        if (match(TRUE)) return new Expr.Literal(true);
        if (match(NIL)) return new Expr.Literal(null);

        if (match(NUMBER, STRING)) {
            return new Expr.Literal(previous().literal);
        }

        if (match(LEFT_PAREN)) {
            Expr expr = expression();
            consume(RIGHT_PAREN, "Expect ')' after expression.");
            return new Expr.Grouping(expr);
        }
        //if it does not match anything then throw error
        throw error(peek(),"Expected expression");
    }


  Expr parse() {//to kick start parsing
        try {
            return expression();
        } catch (Throwable error) {
            return null;
        }
    }

  //--------------------------ERROR HANDLING-------------------------
  private void synchronize(){
   advance();//move from the token causing the error to the next one

    while(!isAtEnd()){//keep checking until EOF
      if(previous().type == SEMI_COLON){
         return;// if u passed a semi colon then return back to normal parsing
      }
      switch(peek().type){
                case CLASS:
                case FUN:
                case VAR:
                case FOR:
                case IF:
                case WHILE:
                case PRINT:
                case RETURN:
                    return;
          }
      advance();//if no case mathced keep moving
    }
}
//---------------------------------------------------  
    private Token consume(Tokentype type,String message){
              if(check(type)){
                 return advance();
             }
               throw error(peek(),message);
         }

  //---------------------------------------------------
  private ParseError error(Token token,String message){
       lox.error(token,message);
      return new ParseError();

    {
  //---------------------------------------------------
}
 
















}
