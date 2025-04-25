package main.java;

public class Battle{
    public static int totalWins;
    private static int win = 0;
    // Start a battle
    public static boolean battler(SmallEnemy enemy){
        game.clear();
        System.out.println(game.divider + enemy + " appeared!");
        String loseText = "got defeated by " + enemy;
        String[] battleOptions={"Attack","Bag","Use Ability","Run"};
        boolean skip;
        int abilityUses = 1, coins = enemy.getCoinValue();
        win = 0;
        do{
            System.out.printf(game.divider + "%s has %.1f/%.1f HP remaining.%n%s has %.1f/%.1f HP remaining.%n" +game.divider, game.player, game.player.getHp(), game.player.getMaxHp(), enemy, enemy.getHp(), enemy.getMaxHp());
            skip = false;
            switch(game.optionSelect(battleOptions,0)) {
                case 1 -> //Battle
                    win = battleEnemy(game.player,enemy);
                case 2 -> { //Bag
                    skip=game.player.getBag();
                    if(skip) break;
                    battleCalculator(enemy,game.player);
                    if(game.player.getHp() <= 0) win = -1;
                }case 3 -> { //Ability
                    if(abilityUses > 0){
                        switch(game.player.getPlayerClass()){
                            case "mage" -> {
                                enemy.setHp(enemy.getHp()/2.0);
                                System.out.println("Cut half of the enemy's current HP.");
                            }
                            case "archer" -> {
                                game.player.setHp((game.player.getMaxHp()/2.0)+game.player.getHp());
                                if(game.player.getHp()>=game.player.getMaxHp()) game.player.setHp(game.player.getMaxHp());
                                System.out.println("Healed half of your HP!");
                            }
                            case "warrior" -> {
                                battleCalculator(game.player,enemy);
                                battleCalculator(game.player,enemy);
                                System.out.println("You used Double Attack!");
                                if(enemy.getHp() <= 0) win = 1;
                            }
                            default -> {
                                System.out.println("Due to being simple, you have no ability!");
                                skip = true;
                            }
                        }
                        abilityUses--;
                        if(skip) break;
                        battleCalculator(enemy,game.player);
                        if(game.player.getHp() <= 0) win = -1;
                    }
                    else{
                        System.out.println("You have no ability uses!");
                        skip = true;
                    }
                }case 4 -> { //Run
                    loseText = "ran";
                    win = -1;
                }default -> skip = true; 
            }
            if(skip) continue;
            if(win == 1){// Winning against ememy
                System.out.printf(game.divider + "You defeated %s! They dropped %d coins!%n" + game.divider + "Press enter to continue.",enemy,coins);
                game.player.setBal(game.player.getBal()+coins);
                enemy.setHp(enemy.getMaxHp()/2.0);
                totalWins++;
                game.input.nextLine();
                return true;
            }else if(win == -1){ // Losing against Enemy
                System.out.printf(game.divider + "You %s and you dropped %d coins!%n" + game.divider + "Press enter to continue.",loseText,coins);
                game.player.setBal(game.player.getBal()-coins);
                game.player.setHp(game.player.getMaxHp()/2.0);
                game.input.nextLine();
                return false;
            }
        }while(win == 0);
        return false;
    }
    //Does Speed calculations for battle
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
        }return 0;
    }
    //Calculates Battle Formula
    public static void battleCalculator(Entity attacker, Entity defender){
        double damage = attacker.getAtk();
        if(attacker.equals(game.player)) damage+=game.player.getItem(0).getStat();
        if(Math.random() <= attacker.getCritChance()) {
            System.out.println("Critical Hit!");
            damage *= 1.5;
        }
        if(Math.random() <= attacker.getDodgeChance()) System.out.println("Attack dodged!");
        else {
            if(damage <= defender.getDef()) {
                defender.setHp(defender.getHp() - 1.0);
                System.out.printf("%s dealt 1 HP to %s!%n",attacker,defender);
            }else {
                defender.setHp(defender.getHp() - (damage - defender.getDef()));
                System.out.printf("%s dealt %.1f HP to %s!%n",attacker,(damage - defender.getDef()),defender);
            }
        }
        if(defender.getHp() <= 0) defender.setHp(0);
    }
    public static boolean previousWin() {return win == 1;}
}
