package Absyn;

import java.util.*;
import java.io.PrintWriter;

public class ArrayIdExpr extends Expr {
    String name;
    Expr expr;

    public ArrayIdExpr(String n, Expr ex, Pos s, Pos e){
        name = n;
        expr = ex;
        start = s;
        end = e;
    }

    public void printAST(){
        writer.print(name+"[");
        expr.printAST();
        writer.print("]");
    }

    public ArrayIdExpr checkSemantic(){
        STElem ste = get_sym_table_elem(name);
        if(ste == null)
            semantic_error(this,"Variable "+name+" is not defined.");
        if(!ste.is_array())
            semantic_error(this,"Variable "+name+" is not array type.");

        this.expr = expr.checkSemantic();
        if(this.expr.tn != TypeName.INT)
            semantic_error(expr,"Array subscript of variable "+name+" should have int type.");
        this.tn = ste.typ.typ;
        return this;
    }
}
