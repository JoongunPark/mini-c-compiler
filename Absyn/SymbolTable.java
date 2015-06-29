package Absyn;

import java.util.*;
import java.io.PrintWriter;

public class SymbolTable {
    String name;
    ArrayList<STElem> arr;

    public SymbolTable(String _name) {
        name = _name;
        arr = new ArrayList<STElem>();
    }

    public void add(String name, Type t, int len, boolean is_param) {
        STElem ste = new STElem(name,t,len,is_param);
        arr.add(ste);
    }

    public STElem find(String name){
        for(STElem st : arr){
            if(st.name.equals(name))
                return st;
        }
        return null;
    }
}

class FuncTable {
    String name;
    Type typ;
    ArrayList<STElem> arr;

    public FuncTable(String _name, Type _typ) {
        name = _name;
        typ = _typ;
        arr = new ArrayList<STElem>();
    }

    public void add(String name, Type t, int len, boolean is_param) {
        STElem ste = new STElem(name,t,len,is_param);
        arr.add(ste);
    }
}

class STElem {
    String name;
    Type typ;
    int size;
    boolean is_param;

    public STElem(String name, Type t, int len, boolean is_param) {
        this.name = name;
        typ = t;
        size = len;
        this.is_param = is_param;
        //System.out.println(name + " stelem is made");
    }

    public boolean is_array(){
        return size>=0;
    }
}