package data;

/**
 * Created by jerry on 2017/3/1.
 */
public class PokemonValueData {
    private int[] valArray;

    public PokemonValueData() {
        valArray = new int[6];
    }

    public PokemonValueData(int[] rawData) {
        valArray = rawData.clone();
    }

    public int[] getValArray() {
        return valArray;
    }
    @Override
    public String toString()
    {
        String str = "[" + valArray[0];
        for (int i = 1; i < 6; i++)
            str += ", " + valArray[i];
        str += "]";
        return str;
    }
}