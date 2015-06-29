package Absyn;

import java.util.*;
import java.io.PrintWriter;

public class SwitchStmt extends Stmt {
    Ident ident;
    Caselist cases;
    Stmtlist default_stmt;
    Boolean default_has_break;

    public SwitchStmt(Ident id, Caselist cl, Stmtlist ds, Boolean dhb, Pos s, Pos e) {
        ident = id;
        cases = cl;
        default_stmt = ds;
        default_has_break = dhb;
        start = s;
        end = e;
    }

    public void printAST(){
        pretty("switch(");
        ident.printAST();
        writer.println(")");
        prettyln("{");
        level++;
        cases.printAST();
        if(default_stmt!=null){
            prettyln("default:");
            level++;
            default_stmt.printAST();
            if(default_has_break){
                writer.println("break;");
            }
            level--;
        }
        level--;
        prettyln("}");
    }

    public void printSymTable(){
        StmtCounter temp = new StmtCounter(comp_count);
        temp.switch_count+=1;
        comp_count = new StmtCounter();
        nameStack.add("switch("+temp.switch_count+")");
        printFuncName();
        cases.printSymTable();
        if(default_stmt!=null){
            StmtCounter temp2 = new StmtCounter(comp_count);
            temp2.switch_count+=1;
            comp_count = new StmtCounter();
            nameStack.add("default("+temp2.switch_count+")");
            printFuncName();
            default_stmt.printSymTable();
            comp_count=temp2;
            nameStack.remove(nameStack.size()- 1);
        }
        comp_count=temp;
        nameStack.remove(nameStack.size()-1);
    }

    public SwitchStmt checkSemantic(){
        STElem ste = get_sym_table_elem(ident.name);
        if(ste == null){
            semantic_error(ident, "Variable "+ident.name+" is not defined");
        }
        if(ste.typ.typ != TypeName.INT){
            semantic_error(ident, "statement requires expression of int type");
        }
        this.ident = ident.checkSemantic();
        this.cases = cases.checkSemantic();
        this.default_stmt = default_stmt.checkSemantic();
        return this;
    }
}
