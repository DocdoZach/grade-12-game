public abstract class Entity{
    private String name;
    private int hp;
    private int maxHp;
    private int atk;
    private int def;
    private int spd;

    Entity(String name, int maxHp, int atk, int def, int spd) {
        this.name = name;
        this.hp = maxHp;
        this.maxHp = maxHp;
        this.atk = atk;
        this.def = def;
        this.spd = spd;
    }

    public String toString() {
        return name + " has " + hp + " HP remaining.";
    }

    public String getName() {
        return name;
    }

    public int getHp() {
        return hp;
    }

    public int getMaxHp() {
        return maxHp;
    }

    public int getAtk() {
        return atk;
    }

    public int getDef() {
        return def;
    }

    public int getSpd() {
        return spd;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setHp(int hp) {
        this.hp = hp;
    }

    public void setMaxHp(int maxHp) {
        this.maxHp = maxHp;
    }

    public void setAtk(int atk) {
        this.atk = atk;
    }

    public void setDef(int def) {
        this.def = def;
    }

    public void setSpd(int spd) {
        this.spd = spd;
    }
}
