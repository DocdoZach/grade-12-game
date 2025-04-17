package main.java;

import java.util.Scanner;
public class Shop {
    private int itemCount;
    private Item[] item;
    private int[] stock;
    private Item empty = new Item("", -1, 0);
    public static Scanner input = new Scanner(System.in);
    Shop(Item item1, Item item2, Item item3, Item item4, int stock1, int stock2, int stock3, int stock4) {
        this.itemCount = 4;
        this.item = new Item[] {item1, item2, item3, item4};
        this.stock = new int[] {stock1, stock2, stock3, stock4};
    }
    Shop(Item item1, Item item2, Item item3, int stock1, int stock2, int stock3) {
        this.itemCount = 3;
        this.item = new Item[] {item1, item2, item3, empty};
        this.stock = new int[] {stock1, stock2, stock3, 0};
    }
    Shop(Item item1, Item item2, int stock1, int stock2) {
        this.itemCount = 2;
        this.item = new Item[] {item1, item2, empty, empty};
        this.stock = new int[] {stock1, stock2, 0, 0};
    }
    Shop(Item item1, int stock1) {
        this.itemCount = 1;
        this.item = new Item[] {item1, empty, empty, empty};
        this.stock = new int[] {stock1, 0, 0, 0};
    }
    public void menu(Player customer) {
        // Shop menu option select
        int choice = 0;
        System.out.println("Welcome to the shop. ");
        while(true) {
            System.out.println("----------\nYou have "+customer.getBal()+" coins\nPlease select an item to buy.");
            for(int i=0;i<this.itemCount;i++) System.out.printf("%d. %s x%d, $%d each%n",i+1,item[i].getName(),stock[i],item[i].getValue());
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
            game.clear();
            // If statement for chosen option
            if(choice == 0) break;
            else{
                if(customer.getBal() < item[choice-1].getValue()) System.out.println("You don't have enough money!");
                else if(stock[choice-1] < 1) System.out.println("This item is out of stock!");
                else {
                    customer.setBal(customer.getBal() - item[choice-1].getValue());
                    customer.addItem(item[choice-1]);
                    stock[choice-1]--;
                    System.out.println("You got a" + ((item[choice-1].startsWithVowel()) ? "n " : " ") + item[choice-1].getName() + "!");
                }
            }
        }
    }
    public Item getItem1() {
        return item[0];
    }
    public Item getItem2() {
        return item[1];
    }
    public Item getItem3() {
        return item[2];
    }
    public Item getItem4() {
        return item[3];
    }
    public void setItem1(Item item1) {
        this.item[0] = item1;
    }
    public void setItem2(Item item2) {
        this.item[1] = item2;
    }
    public void setItem3(Item item3) {
        this.item[2] = item3;
    }
    public void setItem4(Item item4) {
        this.item[3] = item4;
    }
    public int getStock1() {
        return stock[0];
    }
    public int getStock2() {
        return stock[1];
    }
    public int getStock3() {
        return stock[2];
    }
    public int getStock4() {
        return stock[3];
    }
    public void setStock1(int stock1) {
        this.stock[0] = stock1;
    }
    public void setStock2(int stock2) {
        this.stock[1] = stock2;
    }
    public void setStock3(int stock3) {
        this.stock[2] = stock3;
    }
    public void setStock4(int stock4) {
        this.stock[3] = stock4;
    }
}
