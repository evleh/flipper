package at.technikum.visitor;

import at.technikum.elements.*;
import at.technikum.elements.GroupTarget;

/*
    Possible improvements: implement "reset"-method in every element and call
    this method in the visitor.
 */

public class ResetVisitor implements Visitor{
    private String resetReport = "";

    @Override
    public void visit(LightTarget e) {
        e.setOn(false);
        resetReport += e.toString() + "\n";
    }

    @Override
    public void visit(TunnelElement e) {
        e.setOpen(false);
        resetReport += e.toString() + "\n";
    }

    @Override
    public void visit(ExternalLightAdapter externalLightAdapter) {
        externalLightAdapter.setOn(false);
    }

    @Override
    public void visit(Hole hole) {
        // Empty because no resetting is necessary
    }

    @Override
    public void visit(Rampe rampe) {
        // Empty because no resetting is necessary
    }

    @Override
    public void visit(GroupTarget e) {
        e.setHitCount(0);
        resetReport += e.toString() + "\n";
    }

    public String getResetReport() {
        return resetReport;
    }

    public void setResetReport(String resetReport) {
        this.resetReport = resetReport;
    }
}
