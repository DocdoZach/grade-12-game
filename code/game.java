import java.util.ArrayList;
import java.util.Scanner;

public class game {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        // Instantiate players
        Player p = new Player("none", 5, 5, 5, 3, 50, new ArrayList<>());

        // Instantiate items
        Item hotCocoa = new Item("Hot Cocoa",25, p.getMaxHp());
        Item apple = new Item("Apple", 3, 3);
        Item bread = new Item("Bread", 5, 5);
        Item salad = new Item("Salad", 8, 7);
        Item applePie = new Item("Apple Pie", 10, 9);

        //Instantiate enemies/shops
        BossEnemy b = new BossEnemy("Architect", 15, 7, 7, 4);
        BigEnemy g = new BigEnemy("Placeholder", 10, 6, 6, 3);
        SmallEnemy s = new SmallEnemy("Small Dude", 5, 5, 5, 2);
        Shop threeCents = new Shop(hotCocoa, applePie, bread, apple, 1, 3, 5, 7);

        // Name select
        int choice = 0;
        System.out.print("Enter your name: ");
        p.setName(input.nextLine());
        clear();

        // Main menu option select
        System.out.println("WIP Game Name debug menu\n1. Battle test\n2. Endless Mode\n3. Quit");
        while(true) {
            try {
                System.out.print("Select an option (1-3): ");
                choice = Integer.parseInt(input.nextLine());
                if(choice < 1 || choice > 3) {
                    System.out.print("Invalid choice. ");
                }
                else break;
            } catch (Exception e) {
                System.out.print("Invalid choice. ");
            }
        }

        // Switch statement for chosen option
        switch(choice) {
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
                } while (!Battle.previousWin());
                System.out.println("final stats: wip");
                break;
            case 3:
                System.exit(0);
        }
    }
    // Clear screen method
    public static void clear() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }
}