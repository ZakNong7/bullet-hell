import greenfoot.*;

public class PauseOverlay extends Actor {

    public PauseOverlay() {
        GreenfootImage img = new GreenfootImage(600, 800);
        img.setColor(new Color(0, 0, 0, 160));
        img.fill();
        setImage(img);
    }

    public void act() {
        // tidak melakukan apa-apa, hanya overlay
    }
}
