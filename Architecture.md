# Compiler vs Interpreter

Both compiler and interpreter have the task to convert high level language to machine code for the computer,but they do differ in the way its done.
The compiler converts the entire source code into machine code at once and displays the error after the entire compilation.Examples include C/C++,whereas the 
interpreter converts source code to machine code line by line which makes debugging easier but its slower.Examples include Python,Ruby
One more key difference is that the compiler produces a executable file whereas interpreters dont

Interpreted languages were once considered significantly slower than compiled languages. However, with the development of just-in-time (JIT) compilation, the 
performance gap is shrinking. however, that JIT compilers turn code from the interpreted language into machine native code as the program runs

Java on the other hand is both compiled and interpreted,in the first step the javac compiler turns the source code into bytecode not machine code , the JVM then
loads the bytecode and uses the JIT(just in time compiler) to compile that bytecode into native machine code.
Modern JVMs also have a JIT compiler. This means that the JVM optimizes our code at runtime to gain similar performance benefits to a compiled language.

The Java Virtual Machine (JVM) is platform-dependent, meaning its implementation varies across operating systems and hardware platforms.
This is because the JVM is responsible for translating Java bytecode into machine-specific instructions that the underlying operating system 
and hardware can execute.

The JVM starts by interpreting bytecode but switches to JIT compilation for performance-critical sections of the code. 
This hybrid approach allows the JVM to balance portability (via interpretation) and performance (via JIT compilation)

The JVM is best described as a virtual machine that combines interpretation and compilation. It interprets bytecode initially and uses JIT compilation 
to optimize performance-critical code. This dual nature makes Java both a compiled and interpreted language

# Difference in the design

Key concepts in compiler design include:
Lexical Analysis: Breaking down source code into tokens.

Syntax Analysis: Ensuring the code adheres to grammatical rules.

Semantic Analysis: Checking for logical consistency.

Code Optimization: Improving performance by refining the generated code.

Code Generation: Producing machine code or intermediate code.

-----------------------------------------------------------------
Key concepts in interpreter design include:
Parsing: Analyzing the structure of the code.

Execution: Directly running the parsed code.

Dynamic Typing: Handling variable types at runtime.

Error Handling: Providing immediate feedback for runtime errors.

Compilers are prefered for perfermance critical applications such as operating systems,video games and large scale enterprise software
Interpreters, however, shine in scenarios requiring flexibility, rapid prototyping.

In a statically typed language, the type of a variable is known and checked at compile-time. This usually means you must explicitly declare the type of a variable (like int, String, or boolean) before you can use it, and that type cannot change later.

In a dynamically typed language, types are associated with the values, not the variables themselves. Type checking happens at runtime. You don't need to specify what kind of data a variable holds; the language figures it out automatically when the code executes.  


Semantic analysis in general refers to checking and computing facts about a program's meaning 

Java's javac, C#'s Roslyn compiler, TypeScript's compiler, Rust's compiler — all of them have an explicit semantic analysis phase (often called "binding" or "type checking" or "resolution") that runs after parsing and before code generation. This is precisely why the editor can underline "undefined variable" or "type mismatch" before you ever run the program — that's semantic analysis output, not a runtime error.



So basically the compiler and the interpreter have the same design on the frontend but the backend differs in case of both the shared frontend design:

--> Lexical analysis: tokens are formed from all the characters.
--> Parser: Organizes those tokens into an AST(abstract syntax tree).
--> Semantic analysis: Checks scopes, variables and type safety rules.(compiler does it at a deeper level)

# The Interpreter

It contains Environment structure that stores,updates and fetches active program variables and the evaluater runs down the AST to perform the program

# The Compiler

The compiler on the other hand uses different methods of doing stuff after the frontend it converts the AST to IR(intermediate representative) which is more 
optimized and easy for analysis its neutral mid level language, the optimizer optimizes the code for hardware efficiency by removing dead code , finnaly the 
code generator converts this IR into low level machine level language for the final stages

Compilers are used when the execution speed and the hardware efficiency is the main target Examples: Video games, operating systems, high-frequency trading algorithms, and machine learning libraries.

Interpreters are used when the priority is on rapid prototyping and fast interactivity, cross platform portability is important




























