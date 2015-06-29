package Absyn;

import java.util.*;
import java.io.*;

public class Program extends Absyn {
    Decllist decls;
    Funclist funcs;

    public Program(){
        decls=null;
        funcs=null;
    }

    public Program(Decllist dl, Funclist fl) {
        decls = dl;
        funcs = fl;
        if(dl==null) {
            if(fl==null){
                start = end = null;
                Absyn.printProdRule("Program" + this.getPosition() + " := ");
            }
            else
            {
                start = fl.start;
                end = fl.end;
                Absyn.printProdRule("Program" + this.getPosition() + " := Funclist" + fl.getPosition());
            }
        }
        else{
            if(fl==null){
                start = dl.start;
                end = dl.end;
                Absyn.printProdRule("Program" + this.getPosition() + " := Decllist" + dl.getPosition());
            }
            else{
                start = dl.start;
                end = fl.end;
                Absyn.printProdRule("Program" + this.getPosition() + " := Decllist" + dl.getPosition() + " Funclist" + fl.getPosition());
            }
        }
    }

    public void printAST(){
        if(decls!=null) decls.printAST();
        if(funcs!=null) funcs.printAST();
    }

    public void printSymTable(){
        printFuncName();
        if (decls != null) decls.printSymTable();
        if (funcs != null) funcs.printSymTable();
    }

    public Program checkSemantic(){
        sym_table_stack.add(new SymbolTable("GLOBAL"));
        cur_sym_table = sym_table_stack.get(sym_table_stack.size()-1);
        SymbolTable temp_sym_table = cur_sym_table;

        if(decls!=null) this.decls = decls.checkSemantic();
        if(funcs!=null) this.funcs = funcs.checkSemantic();

        sym_table_stack.remove(temp_sym_table);

        // main function check
        if(get_fun_table("main")==null)
            semantic_error(null,"Program should have main function");

        return this;

    }
}
