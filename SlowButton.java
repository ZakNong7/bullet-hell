import greenfoot.*;

public class SlowButton extends Actor {

    public SlowButton() {
        setImage("slow_btn.png");
        getImage().setTransparency(160);
    }

    public void act() {
        if (GameWorld.pause || GameWorld.freeze) {
            GameWorld.slowFromButton = false;
            return;
        }

        if (Greenfoot.mousePressed(this)) {
            GameWorld.slowFromButton = true;
        }

        if (Greenfoot.mouseDragEnded(this) || Greenfoot.mouseClicked(null)) {
            GameWorld.slowFromButton = false;
        }
    }
}
