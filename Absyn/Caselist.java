package Absyn;

import java.util.*;
import java.io.PrintWriter;

public class Caselist extends Absyn {
    ArrayList<CaseStmt> arr;

    public Caselist(Pos s, Pos e) {
        arr = new ArrayList<CaseStmt>();
        start = s;
        end = e;
    }

    public void add(Integer k, Stmtlist sl, Boolean hb, Pos s, Pos e){
        arr.add(new CaseStmt(k, sl, hb));
        if (arr.size() == 1)
            start = s;
        end = e;
    }

    public void add(CaseStmt c){
        arr.add(c);
        if (arr.size() == 1)
            start = c.start;
        end = c.end;
    }

    public void printAST(){
        for(CaseStmt c : arr){
            c.printAST();
        }
    }

    public void printSymTable(){
        for(CaseStmt c : arr){
            c.printSymTable();
        }
    }

    public Caselist checkSemantic(){
        for(CaseStmt c : arr){
            c = c.checkSemantic();
        }
        return this;
    }
}
