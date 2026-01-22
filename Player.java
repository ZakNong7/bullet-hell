import greenfoot.*;

public class Player extends Actor {
    int shootDelay = 2;
    int lastTouchX;
    int lastTouchY;
    boolean dragging = false;

    public Player() {
        setImage("char.png");
    }
    public void act() {
    if (GameWorld.pause) return;

    move();
    shoot();
    checkHit();
    mobileDragControl();
    }
    void move() {
    int speed = 6;

    int x = getX();
    int y = getY();

    if (Greenfoot.isKeyDown("a")) x -= speed;
    if (Greenfoot.isKeyDown("d")) x += speed;
    if (Greenfoot.isKeyDown("w")) y -= speed;
    if (Greenfoot.isKeyDown("s")) y += speed;

    x = Math.max(20, Math.min(580, x));
    y = Math.max(20, Math.min(780, y));

    setLocation(x, y);
    }
    void shoot() {
    if (GameWorld.freeze) return;

    shootDelay++;

    int rate = GameWorld.slow ? 20 : 10; 
    if (shootDelay % rate == 2)
        getWorld().addObject(new PlayerBullet(), getX(), getY() - 20);
    }
    void checkHit() {
    if (isTouching(EnemyBullet.class)) {
        removeTouching(EnemyBullet.class);
        HealthUI.hp--;
        ScreenShake.shake(8);
        if (HealthUI.hp <= 0)
            if (getWorld() instanceof GameWorld) {
                ((GameWorld)getWorld()).stopMusic();
                Greenfoot.setWorld(new EndWorld(false));
            }
        }
    }
    void mobileDragControl() {
    if (GameWorld.pause || GameWorld.freeze) return;

    MouseInfo m = Greenfoot.getMouseInfo();
    if (m == null) return;

    // hanya area bawah layar (aman dari bullet padat)
    if (m.getY() < getWorld().getHeight() / 2) return;

    if (Greenfoot.mousePressed(null)) {
        dragging = true;
        lastTouchX = m.getX();
        lastTouchY = m.getY();
    }

    if (Greenfoot.mouseDragged(null) && dragging) {
        int dx = m.getX() - lastTouchX;
        int dy = m.getY() - lastTouchY;

        int speed = GameWorld.slow ? 2 : 4;

        setLocation(
            getX() + dx / speed,
            getY() + dy / speed
        );

        lastTouchX = m.getX();
        lastTouchY = m.getY();
    }

    if (Greenfoot.mouseDragEnded(null)) {
        dragging = false;
    }
    }
}
