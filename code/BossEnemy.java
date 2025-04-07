public class BossEnemy extends BigEnemy {
    private double criticalHit = 0.5;
    private double dodgeChance = 0.2;
    BossEnemy(String name, int maxHp, int atk, int def, int spd){
        super(name, maxHp, atk, def, spd);
    }
}