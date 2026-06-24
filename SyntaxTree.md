# Definations:

There are two defined heirarchy of classes here that is Expr(expression) and Stmt(Statements), Expressions return a certain value after evaluation whereas
statements do not return anything , for example: print is a statement whereas mathematical expressions are expressions.

# Nodes Added in Expr:

Literal: Wraps constant values (numbers, strings, booleans, nil).

Variable: Handles variable access identifiers by querying the Environment.

Assign: Evaluates an expression and binds the resulting value to a variable string key.

Logical: Handles and and or tracking. Unlike typical binary operators, these evaluate sequentially to support short-circuiting properties.

Binary & Grouping: Manage traditional mathematical operations and explicit parentheses grouping.


# Nodes Added in Stmt:

Expression: Wraps an expression statement (like an assignment or method call) where a value-producing action is evaluated purely for its side effects.

Print: Evaluates a wrapped expression and pipes the string output directly to the system console.

Var: Introduces a new variable identifier binding into the current lexical environment scale.

Block: Encapsulates a nested list of statements inside a localized block wrapper { ... }, generating a scoped sub-environment.

If: Evaluates a conditional Expr node to determine whether to visit the thenBranch or elseBranch.

While: Continuously re-evaluates a conditional condition node, looping execution over a statement body as long as it returns truthy.
