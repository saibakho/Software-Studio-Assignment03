
import data.*;
import sprite.PokemonSprite;

import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;


import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.util.ArrayList;
import java.lang.Integer;
import java.lang.NullPointerException;

/**
 * Created by jerry on 2017/3/19.
 */
public class PokeGen
{
    private JComboBox<String> speciesComboBox;
    // button
    private JButton save;
    private JButton load;
    private JButton delete;
    // label
    private JLabel warning;
    private JLabel speciesLabel;
    private JLabel imageLabel;
    private JLabel nameLabel;
    private JLabel typeLabel;
    // text fields
    private JTextField nickname;
    // lists
    private ArrayList<Slot> slotList;
    private ArrayList<JTextField> statTextList;
    private ArrayList<JLabel> statLabels;
    private ArrayList<String> statString;
    Pokedex pokedex;
    private int curSlotIndex;
    

    public PokeGen()
    {
        // UI Manager
        try {
            UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
        } catch (Exception e) {
            e.printStackTrace(System.err);
        }
    //=========================================================================
        pokedex = new Pokedex("./json/pokemonData.json");
        slotList = new ArrayList<>();
        statLabels = new ArrayList<>();
        statString = new ArrayList<>();
        statTextList = new ArrayList<>();
        PokemonSpeciesData initPokemon = pokedex.getPokemonData(0);
        // stats
    //=========================================================================
        statString.add(" HP      : ");      statString.add(" Attack  : ");
        statString.add(" Defense : ");      statString.add(" SP.ATK  : ");
        statString.add(" SP.DEF  : ");      statString.add(" Speed   : ");
        JLabel hp       = new JLabel(" HP      : (--)");     statLabels.add(hp);
        JLabel attack   = new JLabel(" Attack  : (--)");     statLabels.add(attack);
        JLabel defense  = new JLabel(" Defense : (--)");     statLabels.add(defense);
        JLabel sp_atk   = new JLabel(" SP.ATK  : (--)");     statLabels.add(sp_atk);
        JLabel sp_def   = new JLabel(" SP.DEF  : (--)");     statLabels.add(sp_def);
        JLabel speed    = new JLabel(" Speed   : (--)");     statLabels.add(speed);
        for (int i = 0; i < 6; i++) {
            statLabels.get(i).setBounds(70+((i<3)?0:200), 170+(i%3)*40, 150, 30);
            statLabels.get(i).setFont(new Font("Consolas", Font.PLAIN, 16));
        }
    //=========================================================================
        // 6 slots
        for (int i = 0; i < 6; i++) {
            Slot tempSLT = new Slot();
            tempSLT.panel.setBounds(10, 10+i*50, 50, 50);
            if (i == 0) {
                curSlotIndex = 0;
                tempSLT.panel.setBorder(BorderFactory.createBevelBorder(1));
            } else {
                tempSLT.panel.setBorder(BorderFactory.createEtchedBorder());
            }   slotList.add(tempSLT);
            JPanel panel = slotList.get(i).panel;
            int cur = i;
            slotList.get(i).panel.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    if (saveData(curSlotIndex) != -999) {
                        panel.setBorder(BorderFactory.createBevelBorder(1));
                        curSlotIndex = cur;
                        loadData(curSlotIndex);
                        for (int j = 0; j < 6; j++)
                            if (j != cur)
                                slotList.get(j).panel.setBorder(BorderFactory.createEtchedBorder());
                    }
                }
            });
        }
    //=========================================================================
        // label
        nameLabel = new JLabel("Nickname : ");
        nameLabel.setBounds(70, 90, 200, 30);
        nameLabel.setFont(new Font("Consolas", Font.PLAIN, 16));

        typeLabel = new JLabel("Type     :    " + "-----");
        typeLabel.setBounds(70, 50, 400, 30);
        typeLabel.setFont(new Font("Consolas", Font.PLAIN, 16));
    
        speciesLabel   = new JLabel("Species  : ");
        speciesLabel.setBounds(70, 10, 100, 30);
        speciesLabel.setFont(new Font("Consolas", Font.PLAIN, 16));
    //=========================================================================
        // warning
        warning = new JLabel();
        warning.setBounds(70, 130, 390, 30);
        warning.setFont(new Font("Consolas", Font.PLAIN, 16));
        // text fields
        nickname = new JTextField("----------");
        nickname.setBounds(180, 90, 280, 30);
        nickname.setFont(new Font("Consolas", Font.PLAIN, 16));

        for (int i = 0; i < 6; i++) {
            JTextField tempTF = new JTextField( "" + 0 );
            tempTF.setBounds(220+((i<3)?0:200), 170+(i%3)*40, 40, 30);
            tempTF.setFont(new Font("Consolas", Font.PLAIN, 16));
            statTextList.add(tempTF);
        }
    //=========================================================================
        // comboBox
        speciesComboBox = new JComboBox<>();
        speciesComboBox.setBounds(180, 10, 280, 30);
        speciesComboBox.setFont(new Font("Consolas", Font.PLAIN, 16));
        speciesComboBox.addItem("-- : ----------");
        for (int i = 0; i < pokedex.getPokemonSize(); i++)
            speciesComboBox.addItem(pokedex.getPokemonData(i).getId() + " : " + pokedex.getPokemonData(i).getSpeciesName());
        speciesComboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //TODO update fields when select items in combobox
                int curIndex = speciesComboBox.getSelectedIndex();
                if (curIndex != 0){
                    PokemonSpeciesData curPokemon = pokedex.getPokemonData(curIndex-1);
                    typeLabel.setText("Type     :    " + curPokemon.getType());
                    slotList.get(curSlotIndex).pokemonData.setSpeciesData(curPokemon);
                    slotList.get(curSlotIndex).comboBoxIndex = curIndex;
                    //if (slotList.get(curSlotIndex).isEmpty()) {
                        nickname.setText(curPokemon.getSpeciesName());
                        slotList.get(curSlotIndex).imageLabel.setIcon(new ImageIcon(PokemonSprite.getSprite(curIndex-1)));
                        for (int i = 0; i < 6; i++)
                            statLabels.get(i).setText(statString.get(i) + "(" + curPokemon.getSpeciesValue().getValArray()[i] + ")");
                    //}
                }
            }
        });
    //=========================================================================
        // button
        load = new JButton("Load");
        load.setBounds(340, 290, 120, 30);
        load.setFont(new Font("Consolas", Font.PLAIN, 16));
        load.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // function not available yet
                warning.setText("function not available yet. :(");
                warning.setForeground(Color.red);/*
                try {
                    loadFromJson();
                } catch (IOException exc) {
                    exc.printStackTrace();
                }*/
            }
        });
        save = new JButton("Save");
        save.setBounds(210, 290, 120, 30);
        save.setFont(new Font("Consolas", Font.PLAIN, 16));
        save.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                saveData(curSlotIndex);
                try {
                    saveToJson();
                } catch (IOException exc) {
                    exc.printStackTrace();
                }
            }
        });
        delete = new JButton("Delete");
        delete.setBounds(70, 290, 130, 30);
        delete.setFont(new Font("Consolas", Font.PLAIN, 16));
        delete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                blocksInit(curSlotIndex);
            }
        });
    }
    private void blocksInit(int curSlotIndex)
    {
        slotList.get(curSlotIndex).imageLabel.setIcon(null);
        speciesComboBox.setSelectedIndex(0);
        warning.setText("");
        typeLabel.setText("Type     :    " + "-----");
        nickname.setText("----------");
        for (int i = 0; i < 6; i++) {
            statLabels.get(i).setText(statString.get(i) + "(--)");
            statTextList.get(i).setText("" + 0);
        }
    }
    private void loadData(int curIndex)
    {
        Slot curSlot = slotList.get(curIndex);
        //System.out.println(curSlot);  // for debug
        if (curSlot.isEmpty()) {
            blocksInit(curIndex);
        } else {
            slotList.get(curIndex).imageLabel.setIcon(new ImageIcon(PokemonSprite.getSprite(curSlot.comboBoxIndex-1)));
            speciesComboBox.setSelectedIndex(curSlot.comboBoxIndex);
            typeLabel.setText("Type     :    " + curSlot.pokemonData.getType());
            nickname.setText(curSlot.pokemonData.getNickname());
            for (int i = 0; i < 6; i++)
                statTextList.get(i).setText("" + curSlot.pokemonData.getIndividualValue().getValArray()[i]);
        }
        
    }
    private int saveData(int curIndex)
    {
        if (slotList.get(curIndex).comboBoxIndex > 0) {
            int[] statList = new int[6];
            for (int i = 0; i < 6; i++) {
                if (statTextList.get(i).getText().equals("")) {
                    warning.setText("Error : values can't be blanked.");
                    warning.setForeground(Color.red);
                    return -999;
                }   int tempVal = Integer.parseInt( statTextList.get(i).getText() );
                if (tempVal > 31 || tempVal < 0) {
                    warning.setText("Error : values must between 0 to 31.");
                    warning.setForeground(Color.red);
                    return -999;
                }   statList[i] = tempVal;
            }
            String tempNM = nickname.getText();
            if (tempNM.equals("")) {
                warning.setText("Error : Nickname can't be blanked.");
                warning.setForeground(Color.red);
                return -999;
            }   slotList.get(curIndex).pokemonData.setNickname(tempNM);
            slotList.get(curIndex).pokemonData.setIndividualValue(new PokemonValueData(statList));
        }
        return 0;
    }
    private void saveToJson() throws IOException
    {
        //Create JsonWriter with fileName
        JsonWriter writer = new JsonWriter(new FileWriter("json/morris_new_pokemon.json"));
        //create a gson object
        Gson gson = new Gson();
    //===============================================================================
        ArrayList<PokemonIndividualData> createdList = new ArrayList<>();
        for (int i = 0; i < 6; i++)
            if (!slotList.get(i).isEmpty())
                createdList.add(slotList.get(i).pokemonData);
        gson.toJson(createdList.toArray(),PokemonIndividualData[].class,writer);
    //===============================================================================
        //close the writer, very important!!!
        writer.close();
    }
    public void loadFromJson() throws IOException
    {
        //use JsonReader to read json file
        JsonReader reader = new JsonReader(new FileReader("json/morris_new_pokemon.json"));
        //create a gson object
        Gson gson = new Gson();
    //===============================================================================
        PokemonIndividualData[] dataArray = gson.fromJson(reader,PokemonIndividualData[].class);
        for (int i = 0; i < dataArray.length; i++) {
            blocksInit(i);
            slotList.get(i).pokemonData = dataArray[i];
        }
    //===============================================================================
        //close the reader to stop reading file
        reader.close();
    }
    public static void main(String[] args)
    {
        JFrame frame = new JFrame("PokeGen");
        PokeGen init = new PokeGen();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(490, 385);
        frame.setLayout(null);
        frame.setVisible(true);
        // add components
        frame.add(init.speciesLabel);
        frame.add(init.speciesComboBox);
        frame.add(init.nameLabel);
        frame.add(init.nickname);
        frame.add(init.typeLabel);
        frame.add(init.warning);
        frame.add(init.save);
        frame.add(init.load);
        frame.add(init.delete);
        for (int i = 0; i < 6; i++) {
            frame.add(init.slotList.get(i).panel);
            frame.add(init.statLabels.get(i));
            frame.add(init.statTextList.get(i));
        }
    }
}

