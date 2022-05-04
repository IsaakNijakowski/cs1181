class Monster extends Base {

    private String monsterName;
    private int lootValue;

    public Monster(Monster m) {
        super(m);
        this.monsterName = m.monsterName;
        this.lootValue = m.lootValue;
    }
    public Monster(String monsterName, int health, int damage, int armor, int attackSpeed, int lootValue) {
        super(health, damage, armor, attackSpeed);
        this.monsterName = monsterName;
        this.lootValue = lootValue;
    }

    public int death() {
        return lootValue;
    }

    public String getName() {
        return monsterName;
    }

    public int getLoot() {
        return lootValue;
    }
}
