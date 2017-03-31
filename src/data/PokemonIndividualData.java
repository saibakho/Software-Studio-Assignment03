package data;


/**
 * Created by jerry on 2017/3/21.
 */
public class PokemonIndividualData extends PokemonSpeciesData
{
    //TODO create variables and constructor for this class
    private String nickname;
    private PokemonValueData individualValue;

    //HashMap<Integer,MoveData> learnMoveTable;
    public PokemonIndividualData(int id, String speciesName, String nickname,PokemonValueData valueData,String[] type)
    {
        super(id, speciesName, valueData, type);
        this.nickname = nickname;
        int[] valueArr = {0, 0, 0, 0, 0, 0};
        this.individualValue = new PokemonValueData(valueArr);
    }
    public void setSpeciesData(PokemonSpeciesData speciesData)
    {
        super.id = speciesData.getId();
        super.speciesName = speciesData.getSpeciesName();
        super.speciesValue = speciesData.getSpeciesValue();
        super.type = speciesData.getTypeArr();
    }
    public void setIndividualValue(PokemonValueData idValue)
    {
        individualValue = idValue;
    }
    public PokemonValueData getIndividualValue()
    {
        return individualValue;
    }
    public void setNickname(String setNM)
    {
        nickname = setNM;
    }
    public String getNickname()
    {
        return nickname;
    }
    @Override
    public String toString()
    {
        return "{\nPokemonSpeciesData : " + super.toString() + ",\nnickname : " + nickname + ",\nidValue : " + individualValue + "\n}";
    }
}
