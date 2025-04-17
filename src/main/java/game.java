package main.java;

import java.util.*;
public class game {
    public static Scanner input = new Scanner(System.in);
    public static void main(String[] args) {
        // Instantiate players
        Player p = new Player("none", 5, 5, 5, 3, 50, new ArrayList<>());

        // Instantiate items
        Item hotCocoa = new Item("Hot Cocoa", 25, p.getMaxHp());
        Item apple = new Item("Apple", 3, 3);
        Item bread = new Item("Bread", 5, 5);
        Item salad = new Item("Salad", 7, 8);
        Item applePie = new Item("Apple Pie", 9, 10);

        // Instantiate weapons
        Item stick = new Item("Stick", -1, 1);
        Item sword = new Item("Sword", -1, 2);
        Item megaSword = new Item("Mega Sword", -1, 4);
        Item staff = new Item("Staff", -1, 2);
        Item ultraStaff = new Item("Ultra Staff", -1, 4);
        Item bow = new Item("Bow", -1, 2);
        Item superBow = new Item("Super Bow", -1, 4);

        // Instantiate shop
        Shop threeCents = new Shop(hotCocoa, applePie, bread, apple, 1, 3, 5, 7);

        // Name select
        System.out.print("Enter your name: ");
        p.setName(input.nextLine());
        p.addItem(stick);
        int choice = 0;
        // Main menu option select
        while(true) {
            clear();
            System.out.println("----------\nWIP Game Name Debug menu\n1. Test Battle\n2. Test Endless Mode\n3. Test Shop\n4. Change Weapon\n5. Upgrade Weapon\n6. Quit");
            while(true) {
                try {
                    System.out.print("Select an option (1-6): ");
                    choice = Integer.parseInt(input.nextLine());
                    if (choice < 1 || choice > 6)  System.out.print("Invalid choice. ");
                    else break;
                } catch (Exception e) { System.out.print("Invalid choice. ");}
            }
            // Switch statement for chosen option
            switch (choice) {
                case 1:
                    System.out.println("WIP");
                    Battle.battler(p, new SmallEnemy("Small Dude", 5, 2, 1, 1));
                    break;
                case 2:
                    System.out.println("WIP");
                    int wins = 0;
                    int battlesSinceShop = 0;
                    double random = Math.random();
                    do {
                        threeCents.menu(p);
                        if (random < 0.5) Battle.battler(p, new SmallEnemy("Small Dude", 5, 2, 1, 1));
                        else if (random < 0.8) Battle.battler(p, new BigEnemy("Guard", 8, 4, 3, 3));
                        else Battle.battler(p, new BossEnemy("Architect", 12, 8, 6, 10));
                        if(Battle.previousWin()) {
                            wins++;
                            battlesSinceShop++;
                        }
                        if(battlesSinceShop == 6) threeCents.menu(p);
                    } while (Battle.previousWin());
                    System.out.print("----------\nFinal stats\nWins: " + wins + "\nPress enter to return to the main menu.");
                    input.nextLine();
                    clear();
                    break;
                case 3:
                    threeCents.menu(p);
                    break;
                case 4:
                    do{
                        System.out.print("Choose your class: ");
                        switch(input.nextLine()){
                            case "archer": p.setItem(bow,0); break;
                            case "mage": p.setItem(staff,0); break;
                            case "warrior": p.setItem(sword,0); break;
                            default: clear(); System.out.println("Not an option.");
                        }
                        if(!(p.getInvAtIndex(0).equals("empty"))){
                            p.setPlayerClass();
                            break;
                        }
                    }while(true);
                    System.out.print("You changed your class to "+p.getPlayerClass()+".\nPress enter to return to the main menu.");
                    input.nextLine();
                    break;
                case 5:
                    switch(p.getInvAtIndex(0)){
                        case "Bow": p.setItem(superBow,0); System.out.println("Weapon Upgraded!"); break;
                        case "Staff": p.setItem(ultraStaff,0); System.out.println("Weapon Upgraded!"); break;
                        case "Sword": p.setItem(megaSword,0); System.out.println("Weapon Upgraded!"); break;
                        default: clear(); System.out.println("Your weapon can't be upgraded.");
                    }
                    System.out.print("Press enter to return to the main menu.");
                    input.nextLine();
                    break;
                case 6:
                    System.exit(0);
            }
        }
    }
    // Clear screen method
    public static void clear() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }
}