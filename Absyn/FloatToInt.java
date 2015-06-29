package Absyn;

import java.util.*;
import java.io.PrintWriter;

public class FloatToInt extends Expr{
    Expr expr;

    public FloatToInt(Expr e){
        expr = e;
        tn = TypeName.INT;
    }

    public void printAST(){
        writer.print("__float2int__(");
        expr.printAST();
        writer.print(")");
    }
}
