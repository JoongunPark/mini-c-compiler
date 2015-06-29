package Absyn;

import java.util.*;
import java.io.PrintWriter;

public class CallExpr extends Expr {
    Call call;

    public CallExpr(Call c, Pos s, Pos e){
        call = c;
        start = s;
        end = e;
    }

    public void printAST(){
        call.printAST();
    }

    public CallExpr checkSemantic(){
        this.call = call.checkSemantic();
        this.tn = call.tn;
        return this;
    }
}
