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
        critChance = 0.25;
        dodgeChance = 0;
        playerClass = "mage";
    }
    public double getCritChance(){
        return critChance;
    }
    public void setCritChance(double critChance){
        this.critChance=critChance;
    }
    public double getDodgeChance(){
        return dodgeChance;
    }
    public void setDodgeChance(double dodgeChance){
        this.dodgeChance=dodgeChance;
    }
    public int getBal() {
        return bal;
    }
    public void setBal(int bal) {
        this.bal = bal;
    }
    public String getInv() {
        String output = "";
        if(this.inv.isEmpty()) return "Your bag is empty.";
        for(int i = 0; i < 10; i++) {
            System.out.print("Your bag: ");
            output += inv.get(i).getName();
            output += ", ";
        }
        return output;
    }
    public String getInvAtIndex(int x){
        if(this.inv.isEmpty()) return "empty";
        return inv.get(x).getName();
    }
    public void addItem(Item item) {
        this.inv.add(item);
    }
    public void removeItem(Item item) {
        this.inv.remove(item);
    }
    public String getPlayerClass(){
        return playerClass;
    }
    public void setPlayerClass(){
        String weapon;
        if(this.inv.isEmpty()) weapon = "none";
        else weapon = getInvAtIndex(0);
        if(weapon.contains("Sword")) this.playerClass="warrior";
        else if(weapon.contains("Staff")) this.playerClass="mage";
        else if(weapon.contains("Bow")) this.playerClass="archer";
        else this.playerClass="default";
    }
}