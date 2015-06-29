package Absyn;

import java.util.*;
import java.io.PrintWriter;

public class Absyn {
    public Pos start = null;
    public Pos end = null;

    public String getPosition(){
        if(start==null)
            start = new Pos(0,0);
        if(end==null)
            end = new Pos(0,0);
        return "("+ start.format()+" - " +end.format() + ")";
    }
    static public PrintWriter writer = null;

    static public void printProdRule(String msg){
            writer.println(msg);
    }
    public void printAST(){}

    // Indentation
    static public int tab = 4;
    static public int level = 0;
    protected void pretty(String msg){
        String space="";
        for(int i=0;i<tab*level;i++) space+=" ";
        writer.print(space + msg);
    }
    protected void prettyln(String msg){
        String space="";
        for(int i=0;i<tab*level;i++) space+=" ";
        writer.println(space+msg);
    }

    public void printSymTable(){ }

    // Symbol Table

    static public Integer sym_count = 0;
    static public StmtCounter comp_count = new StmtCounter();

    static public ArrayList<String> nameStack = new ArrayList<String>();

    static public void printFuncName(){
        int i;
        if(nameStack.size()!=0){
            writer.print("\nFunction Name : ");
            for(i=0;i<nameStack.size()-1;i++)
                writer.print(nameStack.get(i) + " - ");
            writer.println(nameStack.get(i));
        }
        else    writer.println("Function Name : GLOBAL");
        writer.println("     count      type                          name     array      role");
    }

    static public void printSymbols(Type t, Ident id, boolean is_param){
        String role = "variable";
        String typeName=null;
        String idx = "";
        if(is_param){
            role = "parameter";
        }
        switch(t.typ){
            case INT:
                typeName="int";
                break;
            case FLOAT:
                typeName="float";
                break;
        }
        if(id.is_array()){
            ArrayIdent ident = (ArrayIdent) id;
            idx = String.valueOf(ident.size);
            writer.printf("%10d%10s%30s%10s%10s\n",sym_count,typeName,ident.name,idx,role);
        }
        else{
            SingleIdent ident = (SingleIdent) id;
            writer.printf("%10d%10s%30s%10s%10s\n",sym_count,typeName,ident.name,idx,role);
        }

    }

    // Semantic Check

    static public boolean is_func = false;
    static public ArrayList<SymbolTable> sym_table_stack = new ArrayList<SymbolTable>();
    static public ArrayList<FuncTable> func_table_stack = new ArrayList<FuncTable>();
    static public SymbolTable cur_sym_table = null;
    static public FuncTable cur_fun_table = null;

    static public STElem get_sym_table_elem(String name){
        int i;
        STElem st = cur_sym_table.find(name);

        if(st != null)
            return st;

        for(i = sym_table_stack.size()-1;i>=0;i--){
            st = sym_table_stack.get(i).find(name);
            if(st != null) break;
        }
        //System.out.println("looking for " +  name);
        return st;
    }

    static public FuncTable get_fun_table(String name){
        for(FuncTable st : func_table_stack){
            if(st.name.equals(name)) return st;
        }
        return null;
    }

    static public void add_symbol(Type t, Ident id, boolean is_param){
        if(id.is_array()){
            if(t.typ != TypeName.INT)
                semantic_error(id, "A variable or expression indexed as an array that is not an int[] type.");
            ArrayIdent ident = (ArrayIdent) id;
            cur_sym_table.add(id.name,t,ident.size,is_param);
            if (is_param)
                cur_fun_table.add(id.name,t,ident.size,is_param);
        }
        else{
            SingleIdent ident = (SingleIdent) id;
            cur_sym_table.add(id.name,t,-1,is_param);
            if (is_param)
                cur_fun_table.add(id.name,t,-1,is_param);
        }
        //System.out.println(id.name+"is added");
    }

    static public void semantic_error(Absyn node, String message){
        if(node == null)
            System.err.println("SemanticError: "+message);
        else
            System.err.println("["+node.start.format()+"-"+node.end.format()+"]:"+" SemanticError: "+message);
        System.exit(0);
    }
    static public void semantic_warning(Absyn node, String message){
        if(node == null)
            System.err.println("Warning: "+message);
        else
            System.err.println("["+node.start.format()+"-"+node.end.format()+"]:"+" Warning: "+message);
    }
}
