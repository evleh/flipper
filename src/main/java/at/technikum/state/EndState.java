package at.technikum.state;

import at.technikum.Flipper;

public class EndState extends Zustand{

    public EndState(Flipper flipper){
        super(flipper);
    }

    @Override
    public String pressStart() {

        // Automat wechselt automatisch, je nach Kredit, in den No-Credit- bzw. CreditState.
        if(getFlipper().getCredits() == 0){
            getFlipper().setZustand(new NoCreditState(getFlipper()));
        } else {
            getFlipper().setZustand(new CreditState(getFlipper()));
        }

        return "You lost all your balls. Remaining Credits: " + getFlipper().getCredits();
        // todo: Kugel 3-mal verloren gegangen: wechseln sie in den End-State, bei welchem sie ein Spiel gewinnen können.

    }

    @Override
    public void flipLeft() {
        // todo
    }

    @Override
    public void flipRight() {
        // todo
    }
}
