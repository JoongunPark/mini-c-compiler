package Absyn;

import java.util.*;
import java.io.PrintWriter;

public class Func extends Absyn {
    Type typ;
    String name;
    Paramlist params;
    CompStmt comp_stmt;

    public Func(){
        typ=null;
        name="";
        params=null;
        comp_stmt=null;
    }

    public Func(Type t, String fn, Paramlist pl, CompStmt cs) {
        typ = t;
        name = fn;
        params = pl;
        comp_stmt = cs;
        start = t.start;
        end = cs.end;
        if (pl==null){
            Absyn.printProdRule("Function"+this.getPosition()+" := Type"+t.getPosition()+" ID:'"+fn+"' ( ) CompoundStmt"+cs.getPosition());
        }
        else{
            Absyn.printProdRule("Function"+this.getPosition()+" := Type"+t.getPosition()+" ID:'"+fn+"' ( Paramlist"+pl.getPosition()+" ) CompoundStmt"+cs.getPosition());
        }

    }

    public void printAST(){
        typ.printAST();
        writer.print(" "+name+"(");
        if(params!=null){
            params.printAST();
        }
        writer.println(")");
        comp_stmt.printAST();
    }

    public void printSymTable(){
        sym_count=0;
        comp_count = new StmtCounter();
        nameStack.add(name);
        printFuncName();
        if(params!=null) params.printSymTable();
        comp_stmt.printSymTable();
        nameStack.remove(nameStack.size()-1);
    }


    public Func checkSemantic(){
        // redefine check

        if(get_fun_table(name)!=null)
            semantic_error(this,"Redefine function "+name+".");
        if(sym_table_stack.get(0).find(name) != null)
            semantic_error(this,"Duplicated function "+name+" with global variable.");

        sym_table_stack.add(new SymbolTable(name));
        func_table_stack.add(new FuncTable(name, typ));
        //System.out.println(typ.typ);

        cur_sym_table = sym_table_stack.get(sym_table_stack.size()-1);
        cur_fun_table = func_table_stack.get(func_table_stack.size()-1);
        SymbolTable temp_sym_table = cur_sym_table;

        if(params!=null) this.params = params.checkSemantic();
        is_func = true;
        this.comp_stmt = comp_stmt.checkSemantic();

        if(comp_stmt.stmts.arr.get(comp_stmt.stmts.arr.size()-1) instanceof RetStmt){

        }
        else {
            if(!name.equals("main"))
                semantic_warning(null, "function "+name+", which is non-void function needs a return");
        }


        sym_table_stack.remove(temp_sym_table);

        return this;
    }
}
