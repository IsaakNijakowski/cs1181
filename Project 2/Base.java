class Base {

    protected int maxHealth;
    protected int damage; // Upgrade Index 1
    protected int health; // Upgrade Index 2
    protected int armor; // Upgrade Index 3
    protected int attackSpeed = 100; // Upgrade Index 4
    protected int speed = 0;

    public Base(Base e) {
        this.maxHealth = e.maxHealth;
        this.health = e.health;
        this.damage = e.damage;
        this.armor = e.armor;
        this.attackSpeed = e.attackSpeed;
    }
    public Base(int health, int damage, int armor, int attackSpeed) {
        maxHealth = health;
        this.health = health;
        this.damage = damage;
        this.armor = armor;
        this.attackSpeed = attackSpeed;
    }

    public void attack(Base opponent) {
        int actualDamage = (damage - opponent.armor < 0) ? 0 : damage - opponent.armor;
        opponent.health = opponent.health - actualDamage;
    }

    //Speed Setup
    public int getSpeed() {
        return speed;
    }
    public void speedPlus() {
        speed++;
    }
    public void resetSpeed() {
        speed = 0;
    }

    // Getters and setters
    public int getMaxHealth() {
        return maxHealth;
    }
    public int getDamage() {
        return damage;
    }
    public int getHealth() {
        return health;
    }
    public int getArmor() {
        return armor;
    }
    public int getAttackSpeed() {
        return attackSpeed;
    }
    public void setMaxHealth(int amount) {
        maxHealth = amount;
    }
    public void setDamage(int amount) {
        damage = amount;
    }
    public void setHealth(int amount) {
        health = amount;
    }
    public void setArmor(int amount) {
        armor = amount;
    }
    public void setAttackSpeed(int amount) {
        attackSpeed = amount;
    }
}
