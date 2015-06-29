package Absyn;

import java.util.*;
import java.io.PrintWriter;

public class PlusExpr extends BinaryExpr {
    public PlusExpr(Expr le, Expr re, Pos s, Pos e){
        super(le,re,s,e);
        symbol = "+";
    }

    public PlusExpr checkSemantic(){
        this.expr_check();
        return this;
    }
}
