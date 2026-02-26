package at.technikum.visitor;

import at.technikum.elements.*;
import at.technikum.elements.grouped.GroupTarget;

/*
    Possible improvements: implement "reset"-method in every element and call
    this method in the visitor.
 */

public class ResetVisitor implements Visitor{
    @Override
    public void visit(LightTarget e) {
        e.setOn(false);
        System.out.println(e.toString());
    }

    @Override
    public void visit(TunnelElement e) {
        e.setOpen(false);
        System.out.println(e.toString());
    }

    @Override
    public void visit(ExternalLightAdapter externalLightAdapter) {
        externalLightAdapter.setOn(false);
    }

    @Override
    public void visit(Hole hole) {
        System.out.println("todo: reset visitor");
    }

    @Override
    public void visit(Rampe rampe) {
        System.out.println("todo: reset visitor");
    }

    @Override
    public void visit(GroupTarget groupTarget) {
        groupTarget.setHitCount(0);
        System.out.println(groupTarget.toString());

    }

}
