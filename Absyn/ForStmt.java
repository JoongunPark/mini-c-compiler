package Absyn;

import java.util.*;
import java.io.PrintWriter;

public class ForStmt extends Stmt {
    Assign initial;
    Expr condition;
    Assign incl;
    Stmt stmt;

    public ForStmt(Assign init, Expr cond, Assign as, Stmt st, Pos s, Pos e) {
        initial = init;
        condition = cond;
        incl = as;
        stmt = st;
        start = s;
        end = e;
    }

    public void printAST(){
        pretty("for(");
        initial.printAST();
        writer.print("; ");
        condition.printAST();
        writer.print("; ");
        incl.printAST();
        writer.println(")");
        if(!CompStmt.class.isInstance(stmt)) level++;
        stmt.printAST();
        if(!CompStmt.class.isInstance(stmt)) level--;
    }

    public void printSymTable(){
        StmtCounter temp = new StmtCounter(comp_count);
        temp.for_count+=1;
        comp_count = new StmtCounter();
        nameStack.add("for("+temp.for_count+")");
        printFuncName();
        stmt.printSymTable();
        comp_count=temp;
        nameStack.remove(nameStack.size()-1);
    }

    public ForStmt checkSemantic(){
        this.initial = initial.checkSemantic();
        this.condition = condition.checkSemantic();
        if(this.condition.tn != TypeName.INT && this.condition.tn != TypeName.FLOAT)
            semantic_error(this.condition,"Condition of for-statementshould have int or float type.");
        this.incl = incl.checkSemantic();
        this.stmt = stmt.checkSemantic();
        return this;
    }
}
