import java.util.ArrayList;
import java.util.Scanner;

public class game {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        // Instantiate players
        Player p = new Player("none", 5, 5, 5, 3, 50, new ArrayList<>());

        // Instantiate items
        Item hotCocoa = new Item("Hot Cocoa", 25, p.getMaxHp());
        Item apple = new Item("Apple", 3, 3);
        Item bread = new Item("Bread", 5, 5);
        Item salad = new Item("Salad", 7, 8);
        Item applePie = new Item("Apple Pie", 9, 10);

        // Instantiate weapons
        Item sword = new Item("Sword", -1, 2);
        Item megaSword = new Item("Mega Sword", -1, 4);
        Item staff = new Item("Staff", -1, 2);
        Item ultraStaff = new Item("Ultra Staff", -1, 4);
        Item bow = new Item("Bow", -1, 2);
        Item superBow = new Item("Super Bow", -1, 4);

        //Instantiate enemies/shops
        BossEnemy b = new BossEnemy("Architect", 12, 8, 6, 10);
        BigEnemy g = new BigEnemy("Guard", 8, 4, 3, 3);
        SmallEnemy s = new SmallEnemy("Small Dude", 5, 2, 1, 1);
        Shop threeCents = new Shop(hotCocoa, applePie, bread, apple, 1, 3, 5, 7);

        // Name select
        System.out.print("Enter your name: ");
        p.setName(input.nextLine());
        p.addItem(bow);
        p.setPlayerClass();
        clear();

        int choice = 0;
        // Main menu option select
        while(true) {
            System.out.println("WIP Game Name debug menu\n1. Battle test\n2. Endless Mode\n3. Quit");
            while(true) {
                try {
                    System.out.print("Select an option (1-3): ");
                    choice = Integer.parseInt(input.nextLine());
                    if (choice < 1 || choice > 3) {
                        System.out.print("Invalid choice. ");
                    } else break;
                } catch (Exception e) {
                    System.out.print("Invalid choice. ");
                }
            }

            // Switch statement for chosen option
            switch (choice) {
                case 1:
                    System.out.println("WIP");
                    Battle.battler(p, s);
                    break;
                case 2:
                    System.out.println("WIP");
                    double random = Math.random();
                    do {
                        if (random < 0.5) {
                            Battle.battler(p, s);
                        } else if (random < 0.8) {
                            Battle.battler(p, g);
                        } else Battle.battler(p, b);
                    } while (Battle.previousWin());
                    System.out.print("final stats: wip\nPress enter to return to the main menu.");
                    input.nextLine();
                    clear();
                    break;
                case 3:
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