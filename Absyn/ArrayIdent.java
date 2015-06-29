package Absyn;

import java.util.*;
import java.io.PrintWriter;

public class ArrayIdent extends Ident {
    Integer size;

    public ArrayIdent(String n, Integer si, Pos s, Pos e) {
        name=n;
        size=si;
        start = s;
        end = e;
        Absyn.printProdRule("Identifier"+this.getPosition()+" := ID:'"+n+"' [ INT:"+si+" ]");
    }

    public boolean is_array(){ return true; }

    public void printAST(){
        writer.print(name+"["+size+"]");
    }

    public ArrayIdent checkSemantic(){
        return this;
    }

}
