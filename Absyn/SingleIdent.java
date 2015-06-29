package Absyn;

import java.util.*;
import java.io.PrintWriter;

public class SingleIdent extends Ident {
    public SingleIdent(String n, Pos s, Pos e) {
        name = n;
        start = s;
        end = e;
        Absyn.printProdRule("Identifier"+this.getPosition()+" := ID:'"+n+"'");
    }

    public boolean is_array(){ return false; }

    public void printAST(){
        writer.print(name);
    }
}
