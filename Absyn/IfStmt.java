package Absyn;

import java.util.*;
import java.io.PrintWriter;

public class IfStmt extends Stmt {
    Expr condition;
    Stmt then_stmt, else_stmt;

    public IfStmt(Expr cond, Stmt th, Stmt el, Pos s, Pos e) {
        condition = cond;
        then_stmt = th;
        else_stmt = el;
        start = s;
        end = e;
    }

    public void printAST(){
        pretty("if(");
        condition.printAST();
        writer.println(")");
        if(!CompStmt.class.isInstance(then_stmt)) level++;
        then_stmt.printAST();
        if(!CompStmt.class.isInstance(then_stmt)) level--;
        if(else_stmt!=null){
            prettyln("else");
            if(!CompStmt.class.isInstance(else_stmt)) level++;
            else_stmt.printAST();
            if(!CompStmt.class.isInstance(else_stmt)) level--;
        }
    }

    public void printSymTable(){
        StmtCounter temp = new StmtCounter(comp_count);
        temp.if_count+=1;
        comp_count = new StmtCounter();
        nameStack.add("if("+temp.if_count+")");
        printFuncName();
        then_stmt.printSymTable();
        comp_count=temp;
        nameStack.remove(nameStack.size()-1);

        if(else_stmt!=null){
            StmtCounter temp2 = new StmtCounter(comp_count);
            temp2.else_count+=1;
            comp_count = new StmtCounter();
            nameStack.add("else("+temp2.else_count+")");
            printFuncName();
            else_stmt.printSymTable();
            comp_count=temp2;
            nameStack.remove(nameStack.size()-1);
        }
    }

    public IfStmt checkSemantic(){
        this.condition = condition.checkSemantic();
        if(this.condition.tn != TypeName.INT && this.condition.tn != TypeName.FLOAT)
            semantic_error(this.condition,"Condition of if- statementshould have int or float type.");
        this.then_stmt = then_stmt.checkSemantic();
        if(else_stmt!=null) this.else_stmt = else_stmt.checkSemantic();
        return this;
    }
}
