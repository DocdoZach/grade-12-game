import java.util.Scanner;

public class game {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        Player p = new Player("none", 5, 5, 5, 3);
        BossEnemy b = new BossEnemy("Architect", 15, 7, 7, 4);
        BigEnemy g = new BigEnemy("Placeholder", 10, 6, 6, 3);
        SmallEnemy s = new SmallEnemy("Small Dude", 5, 5, 5, 2);

        System.out.print("Enter your name: ");
        p.setName(input.nextLine());
        clear();

        int choice = 0;
        System.out.println("WIP Game Name\n1. Story Mode\n2. Endless Mode\n3. Quit");
        while(true) {
            try {
                System.out.print("Select an option (1-3): ");
                choice = input.nextInt();
                if(choice < 1 || choice > 3) {
                    System.out.print("Invalid choice. ");
                }
                else break;
            } catch (Exception e) {
                System.out.print("Invalid choice. ");
                input.nextLine();
            }
        }

        switch(choice) {
            case 1:
                System.out.println("WIP");
                break;
            case 2:
                System.out.println("WIP");
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