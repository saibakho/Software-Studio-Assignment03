# for windows

CPATH = -classpath .;.\gson.jar
SRC   = *.java
DUMP  = *.class
DEL   = del

all: $(SRC)
	javac $(CPATH) $(SRC)
	jar -cfm PokeGen.jar manifest.mf *.class
	java -jar PokeGen.jar
clean: $(DUMP)
	$(DEL) $(DUMP)