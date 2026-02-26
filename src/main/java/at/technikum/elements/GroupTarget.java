package at.technikum.elements;

import at.technikum.commands.Command;
import at.technikum.mediator.Mediator;
import at.technikum.visitor.PointSource;
import at.technikum.visitor.Visitor;

/**
 * GroupTarget is a target that has a mediator
 */

public class GroupTarget extends FlipperElement implements PointSource {
    private Mediator mediator;
    private int hitCount;
    private int pointsPerHit;

    public GroupTarget(Command command, Mediator mediator, String name, int pointsPerHit) {
        super(command, name);
        this.mediator = mediator;
        this.hitCount = 0;
        this.pointsPerHit = pointsPerHit;
    }

    /**
     * Group targets have basic functionality and notify mediator to mediate group-functionality
     */
    @Override
    public void hit(){
        super.hit();
        this.hitCount = this.hitCount + 1;
        if (mediator != null){
            mediator.makeNotification(this, getName());
        }
    }

    @Override
    public void accept(Visitor v) {
        v.visit(this);
    }

    @Override
    public int getHitCount() {
        return this.hitCount;
    }

    @Override
    public int getPointsPerHit() {
        return pointsPerHit;
    }

    @Override
    public int calculatePoints() {
        return pointsPerHit * hitCount;
    }

    public void setHitCount(int hitCount) {
        this.hitCount = hitCount;
    }

    @Override
    public String toString(){
        return this.getName() + ": " + getHitCount() + " HITS";
    }
}
