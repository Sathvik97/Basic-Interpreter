enum TokenType {
    // Single-character tokens.
    LEFT_PAREN, RIGHT_PAREN, LEFT_BRACE, RIGHT_BRACE,
    COMMA, DOT, MINUS, PLUS, SEMICOLON, SLASH, STAR,

    // One or two character tokens.
    BANG, BANG_EQUAL,
    EQUAL, EQUAL_EQUAL,
    GREATER, GREATER_EQUAL,
    LESS, LESS_EQUAL,

    // Literals.
    IDENTIFIER, STRING, NUMBER,

    // Keywords.
    AND, CLASS, ELSE, FALSE, FUN, FOR, IF, NIL, OR,
    PRINT, RETURN, SUPER, THIS, TRUE, VAR, WHILE,

    EOF
}

public class lox{
  static void main(String[] args) throws IOException{
        //If the args length is too much the machine doesn't know which file to run
        if(args.length > 1){
            System.out.println("Usage: lox[script]");
            System.exit(64);
        }
        else if(args.length == 1){
            runFile(args[0]);
        }
        else{
            runPrompt();
        }                       

 }
      private static void runPrompt() throws IOException {
        InputStreamReader input = new InputStreamReader(System.in);
        BufferedReader reader = new BufferedReader(input);

        for(;;){
            System.out.print("> ");
            String line = reader.readLine();
            if(line == null){
                break;
            }
            run(line);
            hadError = false;
        }
    }
        private static void run(String source) {
        Scanner scanner = new Scanner(source);
        List<Token> tokens = scanner.scanTokens();

        Parser parser = new Parser(tokens);
        Expr expression = parser.parse();

        // Stop if there was a syntax error.
        if (hadError) {
            return;
        }
    }
// Error handling--------------------------------------------------------
    static void error(Token token, String message) {
        if (token.type == TokenType.EOF) {
            report(token.line, " at end", message);
        } else {
            report(token.line, " at '" + token.lexeme + "'", message);
        }
    }
    static void report(int line,String where,String message){
        System.err.println(
                "[line " + line + "] Error" + where + ": " + message);
        hadError = true;
    }
    //------------------------------------------------------------------------
    private static void runFile(String path) throws IOException {
        byte[] bytes = Files.readAllBytes(Paths.get(path));
        //converts bytes to characters
        run(new String(bytes,Charset.defaultCharset()));
        if(hadError){
            System.exit(64);
        }
    }
}

// TOKEN CLASS
class Token{
    final TokenType type;
    final String lexeme;
    final Object literal;
    final int line;

    Token(TokenType type,String lexeme,Object literal,int line){
        this.lexeme = lexeme;
        this.line = line;
        this.literal = literal;
        this.type = type;
    }

    public String toString() {
        return type + " " + lexeme + " " + literal;
    }
}


// SCANNER CLASS

class Scanner{
    private final String source;
    private final List<Token> tokens = new ArrayList<>();
    private int start = 0;
    private int current = 0;
    private int line = 1;

    Scanner(String source){
        this.source = source;
    }
    List<Token> scanTokens(){
        while(!isAtEnd()){
            start = current;
            scanToken();//each loop we scan a token
        }
        // after scanning all tokens just add NULL OBJECT
        tokens.add(new Token(TokenType.EOF,"",null,line));
        return tokens;
    }
    private void scanToken(){
          char c = advance();
          switch (c){
              //checking for single operators
              case '(': addToken(LEFT_PAREN); break;
              case ')': addToken(RIGHT_PAREN); break;
              case '{': addToken(LEFT_BRACE); break;
              case '}': addToken(RIGHT_BRACE); break;
              case ',': addToken(COMMA); break;
              case '.': addToken(DOT); break;
              case '-': addToken(MINUS); break;
              case '+': addToken(PLUS); break;
              case ';': addToken(SEMICOLON); break;
              case '*': addToken(STAR); break;

              //checking for operators like !=,>=,<= etc
              case '!': addToken(match('=') ? BANG_EQUAL : BANG) ;
              break;
              case '>':addToken(match('=') ? GREATER_EQUAL : GREATER);
              break;
              case '<':addToken(match('!') ? LESS_EQUAL : LESS);
              break;
              case '=':addToken(match('=') ? EQUAL_EQUAL : EQUAL);
              break;

              //next line case if we find \n then line variable is incremented
              case '\n':
                  line++;
                  break;

                  //special case of '/' cause it can mean division
              // or if followed with another slash would make it a comment
              //so we check if the / is followed with another /
              case '/':
                  if(match('/')){
                      //if it's a comment we just keep moving until we find \n or eof
                      while(peek()!='\n' && !isAtEnd()){
                          advance();
                      }
                  }
                  else{
                      addToken(SLASH);
                  }
                  break;


              //ignore white spaces
              case ' ':
              case '\r':
              case '\t':
                  break;

              //string literals
              case '"':string();
              break;

              default:
                  //for number literals since we cannot make a case for every single digit
                  // we put it in the default
                  if(isDigit(c)){
                      number();
                  }
                  else if(isAlpha(c)){
                      identifier();
                  }
                   else {
                  lox.report(line,"+","Unexpected character used");
                  }
          }
    }

    //for the number token
    private void number() {
        if(isDigit(peek())){
            advance();
        }
        //checking the fractional part
    if( peek()=='.' && isDigit(peekNext())){
        //consuming the '.'
       advance();
       }
    //parseInt and parseDouble and likewise convert string into numeric data types
    addToken(NUMBER,Double.parseDouble(source.substring(start,current)));
    }

    //peek the next char
    private char peekNext(){
        if(current+1 >=source.length()){
            return '\0';
        }
        return source.charAt(current+1);
    }


    //check if it's a digit
    private boolean isDigit(char c){
        return c >= '0' && c <= '9';
    }

    //check if its alpha
    private boolean isAlpha(char c){//underscore is allowed
        return (c >= 'a' && c <= 'z') || (c >= 'A' && c <= 'Z') || (c == '_');
    }
    private boolean isAlphanumeric(char c){
        return isDigit(c) || isAlpha(c);//check if its either digit or alpha
    }

    //identifier token
    private void identifier(){
        while(isAlphanumeric(peek())){
            advance();
        }
        String text = source.substring(start,current);
        TokenType type = keywords.get(text);
        if(type == null){
            type = IDENTIFIER;
        }
        addToken(type);
    }

    //string token
    private void string() {
        while(peek()!='"' && !isAtEnd()){
            if(peek()=='\n'){
                line++;
            }
            advance();
        }
        if(isAtEnd()){//if we are at the end that means the " isn't closed hence error display
            lox.report(line,"+","String isnt closed");
        }
        advance();

        //making the string token
        String value = source.substring(start+1,current-1);
        addToken(STRING,value);
    }

    private char peek(){
        if(isAtEnd()){
            return '\0';
        }
        //if not an end return the current character
       return source.charAt(current);
    }


    private boolean match(char expected){
        if(isAtEnd()){
            return false;
        }
        if(source.charAt(current) != expected){
            return false;
        }
        current++;
        return true;
    }

    private boolean isAtEnd(){
        return current>=source.length();
    }
    private char advance(){
        return source.charAt(current++);
    }
    private void addToken(TokenType t){
        addToken(t,null);
        //calls the addToken(TokenType t , Object literal)
    }
    private void addToken(TokenType t , Object literal){
        String text = source.substring(start,current);
        tokens.add(new Token(t,text,literal,line));
    }

    //KEYWORDS HASHMAP-----------------------------------
    private static final Map<String, TokenType> keywords;
    static {
        keywords = new HashMap<>();
        keywords.put("and",    AND);
        keywords.put("class",  CLASS);
        keywords.put("else",   ELSE);
        keywords.put("false",  FALSE);
        keywords.put("for",    FOR);
        keywords.put("fun",    FUN);
        keywords.put("if",     IF);
        keywords.put("nil",    NIL);
        keywords.put("or",     OR);
        keywords.put("print",  PRINT);
        keywords.put("return", RETURN);
        keywords.put("super",  SUPER);
        keywords.put("this",   THIS);
        keywords.put("true",   TRUE);
        keywords.put("var",    VAR);
        keywords.put("while",  WHILE);
    }
}
