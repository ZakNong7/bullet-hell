import greenfoot.*;

public class ResumeButton extends Actor {

    public ResumeButton() {
        setImage("resume_btn.png");
    }

    public void act() {
        if (Greenfoot.mouseClicked(this)) {
            GameWorld.pause = false;

            World w = getWorld();
            w.removeObjects(w.getObjects(PauseOverlay.class));
            w.removeObjects(w.getObjects(PauseText.class));
            w.removeObjects(w.getObjects(ResumeButton.class));
            w.removeObjects(w.getObjects(BackToMenuButton.class));
        }
    }
}
