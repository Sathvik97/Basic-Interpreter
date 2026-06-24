# Stack:

Stack is region of memory which grows and shrinks in a predictable order LIFO(last in first out), stack allocation basically means assigning memory for local 
variables and function calls in call stack. It has limited space,it happens automatically when a function is called and freed after it returns something.
If the stack is full an error is displayed like Segmentation fault in C or java.lang.StackOverflowError in java.Static variables and global variables are not
stored in the stack.

# Features:
--> They are stored in contigous memory locations called as call stack
-->Once the function finishes execution, the allocated memory is automatically freed.
-->The programmer does not need to handle allocation or deallocation.
-->Since stack memory is freed when a function completes, it is also called temporary memory allocation.

# Heap:

Heap memory is allocated dynamically during program execution. Unlike stack memory, heap memory is not freed automatically when a function ends.
Instead, it requires manual deallocation (In C/C++) or a garbage collector (in Java or Python) to reclaim unused memory.


# Features:
-->If heap memory is full, JVM throws an error: java.lang.OutOfMemoryError.
-->Slower than stack memory due to manual allocation and garbage collection.
-->Typically larger in size compared to stack memory.
-->every object you create with new goes on the heap.


pass by value means sending a copy of the variable’s value to a function, while pass by reference means sending the variable’s address so the function can directly modify the original data. 
int is a primitive type. Primitives in Java don't involve any reference/pointer indirection at all. There's no separate object floating around somewhere 
that x "points to." The value 10 is stored directly inside the memory slot for x.
STACK
┌──────────────┐
│ x = 10        │   ← that's it. Nothing else. No heap involved.
└──────────────┘
There's no heap object here, because there's nothing to point to — x doesn't hold an address, it holds the number itself.


The split you saw earlier (stack reference → heap object) only happens with reference types — things like String, arrays, or any class instance.Those that involve:
stack: [reference/address] ──▶ heap: [actual object data]
But primitives skip that indirection entirely:
stack: [the actual value, directly]
Java's full list of primitives (all behave like int here)
int, long, short, byte, float, double, boolean, char — none of these ever go on the heap on their own. If they're local variables, the value sits directly in the stack frame. Full stop.
javaint x = 10;        // stack only
double pi = 3.14;  // stack only
boolean flag = true; // stack only
char c = 'A';      // stack only

The stack-points-to-heap split only happens when a variable is a reference/pointer. If a variable directly holds its value (a primitive), there's no split
it's stack-only (for locals), with nothing on the heap at all.
Primitive variable:        [value] ────────── stored directly, done.

Reference variable:        [address] ───────▶ [actual data, elsewhere]

A primitive (int, double, boolean, etc.) has a small, fixed size known at compile time. The compiler can just say "reserve 4 bytes for this int, right here" and 
put the value directly in that slot — no indirection needed.
A reference type (objects, arrays, strings) could be huge, variable-sized, or need to be shared/aliased by multiple variables at once. 
You can't just "copy the whole object" every time you pass it around or store it somewhere — that'd be wasteful and would break sharing semantics. 
So instead, the variable just stores a small fixed-size address (the pointer), and the real data sits once, somewhere on the heap, and anyone can point to it.




