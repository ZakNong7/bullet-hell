import greenfoot.*;

public class EndButton extends Actor {
    public EndButton() {
        setImage("menu-btn.png");
    }
    public void act() {
        if (Greenfoot.mouseClicked(this)) {

            World w = getWorld();

            if (w instanceof EndWorld) {
                ((EndWorld)w).stopMusic();
            }

            Greenfoot.setWorld(new MenuWorld());
        }
    }
}
