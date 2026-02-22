package at.technikum.state;

import at.technikum.Flipper;

/**
 * abstrakte klasse statt nterface: manche sachen in jedem zustand möglich -> erleichtert teilen
 * von gemeinsamen methoden
 *
 * Zustand mit Operationen
 */
public abstract class Zustand {

    private Flipper flipper;

    public Zustand(Flipper flipper){
        this.flipper = flipper;
    }

    public abstract String pressStart();
    public abstract void flipLeft();
    public abstract void flipRight();
    public void insertCoin(){
        flipper.incrementCredits();
    };

    public Flipper getFlipper() {
        return flipper;
    }
}
