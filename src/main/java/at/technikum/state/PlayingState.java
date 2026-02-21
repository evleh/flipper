package at.technikum.state;

import at.technikum.Flipper;

public class PlayingState extends Zustand {

    public PlayingState(Flipper flipper) {
        super(flipper);
    }

    @Override
    public void pressStart() {
        System.out.println("Playing -> Author: Eva Lehner ");
    }

    @Override
    public void flipLeft() {
        System.out.println("Playing -> flipLeft");
        // Note: getFirst ist List-Interface methods since Java 21
        getFlipper().getElements().getFirst().hit();
        getFlipper().getElements().get(2).hit();



    }

    @Override
    public void flipRight() {
        System.out.println("Playing -> flipRight");
        getFlipper().getElements().get(1).hit();
        getFlipper().getElements().get(1).hit();
        getFlipper().getElements().get(1).hit();

        super.getFlipper().setZustand(new EndState(getFlipper()));
    }

}
