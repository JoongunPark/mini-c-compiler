package Absyn;

import java.util.*;
import java.io.PrintWriter;

public class Paramlist extends Absyn {
    ArrayList<Param> arr;

    public Paramlist(){
        arr = new ArrayList<Param>();
    }

    public Paramlist(Type t, Ident id) {
        arr = new ArrayList<Param>();
        arr.add(new Param(t, id));
        start = t.start;
        end = id.end;
        Absyn.printProdRule("Paramlist"+this.getPosition()+" := Type"+t.getPosition()+" Ident"+id.getPosition());
    }

    public void add(Type t, Ident id) {
        String temp = this.getPosition();
        arr.add(new Param(t, id));
        end = id.end;
        Absyn.printProdRule("Paramlist"+this.getPosition()+" := Paramlist"+temp+" , Type"+t.getPosition()+"Ident"+id.getPosition());
    }

    public void printAST(){
        int idx;
        for(idx=0;idx<arr.size()-1;idx++){
            arr.get(idx).printAST();
            writer.print(", ");
        }
        arr.get(idx).printAST();
    }

    public void printSymTable(){
        for(Param p : arr){
            sym_count++;
            Type typ = p.typ;
            Ident id = p.ident;
            printSymbols(typ,id,true);
        }
    }

    public Paramlist checkSemantic(){
        for(Param p : arr){
            Type typ = p.typ;
            Ident id = p.ident;
            if(cur_sym_table.find(id.name) != null){
                semantic_error(id,"Parameter "+id.name+" is already defined");
            }
            add_symbol(typ,id,true);
        }
        return this;
    }

}
