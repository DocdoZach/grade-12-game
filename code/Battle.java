import java.util.Scanner;
public class Battle{//Temporary
    public Scanner input = new Scanner(System.in);
    public int coins = 1, abilityUses; //Coins is Temporary
    public boolean battler(Entity player,Entity enemy){
        System.out.println(enemy.getName() + " appeared!");
        String skillClass = "mage"; //Temporary
        boolean skip;
        abilityUses = 1;
        while((player.getHp()>0)&&(enemy.getHp()>0)){
            game.clear();
            System.out.print("Options:\n1. Attack\n2. Bag\n3. Use Ability\n4. Run");
            skip = false;
            switch(Integer.parseInt(input.nextLine())) {
                case 1://Battle
                    battleCalculator(player, enemy);
                    break;
                case 2://Bag
                    break;
                case 3://Ability
                    if(abilityUses > 0){
                        switch(skillClass){
                            case "mage":
                                enemy.setHp(enemy.getHp()/2.0);
                                break;
                            default: break;
                        }
                        abilityUses--;
                    }
                    else{
                        System.out.println("You have no ability uses!");
                        skip = true;
                    }
                    break;
                case 4://Run
                    player.setHp(0);
                    break;
                default:
                    break;
            }
            if(skip) continue;
            if(player.getHp() <= 0) {
                System.out.printf("You lost and dropped %d coins!",coins);
                coins -= 2;
                return true;
            }
            battleCalculator(enemy,player);
            if(enemy.getHp() <= 0) {
                System.out.printf("You defeated %s and they dropped %d coins!",enemy.getName(),coins);
                coins += 2;
                return false;
            }
        }
        return false; //Fallback
    }
    public void battleCalculator(Entity attacker,Entity defender){
        double damage = attacker.getAtk();
        if(Math.random() <= attacker.getCritChance()){
            System.out.println("Critical Hit!");
            damage *= 1.5;
        }
        if(Math.random() <= attacker.getDodgeChance()) System.out.println("Attack dodged!");
        else if(damage <= defender.getDef()) defender.setHp(defender.getHp() - 1.0);
        else defender.setHp(defender.getHp() - (damage - defender.getDef()));
        if(defender.getHp() <= 0) defender.setHp(0);
    }
}
