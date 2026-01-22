import greenfoot.*;

public class EndWorld extends World {

    private GreenfootSound endMusic;

    public EndWorld(boolean win) {
        super(600, 800, 1);

        if (win) {
            setBackground("win-bg.png");
            endMusic = new GreenfootSound("win.mp3");
            endMusic.play();
        } else {
            setBackground("lose-bg.png");
            endMusic = new GreenfootSound("lose.mp3");
            endMusic.playLoop();
        }

        endMusic.setVolume(70);

        addObject(new EndButton(), 300, 600);
    }

    public void stopMusic() {
        if (endMusic != null) {
            endMusic.stop();
        }
    }
}
