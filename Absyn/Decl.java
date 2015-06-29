package Absyn;

import java.util.*;
import java.io.PrintWriter;

public class Decl extends Absyn {
    Type typ;
    Identlist idents;

    public Decl(){
        typ=null;
        idents=null;
    }

    public Decl(Type t, Identlist idl, Pos e){
        typ = t;
        idents = idl;
        start = t.start;
        end = e;
        Absyn.printProdRule("Declaration" + this.getPosition() + " := Type" + t.getPosition() + " Identlist" + idl.getPosition() + " ;");
    }

    public void printAST(){
        pretty("");
        typ.printAST();
        writer.print(" ");
        idents.printAST();
        writer.println(";");
    }

    public void printSymTable(){
        for(Ident id : idents.arr){
            sym_count++;
            printSymbols(typ,id,false);
        }
    }

    public Decl checkSemantic(){

        for(Ident id : idents.arr){
            if(is_func){
                int size = sym_table_stack.size();
                SymbolTable st = sym_table_stack.get(size-2);
                if(st.find(id.name)!=null){
                    semantic_error(id,"Variable "+id.name+" is already defined");
                }
            }
            if(cur_sym_table.find(id.name)!=null){
                semantic_error(id, "Parameter " + id.name + " is already defined");
            }
            add_symbol(typ, id, false);
        }
        return this;
    }
}
