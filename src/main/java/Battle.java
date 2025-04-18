package main.java;

import java.util.Scanner;
public class Battle{
    public static Scanner input = new Scanner(System.in);
    public static int totalWins;
    private static int win = 0;
    // Start a battle
    public static void battler(Player player,SmallEnemy enemy){
        game.clear();
        System.out.println(game.divider + enemy + " appeared!");
        String loseText = "got defeated by " + enemy;
        boolean skip;
        int abilityUses = 1, coins = enemy.getCoinValue(), plays;
        do{
            System.out.printf(game.divider + "%s has %.1f/%.1f HP remaining.%n%s has %.1f/%.1f HP remaining.%n" + game.divider + "Options:%n1. Attack%n2. Bag%n3. Use Ability%n4. Run%n"+game.divider, player, player.getHp(), player.getMaxHp(), enemy, enemy.getHp(), enemy.getMaxHp());
            skip = false;
            do{
                try{plays = Integer.parseInt(input.nextLine());game.clear();break;}
                catch(Exception e){plays = 0;System.out.print("Invalid input. ");}
            }while(true);
            switch(plays) {
                case 1://Battle
                    win = battleEnemy(player,enemy);
                    break;
                case 2://Bag
                    System.out.println(player.getInv());
                    battleCalculator(enemy,player);
                    if(player.getHp() <= 0) win = -1;
                    break;
                case 3://Ability
                    if(abilityUses > 0){
                        switch(player.getPlayerClass()){
                            case "mage":
                                enemy.setHp(enemy.getHp()/2.0);
                                System.out.println("Cut half of the enemy's current HP.");
                                break;
                            case "archer":
                                player.setHp((player.getMaxHp()/2.0)+player.getHp());
                                if(player.getHp()>=player.getMaxHp()) player.setHp(player.getMaxHp());
                                System.out.println("Healed half of your HP.");
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
                    loseText = "ran";
                    win = -1;
                    break;
                default:
                    skip = true;
                    break; 
            }
            if(skip) continue;
            if(win == 1){
                System.out.printf(game.divider + "You defeated %s! They dropped %d coins!%n" + game.divider,enemy,coins);
                player.setBal(player.getBal()+coins);
                enemy.setHp(enemy.getMaxHp()/2.0);
                totalWins++;
            }else if(win == -1){
                System.out.printf(game.divider + "You %s and you dropped %d coins!%n" + game.divider,loseText,coins);
                player.setBal(player.getBal()-coins);
                player.setHp(player.getMaxHp()/2.0);
            }
        }while(win == 0);
        System.out.print("Press enter to continue.");
        input.nextLine();
    }
    //Does Speed calculations for Battle
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
    //Calculates Battle Formula
    public static void battleCalculator(Entity attacker, Entity defender){
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
                System.out.printf("%s dealt %.1f HP to %s!%n",attacker,(damage - defender.getDef()),defender);
            }
        }
        if(defender.getHp() <= 0) defender.setHp(0);
    }
    public static boolean previousWin() {
        return win == 1;
    }
}
