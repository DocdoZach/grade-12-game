package main.java;

public class Item {
    private String name;
    private int value;
    private double stat;

    Item(String name, int value, double stat){
        this.name = name;
        this.value = value;
        this.stat = stat;
    }
    public String getName() {return name;}
    public int getValue() {return value;}
    public double getStat() {return stat;}
    public void setName(String name) {this.name = name;}
    public void setValue(int value) {this.value = value;}
    public void setStat(double stat) {this.stat = stat;}
    // Method for checking if a word starts with a vowel
    public boolean startsWithVowel() {
        return "AEIOUaeiou".indexOf(this.name.charAt(0)) != -1;
    }
}
