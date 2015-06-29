package Absyn;

import java.util.*;
import java.io.PrintWriter;

public class MinusExpr extends BinaryExpr {
    public MinusExpr(Expr le, Expr re, Pos s, Pos e){
        super(le,re,s,e);
        symbol = "-";
    }

    public MinusExpr checkSemantic(){
        this.expr_check();
        this.tn = TypeName.INT;
        return this;
    }
}
