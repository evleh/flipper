package at.technikum.elements;

import at.technikum.commands.Command;
import at.technikum.mediator.Mediator;

public class TunnelElement extends FlipperElement{
    private Mediator mediator;
    public TunnelElement(Command command, Mediator mediator) {
        super(command);
        this.mediator = mediator;
    }
}
