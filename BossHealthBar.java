import greenfoot.*;

public class BossHealthBar extends Actor {

    private Boss boss;
    private int maxHp;
    private int displayHp;
    private int phase = 1;
    private int animTimer = 0;

    public BossHealthBar(Boss boss, int maxHp) {
        this.boss = boss;
        this.maxHp = maxHp;
        this.displayHp = maxHp;
        updateImage();
    }

    public void act() {
        if (boss == null || boss.getWorld() == null) {
            if (getWorld() != null)
                getWorld().removeObject(this);
            return;
        }

        if (displayHp > boss.getHp())
            displayHp--;
        else if (displayHp < boss.getHp())
            displayHp++;

        updateImage();
    }

    public void startPhaseTransition(int newPhase) {
        phase = newPhase;

        if (phase == 2) maxHp = 35;
        else if (phase == 3) maxHp = 50;

        displayHp = maxHp;
        animTimer = 30; 
    }

    private void updateImage() {
        int width = 400;
        int height = 16;

        GreenfootImage img = new GreenfootImage(width, height);
        img.setColor(Color.DARK_GRAY);
        img.fill();

        Color barColor;
        switch (phase) {
            case 2: barColor = Color.ORANGE; break;
            case 3: barColor = new Color(160, 60, 200); break;
            default: barColor = Color.RED;
        }

        img.setColor(barColor);

        int barWidth = (int)((double)displayHp / maxHp * width);
        img.fillRect(0, 0, barWidth, height);

        if (animTimer > 0) {
            img.setColor(Color.WHITE);
            img.drawRect(0, 0, width - 1, height - 1);
            animTimer--;
        }

        setImage(img);
    }
}
