package main.java;

public class BigEnemy extends SmallEnemy{
    private double critChance;
    BigEnemy(String name, int maxHp, int atk, int def, int spd) {
        super(name, maxHp, atk, def, spd);
        critChance = 0.5;
    }
    public double getCritChance(){
        return critChance;
    }
    public void setCritChance(double critChance){
        this.critChance=critChance;
    }
}