import greenfoot.*;

public class BackToMenuButton extends Actor {

    public BackToMenuButton() {
        setImage("menu-btn.png");
    }

    public void act() {
        if (Greenfoot.mouseClicked(this)) {

            // STOP SEMUA MUSIK GAME
            if (GameWorld.bgm != null) {
                GameWorld.bgm.stop();
                GameWorld.bgm = null;
            }

            GameWorld.pause = false;
            GameWorld.freeze = false;

            Greenfoot.setWorld(new MenuWorld());
        }
    }
}
