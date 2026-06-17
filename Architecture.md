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

Key concepts in interpreter design include:

Parsing: Analyzing the structure of the code.
Execution: Directly running the parsed code.
Dynamic Typing: Handling variable types at runtime.
Error Handling: Providing immediate feedback for runtime errors.

Compilers are prefered for perfermance critical applications such as operating systems,video games and large scale enterprise software
Interpreters, however, shine in scenarios requiring flexibility, rapid prototyping.


