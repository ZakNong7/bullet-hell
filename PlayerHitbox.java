import greenfoot.*;

public class PlayerHitbox extends Actor {

    private int invincibleTimer = 0;

    public void act() {

        if (GameWorld.pause || GameWorld.freeze) return;

        if (invincibleTimer > 0) {
            invincibleTimer--;
            return;
        }

        EnemyBullet b = (EnemyBullet)getOneIntersectingObject(EnemyBullet.class);
        Enemy e = (Enemy)getOneIntersectingObject(Enemy.class);
        Boss boss = (Boss)getOneIntersectingObject(Boss.class);

        if (b != null || e != null || boss != null) {
            HealthUI.hp -= 1; 
            invincibleTimer = 60; 

            ScreenShake.shake(8);

            if (b != null) getWorld().removeObject(b);

            if (HealthUI.hp <= 0) {
            GameWorld gw = (GameWorld)getWorld();
            gw.gameOver = true;
            gw.stopMusic();
            Greenfoot.setWorld(new EndWorld(false));
        }   
        }
    }
}
