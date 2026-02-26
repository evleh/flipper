package at.technikum.elements;

import at.technikum.commands.Command;
import at.technikum.visitor.Visitor;

public class TunnelElement extends FlipperElement {
    private boolean isOpen;

    public TunnelElement(Command command, String name) {
        super(command, name);
        this.isOpen = false;
    }

    @Override
    public void accept(Visitor v) {
        v.visit(this);
    }

    public boolean isOpen() {
        return isOpen;
    }

    public void setOpen(boolean open) {
        isOpen = open;
    }

    @Override
    public void hit(){
        setOpen(true);
        super.hit();
        setOpen(false);
    }

    @Override
    public String toString(){
        return this.getName() + ": " + (isOpen() ? "OPEN":"CLOSED");
    }
}
