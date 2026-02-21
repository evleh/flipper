package at.technikum.commands;

import at.technikum.Flipper;

public class LightOnCommand implements Command{
    private int seconds;
    private Flipper flipper;

    public LightOnCommand(Flipper flipper, int seconds) {
        this.seconds = seconds;
        this.flipper = flipper;
    }

    @Override
    public void execute() {
        this.flipper.setLight(this.seconds);
    }
}
