package at.technikum.elements;

import at.technikum.commands.Command;
import at.technikum.visitor.Visitor;

/**
 * Class represents Flipper Element where the ball is lost
 * the hit method implemented in it's abstract super class
 * is equivalent to a ball was lost in the hole
 */
public class Hole extends FlipperElement {

    public Hole(Command command) {
        super(command);
    }

    @Override
    public void accept(Visitor v) {
        v.visit(this);
    }
}
