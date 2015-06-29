package Absyn;

import java.util.*;
import java.io.PrintWriter;

public class CompStmt extends Stmt{
    Decllist decls;
    Stmtlist stmts;

    public CompStmt(Decllist dl, Stmtlist sl, Pos s, Pos e) {
        decls = dl;
        stmts = sl;
        start = s;
        end = e;
        if(dl==null){
            Absyn.printProdRule("CompoundStmt"+this.getPosition()+" := { Stmtlist"+sl.getPosition()+" }");
        }
        else{
            Absyn.printProdRule("CompoundStmt"+this.getPosition()+" := { Decllist"+dl.getPosition()+" Stmtlist"+sl.getPosition()+" }");
        }
    }

    public void printAST(){
        prettyln("{");
        level++;
        if(decls!=null) decls.printAST();
        stmts.printAST();
        level--;
        prettyln("}");
    }

    public void printSymTable(){
        if(decls!=null)decls.printSymTable();
        stmts.printSymTable();
    }

    public CompStmt checkSemantic(){
        sym_table_stack.add(new SymbolTable("compound"));
        cur_sym_table = sym_table_stack.get(sym_table_stack.size()-1);
        SymbolTable temp_sym_table = cur_sym_table;

        if(decls!=null) this.decls = decls.checkSemantic();
        this.stmts = stmts.checkSemantic();

        sym_table_stack.remove(temp_sym_table);
        return this;
    }
}
