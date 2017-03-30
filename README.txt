Software Stujardio 2017 Spring Assignment 03

1. Program Running Method : makefile (or already packed "PokeGen.jar")

2. Program Structure :

	The structure is based on lab03. Because I dont know how to parse the ".form" file, I set my own layouts.

	The implementation of pokemon individual data:

	PokemonIndividualData extends PokemonSpeciesData {
		private String nickname;
		private PokemonValueData individualValue;
		...
	}

	All the variables of PokemonSpeciesData is protected that we can call them straight in PokemonIndividualData.

	I also have the warnings when the individual values are not between 0 and 31, and when the nickname is blanked.

	I wanted to implement a function that we can load the created "morris_new_pokemon.json" to current slots. However, I don't have enough time to do it, so, u'll see a useless "load" button.

3. Problems Encountered :
	Gson is one of my nightmare since lab03, and that's one of the reason why I handed lab03 and this assignment so late.
	
	I can hardly get my classpath well read when file-makeing.

	And I found that I'm not good at managing the structure of the project, when wondering if to pack the codes into a function or not because I dont really know if it would be more efficient.
