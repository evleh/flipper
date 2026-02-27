package at.technikum.commands;

import at.technikum.Flipper;

public class BallLostCommand implements Command{
    private Flipper flipper;

    public BallLostCommand(Flipper flipper){
        this.flipper = flipper;
    }

    @Override
    public void execute() {
        this.flipper.getDisplayFactory().printBallLost();
        this.flipper.decrementRemainingGames();
        this.flipper.calculateAllPoints();
    }
}
