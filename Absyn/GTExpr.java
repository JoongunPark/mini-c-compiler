package Absyn;

import java.util.*;
import java.io.PrintWriter;

public class GTExpr extends BinaryExpr {
    public GTExpr(Expr le, Expr re, Pos s, Pos e){
        super(le,re,s,e);
        symbol = ">";
    }

    public GTExpr checkSemantic(){
        this.expr_check();
        this.tn = TypeName.INT;
        return this;
    }
}
