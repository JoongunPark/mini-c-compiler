package Absyn;

import java.util.*;
import java.io.PrintWriter;

public class RetStmt extends Stmt {
    Expr expr;

    public RetStmt(Expr ex, Pos s, Pos e) {
        expr = ex;
        start = s;
        end = e;
    }

    public void printAST(){
        pretty("return ");
        if(expr!=null)expr.printAST();
        writer.println(";");
    }

    public RetStmt checkSemantic(){

        if(expr==null) semantic_error(this,"Return of function should have expression.");
        this.expr = expr.checkSemantic();
        //System.out.println(expr);
        TypeName fun_typ = cur_fun_table.typ.typ;
        TypeName expr_typ = expr.tn;
        if(fun_typ != expr_typ){
            String t;
            if(fun_typ == TypeName.INT) t = "int";
            else t = "float";
            switch(expr_typ){
                case INT:
                    semantic_warning(this.expr,"Return of function "+cur_fun_table.name+" should have "+t+" type.");
                    this.expr = new IntToFloat(this.expr);
                    break;
                case FLOAT:
                    semantic_warning(this.expr,"Return of function "+cur_fun_table.name+" should have "+t+" type.");
                    this.expr = new FloatToInt(this.expr);
                    break;
                default:
                    semantic_error(this.expr,"Return of function "+cur_fun_table.name+" should have "+t+" type.");
            }
        }
        return this;
    }
}
