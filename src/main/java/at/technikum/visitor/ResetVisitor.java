package at.technikum.visitor;

import at.technikum.elements.*;
import at.technikum.elements.grouped.GroupTarget;

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
    public void accept(ExternalLightAdapter externalLightAdapter) {
        externalLightAdapter.setOn(false);
    }

    @Override
    public void accept(Hole hole) {
        System.out.println("todo: reset visitor");
    }

    @Override
    public void accept(Rampe rampe) {
        System.out.println("todo: reset visitor");
    }

    @Override
    public void accept(GroupTarget groupTarget) {
        System.out.println("todo: reset visitor");
    }

}
