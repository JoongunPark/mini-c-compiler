package Absyn;

import java.util.*;
import java.io.PrintWriter;

public class IntExpr extends Expr {
    Integer num;

    public IntExpr(Integer k, Pos s, Pos e){
        tn = TypeName.INT;
        num = k;
        start = s;
        end = e;
    }

    public void printAST(){
        writer.print(num);
    }
}
