package Absyn;

import java.util.*;
import java.io.PrintWriter;

public class Ident extends Absyn {
    String name;
    public boolean is_array(){ return false; }

    public Ident checkSemantic(){
        return this;
    }
}
