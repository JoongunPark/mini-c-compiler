package Absyn;

import java.util.*;
import java.io.PrintWriter;

public class GTEExpr extends BinaryExpr {
    public GTEExpr(Expr le, Expr re, Pos s, Pos e){
        super(le,re,s,e);
        symbol = ">=";
    }

    public GTEExpr checkSemantic(){
        this.expr_check();
        this.tn = TypeName.INT;
        return this;
    }
}
