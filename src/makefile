# for windows

CPATH = -classpath .;.\gson.jar
SRC   = sprite\PokemonSprite.java \
		data\PokemonIndividualData.java \
		data\PokemonValueData.java \
		data\PokemonSpeciesData.java \
		*.java
DUMP  = sprite\PokemonSprite.class \
		data\PokemonIndividualData.class \
		data\PokemonValueData.class \
		data\PokemonSpeciesData.class \
		*.class
# windows command
DEL   = del


all: $(SRC)
	javac $(CPATH) $(SRC)
	jar -cfm PokeGen.jar manifest.mf $(DUMP)
	$(DEL) $(DUMP)
	java -jar PokeGen.jar
clean: $(DUMP)
	$(DEL) $(DUMP)