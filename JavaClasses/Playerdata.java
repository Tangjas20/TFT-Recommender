//package TFTBot;

public class Playerdata {

    // ATTRIBUTES ----------------------------------------------------------------------------------------------
    private int hp; // Player hitpoints
    private int lvl; // Player level
    private int xp; // Amount of current experience points
    private int gold; // Player gold
    private int[] stage = {1, 1}; // First number is the Stage, Second number is the round e.g. 2-3 is Stage 2, Round 3
    private int streak; // Current Player Streak, positive is wins, negative are losses

    // CONSTRUCTOR METHOD --------------------------------------------------------------------------------------
    public Playerdata() {
        this.hp = 100;
        this.lvl = 1;
        this.xp = 0;
        this.gold = 0;
        this.streak = 0;
    }

    // GETTER METHODS ------------------------------------------------------------------------------------------
    public int getHp() {
        return this.hp;
    }

    public int getLvl() {
        return this.lvl;
    }

    public int getXp() {
        return this.xp;
    }

    public int getGold() {
        return this.gold;
    }

    public int[] getStage() {
        return this.stage;
    }

    public int getStreak() {
        return this.streak;
    }

    public int getXpCap() { // XP cap for the current level, e.g. Level 5-->6 requires 20 total XP
        int[] xpCap = {0, 2, 6, 10, 20, 36, 56, 80, 100};
        return xpCap[this.getLvl()-1];
    }

    public int getXpDiff() { // XP required to hit the next level
        return (this.getXpCap()-this.getXp());
    }

    public int getInterestIncome() {
        if (this.getGold() < 10) return 0;
        else if (this.getGold() < 20) return 1;
        else if (this.getGold() < 30) return 2;
        else if (this.getGold() < 40) return 3;
        else if (this.getGold() < 50) return 4;
        return 5;
    }

    public int getPassiveIncome() { // Returns the amount of passive income based on stage
        if (this.stage[0] == 1 && this.stage[1] == 1) return 0;
        else if (this.stage[0] == 1 && this.stage[1] == 2) return 1;
        else if (this.stage[0] == 1 && this.stage[1] == 3) return 2;
        else if (this.stage[0] == 1 && this.stage[1] == 4) return 3;
        else if (this.stage[0] == 2 && this.stage[1] == 1) return 4;

        return 5;
    }

    public int getTotalWinIncome() { // Returns the total amount of income gained at the end of a round through a win
        int income = 1; // 1 Gold for winning combat
        
        int streak = 1; // Guaranteed at least 1 win

        // Sets win-streak
        if (this.streak > 0) streak = this.streak + 1; // If current streak exceeds 1

        // Generates gold for win streaking
        if (streak == 2 || streak == 3) income += 1;
        else if (streak == 4) income += 2;
        else if (streak >= 5) income += 3;

        // Passive Income
        income += this.getPassiveIncome();
        // Interest Income 
        this.gold = this.gold+1; // If you win a combat, you gain 1 gold before the end of the round
        income += this.getInterestIncome();
        this.gold = this.gold-1;

        return income;
    }

    public int getTotalLossIncome() { // Returns the total amount of income gained at the end of a round through a loss
        int income = 0;
        int streak = -1;

        // Sets loss-streak
        if (this.streak < 0) streak = this.streak - 1;

        // Generates gold for loss streaking
        if (streak == -2 || streak == -3) income += 1;
        else if (streak == -4) income += 2;
        else if (streak <= -5) income += 3;

        // Passive Income
        income += this.getPassiveIncome();
        // Interest Income
        income += this.getInterestIncome();

        return income;
    }

    // SETTER METHODS ------------------------------------------------------------------------------------------

    public void setHp(int newHp) {
        if (newHp > 100 || newHp < 0) System.out.println("ERROR: New HP cannot be above 100 or below 0");
        else this.hp = newHp;
    }

    public void setLvl(int newLvl) {
        if (newLvl < 0 || newLvl > 11) System.out.println("ERROR: New LVL cannot be above 11 or below 0");
        else this.lvl = newLvl;
    }

    public void setXp(int newXp) {
        this.xp = newXp;
    }

    public void setGold(int newGold) {
        if (newGold < 0) System.out.println("ERROR: New GOLD cannot be below 0");
        else this.gold = newGold;
    }

    public void setStage(int[] newStage) {
        if (newStage[0] < 0) System.out.println("ERROR: New STAGE cannot be less than 0");
        else if (newStage[1] < 0 || newStage[1] > 6) System.out.println("ERROR: New ROUND cannot be above 6 or below 0");
        else this.stage = newStage;
    }

    public void setStreak(int newStreak) {
        this.streak = newStreak;
    }

    // PRINTER FUNCTION ---------------------------------------------------------------------------------------
    
    public void printStats() {
        System.out.println("Player HP: \t" + this.getHp());
        System.out.println("Player LVL: \t" + this.getLvl());
        System.out.println("Player XP: \t" + this.getXp());
        System.out.println("Player T_XP: \t" + this.getXpCap()); // XP cap on the level
        System.out.println("Player R_XP: \t" + this.getXpDiff()); // Required Xp to level
        System.out.println("Player GOLD: \t" + this.getGold());
        System.out.println("Player STAGE: \t" + this.getStage()[0] + "-" + this.getStage()[1]);
        System.out.print("Player STREAK: \t");
        if (this.getStreak() >= 0) System.out.println("W-streak of " + this.getStreak());
        else System.out.println("L-streak of " + this.getStreak()*-1);
    }

    // MAIN METHOD --------------------------------------------------------------------------------------------
    public static void main(String[] args) {
        Playerdata p1 = new Playerdata();
        p1.setGold(29);
        int[] newStage = {4, 5};
        p1.setStage(newStage);
        p1.setStreak(2);
        //p1.printStats();
        System.out.print("WinStreak: \t");
        System.out.println(p1.getTotalWinIncome() + p1.getGold());
        System.out.print("LossStreak: \t");
        System.out.println(p1.getTotalLossIncome() + p1.getGold());
    }

}
