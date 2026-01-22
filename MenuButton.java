import greenfoot.*;

public class MenuButton extends Actor {

    private String type;

    public MenuButton(String type) {
        this.type = type;

        switch (type) {
            case "start":
                setImage("main-btn2.png");
                break;
            case "tutorial":
                setImage("cara_main-btn.png");
                break;
            case "credit":
                setImage("credit-btn.png");
                break;
        }
    }

    public void act() {
        if (Greenfoot.mouseClicked(this)) {

            World w = getWorld();
            if (type.equals("start") && w instanceof MenuWorld) {
                ((MenuWorld)w).stopMusic();
            }

            switch (type) {
                case "start":
                    Greenfoot.setWorld(new GameWorld());
                    break;
                case "tutorial":
                    Greenfoot.setWorld(new TutorialWorld());
                    break;
                case "credit":
                    Greenfoot.setWorld(new CreditWorld());
                    break;
            }
        }
    }
}
