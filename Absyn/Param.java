package Absyn;

import java.util.*;
import java.io.PrintWriter;

public class Param extends Absyn {
    Type typ;
    Ident ident;

    public Param(Type t, Ident id){
        typ = t;
        ident = id;
    }

    public void printAST(){
        typ.printAST();
        writer.print(" ");
        ident.printAST();
    }
}
