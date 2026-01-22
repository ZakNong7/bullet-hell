import greenfoot.*;

public class RecoverText extends Actor {
    private int timer = 0;

    public RecoverText(int amount) {
        GreenfootImage img = new GreenfootImage(
            "RECOVER +" + amount,
            36,
            Color.GREEN,
            new Color(0, 0, 0, 0)
        );
        setImage(img);
    }

    public void act() {
        if (GameWorld.pause) return;

        timer++;
        setLocation(getX(), getY() - 1); 

        if (timer > 120) { 
            getWorld().removeObject(this);
        }
    }
}
