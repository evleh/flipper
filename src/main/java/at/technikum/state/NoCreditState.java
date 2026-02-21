package at.technikum.state;

import at.technikum.Flipper;

public class NoCreditState extends Zustand{

    public NoCreditState(Flipper flipper){
        super(flipper);
    }

    @Override
    public void pressStart() {
        System.out.println("No credit -> Please insert coin.");
    }

    @Override
    public void flipLeft() {
        System.out.println("No credit -> Print current high score");
    }

    @Override
    public void flipRight() {
        System.out.println("No credit -> Nothing happens on flipRight");
    }

    @Override
    public void insertCoin() {
        super.insertCoin();
        System.out.println("No credit: Coin inserted -> Flipper switched to credit state ");
        super.getFlipper().setZustand(new CreditState(getFlipper()));
    }

}
