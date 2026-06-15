# Parser 
The parser's job is:

Tokens
    ↓
Parser
    ↓
AST (Syntax Tree)

The parser takes a sequence of tokens and turns it into an Expr tree

# 1) Recursive Descent Parsing::
You write methods like:
Expr expression() {
    return equality();
}

Expr equality() {
    ...
}

Expr comparison() {
    ...
}

Each grammar rule becomes a function.

--->Pros:
Easy to understand
Easy to debug
Great for interpreters

--->Used in:
Lox
Many scripting languages
Small compilers
Configuration languages

expression     → equality ;
equality       → comparison ( ( "!=" | "==" ) comparison )* ;
comparison     → term ( ( ">" | ">=" | "<" | "<=" ) term )* ;
term           → factor ( ( "-" | "+" ) factor )* ;
factor         → unary ( ( "/" | "*" ) unary )* ;
unary          → ( "!" | "-" ) unary
               | primary ;
primary        → NUMBER | STRING | "true" | "false" | "nil"
               | "(" expression ")" ;


# Why Are Parsers Essential?
Syntax Validation: Ensures code follows language rules.
Error Detection: Identifies and reports syntax errors early.
Structural Representation: Generates an AST for further compiler phases.
Enables Tooling: Powers IDEs, linters, and static analyzers.
Without parsers, compilers couldn’t proceed beyond raw tokenization, making them indispensable.

The parser is the brain that reads your intentions and turns them into structure — quietly enabling every optimization, every warning, and every successful build.
It’s the unsung hero of the software you write



# THE CORE IDEA OF RECURSIVE DESCENT PARSING
A recursive descent parser is just a set of functions where each function handles one grammar rule, and they call each other to handle nested rules.
The "descent" is you going deeper into the grammar. The "recursive" is that expressions can contain expressions.
Each level in that pyramid is one method in your parser. The key insight: each method only calls methods below it, which means lower = tighter binding = evaluated first. That's how precedence falls out naturally.
