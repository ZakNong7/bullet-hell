import greenfoot.*;

public class BGM {
    static GreenfootSound bgm;

    public static void play(String file) {
        if (bgm != null) bgm.stop();
        bgm = new GreenfootSound(file);
        bgm.playLoop();
    }

    public static void stop() {
        if (bgm != null) bgm.stop();
        if (current != null) current.stop();
    }
    private static GreenfootSound current;

    public static void play(String file, int volume) {
        if (current != null && current.isPlaying()) return;

        current = new GreenfootSound(file);
        current.setVolume(volume);
        current.playLoop();
    }

    public static void change(String file, int volume) {
        if (current != null) current.stop();
        current = new GreenfootSound(file);
        current.setVolume(volume);
        current.playLoop();
    }
}
