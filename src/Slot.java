
import javax.swing.*;
import java.awt.*;

public class Slot
{
	public JPanel panel;
	public JLabel imageLabel;
	//public PokemonSpeciesData speciesData;
	public PokemonIndividualData pokemonData;
	public int comboBoxIndex;

	public Slot() {
		int[] valueArr = {0, 0, 0, 0, 0, 0};
		String[] typeArr = {"-----"};
		this.pokemonData = new PokemonIndividualData(0, "----------", "----------",
						   new PokemonValueData(valueArr), typeArr);

		this.imageLabel = new JLabel();
		this.panel = new JPanel();
		this.panel.add(this.imageLabel);
	}
	public boolean isEmpty()
	{
		return comboBoxIndex == 0 ? true : false ;
	}
	@Override
	public String toString()
	{
		return "{\ncomboBoxIndex : " + comboBoxIndex + ",\nPokemonIDV : " + pokemonData.toString() + "\n}";
	}
}