public class Player extends Entity{
    private double critChance;
    private double dodgeChance;
    private int bal;
    private Item[] inv;

    Player(String name, int maxHp, int atk, int def, int spd, int bal, Item[] inv){
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
        for(int i = 0; i < 10; i++) {
            System.out.print("Your bag: ");
            if(inv[i] != null) {
                output += inv[i].getName();
                output += ", ";
            }
        }
        return output;
    }
    public void setInvSlot(Item item, int index) {
        this.inv[index] = item;
    }
    public int getEmptyIndex() {
        for(int i = 0; i < inv.length; i++) {
            if(inv[i] == null) return i;
        }
        return -1;
    }
}