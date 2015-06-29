package Absyn;

import java.util.*;
import java.io.PrintWriter;

public class EqualExpr extends BinaryExpr {
    public EqualExpr(Expr le, Expr re, Pos s, Pos e){
        super(le,re,s,e);
        symbol = "==";
    }

    public EqualExpr checkSemantic(){
        this.expr_check();
        this.tn = TypeName.INT;
        return this;
    }
}
