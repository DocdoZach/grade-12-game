public class Player extends Entity{
    private double critChance;
    private double dodgeChance;
    Player(String name, int maxHp, int atk, int def, int spd){
        super(name, maxHp, atk, def, spd);
        critChance = 0.25;
        dodgeChance = 0;
    }
    public double getCritChance(){
        return critChance;
    }
    public void setCritChance(double critChance){
        this.critChance=critChance;
    }
    public double getDodgeChance(){
        return dodgeChance;
    }
    public void setDodgeChance(double dodgeChance){
        this.dodgeChance=dodgeChance;
    }
}