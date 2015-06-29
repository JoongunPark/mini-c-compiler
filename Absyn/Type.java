package Absyn;

import java.util.*;
import java.io.PrintWriter;

public class Type extends Absyn {
    TypeName typ;

    public Type(TypeName t, Pos s, Pos e) {
        typ = t;
        start = s;
        end = e;

        switch (typ){
            case INT:
                Absyn.printProdRule("Type" + this.getPosition() + " := int");
                break;
            case FLOAT:
                Absyn.printProdRule("Type"+this.getPosition()+" := float");
                break;
        }
    }

    public void printAST(){
        switch (typ){
            case INT:
                writer.print("int");
                break;
            case FLOAT:
                writer.print("float");
                break;
        }
    }
}
