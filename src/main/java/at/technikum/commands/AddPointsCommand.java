package at.technikum.commands;

import at.technikum.Flipper;

// konkreter befehl
public class AddPointsCommand implements Command{
    private final Flipper flipper;
    private final int points;

    public AddPointsCommand(Flipper flipper, int points){
        this.flipper = flipper;
        this.points = points;
    }

    @Override
    public void execute() {
        this.flipper.addPoints(this.points);
    }
}
