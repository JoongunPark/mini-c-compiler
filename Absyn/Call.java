package Absyn;

import java.util.*;
import java.io.PrintWriter;

public class Call extends Expr {
    String name;
    Arglist args;

    public Call(String n, Arglist al, Pos s, Pos e){
        name = n;
        args = al;
        start = s;
        end = e;
    }

    public void printAST(){
        writer.print(name+"(");
        if(args!=null)args.printAST();
        writer.print(")");
    }


    public Call checkSemantic(){
        // function existance check
        FuncTable ft = get_fun_table(name);
        if(ft==null) semantic_error(this,"Function "+name+" is not defined.");
        if(args==null) return this;
        // argument size check
        int arg_size = args.arr.size();
        int param_size = ft.arr.size();

        if(arg_size != param_size)
            semantic_error(this,"The number of arguments of function "+name+" should be "+param_size);

        //System.out.println(arg_size);
        //System.out.println(param_size);

        int i=0;
        for(Expr arg : args.arr){
            arg = arg.checkSemantic();
            STElem param = ft.arr.get(i);

            if(param.is_array()){
                //System.out.println(name);
                if(param.typ.typ == TypeName.INT && arg.tn != TypeName.INT_ARR)
                    semantic_warning(arg,  (i + 1) + " argument of the function call " + name + " should be int array type.");
                if(param.typ.typ == TypeName.FLOAT && arg.tn != TypeName.FLOAT_ARR)
                    semantic_warning(arg, (i+1)+" argument of the function call "+name+" should be float array type.");
            }
            else{
                if(param.typ.typ == TypeName.INT && arg.tn != TypeName.INT){
                    if(arg.tn == TypeName.FLOAT)
                        semantic_warning(arg,"This expression should have int type.");
                    else
                        semantic_error(arg,"This expression should have int type.");
                    arg = new FloatToInt(arg);
                }
                if(param.typ.typ == TypeName.FLOAT && arg.tn != TypeName.FLOAT){
                    if(arg.tn == TypeName.INT)
                        semantic_warning(arg,"This expression should have int type.");
                    else
                        semantic_error(arg,"This expression should have int type.");
                    arg = new IntToFloat(arg);
                }
            }
            args.arr.set(i,arg);
            i=i+1;
        }
        this.tn = ft.typ.typ;
        return this;
    }
}
