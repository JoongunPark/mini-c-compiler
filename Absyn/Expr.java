package Absyn;

import java.util.*;
import java.io.PrintWriter;

public class Expr extends Absyn {
    TypeName tn;
    public Expr checkSemantic(){ return this; }
}
