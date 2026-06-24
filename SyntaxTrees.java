import java.util.List;

abstract class Expr {
    interface Visitor<R> {
        R visitBinaryExpr(Binary expr);
        R visitGroupingExpr(Grouping expr);
        R visitLiteralExpr(Literal expr);
        R visitLogicalExpr(Logical expr);
        R visitVariableExpr(Variable expr);
        R visitAssignExpr(Assign expr);
    }
    abstract <R> R accept(Visitor<R> visitor);

    static class Binary extends Expr {
        final Expr left; final Token operator; final Expr right;
        Binary(Expr left, Token operator, Expr right) { this.left = left; this.operator = operator; this.right = right; }
        @Override <R> R accept(Visitor<R> visitor) { return visitor.visitBinaryExpr(this); }
    }

    static class Grouping extends Expr {
        final Expr expression;
        Grouping(Expr expression) { this.expression = expression; }
        @Override <R> R accept(Visitor<R> visitor) { return visitor.visitGroupingExpr(this); }
    }

    static class Literal extends Expr {
        final Object value;
        Literal(Object value) { this.value = value; }
        @Override <R> R accept(Visitor<R> visitor) { return visitor.visitLiteralExpr(this); }
    }

    static class Logical extends Expr {
        final Expr left; final Token operator; final Expr right;
        Logical(Expr left, Token operator, Expr right) { this.left = left; this.operator = operator; this.right = right; }
        @Override <R> R accept(Visitor<R> visitor) { return visitor.visitLogicalExpr(this); }
    }

    static class Variable extends Expr {
        final Token name;
        Variable(Token name) { this.name = name; }
        @Override <R> R accept(Visitor<R> visitor) { return visitor.visitVariableExpr(this); }
    }

    static class Assign extends Expr {
        final Token name; final Expr value;
        Assign(Token name, Expr value) { this.name = name; this.value = value; }
        @Override <R> R accept(Visitor<R> visitor) { return visitor.visitAssignExpr(this); }
    }
}

abstract class Stmt {
    interface Visitor<R> {
        R visitBlockStmt(Block stmt);
        R visitExpressionStmt(Expression stmt);
        R visitIfStmt(If stmt);
        R visitPrintStmt(Print stmt);
        R visitVarStmt(Var stmt);
        R visitWhileStmt(While stmt);
    }
    abstract <R> R accept(Visitor<R> visitor);

    static class Block extends Stmt {
        final List<Stmt> statements;
        Block(List<Stmt> statements) { this.statements = statements; }
        @Override <R> R accept(Visitor<R> visitor) { return visitor.visitBlockStmt(this); }
    }

    static class Expression extends Stmt {
        final Expr expression;
        Expression(Expr expression) { this.expression = expression; }
        @Override <R> R accept(Visitor<R> visitor) { return visitor.visitExpressionStmt(this); }
    }

    static class If extends Stmt {
        final Expr condition; final Stmt thenBranch; final Stmt elseBranch;
        If(Expr condition, Stmt thenBranch, Stmt elseBranch) { this.condition = condition; this.thenBranch = thenBranch; this.elseBranch = elseBranch; }
        @Override <R> R accept(Visitor<R> visitor) { return visitor.visitIfStmt(this); }
    }

    static class Print extends Stmt {
        final Expr expression;
        Print(Expr expression) { this.expression = expression; }
        @Override <R> R accept(Visitor<R> visitor) { return visitor.visitPrintStmt(this); }
    }

    static class Var extends Stmt {
        final Token name; final Expr initializer;
        Var(Token name, Expr initializer) { this.name = name; this.initializer = initializer; }
        @Override <R> R accept(Visitor<R> visitor) { return visitor.visitVarStmt(this); }
    }

    static class While extends Stmt {
        final Expr condition; final Stmt body;
        While(Expr condition, Stmt body) { this.condition = condition; this.body = body; }
        @Override <R> R accept(Visitor<R> visitor) { return visitor.visitWhileStmt(this); }
    }
}
