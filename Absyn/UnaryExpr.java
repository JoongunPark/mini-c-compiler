package Absyn;

import java.util.*;
import java.io.PrintWriter;

public class UnaryExpr extends Expr{
    Expr expr;
    String symbol;

    public UnaryExpr(Expr ex, Pos s, Pos e){
        expr = ex;
        start = s;
        end = e;
    }

    public void printAST(){
        writer.print(symbol);
        expr.printAST();
    }

    public void expr_check(){
        if(expr.tn!=TypeName.INT && expr.tn!=TypeName.FLOAT){
            semantic_error(expr ,"This expression should have int or float type.");
        }
    }
}
