package Absyn;

import java.util.*;
import java.io.PrintWriter;

public class Identlist extends Absyn {
    ArrayList<Ident> arr;

    public Identlist(Ident id) {
        arr = new ArrayList<Ident>();
        arr.add(id);
        start = id.start;
        end = id.end;
        Absyn.printProdRule("Identlist"+this.getPosition()+" := Identifier"+id.getPosition());
    }

    public void add(Ident id) {
        String temp = this.getPosition();
        arr.add(id);
        end = id.end;
        Absyn.printProdRule("Identlist"+this.getPosition()+" := Identlist"+temp+" , Identifier"+id.getPosition());
    }

    public void printAST(){
        arr.get(0).printAST();
        for(Ident i : arr.subList(1,arr.size())){
            writer.print(", ");
            i.printAST();
        }
    }
}
