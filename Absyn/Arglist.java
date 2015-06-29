package Absyn;

import java.util.*;
import java.io.PrintWriter;

public class Arglist extends Absyn {
    ArrayList<Expr> arr;

    public Arglist(){
        arr = new ArrayList<Expr>();
    }

    public Arglist(Expr ex) {
        arr = new ArrayList<Expr>();
        arr.add(ex);
        start = ex.start;
        end = ex.end;
        Absyn.printProdRule("Arglist"+this.getPosition()+" := Expr"+this.getPosition());
    }

    public void add(Expr ex) {
        String temp = this.getPosition();
        arr.add(ex);
        end = ex.end;
        Absyn.printProdRule("Arglist"+this.getPosition()+" := Arglist"+temp+" , Expr"+ex.getPosition());
    }

    public void printAST(){
        int i;
        for(i=0;i<arr.size()-1;i++){
            arr.get(i).printAST();
            writer.print(", ");
        }
        arr.get(i).printAST();
    }


}
