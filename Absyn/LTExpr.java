package Absyn;

import java.util.*;
import java.io.PrintWriter;

public class LTExpr extends BinaryExpr {
    public LTExpr(Expr le, Expr re, Pos s, Pos e){
        super(le,re,s,e);
        symbol = "<";
    }

    public LTExpr checkSemantic(){
        this.expr_check();
        this.tn = TypeName.INT;
        return this;
    }
}
