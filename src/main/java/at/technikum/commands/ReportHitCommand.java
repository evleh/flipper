package at.technikum.commands;

import at.technikum.Flipper;

// konkreter befehl
public class ReportHitCommand implements Command{
    private final Flipper flipper;
    private final int points;

    public ReportHitCommand(Flipper flipper, int points){
        this.flipper = flipper;
        this.points = points;
    }

    @Override
    public void execute() {
        this.flipper.reportHit(this.points);
    }
}
