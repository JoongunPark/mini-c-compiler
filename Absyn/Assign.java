package Absyn;

import java.util.*;
import java.io.PrintWriter;

public class Assign extends Absyn {
    String name;
    Expr index, expr;

    public Assign(String n, Expr idx, Expr ex, Pos s, Pos e) {
        name = n;
        index = idx;
        expr = ex;
        start = s;
        end = e;
    }

    public void printAST(){
        writer.print(name);
        if(index!=null){
            writer.print("[");
            index.printAST();
            writer.print("]");
        }
        writer.print(" = ");
        expr.printAST();
    }

    public Assign checkSemantic(){

        STElem ste = get_sym_table_elem(name);

        if(ste == null)
            semantic_error(this,"Variable "+name+" is not defined.");
        this.expr = expr.checkSemantic();

        if(index!=null){
            this.index = index.checkSemantic();

            if(!ste.is_array())
                semantic_error(this,"Variable "+name+" is not array type.");
            if(this.index.tn != TypeName.INT)
                semantic_error(this.index,"Index of array variable should have int type.");
        }else{
            if(ste.is_array()){
                semantic_error(this,"Variable "+name+" should have array format in assign statement.");
            }
        }

        // error correction
        if(ste.typ.typ != this.expr.tn){
            String t;
            if(ste.typ.typ == TypeName.INT) t = "int";
            else t = "float";
            switch(this.expr.tn){
                case INT:
                    semantic_warning(this.expr,"Expression should have "+t+" type.");
                    this.expr = new IntToFloat(this.expr);
                    break;
                case FLOAT:
                    semantic_warning(this.expr,"Expression should have "+t+" type.");
                    this.expr = new FloatToInt(this.expr);
                    break;
                default:
                    semantic_error(this.expr,"Expression should have "+t+" type.");
            }
        }

        return this;
    }
}
