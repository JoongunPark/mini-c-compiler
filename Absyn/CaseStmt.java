package Absyn;

import java.util.*;
import java.io.PrintWriter;

public class CaseStmt extends Stmt {
    Integer num;
    Stmtlist stmts;
    Boolean has_break;

    public CaseStmt(Integer k, Stmtlist sl, Boolean hb){
        num = k;
        stmts = sl;
        has_break = hb;
    }

    public void printAST(){
        prettyln("case "+num+":");
        level++;
        stmts.printAST();
        if(has_break) prettyln("break;");
        level--;
    }

    public void printSymTable(){
        StmtCounter temp = new StmtCounter(comp_count);
        temp.case_count+=1;
        comp_count = new StmtCounter();
        nameStack.add("case("+temp.case_count+")");
        printFuncName();
        stmts.printSymTable();
        comp_count=temp;
        nameStack.remove(nameStack.size() - 1);
    }

    public CaseStmt checkSemantic(){
        this.stmts = stmts.checkSemantic();
        return this;
    }
}
