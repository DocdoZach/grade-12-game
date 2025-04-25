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
        int x=game.optionSelect(output,2);
        if(x==0) return true;
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
        String[] classes={"Archer","Mage","Warrior"};
        this.playerClass="none";
        while(this.playerClass=="none"){
            System.out.println(game.divider+"Choose your class: ");
            switch(game.optionSelect(classes, 1)){
                case 0 -> {setItem(game.weapons[0],0); this.playerClass="default";}
                case 1 -> {setItem(game.weapons[1],0);this.playerClass="archer";}
                case 2 -> {setItem(game.weapons[2],0); this.playerClass="mage";}
                case 3 -> {setItem(game.weapons[3],0); this.playerClass="warrior";}
            }
        }
        System.out.print("You set your class to "+this.playerClass+".\nPress enter to continue.");
        game.input.nextLine();
    }
}