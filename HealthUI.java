import greenfoot.*;

public class HealthUI extends UI {
    public static final int MAX_HP =100; 
    public static int hp = MAX_HP;
    
    public void act() {
        setImage(new GreenfootImage("HP x" + hp, 24, Color.WHITE, null));
    }
    public static void healByStage(int stage) {
    hp += stage * 5;
    if (hp > MAX_HP)
        hp = MAX_HP;
    }
    public static void healRaw(int amount) {
    hp += amount;
    if (hp > MAX_HP)
        hp = MAX_HP;
    }
}
