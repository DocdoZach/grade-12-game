package main.java;
import java.util.*;

public class game {
    // Instantiate Helpful Variables
    public static Scanner input = new Scanner(System.in);
    public static String divider = "----------\n";
    // Instantiate player and shop
    public static Player player;
    public static Shop threeCents;
    public static ArrayList<Item> items;
    public static Item[] weapons;
    public static Item[] upgrades;
    //Main Function
    public static void main(String[] args) {
        // Instantiate players, items and shop
        player = new Player("none", 5, 3, 5, 3);
        Item hotCocoa = new Item("Hot Cocoa", 25, player.getMaxHp());
        Item apple = new Item("Apple", 3, 3);
        Item bread = new Item("Bread", 5, 5);
        Item salad = new Item("Salad", 7, 8);
        Item applePie = new Item("Apple Pie", 9, 10);
        items = new ArrayList<>();
        items.add(hotCocoa); items.add(apple); items.add(bread); items.add(salad); items.add(applePie);
        threeCents = new Shop(new Item[] {hotCocoa, applePie, salad, apple}, new int[] {1, 3, 5, 7});
        
        // Instantiate weapons
        Item stick = new Item("Stick", -1, 1);
        Item sword = new Item("Sword", -1, 2);
        Item staff = new Item("Staff", -1, 2);
        Item bow = new Item("Bow", -1, 2);
        weapons = new Item[]{stick,bow,staff,sword};
        Item powerStick = new Item("Power Stick", -2, 10);
        Item megaSword = new Item("Mega Sword", -2, 4);
        Item ultraStaff = new Item("Ultra Staff", -2, 4);
        Item superBow = new Item("Super Bow", -2, 4);
        upgrades = new Item[]{powerStick,superBow,ultraStaff,megaSword};
        
        // Name select
        System.out.print("Enter your name: ");
        player.setName(input.nextLine());
        // Class select
        player.setPlayerClass();
        mainMenu();
        // Debug Menu option select
        String[] debugText={"Test Battle","Test Main Menu","Test Shop","Change Weapon","Upgrade Weapon","Quit"};
        while(true) {
            clear();
            System.out.println(divider + "Tpircsdiov Debug Menu");
            // Switch Statement for Chosen Option
            switch (optionSelect(debugText,0)) {
                case 1 -> Battle.battler(new SmallEnemy("Small Dude", 5, 2, 1, 1));
                case 2 -> mainMenu();
                case 3 -> threeCents.menu();
                case 4 -> player.setPlayerClass();
                case 5 -> {
                    game.player.upgradeWeapon();
                    System.out.print("Press enter to continue.");
                    input.nextLine();
                }case 6 -> System.exit(0);
                default -> {}
            }
        }
    }
    //Main Menu
    public static void mainMenu() {
        String[] option={"Story Mode", "Endless Mode", "Quit"};
        do{
            clear();
            System.out.println(divider + "Welcome to Tpircsdiov");
            switch(optionSelect(option,1)){
                case 1: storyMode(); break;
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
        String currentLocation = "your home", extraDetails="";
        boolean searchedChest = false,x=true;
        reset();
        //Options
        String[] home = {"Open Chest", "Leave Home", "Check Bag"},
        village = {"Look Around", "Check Out Shop", "Leave Village", "Check Bag"},
        field = {"Attack Shed Fighter", "Go to Castle", "Check Bag"},
        castle = {"Fight Architect", "Fight his Guards", "Go to Shop", "Check Bag"};
        do{//Extra information about location
            switch(currentLocation) {
                case "your home" -> extraDetails = ". You look around and find a chest";
                case "the village" -> extraDetails = ". You see a field in the distance";
                case "an open field" -> extraDetails = ". You see a fighter near a shed, and also see a castle in the other direction";
                case "a castle" -> extraDetails = ". After defeating the guard, you see the architect.";
            }
            System.out.println(divider + "You are at " + currentLocation + extraDetails +".\nOptions: ");
            //Chooses info based on location
            switch(currentLocation) {
                case "your home" -> {// Home (Beginning Area)
                    switch(optionSelect(home,0)){
                        case 1 -> {
                            if(!searchedChest) {
                                System.out.println("You took a slice of bread.");
                                searchedChest = true;
                                player.getInv().add(items.get(2));
                            } else System.out.println("The chest is empty.");
                        }
                        case 2 -> currentLocation = "the village";
                        case 3 -> player.getBag();
                    }
                }
                case "the village" -> {// Village
                    switch(optionSelect(village,0)) {
                        case 1 -> System.out.println("You look around.");
                        case 2 -> game.threeCents.menu();
                        case 3 -> {
                            x=Battle.battler(new SmallEnemy("Small Dude", 5, 2, 1, 1));
                            currentLocation = "an open field";
                        }
                        case 4 -> player.getBag();
                    }
                }
                case "an open field" -> {// Field
                    switch(optionSelect(field, 0)) {
                        case 1 -> x = Battle.battler(new SmallEnemy("Shed Protector", 4, 3, 1, 1));
                        case 2 -> {
                            x = Battle.battler(new BigEnemy("Castle Guard", 6, 4, 2, 2));
                            currentLocation="a castle";
                        }
                        case 3 -> player.getBag();
                    }
                }
                case "a castle" -> {// Castle
                    switch(optionSelect(castle, 0)) {
                        case 1 -> {
                            x = Battle.battler(new BossEnemy("Architect", 15, 6, 4, 4));
                            currentLocation="abyss";
                        }
                        case 2 -> x = Battle.battler(new BigEnemy("Castle Guard", 6, 4, 2, 2));
                        case 3 -> game.threeCents.menu();
                        case 4 -> player.getBag();
                    }
                }
                default -> {// Winning Area
                    clear();
                    System.out.print(divider+"You succeeded in your goal. Press enter to continue.\n"+divider);
                    input.nextLine();
                    return;
                }
            }
        }while(x);
        clear();
        System.out.print(divider+"You failed your goal in defeating the Architect.\nPress Enter to Continue.\n"+divider);
        input.nextLine();
    }
    //Endless Mode Code
    public static void endlessMode(){
        double random = Math.random();
        int battlesSinceShop = 0;
        reset();
        do{
            clear();
            if(battlesSinceShop++%6 == 0) threeCents.menu();
            if(random < 0.5) Battle.battler(new SmallEnemy("Civilian", 5, 2, 1, 1));
            else if(random < 0.85) Battle.battler(new BigEnemy("Castle Guard", 8, 4, 3, 3));
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
        }clear();
        return choice;
    //Reset player and shop
    }public static void reset(){
        Battle.totalWins = 0;
        player.setHp(player.getMaxHp());
        player.setMaxHp(5);
        threeCents.resetStock();
        player.setBal(25);
        player.setSpd(3);
        player.resetBag();
    }
}