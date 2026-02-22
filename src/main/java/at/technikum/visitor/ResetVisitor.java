package at.technikum.visitor;

import at.technikum.elements.LightTarget;
import at.technikum.elements.Rampe;
import at.technikum.elements.TunnelElement;

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

}
