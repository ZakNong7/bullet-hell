import greenfoot.*;

public class ScreenShake {

    private static int timer = 0;
    private static int strength = 0;

    // PANGGIL SAAT PLAYER TERKENA HIT
    public static void shake(int power) {
        strength = power;
        timer = 15; // durasi shake (frame)
    }

    // PANGGIL SETIAP FRAME DI WORLD.act()
    public static void update(World world) {

        if (timer <= 0) return;

        timer--;

        int dx = Greenfoot.getRandomNumber(strength * 2 + 1) - strength;
        int dy = Greenfoot.getRandomNumber(strength * 2 + 1) - strength;

        // HANYA SHAKE ACTOR GAMEPLAY
        for (Actor a : world.getObjects(Actor.class)) {

            // JANGAN SHAKE UI
            if (a instanceof UI) continue;
            if (a instanceof StageText) continue;
            if (a instanceof PauseText) continue;
            if (a instanceof Enemy) continue;
            if (a instanceof PlayerBullet) continue;
            if (a instanceof EnemyBullet) continue;
            if (a instanceof Boss) continue;
            if (a instanceof RecoverText) continue;
            if (a instanceof BossHealthBar) continue;
            if (a instanceof PauseButton) continue;
            if (a instanceof SlowButton) continue;

            a.setLocation(a.getX() + dx, a.getY() + dy);
        }
    }
}
