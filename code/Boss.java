public class Boss extends Entity{
    private double criticalHit=0.5;
    private double dodgeChance=0.2;
    Boss(String name,int health,int damage){
        super(name,health,damage);
    }
}