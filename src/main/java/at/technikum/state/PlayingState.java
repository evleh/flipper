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
//        getFlipper().getElements().getFirst().hit();
//        getFlipper().getElements().get(2).hit();



    }

    @Override
    public void flipRight() {
        System.out.println("Playing -> flipRight");
        /*
        testcode for loosing the ball 3x
        getFlipper().getElements().get(1).hit();
        getFlipper().getElements().get(1).hit();
        getFlipper().getElements().get(1).hit();
        */

        hitTargetGroup();

        super.getFlipper().setZustand(new EndState(getFlipper()));
    }

    /**
     * Executes target group, implementing mediator pattern
     */
    public void hitTargetGroup(){
        System.out.println();
        System.out.println("------ MEDIATOR PATTERN: START ------");
        getFlipper().getElements().get(3).hit();
        getFlipper().getElements().get(4).hit();
        getFlipper().getElements().get(6).hit(); // resetTarget
        getFlipper().getElements().get(5).hit();


        getFlipper().getElements().get(3).hit();
        getFlipper().getElements().get(4).hit();
        getFlipper().getElements().get(5).hit();

        System.out.println("------ MEDIATOR PATTERN: STOP ------");
        System.out.println();
    }

}
