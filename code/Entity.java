public abstract class Entity{
    private String name;
    private int health;
    private int damage;
    Entity(String name,int health,int damage){
        this.name=name;
        this.health=health;
        this.damage=damage;
    }public String toString(){
        return name+" has "+health+" health remaining.";
    }
}
