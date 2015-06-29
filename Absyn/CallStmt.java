package Absyn;

import java.util.*;
import java.io.PrintWriter;

public class CallStmt extends Stmt {
    Call call;

    public CallStmt(Call c, Pos s, Pos e){
        call = c;
        start = s;
        end = e;
    }

    public void printAST(){
        pretty("");
        call.printAST();
        writer.println(";");
    }

    public CallStmt checkSemantic(){
        this.call = call.checkSemantic();
        return this;
    }
}
