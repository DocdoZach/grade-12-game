public class Battle{
    public void battleCalculator(Entity attacker, Entity defender){
        int damage = attacker.getAtk();
        if(Math.random() <= attacker.getCritChance()){
            damage *= 1.5;
        }
        if(Math.random()<=attacker.getDodgeChance());
        else if(damage<=defender.getDef()){
            defender.setHp(defender.getHp()-1.0);
        } 
        else{
            defender.setHp(defender.getHp()-(damage-defender.getDef()));
        }
        if(defender.getHp()<=0){
            defender.setHp(0);
        }
    }
}