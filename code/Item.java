import java.util.ArrayList;
import java.util.Scanner;

public class Item {
    private String name;
    private int value;
    private double stat;

    Item(String name, int value, double stat){
        this.name = name;
        this.value = value;
        this.stat = stat;
    }
    public String getName() {
        return name;
    }
    public int getValue() {
        return value;
    }
    public double getStat() {
        return stat;
    }
    public void setName(String name) {
        this.name = name;
    }
    public void setValue(int value) {
        this.value = value;
    }
    public void setStat(double stat) {
        this.stat = stat;
    }
    public String toString() {
        return name + " has a value of " + value;
    }
    // Method for checking if a word starts with a vowel
    public boolean startsWithVowel() {
        if(this.name.charAt(0) == 'a'
        || this.name.charAt(0) == 'e'
        || this.name.charAt(0) == 'i'
        || this.name.charAt(0) == 'o'
        || this.name.charAt(0) == 'u') {
            return true;
        } else return false;
    }
}
