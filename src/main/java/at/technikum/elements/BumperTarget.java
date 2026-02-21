package at.technikum.elements;

import at.technikum.commands.Command;
import at.technikum.mediator.Mediator;

public class BumperTarget extends FlipperElement{
    private Mediator mediator;
    private String name;
    public BumperTarget(Command command, Mediator mediator, String name) {
        super(command);
        this.mediator = mediator;
        this.name = name;
    }

    @Override
    public void hit(){
        super.hit();
        if (mediator != null){
            mediator.makeNotification(this, name);
        }
    }
}
