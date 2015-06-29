package Absyn;

import java.util.*;
import java.io.PrintWriter;

public class UnaryMinusExpr extends UnaryExpr {
    public UnaryMinusExpr(Expr ex, Pos s, Pos e){
        super(ex,s,e);
        symbol = "-";
    }

    public UnaryMinusExpr checkSemantic(){
        this.expr = expr.checkSemantic();
        this.tn = expr.tn;
        this.expr_check();
        return this;
    }
}
