package at.technikum.commands;

import at.technikum.Flipper;

// konkreter befehl
public class ReportHitCommand implements Command{
    private final Flipper flipper;

    public ReportHitCommand(Flipper flipper){
        this.flipper = flipper;
    }

    @Override
    public void execute() {
        this.flipper.reportHit();
    }
}
