package at.technikum.commands;

import at.technikum.Flipper;

public class GameLostCommand implements Command{
    private Flipper flipper;

    public GameLostCommand(Flipper flipper){
        this.flipper = flipper;
    }

    @Override
    public void execute() {
        this.flipper.onGameLost();
    }
}
