package at.technikum.commands;

import at.technikum.Flipper;

public class BlinkingLightCommand implements Command{
    private int seconds;
    private Flipper flipper;

    public BlinkingLightCommand(Flipper flipper, int seconds) {
        this.seconds = seconds;
        this.flipper = flipper;
    }

    @Override
    public void execute() {
        this.flipper.blinkLight(this.seconds);
    }
}
