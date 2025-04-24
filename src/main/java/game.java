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

    public static void main(String[] args) {
        // Instantiate players, items and shop
        player = new Player("none", 5, 3, 5, 3, 50, new ArrayList<>());
        Item hotCocoa = new Item("Hot Cocoa", 25, player.getMaxHp());
        Item apple = new Item("Apple", 3, 3);
        Item bread = new Item("Bread", 5, 5);
        Item salad = new Item("Salad", 7, 8);
        Item applePie = new Item("Apple Pie", 9, 10);
        items = new ArrayList<>();
        items.add(hotCocoa); items.add(apple); items.add(bread); items.add(salad); items.add(applePie);
        threeCents = new Shop(hotCocoa, applePie, bread, apple, 1, 3, 5, 7);

        // Instantiate weapons
        Item stick = new Item("Stick", -1, 1);
        Item sword = new Item("Sword", -1, 2);
        Item staff = new Item("Staff", -1, 2);
        Item bow = new Item("Bow", -1, 2);
        Item powerStick = new Item("Power Stick", -2, 10);
        Item megaSword = new Item("Mega Sword", -2, 4);
        Item ultraStaff = new Item("Ultra Staff", -2, 4);
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
            if(!player.getItem(0).getName().equals("Fists")) break;
            clear();
            System.out.println("Not an option.");
        }while(player.getItem(0).getName().equals("Fists"));
        player.setPlayerClass();
        System.out.println("You set your class to "+player.getPlayerClass()+".");
        mainMenu();
        // Debug Menu option select
        String[] text={"Test Battle","Test Main Menu","Test Shop","Change Weapon","Upgrade Weapon","Quit"};
        while(true) {
            clear();
            System.out.println(divider + "Tpircsdiov Debug Menu");
            // Switch Statement for Chosen Option
            switch (optionSelect(text,0)) {
                case 1: Battle.battler(new SmallEnemy("Small Dude", 5, 2, 1, 1)); break;
                case 2: mainMenu(); break;
                case 3: threeCents.menu(); break;
                case 4:
                    y=player.getItem(0).getName();
                    do{
                        System.out.print("Choose your class: ");
                        x=input.nextLine();
                        switch(x){
                            case "archer" -> player.setItem(bow,0);
                            case "mage" -> player.setItem(staff,0);
                            case "warrior" -> player.setItem(sword,0);
                            case "fred" -> player.setItem(stick,0);
                            default -> {clear(); System.out.println("Not an option.");}
                        }
                        if(player.getItem(0).getName().equals(player.getItem(0).getName())){
                            clear();
                            System.out.println("Class already chosen.");
                        }
                    }while(player.getItem(0).getName().equals(y));
                    player.setPlayerClass();
                    System.out.println("You changed your class to "+player.getPlayerClass()+".\nPress enter to return to the main menu.");
                    input.nextLine();
                    break;
                case 5:
                    System.out.print("Your weapon "+((player.getItem(0).getValue()!=-2) ? "was" : "can't be")+" upgraded.\nPress enter to return to the main menu.");
                    player.setItem(player.getItem(0).getName().equals("Stick")?powerStick:player.getItem(0).getName().equals("Bow")?superBow:player.getItem(0).getName().equals("Staff")?ultraStaff:player.getItem(0).getName().equals("Sword")?megaSword:player.getItem(0),0);
                    input.nextLine();
                    break;
                case 6: System.exit(0);
                default: break;                    
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
        field = {"Attack Fighter", "Go to Castle", "Check Bag"},
        castle = {"Fight Architect", "Fight his Guards", "Go to Shop", "Check Bag"};
        //Looking Around
        do{
            switch(currentLocation) {
                case "your home" -> extraDetails = ". You look around and find a chest";
                case "the village" -> extraDetails = ". You see a field in the distance";
                case "an open field" -> extraDetails = ". You see a fighter near a shed, and also see a castle in the other direction";
                case "a castle" -> extraDetails = ". After defeating the guard, you see the architect.";
            }
            System.out.println(divider + "You are at " + currentLocation + extraDetails +".\nOptions: ");
            switch(currentLocation) {
                case "your home":
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
                    break;
                case "the village":
                    switch(optionSelect(village,0)) {
                        case 1 -> System.out.println("You look around.");
                        case 2 -> game.threeCents.menu();
                        case 3 -> {
                            x=Battle.battler(new SmallEnemy("Small Dude", 5, 2, 1, 1));
                            currentLocation = "an open field";
                        }
                        case 4 -> player.getBag();
                    }
                    break;
                case "an open field":
                    switch(optionSelect(field, 0)) {
                        case 1 -> x = Battle.battler(new SmallEnemy("Shed Protecter", 4, 3, 1, 1));
                        case 2 -> {
                            x = Battle.battler(new BigEnemy("Castle Guard", 8, 4, 2, 2));
                            currentLocation="a castle";
                        }
                        case 3 -> player.getBag();
                    }
                    break;
                case "a castle":
                    switch(optionSelect(castle, 0)) {
                        case 1 -> {
                            x = Battle.battler(new BossEnemy("Architect", 15, 6, 4, 4));
                            currentLocation="abyss";
                        }
                        case 2 -> x = Battle.battler(new BigEnemy("Castle Guard", 8, 4, 2, 2));
                        case 3 -> game.threeCents.menu();
                        case 4 -> player.getBag();
                    }break;
                default:
                    clear();
                    System.out.print(divider+"You succeeded in your goal. Press Enter to Continue.\n"+divider);
                    input.nextLine();
                    return;
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
    //Reset Player and Shop
    }public static void reset(){
        Battle.totalWins = 0;
        player.setBal(50);
        player.setHp(player.getMaxHp());
        player.resetBag();
        threeCents.resetStock();
    }
}