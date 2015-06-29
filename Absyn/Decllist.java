package Absyn;

import java.util.*;
import java.io.PrintWriter;

public class Decllist extends Absyn {
    ArrayList<Decl> arr;

    public Decllist(){
        arr = new ArrayList<Decl>();
    }

    public Decllist(Decl decl) {
        arr = new ArrayList<Decl>();
        arr.add(decl);
        start = decl.start;
        end = decl.end;
        Absyn.printProdRule("Decllist"+this.getPosition()+" := Declaration"+decl.getPosition());
    }

    public void add(Decl decl) {
        String temp = this.getPosition();
        arr.add(decl);
        end = decl.end;
        Absyn.printProdRule("Decllist"+this.getPosition()+" := Decllist"+temp+" Declaration"+decl.getPosition());
    }

    public void printAST(){
        for(Decl d : arr){
            d.printAST();
        }
    }

    public void printSymTable(){
        for(Decl d : arr){
            d.printSymTable();
        }
    }

    public Decllist checkSemantic(){
        for(Decl d : arr){
            d = d.checkSemantic();
        }
        is_func = false;
        return this;
    }
}
