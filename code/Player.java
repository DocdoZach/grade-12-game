public class Player extends Entity{
    private double criticalHit=0.25;
    private double dodgeChance=0;
    Player(String name,int health,int damage){
        super(name,health,damage);
    }
}