package at.technikum.elements;

import at.technikum.commands.Command;
import at.technikum.visitor.Resettable;
import at.technikum.visitor.Visitor;

public class LightTarget extends FlipperElement implements Resettable {
    private boolean isOn;

    public LightTarget(Command command, boolean isOn) {
        super(command);
        this.isOn = isOn;
    }

    public boolean isOn() {
        return isOn;
    }

    public void setOn(boolean on) {
        isOn = on;
    }

    @Override
    public void accept(Visitor v) {
        v.visit(this);
    }
}
