package at.technikum.elements.grouped;

import at.technikum.commands.Command;
import at.technikum.elements.FlipperElement;
import at.technikum.mediator.Mediator;
import at.technikum.visitor.Visitor;

public class GroupTarget extends FlipperElement {
    private Mediator mediator;
    private int hitCount;
    public GroupTarget(Command command, Mediator mediator, String name) {
        super(command, name);
        this.mediator = mediator;
        this.hitCount = 0;
    }

    /**
     * Group targets have basic functionality and notify mediator to mediate group-functionality
     */
    @Override
    public void hit(){
        super.hit();
        hitCount =+ 1;
        if (mediator != null){
            mediator.makeNotification(this, getName());
        }
    }

    @Override
    public void accept(Visitor v) {
        v.visit(this);
    }

    public int getHitCount() {
        return hitCount;
    }

    public void setHitCount(int hitCount) {
        this.hitCount = hitCount;
    }

    @Override
    public String toString(){
        return this.getName() + ": " + getHitCount() + " HITS";
    }
}
