package Absyn;

import java.util.*;
import java.io.PrintWriter;

public class NullStmt extends Stmt{
    public NullStmt(Pos s, Pos e) {
        start = s;
        end = e;
    }

    public void printAST(){
        prettyln(";");
    }

}
