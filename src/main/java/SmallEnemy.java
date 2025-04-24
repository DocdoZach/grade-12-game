package main.java;

public class SmallEnemy extends Entity {
    private int coinValue;
    SmallEnemy(String name, int maxHp, int atk, int def, int spd) {
        super(name, maxHp, atk, def, spd);
        coinValue=1;
    }
    public int getCoinValue() {return coinValue;}
    public void setCoinValue(int coinValue) {this.coinValue=coinValue;}
}
