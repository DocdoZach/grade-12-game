package main.java;

import java.util.*;
public class game {
    public static Scanner input = new Scanner(System.in);
    public static String divider = "----------\n";
    public static void main(String[] args) {
        // Instantiate players
        Player p = new Player("none", 5, 5, 5, 3, 50, new ArrayList<>());

        // Instantiate items and shop
        Item hotCocoa = new Item("Hot Cocoa", 25, p.getMaxHp());
        Item apple = new Item("Apple", 3, 3);
        Item bread = new Item("Bread", 5, 5);
        Item salad = new Item("Salad", 7, 8);
        Item applePie = new Item("Apple Pie", 9, 10);
        Shop threeCents = new Shop(hotCocoa, applePie, bread, apple, 1, 3, 5, 7);

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
        p.setName(input.nextLine());
        int choice = 0;
        // Class select
        String x, y;
        System.out.println("Classes: Mage, Warrior, Archer");
        do{
            System.out.print("Choose your class: ");
            x=input.nextLine();
            p.setItem(x.equals("fred")?stick:x.equals("archer")?bow:x.equals("mage")?staff:x.equals("warrior")?sword:p.getItem(0),0);
            if(!p.getInv(0).equals("Fists")) break;
            clear();
            System.out.println("Not an option.");
        }while(p.getInv(0).equals("Fists"));
        p.setPlayerClass();
        System.out.println("You set your class to "+p.getPlayerClass()+".");

        // Debug menu option select
        while(true) {
            clear();
            System.out.println(divider + "WIP Game Name Debug Menu\n1. Test Battle\n2. Test Main Menu\n3. Test Shop\n4. Change Weapon\n5. Upgrade Weapon\n6. Quit");
            while(true) {
                try {
                    System.out.print("Select an option (1-6): ");
                    choice = Integer.parseInt(input.nextLine());
                    if (choice < 1 || choice > 6)  System.out.print("Invalid choice." + divider);
                    else break;
                } catch (Exception e) { System.out.println("Invalid choice. ");}
            }
            // Switch statement for chosen option
            switch (choice) {
                case 1:
                    System.out.println("WIP");
                    Battle.battler(p, new SmallEnemy("Small Dude", 5, 2, 1, 1));
                    break;
                case 2: mainMenu(p,threeCents); break;
                case 3: threeCents.menu(p); break;
                case 4:
                    y=p.getInv(0);
                    do{
                        System.out.print("Choose your class: ");
                        x=input.nextLine();
                        switch(x){
                            case "archer": p.setItem(bow,0); break;
                            case "mage": p.setItem(staff,0); break;
                            case "warrior": p.setItem(sword,0); break;
                            case "fred": p.setItem(stick,0); break;
                            default: clear(); System.out.println("Not an option.");
                        }
                        if(p.getInv(0).equals(p.getInv(0))){
                            clear();
                            System.out.println("Class already chosen.");
                        }
                    }while(p.getInv(0).equals(y));
                    p.setPlayerClass();
                    System.out.println("You changed your class to "+p.getPlayerClass()+".\nPress enter to return to the main menu.");
                    input.nextLine();
                    break;
                case 5:
                    System.out.print("Your weapon "+((p.getInvValue(0)!=-2) ? "was" : "can't be")+" upgraded.\nPress enter to return to the main menu.");
                    p.setItem(p.getInv(0).equals("Stick")?powerStick:p.getInv(0).equals("Bow")?superBow:p.getInv(0).equals("Staff")?ultraStaff:p.getInv(0).equals("Sword")?megaSword:p.getItem(0),0);
                    input.nextLine();
                    break;
                case 6: System.exit(0);
                default: break;                    
            }
        }
    }
    //Main Menu
    public static void mainMenu(Player player, Shop shop){
        int choice=0, x=0;
        do{
            clear(); 
            if(choice!=0) System.out.println("Invalid choice. ");
            System.out.println(divider + "Welcome to (WIP Name)!\n1. Start Endless Mode\n2. Quit\n(0 is Debug)\n" + divider + "Select an option (1-2): ");
            try{choice = Integer.parseInt(input.nextLine());}
            catch(Exception e){ choice=-1;}
            switch(choice){
                case 1: endlessMode(player, shop); break;
                case 2: System.exit(0);
                case 0: x=1;
                default: break;
            }
        }while(x==0);

    }
    //Endless Mode Code
    public static void endlessMode(Player player, Shop shop){
        int battlesSinceShop = 0;
        double random = Math.random();
        Battle.totalWins = 0;
        player.setBal(50);
        player.setHp(player.getMaxHp());
        do{
            clear();
            if(battlesSinceShop++%6 == 0) shop.menu(player);
            if(random < 0.5) Battle.battler(player, new SmallEnemy("Small Dude", 5, 2, 1, 1));
            else if(random < 0.85) Battle.battler(player, new BigEnemy("Guard", 8, 4, 3, 3));
            else Battle.battler(player, new BossEnemy("Architect", 12, 8, 6, 10));
        }while(Battle.previousWin());
        System.out.print(divider + "Final stats\nWins: " + Battle.totalWins + "\nPress enter to return to the main menu.");
        input.nextLine();
        clear();
    }
    // Clear screen method
    public static void clear() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }
}