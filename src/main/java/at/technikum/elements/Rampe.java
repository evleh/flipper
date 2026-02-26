package at.technikum.elements;

import at.technikum.commands.Command;
import at.technikum.visitor.Visitor;

public class Rampe extends FlipperElement{

    public Rampe(Command command) {
        super(command);
    }

    @Override
    public void accept(Visitor v) {
        v.visit(this);
    }
}
