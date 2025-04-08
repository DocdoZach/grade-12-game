public class Battle{
    public void battleCalculator(Entity attacker,Entity defender){
        int damage=attacker.getAtk();
        double health=defender.getHp();
        if(Math.random()<=attacker.getCritChance()){
            damage*=1.5;
        }
        if(damage<=defender.getDef()){
            defender.setHp(health-1.0);
        } 
        else{
            defender.setHp(health-(damage-defender.getDef()));
        }
        if(defender.getHp()<=0){
            defender.setHp(0);
        }
    }
}
