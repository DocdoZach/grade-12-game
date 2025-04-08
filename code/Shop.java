import java.util.Scanner;

public class Shop {
    private Item item1;
    private Item item2;
    private Item item3;
    private Item item4;
    private int itemCount;
    private int stock1;
    private int stock2;
    private int stock3;
    private int stock4;

    Shop(Item item1, Item item2, Item item3, Item item4, int stock1, int stock2, int stock3, int stock4) {
        this.itemCount = 4;
        this.item1 = item1;
        this.item2 = item2;
        this.item3 = item3;
        this.item4 = item4;
        this.stock1 = stock1;
        this.stock2 = stock2;
        this.stock3 = stock3;
        this.stock4 = stock4;
    }

    Shop(Item item1, Item item2, Item item3, int stock1, int stock2, int stock3) {
        Item empty = new Item("", -1);
        this.itemCount = 3;
        this.item1 = item1;
        this.item2 = item2;
        this.item3 = item3;
        this.item4 = empty;
        this.stock1 = stock1;
        this.stock2 = stock2;
        this.stock3 = stock3;
    }

    Shop(Item item1, Item item2, int stock1, int stock2) {
        Item empty = new Item("", -1);
        this.itemCount = 2;
        this.item1 = item1;
        this.item2 = item2;
        this.item3 = empty;
        this.item4 = empty;
        this.stock1 = stock1;
        this.stock2 = stock2;
    }

    Shop(Item item1, int stock1) {
        Item empty = new Item("", -1);
        this.itemCount = 1;
        this.item1 = item1;
        this.item2 = empty;
        this.item3 = empty;
        this.item4 = empty;
        this.stock1 = stock1;
    }

    public void menu() {
        Scanner input = new Scanner(System.in);
        int choice = 0;
        System.out.println("Welcome to the shop. Please select an item to buy.");
        System.out.println("1. " + item1.getName() + " x" + stock1 + ", $" + item1.getValue() + " each");
        if(!item2.getName().isEmpty()) {
            System.out.println("2. " + item2.getName() + "x" + stock2 + ", $" + item2.getValue() + " each");
        }
        if(!item3.getName().isEmpty()) {
            System.out.println("3. " + item3.getName() + "x" + stock3 + ", $" + item3.getValue() + " each");
        }
        if(!item4.getName().isEmpty()) {
            System.out.println("4. " + item4.getName() + "x" + stock4 + ", $" + item4.getValue() + " each");
        }

        while(true) {
            try {
                if(itemCount == 1) System.out.print("Your choice (1, or 0 to exit): ");
                else System.out.print("Your choice (1-" + itemCount + ", or 0 to exit): ");
                choice = input.nextInt();
                if(choice < 0 || choice > itemCount) System.out.print("Invalid choice. ");
                else break;
            } catch(Exception e) {
                System.out.print("Invalid choice. ");
                input.nextLine();
            }
        }
    }

    public Item getItem1() {
        return item1;
    }

    public Item getItem2() {
        return item2;
    }

    public Item getItem3() {
        return item3;
    }

    public Item getItem4() {
        return item4;
    }

    public void setItem1(Item item1) {
        this.item1 = item1;
    }

    public void setItem2(Item item2) {
        this.item2 = item2;
    }

    public void setItem3(Item item3) {
        this.item3 = item3;
    }

    public void setItem4(Item item4) {
        this.item4 = item4;
    }
}
