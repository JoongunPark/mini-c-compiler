package Absyn;

import java.util.*;
import java.io.PrintWriter;

public class WhileStmt extends Stmt {
    Expr expr;
    Stmt stmt;
    Boolean is_do;

    public WhileStmt(Expr ex, Stmt st, Boolean d, Pos s, Pos e) {
        expr = ex;
        stmt = st;
        is_do = d;
        start = s;
        end = e;
    }

    public void printAST(){
        if(is_do){
            prettyln("do");
            if(!CompStmt.class.isInstance(stmt)) level++;
            stmt.printAST();
            if(!CompStmt.class.isInstance(stmt)) level--;
            pretty("while(");
            expr.printAST();
            writer.println(");");
        }else{
            pretty("while(");
            expr.printAST();
            writer.println(")");
            if(!CompStmt.class.isInstance(stmt)) level++;
            stmt.printAST();
            if(!CompStmt.class.isInstance(stmt)) level--;
        }
    }

    public void printSymTable(){
        StmtCounter temp = new StmtCounter(comp_count);
        temp.while_count+=1;
        comp_count = new StmtCounter();
        if(is_do){
            nameStack.add("do_while("+temp.while_count+")");
        }else{
            nameStack.add("while("+temp.while_count+")");
        }
        printFuncName();
        stmt.printSymTable();
        comp_count=temp;
        nameStack.remove(nameStack.size()-1);
    }

    public WhileStmt checkSemantic(){
        this.expr = expr.checkSemantic();
        if(this.expr.tn != TypeName.INT && this.expr.tn != TypeName.FLOAT)
            semantic_error(this.expr,"Condition of while-statementshould have int or float type.");
        this.stmt = stmt.checkSemantic();
        return this;
    }
}
