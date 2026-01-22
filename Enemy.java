import greenfoot.*;

public class Enemy extends Actor {
    int hp = 25;
    int timer = 0;
    int rot = 0;

    public Enemy() {
        setImage("Enemy.png");
    }
    public void act() {
        if (GameWorld.pause || GameWorld.freeze) return;

        timer++;
        rot += 2 + GameWorld.stageLevel;

        if (GameWorld.stageLevel == 3) {
            aimedPattern();     
        } else {
            hellPattern();      
        }
    }
    public void hit() {
        hp--;
        if (hp <= 0)
            getWorld().removeObject(this);
    }
    void hellPattern() {
        int lvl = GameWorld.stageLevel;

        int rate = Math.max(2, 18 - lvl * 2);
        if (timer % rate != 0) return;

        int bullets = 8 + lvl * 4;
        int speed = 1 + lvl + GameWorld.bulletSpeedScale();

        for (int i = 0; i < bullets; i++) {
            int angle =
                i * (360 / bullets)
                + rot
                + (int)(Math.sin(timer * 0.05) * 45);

            getWorld().addObject(
                new EnemyBullet(angle, speed),
                getX(), getY()
            );
        }
    }
    void aimedPattern() {
        int rate = 10; 
        if (timer % rate != 0) return;

        Player player = (Player)getWorld().getObjects(Player.class).get(0);

        int dx = player.getX() - getX();
        int dy = player.getY() - getY();

        int baseAngle = (int)Math.toDegrees(Math.atan2(dy, dx));

        int bullets = 5;  
        int spread = 12;  

        int speed = 5 + GameWorld.bulletSpeedScale();

        for (int i = 0; i < bullets; i++) {
            int angle = baseAngle - spread * 2 + i * spread;

            getWorld().addObject(
                new EnemyBullet(angle, speed),
                getX(), getY()
            );
        }
    }
}
