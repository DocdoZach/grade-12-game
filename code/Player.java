public class Player extends Entity{
    private double criticalHit = 0.25;
    private double dodgeChance = 0;
    Player(String name, int maxHp, int atk, int def, int spd){
        super(name, maxHp, atk, def, spd);
    }
}