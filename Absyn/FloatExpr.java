package Absyn;

import java.util.*;
import java.io.PrintWriter;

public class FloatExpr extends Expr {
    Float num;

    public FloatExpr(Float k, Pos s, Pos e){
        tn = TypeName.FLOAT;
        num = k;
        start = s;
        end = e;
    }

    public void printAST(){
        writer.print(num);
    }
}
