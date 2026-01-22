import greenfoot.*;

public class GameWorld extends World {

    public static boolean pause = false;
    public static boolean slow = false;
    public static boolean freeze = false;
    public static int level = 1;        
    public static int stageLevel = 1;
    public static int slowEnergy = 100;
    public static final int MAX_SLOW = 500;
    private boolean showingStage = true;
    private int stageTimer = 0;
    public static GreenfootSound bgm;
    public boolean gameOver = false;
    public static boolean slowFromButton = false;


    public GameWorld() {
        super(600, 800, 1);
        setBackground("game.png");

        pause = false;
        slow = false;
        freeze = true;
        level = 1;
        stageLevel = 1;
        slowEnergy = MAX_SLOW;

        HealthUI.hp = 100;

    
        addObject(new Player(), 300, 650);
        addObject(new HealthUI(), 60, 40);
        changeMusic("game.mp3");
        showStageText("STAGE 1");
        addObject(new PauseButton(), 560, 40);   // pojok kanan atas
        addObject(new SlowButton(), 560, 740);   // pojok kanan bawah

    }

 
    public void act() {
        if (gameOver) return;
            handlePauseInput();
            handleSlowMo();
        if (pause) {
            if (bgm != null) bgm.pause();
            ScreenShake.update(this);
            return;
        } else {
            if (bgm != null) bgm.playLoop();
        }
        if (showingStage) {
            stageTimer++;
            if (stageTimer >= 300) { // 5 detik
                showingStage = false;
                freeze = false;
                spawnLevel();
            }
            ScreenShake.update(this);
            return;
        }
        if (getObjects(Enemy.class).isEmpty()
        && getObjects(EnemyHorizontal.class).isEmpty()
        && getObjects(Boss.class).isEmpty()) {

            int recoverAmount = stageLevel * 5;

       
            HealthUI.healByStage(stageLevel);
            addObject(
                new RecoverText(recoverAmount),
                getWidth() / 2,
                getHeight() / 2 + 60
            );

            level++;

            if (level > 5) {
            if (bgm != null) bgm.stop();
            stopMusic();

            Greenfoot.setWorld(new EndWorld(true));
            return;
            }


            freeze = true;
            stageTimer = 0;
            showingStage = true;

            if (level == 5) {
            changeMusic("boss.mp3");
            showStageText("WARNING!! BOSS");
            } else {
            showStageText("STAGE " + level);
            }
        }
        ScreenShake.update(this);
    }
    
    private void handleSlowMo() {

        slow = false;

        if (pause || freeze) return;

        boolean shift = Greenfoot.isKeyDown("shift");

        if ((shift || slowFromButton) && slowEnergy >= 50) {
            slow = true;
            slowEnergy -= stageLevel;
        }

        if (slowEnergy < 0) slowEnergy = 0;

        if (!slow && slowEnergy < MAX_SLOW) {
            slowEnergy += 1;
        }
    }
    private void handlePauseInput() {
        String key = Greenfoot.getKey();

        if (key != null && key.equals("p")) {
            togglePause();
        }
    }



    private void spawnLevel() {

        stageLevel = level;

        if (level >= 1 && level <= 3) {
            addObject(new Enemy(), 300, 120);
        }
        else if (level == 4) {
            addObject(new EnemyHorizontal(), 300, 120);
        }
        else if (level == 5) {
            Boss boss = new Boss();
            addObject(boss, 300, 120);

            BossHealthBar bar = new BossHealthBar(boss, 25);
            boss.bar = bar;
            addObject(bar, 300, 55);
        }
    }
    private void showStageText(String text) {
        freeze = true;
        showingStage = true;
        stageTimer = 0;
        addObject(new StageText(text), 300, 400);
    }
    public void changeMusic(String file) {
        if (bgm != null) bgm.stop();
        bgm = new GreenfootSound(file);
        bgm.setVolume(70);
        bgm.playLoop();
    }
    public void stopMusic() {
        if (bgm != null) {
            bgm.stop();
        }
    }
    public static int bulletSpeedScale() {
        switch (stageLevel) {
            case 1: return 0;
            case 2: return 0;
            case 3: return 1;
            case 4: return 2;
            case 5: return 6;
        }
        return 0;
    }
    public void togglePause() {
        pause = !pause;
    
        if (pause) {
            addObject(new PauseOverlay(), 300, 400);
            addObject(new PauseText(), 300, 250);
            addObject(new ResumeButton(), 300, 400);
            addObject(new BackToMenuButton(), 300, 480);
        } else {
            removeObjects(getObjects(PauseOverlay.class));
            removeObjects(getObjects(PauseText.class));
            removeObjects(getObjects(ResumeButton.class));
            removeObjects(getObjects(BackToMenuButton.class));
        }
    }
}
