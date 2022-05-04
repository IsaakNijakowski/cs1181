class Player extends Base {

    private int level = 0;
    private int gold = 0;
    private int exp = 0;
    private int skillPoints = 0;
    private int expToNextLevel = 200;
    private double goldMultiplier = 1; // Upgrade Index 11
    private double expMultiplier = 2; // Upgrade Index 12


    public Player(int health, int damage, int armor, int attackSpeed) {
        super(health, damage, armor, attackSpeed);
    }
    public void obtainLoot(int loot) {
        gold += loot*goldMultiplier;
        exp += loot*expMultiplier;
    }
    public void levelUp() {
        exp = 0;
        level++;
        skillPoints++;
        expToNextLevel = (int) (expToNextLevel*1.25);
    }
    // Getters
    public int getLevel() {
        return level;
    }
    public int getGold() {
        return gold;
    }
    public int getExp() {
        return exp;
    }
    public int getSkillPoints() {
        return skillPoints;
    }
    public int getMaxExp() {
        return expToNextLevel;
    }
    public double getGoldMultiplier() {
        return goldMultiplier;
    }
    public double getExpMultiplier() {
        return expMultiplier;
    }
    public void setGold(int amount) {
        gold = amount;
    }
    public void setSkillPoints(int amount) {
        skillPoints = amount;
    }
    public void setGoldMultiplier(double amount) {
        goldMultiplier = amount;
    }
    public void setExpMultiplier(double amount) {
        expMultiplier = amount;
    }
}
