import java.util.Scanner;
public class Battle{//Temporary
    public static Scanner input = new Scanner(System.in);
    public static int abilityUses; //Coins is Temporary
    public static void battler(Player player,SmallEnemy enemy){
        System.out.println(enemy + " appeared!");
        String skillClass = "mage"/*Temporary*/,loseText = "defeated" + enemy;
        boolean skip, run;
        int coins = enemy.getCoinValue(), win = 0;
        abilityUses = 1;
        while(win==0){
            game.clear();
            System.out.printf("%s has %.1f/%.1f HP remaining.%n%s has %.1f/%.1f HP remaining.%nOptions:%n1. Attack%n2. Bag%n3. Use Ability%n4. Run%n", player, player.getHp(), player.getMaxHp(), enemy, enemy.getHp(), enemy.getMaxHp());
            skip = false;
            run = false;
            switch(Integer.parseInt(input.nextLine())) {
                case 1://Battle
                    win=battleEnemy(player,enemy);
                    break;
                case 2://Bag
                    System.out.println(player.getInv());
                    battleCalculator(enemy,player);
                    if(player.getHp() <= 0) win = -1;
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
                        battleCalculator(enemy,player);
                        if(player.getHp() <= 0) win = -1;
                    }
                    else{
                        System.out.println("You have no ability uses!");
                        skip = true;
                    }
                    break;
                case 4://Run
                    loseText="ran";
                    win = -1;
                    break;
                default:
                    skip = true; break;
            }
            if(skip) continue;
            if(win==1){
                System.out.printf("You defeated %s! They dropped %d coins!",enemy,coins);
                player.setBal(player.getBal()+coins);
            }else if(win==-1){
                System.out.printf("You %s and you dropped %d coins!",loseText,coins);
                player.setBal(player.getBal()-coins);
            }
        }
    }
    public static int battleEnemy(Entity player, Entity enemy){
        if(player.getSpd() >= enemy.getSpd()){
            battleCalculator(player, enemy);
            if(enemy.getHp() <= 0) return 1;
            battleCalculator(enemy,player);
            if(player.getHp() <= 0) return -1;
        }else{
            battleCalculator(enemy,player);
            if(player.getHp() <= 0) return -1;
            battleCalculator(player, enemy);
            if(enemy.getHp() <= 0) return 1;
        }
        return 0;
    }
    public static void battleCalculator(Entity attacker,Entity defender){
        double damage = attacker.getAtk();
        if(Math.random() <= attacker.getCritChance()){
            System.out.println("Critical Hit!");
            damage *= 1.5;
        }
        if(Math.random() <= attacker.getDodgeChance()) System.out.println("Attack dodged!");
        else {
            if(damage <= defender.getDef()){
                defender.setHp(defender.getHp() - 1.0);
                System.out.printf("%s dealt 1 HP to %s!%n",attacker,defender);
            }
            else {
                defender.setHp(defender.getHp() - (damage - defender.getDef()));
                System.out.printf("%s dealt %.0f HP to %s!%n",attacker,(damage - defender.getDef()),defender);
            }
        }
        if(defender.getHp() <= 0) defender.setHp(0);
    }
}
