package Absyn;

import java.util.*;
import java.io.PrintWriter;

public class StmtCounter {
    public int case_count = 0;
    public int for_count = 0;
    public int func_count = 0;
    public int if_count = 0;
    public int switch_count = 0;
    public int while_count = 0;
    public int comp_count = 0;
    public int else_count = 0;

    public StmtCounter(){
    }
    public StmtCounter(StmtCounter copy) {
        this.case_count = copy.case_count;
        this.for_count = copy.for_count;
        this.func_count = copy.func_count;
        this.if_count = copy.if_count;
        this.switch_count = copy.switch_count;
        this.while_count = copy.while_count;
        this.comp_count = copy.comp_count;
        this.else_count = copy.else_count;
    }
}