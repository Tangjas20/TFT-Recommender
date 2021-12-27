import java.util.Scanner;

public class Champion {

    // ATTRIBUTES ----------------------------------------------------------------------------------------------
    private String name;
    private int cost;
    private String[] traits;

    // CONSTRUCTOR METHOD --------------------------------------------------------------------------------------
    public Champion(String name, int cost, String[] traits) {
        this.name = name;
        this.cost = cost;
        this.traits = traits;
    }

    // GETTER METHODS ------------------------------------------------------------------------------------------
    public String getName() {
        return this.name;
    }

    public int getCost() {
        return this.cost;
    }

    public String[] getTraits() {
        return this.traits;
    }

    // FUNCTIONAL METHODS --------------------------------------------------------------------------------------
    public Boolean equals(Champion champ) {
        if (this.getName().equals(champ.getName())) return true;
        return false;
    }

    // PRINTER FUNCTION ---------------------------------------------------------------------------------------

    public void printTraits() {
        for (String trait: this.traits) System.out.println(trait);
    }

    public static void main(String[] args) {
        String[] jinx_trait = {"Scrap", "Twinshot", "Sister"};
        Champion jinx = new Champion("Jinx", 5, jinx_trait);
        System.out.println(jinx.getName());
    }

}
