package Absyn;

import java.util.*;
import java.io.PrintWriter;

public class DivExpr extends BinaryExpr {
    public DivExpr(Expr le, Expr re, Pos s, Pos e){
        super(le,re,s,e);
        symbol = "/";
    }

    public DivExpr checkSemantic(){
        this.expr_check();
        return this;
    }
}
