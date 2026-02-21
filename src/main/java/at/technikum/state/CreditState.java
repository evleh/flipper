package at.technikum.state;

import at.technikum.Flipper;

public class CreditState extends Zustand{

    public CreditState(Flipper flipper){
        super(flipper);
    }
    @Override
    public void pressStart() {
        System.out.println("Credit -> Game was started (cost 1 credit)");
        super.getFlipper().setZustand(new PlayingState(getFlipper()));
        super.getFlipper().decrementCredits();
    }

    @Override
    public void flipLeft() {
        System.out.println("Credit -> Press start-button so play!");
    }

    @Override
    public void flipRight() {
        System.out.println("Credit -> Press start-button so play!");
    }

    @Override
    public void insertCoin(){
        super.insertCoin();
        System.out.println("Credit -> Now you have MORE credit!");
    }
}
