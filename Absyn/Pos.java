package Absyn;

import java.util.*;
import java.io.PrintWriter;

public class Pos {
    public int line=0;
    public int column=0;

    public Pos(int line, int column ){
        this.line = line;
        this.column = column;
    }

    public String format(){
        String lineS = "?";
        String columnS = "?";

        if(line!=0 || line!=0){
            lineS = String.valueOf(line);
            columnS = String.valueOf(column);
        }
        return lineS+":"+columnS;
    }
}
