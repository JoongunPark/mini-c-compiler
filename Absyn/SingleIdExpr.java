package Absyn;

import java.util.*;
import java.io.PrintWriter;

public class SingleIdExpr extends Expr {
    String name;

    public SingleIdExpr(String n, Pos s, Pos e){
        name = n;
        start = s;
        end = e;
    }

    public void printAST(){
        writer.print(name);
    }

    public SingleIdExpr checkSemantic(){
        STElem ste = get_sym_table_elem(name);
        if(ste == null)
            semantic_error(this,"Variable "+name+" is not defined.");
        if(ste.is_array()){
            if(ste.typ.typ == TypeName.INT)
                tn = TypeName.INT_ARR;
            else
                tn = TypeName.FLOAT_ARR;
        }else{
            tn = ste.typ.typ;
        }
        return this;
    }
}
