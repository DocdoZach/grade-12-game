package main.java;

import java.util.Scanner;
public class Shop {
    private int itemCount;
    private Item[] item;
    private int[] stock;
    private int[] oldStock;

    public static Scanner input = new Scanner(System.in);
    Shop(Item item1, Item item2, Item item3, Item item4, int stock1, int stock2, int stock3, int stock4) {
        this.itemCount = 4;
        this.item = new Item[] {item1, item2, item3, item4};
        this.stock = new int[] {stock1, stock2, stock3, stock4};
        this.oldStock = new int[] {stock1, stock2, stock3, stock4};
    }
    Shop(Item item1, Item item2, Item item3, int stock1, int stock2, int stock3) {
        this.itemCount = 3;
        this.item = new Item[] {item1, item2, item3};
        this.stock = new int[] {stock1, stock2, stock3};
        this.oldStock = new int[] {stock1, stock2, stock3};
    }
    Shop(Item item1, Item item2, int stock1, int stock2) {
        this.itemCount = 2;
        this.item = new Item[] {item1, item2};
        this.stock = new int[] {stock1, stock2};
        this.oldStock = new int[] {stock1, stock2};
    }
    Shop(Item item1, int stock1) {
        this.itemCount = 1;
        this.item = new Item[] {item1};
        this.stock = new int[] {stock1};
        this.oldStock = new int[] {stock1};
    }
    public void menu() {
        // Shop menu option select
        int choice = 0;
        String[] form=new String[this.itemCount];
        game.clear();
        System.out.println("Welcome to the shop. ");
        while(true) {
            System.out.println("----------\nYou have "+game.player.getBal()+" coins\nPlease select an item to buy.");
            for(int i=0;i<this.itemCount;i++) form[i]=String.format("%s x%d, $%d each",item[i].getName(),stock[i],item[i].getValue());
            choice=game.optionSelect(form, 2);
            // If statement for chosen option
            if(choice == 0) break;
            else{
                if(game.player.getBal() < item[choice-1].getValue()) System.out.println("You don't have enough money!");
                else if(stock[choice-1] < 1) System.out.println("This item is out of stock!");
                else {
                    game.player.setBal(game.player.getBal() - item[choice-1].getValue());
                    game.player.addItem(item[choice-1]);
                    stock[choice-1]--;
                    System.out.println("You got a" + ((item[choice-1].startsWithVowel()) ? "n " : " ") + item[choice-1].getName() + "!");
                }
            }
        }
    }
    public Item getItem(int index) {
        return item[index];
    }
    public void setItem(Item item, int index) {
        this.item[index] = item;
    }
    public int getStock(int index) {
        return stock[index];
    }
    public void setStock(int stock, int index) {
        this.stock[index] = stock;
    }
    public void resetStock(){
        for(int i=0;i<stock.length;i++) stock[i]=oldStock[i];
    }
}
