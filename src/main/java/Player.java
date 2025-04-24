package main.java;
import java.util.ArrayList;

public class Player extends Entity{
    private double critChance;
    private double dodgeChance;
    private int bal;
    private String playerClass;
    private ArrayList<Item> inv = new ArrayList<>();
    
    Player(String name, int maxHp, int atk, int def, int spd, int bal, ArrayList<Item> inv){
        super(name, maxHp, atk, def, spd);
        this.bal = bal;
        this.inv = inv;
        dodgeChance = 0;
        critChance = 0.25;
        playerClass = "default";
        this.inv.add(new Item("Fists", -1, 0));
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
        if(this.inv.size()==1){
            System.out.println("It's Empty.");
            return true;
        }
        String[] output=new String[this.inv.size()-1];
        for(int i=0;i<this.inv.size();i++){
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
        if(item==null)return;
        if(item.getName().equals("Hot Cocoa")) item.setStat(getMaxHp());
        System.out.println("Healed "+item.getStat() + " HP!");
        setHp(getHp()+item.getStat());
        if(getHp()>=getMaxHp()) setHp(getMaxHp());
        removeItem(item);
    }
    public Item getItem(int index) {return this.inv.get(index);}
    public void addItem(Item item) {this.inv.add(item);}
    public void removeItem(Item item) {this.inv.remove(item);}
    public void setItem(Item item, int index) {this.inv.set(index,item);}
    public String getPlayerClass() {return playerClass;}
    public void setPlayerClass() {
        String weapon;
        if(this.inv.isEmpty()) weapon = "none";
        else weapon = getItem(0).getName();
        if(weapon.contains("Sword")) this.playerClass="warrior";
        else if(weapon.contains("Staff")) this.playerClass="mage";
        else if(weapon.contains("Bow")) this.playerClass="archer";
        else this.playerClass="default";
    }
}