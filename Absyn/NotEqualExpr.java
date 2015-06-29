package Absyn;

import java.util.*;
import java.io.PrintWriter;

public class NotEqualExpr extends BinaryExpr {
    public NotEqualExpr(Expr le, Expr re, Pos s, Pos e){
        super(le,re,s,e);
        symbol = "!=";
    }

    public NotEqualExpr checkSemantic(){
        this.expr_check();
        this.tn = TypeName.INT;
        return this;
    }
}
