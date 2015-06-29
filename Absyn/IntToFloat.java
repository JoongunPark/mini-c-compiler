package Absyn;

import java.util.*;
import java.io.PrintWriter;

public class IntToFloat extends Expr{
    Expr expr;

    public IntToFloat(Expr e){
        expr = e;
        tn = TypeName.FLOAT;
    }

    public void printAST(){
        writer.print("__int2float__(");
        expr.printAST();
        writer.print(")");
    }
}
