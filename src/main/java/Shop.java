package main.java;

public class Shop {
    private Item[] item;
    private int[] stock;
    private int[] oldStock;
    private int upgradeStock;

    Shop(Item item1, Item item2, Item item3, Item item4, int stock1, int stock2, int stock3, int stock4) {
        this.item = new Item[] {item1, item2, item3, item4};
        this.stock = new int[] {stock1, stock2, stock3, stock4};
        this.oldStock = new int[] {stock1, stock2, stock3, stock4};
        this.upgradeStock=1;
    }
    Shop(Item item1, Item item2, Item item3, int stock1, int stock2, int stock3) {
        this.item = new Item[] {item1, item2, item3};
        this.stock = new int[] {stock1, stock2, stock3};
        this.oldStock = new int[] {stock1, stock2, stock3};
        this.upgradeStock=1;
    }
    Shop(Item item1, Item item2, int stock1, int stock2) {
        this.item = new Item[] {item1, item2};
        this.stock = new int[] {stock1, stock2};
        this.oldStock = new int[] {stock1, stock2};
        this.upgradeStock=1;
    }
    Shop(Item item1, int stock1) {
        this.item = new Item[] {item1};
        this.stock = new int[] {stock1};
        this.oldStock = new int[] {stock1};
        this.upgradeStock=1;
    }
    public void menu() {
        // Shop menu option select
        int choice;
        String[] form=new String[this.item.length+1];
        game.clear();
        System.out.println("Welcome to the shop. ");
        while(true) {
            System.out.println("----------\nYou have "+game.player.getBal()+" coins\nPlease select an item to buy.");
            for(int i=0;i<this.item.length;i++) form[i]=String.format("%s x%d, $%d each",item[i].getName(),stock[i],item[i].getValue());
            form[this.item.length] = "Weapon Upgrade x" + this.upgradeStock + ", $40 each";
            choice=game.optionSelect(form, 2);
            // If statement for chosen option
            if(choice == 0) break;
            else if(choice <= this.item.length){
                if(game.player.getBal() < item[choice-1].getValue()) System.out.println("You don't have enough money!");
                else if(stock[choice-1] < 1) System.out.println("This item is out of stock!");
                else {
                    game.player.setBal(game.player.getBal() - item[choice-1].getValue());
                    game.player.addItem(item[choice-1]);
                    stock[choice-1]--;
                    System.out.println("You got a" + ((item[choice-1].startsWithVowel()) ? "n " : " ") + item[choice-1].getName() + "!");
                }
            }else{
                if(game.player.getBal() < 40) System.out.println("You don't have enough money!");
                else if(this.upgradeStock < 1) System.out.println("Your weapon is already upgraded!");
                else {
                    game.player.setBal(game.player.getBal() - 40);
                    game.player.upgradeWeapon();
                    this.upgradeStock--;
                }
            }
        }
    }
    public Item getItem(int index) {return item[index];}
    public void setItem(Item item, int index) {this.item[index] = item;}
    public int getStock(int index) {return stock[index];}
    public void setStock(int stock, int index) {this.stock[index] = stock;}
    public void resetStock() {
        System.arraycopy(oldStock, 0, stock, 0, stock.length);
    }
}
