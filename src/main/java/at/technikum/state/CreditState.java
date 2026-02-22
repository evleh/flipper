package at.technikum.state;

import at.technikum.Flipper;

public class CreditState extends Zustand{

    public CreditState(Flipper flipper){
        super(flipper);
    }
    @Override
    public String pressStart() {
        super.getFlipper().setZustand(new PlayingState(getFlipper()));
        super.getFlipper().decrementCredits();
        return "Credit -> Game was started (cost 1 credit)";
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
