java -classpath $CLASSPATH:../../..:../../../build/classes -ea JLex.Main gml.lex
cp gml.lex.java Yylex.java
rm gml.lex.java
java -classpath $CLASSPATH:../../..:../../../build/classes -ea java_cup.Main gml.cup
javac -classpath $CLASSPATH:../../..:../../../build/classes -source 1.4 -d ../../../build/classes GMLReaderPlugin.java
javac -classpath $CLASSPATH:../../..:../../../build/classes -source 1.4 -d ../../../build/classes sym.java parser.java Yylex.java
