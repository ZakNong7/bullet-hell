import greenfoot.*;

public class Boss extends Actor {
    int phase = 1;
    int hp = 30;
    int timer = 0;
    int rot = 0;
    int moveDir = 3;
    BossHealthBar bar;
    private static final int LEFT_BOUND  = 40;
    private static final int RIGHT_BOUND = 560;
    int dir = 2; 
    int speed = GameWorld.slow ? 1 : 3;
    private GreenfootSound bossMusic;
    
    public Boss() {
    setImage("boss2.png");
    phase = 1;
    hp = 30;
    bar = new BossHealthBar(this, 25);
    }
    public int getHp() {
    return hp;
    }
    int bossSpeed(int base) {
    return base + phase * 2 + GameWorld.bulletSpeedScale();
    }
    public void act() {
        if (GameWorld.pause || GameWorld.freeze) return;

        timer++;
        rot += 3 + phase * 2;

        moveHorizontal();
        

        if (phase == 1) hellPhaseOne();
        else if (phase == 2) hellPhaseTwo();
        else if (phase == 3) hellPhaseThree();

        if (hp <= 0)
            nextPhase();
            
    }

    public void moveHorizontal() {

    if (GameWorld.pause || GameWorld.freeze) return;

    setLocation(getX() + dir * speed, getY());

    if (getX() <= LEFT_BOUND) {
        dir = 1;
        setLocation(LEFT_BOUND, getY());
    }

    if (getX() >= RIGHT_BOUND) {
        dir = -1;
        setLocation(RIGHT_BOUND, getY());
    }
    }

    void hellPhaseOne() {
        rotatingRing(18, 6);
        waveCollapse();
    }

    void hellPhaseTwo() {
        rotatingRing(26, 7);
        waveCollapse();
        aimedPattern();
    }

    void hellPhaseThree() {
        rotatingRing(38, 8);
        chaosSpiral();
        wallLock();
    }

    void rotatingRing(int bullets, int baseSpeed) {
    if (timer % 10 != 0) return;

    for (int i = 0; i < bullets; i++) {
        getWorld().addObject(
            new EnemyBullet(
                i * (360 / bullets) + rot,
                bossSpeed(baseSpeed)
            ),
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

    void waveCollapse() {
        if (timer % 20 != 0) return;

        for (int i = 0; i < 18; i++) {
            getWorld().addObject(
                new EnemyBullet(
                    i * 20 + (int)(Math.sin(timer * 0.2) * 60),
                    4
                ),
                getX(), getY()
            );
        }
    }

    void chaosSpiral() {
        if (timer % 2 != 0) return;

        getWorld().addObject(
            new EnemyBullet(rot, 6),
            getX(), getY()
        );
    }

    void wallLock() {
        if (timer % 90 != 0) return;

        for (int x = 40; x < 600; x += 20) {
            getWorld().addObject(
                new EnemyBullet(90, 3),
                x, getY()
            );
        }
    }

    void nextPhase() {

    int recover = phase * 16;
    HealthUI.healRaw(recover);

    getWorld().addObject(
        new RecoverText(recover),
        getWorld().getWidth() / 2,
        getWorld().getHeight() / 2 + 80
    );

    phase++;
    timer = 0;

    if (phase == 2) {
        hp = 35;
        bar.startPhaseTransition(2);
    }
    else if (phase == 3) {
        hp = 50;
        bar.startPhaseTransition(3);
    }
    else {
        if (GameWorld.bgm != null)
            GameWorld.bgm.stop();
        Greenfoot.setWorld(new EndWorld(true));
    }
    }

    public void hit() {
        hp--;
    }
    private void recoverOnPhaseChange() {
    int recover = phase * 14;
    HealthUI.healRaw(recover);

    getWorld().addObject(
        new RecoverText(recover),
        getWorld().getWidth() / 2,
        getWorld().getHeight() / 2 + 80
    );
    }
}
