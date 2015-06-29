package Absyn;

import java.util.*;
import java.io.PrintWriter;

public class LTEExpr extends BinaryExpr {
    public LTEExpr(Expr le, Expr re, Pos s, Pos e){
        super(le,re,s,e);
        symbol = "<=";
    }

    public LTEExpr checkSemantic(){
        this.expr_check();
        this.tn = TypeName.INT;
        return this;
    }
}
