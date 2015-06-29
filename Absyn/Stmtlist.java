package Absyn;

import java.util.*;
import java.io.PrintWriter;

public class Stmtlist extends Absyn {
    ArrayList<Stmt> arr;

    public Stmtlist(Pos s, Pos e) {
        arr = new ArrayList<Stmt>();
        start = s;
        end = e;
        //Absyn.printProdRule("Stmtlist :="+this.getPosition()+" :=");
    }

    public void add(Stmt s) {
        String temp = this.getPosition();
        arr.add(s);
        if (arr.size() == 1)
            start = s.start;
        end = s.end;
        //Absyn.printProdRule("Stmtlist"+this.getPosition()+" := Stmtlist"+temp+" Stmt"+s.getPosition());
    }

    public void printAST(){
        for(Stmt s : arr){
            s.printAST();
        }
    }

    public void printSymTable(){
        for(Stmt s : arr){
            if(s instanceof CompStmt){
                StmtCounter temp = new StmtCounter(comp_count);
                temp.comp_count+=1;
                comp_count = new StmtCounter();
                nameStack.add("compound("+temp.comp_count+")");
                printFuncName();
                s.printSymTable();
                comp_count=temp;
                nameStack.remove(nameStack.size()-1);
            }else{
                s.printSymTable();
            }
        }
    }
    public Stmtlist checkSemantic(){
        for(Stmt s : arr){
            s = s.checkSemantic();
        }
        return this;
    }
}
