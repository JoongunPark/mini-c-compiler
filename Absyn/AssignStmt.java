package Absyn;

import java.util.*;
import java.io.PrintWriter;

public class AssignStmt extends Stmt {
    Assign assign;

    public AssignStmt(Assign as, Pos s, Pos e) {
        assign = as;
        start = s;
        end = e;
    }

    public void printAST(){
        pretty("");
        assign.printAST();
        writer.println(";");
    }

    public AssignStmt checkSemantic(){
        this.assign = assign.checkSemantic();
        return this;
    }
}
