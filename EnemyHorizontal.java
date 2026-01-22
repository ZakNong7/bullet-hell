import greenfoot.*;

public class EnemyHorizontal extends Enemy {
    int speed = 2;
    private static final int LEFT_BOUND  = 40;
    private static final int RIGHT_BOUND = 560;

    public void act() {
        if (GameWorld.pause || GameWorld.freeze) return;

        setLocation(getX() + speed, getY());
        if (getX() < 80 || getX() > 520)
            speed *= -1;

        super.act();
    }
}


