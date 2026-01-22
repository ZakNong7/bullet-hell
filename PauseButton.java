import greenfoot.*;

public class PauseButton extends Actor {

    public PauseButton() {
        setImage("pause_btn.png");
        getImage().setTransparency(170);
    }

    public void act() {
    if (GameWorld.freeze) return;

    if (Greenfoot.mouseClicked(this)) {
        World w = getWorld();
        if (w instanceof GameWorld) {
            ((GameWorld) w).togglePause();
        }
    }
    }
}
