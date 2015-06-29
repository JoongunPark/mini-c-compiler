package Absyn;

import java.util.*;
import java.io.PrintWriter;

public class Funclist extends Absyn {
    ArrayList<Func> arr;

    public Funclist(){
        arr = new ArrayList<Func>();
    }

    public Funclist(Func func) {
        arr = new ArrayList<Func>();
        arr.add(func);
        start = func.start;
        end = func.end;
        Absyn.printProdRule("Funclist"+this.getPosition()+" := Function"+func.getPosition());
    }

    public void add(Func func) {
        String temp = this.getPosition();
        arr.add(func);
        end = func.end;
        Absyn.printProdRule("Funclist"+this.getPosition()+" := Funclist"+temp+" Function"+func.getPosition());
    }

    public void printAST(){
        for(Func f : arr){
            f.printAST();
        }
    }

    public void printSymTable(){
        for(Func f : arr){
            f.printSymTable();
        }
    }

    public Funclist checkSemantic(){
        for(Func f : arr){
            f = f.checkSemantic();
        }
        return this;
    }
}
