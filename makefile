JAVAC = javac
JAVA = java
CLASSPATH = .

init:
	$(JAVAC) JLex/*.java
	$(JAVAC) java_cup/runtime/*.java
	$(JAVAC) java_cup/*.java

parse: mini-c.cup
	$(JAVA) -classpath $(CLASSPATH) java_cup.Main mini-c.cup

lex: mini-c.lex
	$(JAVA) JLex.Main mini-c.lex
	mv mini-c.lex.java Yylex.java

build: sym.java parser.java Yylex.java
	$(JAVAC) Absyn/*.java
	$(JAVAC) sym.java parser.java Yylex.java

all:
	$(JAVA) -classpath $(CLASSPATH) java_cup.Main mini-c.cup
	$(JAVA) JLex.Main mini-c.lex
	$(JAVAC) Absyn/*.java
	mv mini-c.lex.java Yylex.java
	$(JAVAC) -classpath $(CLASSPATH) sym.java parser.java Yylex.java

run:
	$(JAVA) -classpath $(CLASSPATH) parser

test:
	$(JAVA) -classpath $(CLASSPATH) parser < SampleTestInput.txt 

clean:
	-rm Absyn/*.class
	-rm *.class
	-rm Yylex.java
	-rm parser.java
	-rm sym.java

clear: 
	-rm Absyn/*.class
	-rm JLex/*.class
	-rm java_cup/*.class
	-rm *.class
	-rm Yylex.java
	-rm parser.java
	-rm sym.java

