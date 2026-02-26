package at.technikum.elements;

import at.technikum.commands.Command;
import at.technikum.visitor.PointSource;
import at.technikum.visitor.Visitor;

public class LightTarget extends FlipperElement implements PointSource {
    private boolean isOn;
    private int hitCount;
    private int pointsPerHit;

    public LightTarget(Command command, boolean isOn, String name, int pointsPerHit) {
        super(command, name);
        this.isOn = isOn;
        this.pointsPerHit = pointsPerHit;
        this.hitCount = 0;
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

    @Override
    public String toString(){
        return this.getName() + ": " + (isOn() ? "ON":"OFF");
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

    @Override
    public void hit(){
        hitCount += 1;
        super.hit();
    }
}
