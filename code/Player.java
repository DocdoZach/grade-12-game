import java.util.ArrayList;

public class Player extends Entity{
    private double critChance;
    private double dodgeChance;
    private int bal;
    private ArrayList<Item> inv = new ArrayList<>();

    Player(String name, int maxHp, int atk, int def, int spd, int bal, ArrayList<Item> inv){
        super(name, maxHp, atk, def, spd);
        critChance = 0.25;
        dodgeChance = 0;
        this.bal = bal;
        this.inv = inv;
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
    public void addItem(Item item) {
        this.inv.add(item);
    }
    public void removeItem(Item item) {
        this.inv.remove(item);
    }
}