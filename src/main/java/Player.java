package main.java;
import java.util.ArrayList;

public class Player extends Entity{
    private double critChance;
    private double dodgeChance;
    private int bal;
    private String playerClass;
    private ArrayList<Item> inv;
    
    Player(String name, int maxHp, int atk, int def, int spd){
        super(name, maxHp, atk, def, spd);
        this.inv = new ArrayList<>();
        this.inv.add(new Item("Fists", -1, 0));
        this.playerClass = "default";
        this.critChance = 0.25;
        this.dodgeChance = 0;
        this.bal = 25;
    }
    public double getCritChance() {return critChance;}
    public void setCritChance(double critChance) {this.critChance=critChance;}
    public double getDodgeChance() {return dodgeChance;}
    public void setDodgeChance(double dodgeChance) {this.dodgeChance=dodgeChance;}
    public int getBal() {return bal;}
    public void setBal(int bal) {this.bal = bal;}
    public ArrayList<Item> getInv() {return inv;}
    public boolean getBag() {
        System.out.println("Your Bag:");
        if(this.inv.size() == 1){
            System.out.println("It's Empty.");
            return true;
        }
        String[] output=new String[this.inv.size()-1];
        for(int i = 0;i < this.inv.size(); i++){
            if(this.inv.get(i).getValue()<=-1) continue;
            output[i-1]=this.inv.get(i).getName();
        }
        int x=game.optionSelect(output,0);
        game.clear();
        useItem(getItem(x));
        return false;
    }
    public void resetBag() {
        ArrayList<Item> newInv = new ArrayList<>();
        newInv.add(this.inv.get(0));
        this.inv=newInv;
    }
    public void useItem(Item item) {
        if(item == null) return;
        if(item.getName().equals("Hot Cocoa")) item.setStat(getMaxHp());
        else if(item.getName().equals("Apple Pie")) setMaxHp(getMaxHp()+1);
        System.out.println("Healed " + item.getStat() + " HP!");
        setHp(getHp() + item.getStat());
        if(getHp() >= getMaxHp()) setHp(getMaxHp());
        removeItem(item);
    }
    public void upgradeWeapon(){
        System.out.println("Your weapon "+((getItem(0).getValue()!=-2) ? "was" : "can't be")+" upgraded.");
        setItem(getItem(0).getName().equals("Stick")?game.upgrades[0]:getItem(0).getName().equals("Bow")?game.upgrades[1]:getItem(0).getName().equals("Staff")?game.upgrades[2]:getItem(0).getName().equals("Sword")?game.upgrades[3]:getItem(0),0);
    }
    public Item getItem(int index) {return this.inv.get(index);}
    public void addItem(Item item) {this.inv.add(item);}
    public void removeItem(Item item) {this.inv.remove(item);}
    public void setItem(Item item, int index) {this.inv.set(index,item);}
    public String getPlayerClass() {return playerClass;}
    public void setPlayerClass() {
        int y = -1;
        while(y == -1){
            System.out.print("Classes: Mage, Warrior, Archer\nChoose your class: ");
            String x = game.input.nextLine();
            switch(x){
                case "fred" -> {setItem(game.weapons[0],0); y=0;}
                case "archer" -> {setItem(game.weapons[1],0);y=1;}
                case "mage" -> {setItem(game.weapons[2],0); y=2;}
                case "warrior" -> {setItem(game.weapons[3],0); y=3;}
                default -> {game.clear(); System.out.println("Not an option.");}
            }
        }switch (y) {
            case 1 -> this.playerClass="archer";
            case 2 -> this.playerClass="mage";
            case 3 -> this.playerClass="warrior";
            default -> this.playerClass="default";
        }
        System.out.println("You set your class to "+this.playerClass+".\nPress enter to continue.");
        game.input.nextLine();
    }
}