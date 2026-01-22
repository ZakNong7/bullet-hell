import greenfoot.*;

public class PlayerBullet extends Actor {

    public PlayerBullet() {
        GreenfootImage img = new GreenfootImage(10, 10);
        img.setColor(Color.RED); 
        img.fillOval(0, 0, 10, 10);
        setImage(img);
    }

    public void act() {
        if (GameWorld.pause) return;

        if (getY() <= 0) {
            getWorld().removeObject(this);
            return;
        }

        int speed = GameWorld.slow ? 4 : 8;
        setLocation(getX(), getY() - speed);

        Enemy e = (Enemy)getOneIntersectingObject(Enemy.class);
        if (e != null) {
            e.hit();
            getWorld().removeObject(this);
            return;
        }

        Boss b = (Boss)getOneIntersectingObject(Boss.class);
        if (b != null) {
            b.hit();
            getWorld().removeObject(this);
        }
    }
}
