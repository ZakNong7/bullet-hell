import greenfoot.*;
public class EnemyBullet extends Actor {
    double dx, dy;
    double accel = 1.0;
    double vx, vy;
    double speed = 4;

    public EnemyBullet(int angle, int speed) {
        GreenfootImage img = new GreenfootImage(10, 10);
        img.setColor(Color.MAGENTA); 
        img.fillOval(0, 0, 10, 10);
        setImage(img);
        
        double rad = Math.toRadians(angle);
        dx = Math.cos(rad) * speed;
        dy = Math.sin(rad) * speed;

        accel = 1.003 + GameWorld.stageLevel * 0.002;
    }


    public void act() {
        if (GameWorld.pause) return;

        double slowMul = GameWorld.slow ? 0.4 : 1.0;

        dx *= accel;
        dy *= accel;

        setLocation(
            (int)(getX() + dx * slowMul),
            (int)(getY() + dy * slowMul)
        );

        if (isAtEdge()) {
            getWorld().removeObject(this);
        }
    }
}



