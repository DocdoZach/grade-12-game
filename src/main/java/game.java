package main.java;

import java.util.*;
public class game {
    // Instantiate Helpful Variables
    public static Scanner input = new Scanner(System.in);
    public static String divider = "----------\n";
    // Instantiate player and shop
    public static Player player;
    public static Shop threeCents;

    public static void main(String[] args) {
        // Instantiate players, items and shop
        player=new Player("none", 5, 5, 5, 3, 50, new ArrayList<>());
        Item hotCocoa = new Item("Hot Cocoa", 25, player.getMaxHp());
        Item apple = new Item("Apple", 3, 3);
        Item bread = new Item("Bread", 5, 5);
        Item salad = new Item("Salad", 7, 8);
        Item applePie = new Item("Apple Pie", 9, 10);
        threeCents=new Shop(hotCocoa, applePie, bread, apple, 1, 3, 5, 7);

        // Instantiate weapons
        Item stick = new Item("Stick", -1, 1);
        Item powerStick = new Item("Power Stick", -2, 10);
        Item sword = new Item("Sword", -1, 2);
        Item megaSword = new Item("Mega Sword", -2, 4);
        Item staff = new Item("Staff", -1, 2);
        Item ultraStaff = new Item("Ultra Staff", -2, 4);
        Item bow = new Item("Bow", -1, 2);
        Item superBow = new Item("Super Bow", -2, 4);

        // Name select
        System.out.print("Enter your name: ");
        player.setName(input.nextLine());
        // Class select
        String x, y;
        System.out.println("Classes: Mage, Warrior, Archer");
        do{
            System.out.print("Choose your class: ");
            x=input.nextLine();
            player.setItem(x.equals("fred")?stick:x.equals("archer")?bow:x.equals("mage")?staff:x.equals("warrior")?sword:player.getItem(0),0);
            if(!player.getItemName(0).equals("Fists")) break;
            clear();
            System.out.println("Not an option.");
        }while(player.getItemName(0).equals("Fists"));
        player.setPlayerClass();
        System.out.println("You set your class to "+player.getPlayerClass()+".");
        mainMenu();
        // Debug menu option select
        String[] text={"Test Battle","Test Main Menu","Test Shop","Change Weapon","Upgrade Weapon","Quit"};
        while(true) {
            clear();
            System.out.println(divider + "WIP Game Name Debug Menu");
            // Switch statement for Chosen Option
            switch (optionSelect(text,0)) {
                case 1: Battle.battler(new SmallEnemy("Small Dude", 5, 2, 1, 1)); break;
                case 2: mainMenu(); break;
                case 3: threeCents.menu(); break;
                case 4:
                    y=player.getItemName(0);
                    do{
                        System.out.print("Choose your class: ");
                        x=input.nextLine();
                        switch(x){
                            case "archer": player.setItem(bow,0); break;
                            case "mage": player.setItem(staff,0); break;
                            case "warrior": player.setItem(sword,0); break;
                            case "fred": player.setItem(stick,0); break;
                            default: clear(); System.out.println("Not an option.");
                        }
                        if(player.getItemName(0).equals(player.getItemName(0))){
                            clear();
                            System.out.println("Class already chosen.");
                        }
                    }while(player.getItemName(0).equals(y));
                    player.setPlayerClass();
                    System.out.println("You changed your class to "+player.getPlayerClass()+".\nPress enter to return to the main menu.");
                    input.nextLine();
                    break;
                case 5:
                    System.out.print("Your weapon "+((player.getItemValue(0)!=-2) ? "was" : "can't be")+" upgraded.\nPress enter to return to the main menu.");
                    player.setItem(player.getItemName(0).equals("Stick")?powerStick:player.getItemName(0).equals("Bow")?superBow:player.getItemName(0).equals("Staff")?ultraStaff:player.getItemName(0).equals("Sword")?megaSword:player.getItem(0),0);
                    input.nextLine();
                    break;
                case 6: System.exit(0);
                default: break;                    
            }
        }
    }
    //Main Menu
    public static void mainMenu(){
        String[] option={"Story Mode","Endless Mode","Quit"};
        do{
            clear();
            System.out.println(divider + "Welcome to (WIP Name)");
            switch(optionSelect(option,1)){
                case 1: storyMode();
                case 2: endlessMode(); break;
                case 3: System.exit(0);
                case 0: return;
                default: break;
            }
        }while(true);
    }
    //Story Mode
    public static void storyMode() {
        clear();
        System.out.println(divider+"Welcome to the world of Tpircsdiov. The Architect, a mad king, is on a quest to destroy the land of Yrome.\nYou must stop him before he takes over!");
        String currentLocation = "home";
        boolean searchedChest = false;
        //Options
        String[] home={"Open Chest", "Leave Home"},village={"Look Around", "Leave Village"};
        //Looking Around
        while(true){
            System.out.println(divider + "You are at " + currentLocation + ".\nOptions: ");
            switch(currentLocation) {
                case "home":
                    switch(optionSelect(home,0)){
                        
                        case 1:
                            game.clear();
                            if(!searchedChest) {
                                System.out.println("You took a slice of bread.");
                                searchedChest = true;
                            }else System.out.println("The chest is empty.");
                            break;
                        case 2:
                        game.clear();
                            currentLocation = "village";
                            break;
                    }
                    break;
                case "village":
                    switch(optionSelect(village,0)){
                        case 1:
                            game.clear();
                            System.out.println("You look around.");
                            break;
                        case 2:
                            currentLocation = "nowhere";
                            break;
                    }break;
                default:
                    game.clear();
                    System.out.print("None, since you fall to the abyss.");
                    input.nextLine();
                    return;
            }
        }
    }
    //Endless Mode Code
    public static void endlessMode(){
        int battlesSinceShop = 0;
        double random = Math.random();
        Battle.totalWins = 0;
        player.setBal(50);
        player.setHp(player.getMaxHp());
        threeCents.resetStock();
        do{
            clear();
            if(battlesSinceShop++%6 == 0) threeCents.menu();
            if(random < 0.5) Battle.battler(new SmallEnemy("Small Dude", 5, 2, 1, 1));
            else if(random < 0.85) Battle.battler(new BigEnemy("Guard", 8, 4, 3, 3));
            else Battle.battler(new BossEnemy("Architect", 12, 8, 6, 10));
        }while(Battle.previousWin());
        System.out.print(divider + "Final stats\nWins: " + Battle.totalWins + "\nPress enter to return to the main menu.");
        input.nextLine();
        clear();
    }
    // Clear screen method
    public static void clear() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    //Game Options Screen
    }public static int optionSelect(String[] options, int hidden){
        int choice = 0;
        for(int i=0;i<options.length;i++) System.out.println((i+1)+". "+options[i]);
        System.out.print(divider);
        while(true) {
            System.out.print("Select an option (1" + (options.length == 1 ? "" : ("-"+options.length)) + (hidden==2?", or 0 to exit":"")+"): ");
            try {
                choice = Integer.parseInt(input.nextLine());
                if((hidden>=1)&&choice==0) break;
                if (choice < 1 || choice > options.length) {
                    System.out.print("Invalid input. ");
                    continue;
                }
                break;
            } catch(Exception e) {System.out.print("Invalid input. ");}
        }
        return choice;
    }
}