package data;

/**
 * Created by jerry on 2017/3/1.
 */

public class PokemonSpeciesData// implements Comparable<PokemonSpeciesData>
{
    protected int id;
    protected String speciesName;
    protected PokemonValueData speciesValue;
    protected String[] type;

    //HashMap<Integer,MoveData> learnMoveTable;
    public PokemonSpeciesData(int id, String speciesName,PokemonValueData valueData,String[] type)
    {
        this.id = id;
        this.speciesName = speciesName;
        this.speciesValue = valueData;
        this.type = type;
    }

    public int getId() {
        return id;
    }

    public String getSpeciesName() {
        return speciesName;
    }

    public PokemonValueData getSpeciesValue() {
        return speciesValue;
    }
    public String[] getTypeArr()
    {
        return type;
    }
    public String getType()
    {
        String s = type[0];
        for(int i = 1; i < type.length; i++)
            s += " / " + type[i];
        return s;
    }
    @Override
    public String toString()
    {
        return "{\nid : " + id + ",\nspeciesName : " + speciesName + ",\ntype : " + this.getType() + ",\nvalue : " + speciesValue + "\n}";
    }
}

//https://wiki.52poke.com/wiki/SpeciesValue