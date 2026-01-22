import greenfoot.*;

public class TutorialWorld extends World {
    public TutorialWorld() {
        super(600, 800, 1);
        setBackground("cara_main-bg.png");
        
        addObject(new EndButton(), 300, 600);
    }
}
