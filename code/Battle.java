import java.util.Scanner;
public class Battle{
    public void battler(Entity player,Entity enemy){
        Scanner input = new Scanner(System.in);
        int coins = 1, abilityUses = 1;//Temporary
        String skillClass="mage";
        System.out.println(enemy.getName()+" appeared!");
        while((player.getHp()>0)&&(enemy.getHp()>0)){
            System.out.print("Options:\n1. Attack\n2. Bag\n3. Use Ability\n4. Run");
            switch(Integer.parseInt(input.nextline())) {
                case 1://Battle
                    battleCalculator(player, enemy);
                    break;
                case 2://Bag
                    break;
                case 3://Ability
                    if(abilityUses>0){
                        switch(skillClass){
                            case "mage":
                                enemy.setHp(enemy.getHp()/2.0);
                                break;
                            case "barb": break;
                            default: break;
                        }
                        abilityUses--;
                    }
                    else System.out.println("You have no ability uses!");
                    break;
                case 4://Run
                    player.setHp(0);
                    break;
                default:
                    break;
            }
            if(player.getHp()<=0) {
                System.out.printf("You lost and dropped %d coins!",coins);
                coins-=2;
                break;
            }
            battleCalculator(enemy,player);
            if(enemy.getHp()<=0) {
                System.out.printf("You defeated %s and they dropped %d coins!",enemy.getName(),coins);
                coins+=2;
                break;
            }
        }
    }
    public void battleCalculator(Entity attacker,Entity defender){
        double damage = attacker.getAtk();
        if(Math.random()<=attacker.getCritChance()){
            System.out.println("Critical Hit!");
            damage *= 1.5;
        }
        if(Math.random()<=attacker.getDodgeChance()) System.out.println("Attack dodged!");
        else if(damage<=defender.getDef()) defender.setHp(defender.getHp() - 1.0);
        else defender.setHp(defender.getHp() - (damage - defender.getDef()));
        if(defender.getHp()<=0) defender.setHp(0);
    }
}