import greenfoot.*;

public class MenuWorld extends World {

    private GreenfootSound menuMusic;

    public MenuWorld() {
        super(600, 800, 1);
        setBackground("menu.png");

        addObject(new MenuButton("start"), 300, 360);
        addObject(new MenuButton("tutorial"), 300, 430);
        addObject(new MenuButton("credit"), 300, 500);

        if (GameWorld.bgm == null || !GameWorld.bgm.isPlaying()) {
            GameWorld.bgm = new GreenfootSound("menu.mp3");
            GameWorld.bgm.setVolume(70);
            GameWorld.bgm.playLoop();
        }

    }
    public void stopMusic() {
        if (menuMusic != null) {
            menuMusic.stop();
        }
    }
}
