package main.java;

public abstract class Entity{
    private String name;
    private double hp;
    private double maxHp;
    private int atk;
    private int def;
    private int spd;
    Entity(String name, double maxHp, int atk, int def, int spd) {
        this.name = name;
        this.hp = maxHp;
        this.maxHp = maxHp;
        this.atk = atk;
        this.def = def;
        this.spd = spd;
    }
    public String toString() {
        return name;
    }
    public String getName() {
        return name;
    }
    public double getHp() {
        return hp;
    }
    public double getMaxHp() {
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
    public double getCritChance(){
        return 0;
    }
    public double getDodgeChance(){
        return 0;
    }
    public void setName(String name) {
        this.name = name;
    }
    public void setHp(double hp) {
        this.hp = hp;
    }
    public void setMaxHp(double maxHp) {
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
