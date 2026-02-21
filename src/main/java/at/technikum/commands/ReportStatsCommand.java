package at.technikum.commands;

import at.technikum.Flipper;

public class ReportStatsCommand implements Command {
    private Flipper flipper;

    public ReportStatsCommand(Flipper flipper){
        this.flipper = flipper;
    }

    @Override
    public void execute() {
        System.out.println("Game Statistics: ");
        System.out.println("\tRemaining Credit: " + flipper.getCredits());
        System.out.println("\tRemaining Games: " + flipper.getRemainingGames());

    }
}
