# Resolver class:

The Resolver is a separate pass that runs after the parser finishes, but before the interpreter starts executing anything.
Instead of executing the code, it simply reads through your syntax tree (AST)to figure out exactly which variable declaration every single variable usage belongs to. 
It acts like a mapmaker preparing a guide for the interpreter.

# How the Resolver class works:

Inside Resolver.java, it doesn't use the runtime Environment class. Instead, it keeps track of scopes using a simple compile-time stack of string maps:
Javaprivate final Stack<Map<String, Boolean>> scopes = new Stack<>();
When the Resolver walks through the code block from our example, here is what it does step-by-step:
Step 1: Entering the BlockWhen it enters the { block, it pushes an empty map onto its stack to represent the new local scope.PlaintextStack:
[ 1: {} ]  <-- Current local scope
Step 2: Processing the FunctionIt sees fun showA(). It adds "showA" to its current scope map. Then it visits the inside of showA. Inside the function, 
it sees print a;.The Resolver searches its stack from top to bottom to find where a was declared. 
It doesn't find it in the local map, so it realizes a must be a global variable. It tells the interpreter: 
"Hey, whenever you run this specific print a expression, look 0 scopes up (the global scope).
"Step 3: Processing the Local VariableNext, the Resolver encounters var a = "local";. It updates its scope map:PlaintextStack:
[ 1: {"a": true} ]  <-- "a" is now declared locally!
Step 4: Storing the MapFinally, the Resolver finishes walking the code. It hands a side-table (Map<Expr, Integer>) to the Interpreter. This table contains a strict rule for every variable expression in the program:Variable ExpressionDistance (How many scopes up to look)print a (inside showA)0 hops (Look directly in the global environment)
