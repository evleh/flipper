package at.technikum.elements.grouped;

import at.technikum.commands.Command;
import at.technikum.elements.FlipperElement;
import at.technikum.mediator.Mediator;

public class GroupTarget extends FlipperElement {
    private Mediator mediator;
    private String name;
    public GroupTarget(Command command, Mediator mediator, String name) {
        super(command);
        this.mediator = mediator;
        this.name = name;
    }

    /**
     * Group targets have basic functionality and notify mediator to mediate group-functionality
     */
    @Override
    public void hit(){
        super.hit();
        if (mediator != null){
            mediator.makeNotification(this, name);
        }
    }
}
