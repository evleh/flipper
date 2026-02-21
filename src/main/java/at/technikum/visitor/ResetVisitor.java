package at.technikum.visitor;

import at.technikum.elements.LightTarget;
import at.technikum.elements.Rampe;

public class ResetVisitor implements Visitor{
    @Override
    public void visit(LightTarget e) {
        System.out.println("#### VISITOR #####");
        e.setOn(false);
    }

//    @Override
//    public void visit(Rampe e) {
//
//    }
}
