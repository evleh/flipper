package at.technikum.visitor;

import at.technikum.elements.*;
import at.technikum.elements.GroupTarget;

public class PointsVisitor implements Visitor{
    private int totalPoints;

    @Override
    public void visit(LightTarget target) {
        totalPoints += target.calculatePoints();
    }

    @Override
    public void visit(TunnelElement target) {
        totalPoints += target.calculatePoints();
    }

    @Override
    public void visit(ExternalLightAdapter externalLightAdapter) {

    }

    @Override
    public void visit(Hole hole) {

    }

    @Override
    public void visit(Rampe rampe) {

    }

    @Override
    public void visit(GroupTarget target) {
        totalPoints += target.calculatePoints();
    }


    public int getTotalPoints() {
        return totalPoints;
    }

    public void setTotalPoints(int totalPoints) {
        this.totalPoints = totalPoints;
    }
}
