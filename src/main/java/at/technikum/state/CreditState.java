package at.technikum.state;

import at.technikum.Flipper;

public class CreditState extends Zustand{

    public CreditState(Flipper flipper){
        super(flipper);
    }
    @Override
    public void pressStart() {
        super.getFlipper().setZustand(new PlayingState(getFlipper()));
        super.getFlipper().decrementCredits();

        System.out.println("Credit -> Game was started (cost 1 credit)");

    }

    @Override
    public void flipLeft() {
        super.getFlipper().getDisplayFactory().printPressStart();
    }

    @Override
    public void flipRight() {
        super.getFlipper().getDisplayFactory().printPressStart();
    }

    @Override
    public void insertCoin(){
        super.insertCoin();
        System.out.println("Credit -> Now you have MORE credit!");
    }
}
