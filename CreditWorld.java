import greenfoot.*;

public class CreditWorld extends World {
    public CreditWorld() {
        super(600, 800, 1);
        setBackground("credit-bg.png");
        
        addObject(new EndButton(), 300, 600);
    }
}
