package at.technikum.elements;

import at.technikum.commands.Command;
import at.technikum.visitor.PointSource;
import at.technikum.visitor.Visitor;

public class TunnelElement extends FlipperElement implements PointSource {
    private boolean isOpen;
    private int hitCount;
    private int pointsPerHit;

    public TunnelElement(Command command, String name, int pointsPerHit) {
        super(command, name);
        this.isOpen = false;
        this.hitCount = 0;
        this.pointsPerHit = pointsPerHit;
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
        hitCount++;
        super.hit();
        setOpen(false);
    }

    @Override
    public String toString(){
        return this.getName() + ": " + (isOpen() ? "OPEN":"CLOSED");
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
}
