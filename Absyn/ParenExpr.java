package Absyn;

import java.util.*;
import java.io.PrintWriter;

public class ParenExpr extends Expr {
    Expr expr;
    public ParenExpr(Expr ex, Pos s, Pos e){
        expr = ex;
        start = s;
        end = e;
    }

    public void printAST(){
        writer.print("(");
        expr.printAST();
        writer.print(")");
    }

    public ParenExpr checkSemantic(){
        this.expr = expr.checkSemantic();
        this.tn = expr.tn;
        return this;
    }
}
