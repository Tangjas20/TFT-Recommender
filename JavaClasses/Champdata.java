import java.util.*;
import java.io.*;

public class Champdata {

    // ATTRIBUTES ----------------------------------------------------------------------------------------------
    private List<Champion> champdata;


    // CONSTRUCTOR METHOD --------------------------------------------------------------------------------------
    public Champdata() { // When constructor generate the total list of all Champion Data (Name, Cost, Traits)
        this.champdata = loadChamp();
    }

    // FUNCTIONAL METHODS --------------------------------------------------------------------------------------

    public List<Champion> loadChamp() { // Goes into Champ.txt and creates all of the Champion objects to store data

        // DATA IS STORED IN TXT FILE AS '[Name],[Cost],[Trait1 Trait2 Trait3]'
        String path = "Champ.txt";

        List<Champion> champdata = new ArrayList<Champion>(); 

        try {
            File file = new File(path);
            Scanner sc = new Scanner(file);

            while (sc.hasNextLine()) {
                String data = sc.nextLine();
                String[] dataSplit = data.split(",");

                String name = dataSplit[0];
                int cost = Integer.parseInt(dataSplit[1]);
                String[] traits = dataSplit[2].split(" ");
                
                Champion addChamp = new Champion(name, cost, traits);
                champdata.add(addChamp);

            }
        } catch (FileNotFoundException e) {
            System.out.println("ERROR: File could not be found");
        }

        return champdata;
    }

    public List<Champion> champsFromTrait(String trait) { // Returns a list of all champions with a specific trait
        List<Champion> champtraits = new ArrayList<Champion>(); 

        for (Champion champ: champdata) if (Arrays.asList(champ.getTraits()).contains(trait)) champtraits.add(champ);

        return champtraits;
    }

    public List<Champion> similarTraits(Champion champion) { // Returns a list of all champions who share a trait with given champion
        List<Champion> champTraits = new ArrayList<Champion>(); 
        
        String[] traits = champion.getTraits();
        for (String trait: traits) {
            for (Champion champ: this.champsFromTrait(trait)) {
                if (!Arrays.asList(champTraits).contains(champ) && !champion.equals(champ)) champTraits.add(champ);
            }
        }

        return champTraits;
    }

    // PRINTER FUNCTION ---------------------------------------------------------------------------------------
    public static void printNames(List<Champion> champs) {
        for (Champion champ: champs) System.out.println(champ.getName());
    }
    public static void main(String[] args) {
        Champdata champls = new Champdata();
        String[] traits = {"Scrap", "Sister", "Twinshot"};
        Champion jinx = new Champion("Jinx", 5, traits);
    }
}
