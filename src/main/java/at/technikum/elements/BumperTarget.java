package at.technikum.elements;

import at.technikum.commands.Command;
import at.technikum.mediator.Mediator;

public class BumperTarget extends FlipperElement{
    private Mediator mediator;
    public BumperTarget(Command command, Mediator mediator) {
        super(command);
        this.mediator = mediator;
    }
}
