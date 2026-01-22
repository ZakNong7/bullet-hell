import greenfoot.*;

public class StageText extends Actor {
    int life = 300;

    public StageText(String txt) {
        setImage(new GreenfootImage(txt, 48, Color.WHITE, null));
    }

    public void act() {
        life--;
        if (life <= 0) getWorld().removeObject(this);
    }
}
