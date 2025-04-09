import java.util.Scanner;
public class game {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        // item list
        Item hotCocoa = new Item("Hot Cocoa",10);

        BossEnemy b = new BossEnemy("Architect", 15, 7, 7, 4);
        BigEnemy g = new BigEnemy("Placeholder", 10, 6, 6, 3);
        SmallEnemy s = new SmallEnemy("Small Dude", 5, 5, 5, 2);
        Player p = new Player("none", 5, 5, 5, 3, 50, new Item[]{hotCocoa, null, null, null, null, null, null, null, null, null});
        Shop h = new Shop(hotCocoa, 3);

        int choice;
        System.out.print("Enter your name: ");
        p.setName(input.nextLine());
        clear();

        choice = 0;
        System.out.println("WIP Game Name debug menu\n1. Story Mode\n2. Shop menu\n3. Quit");
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

        switch(choice) {
            case 1:
                System.out.println("WIP");
                System.out.println(h.getItem1());
                break;
            case 2:
                System.out.println("WIP");
                h.menu(p);
                break;
            case 3:
                System.exit(0);
        }
    }
    public static void clear() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }
}