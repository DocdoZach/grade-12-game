package main.java;

public class BossEnemy extends BigEnemy {
    private double dodgeChance;
    BossEnemy(String name, int maxHp, int atk, int def, int spd){
        super(name, maxHp, atk, def, spd);
        dodgeChance = 0.2;
    }
    public double getDodgeChance(){
        return dodgeChance;
    }
    public void setDodgeChance(double dodgeChance){
        this.dodgeChance=dodgeChance;
    }
}