package at.technikum.visitor;

import at.technikum.elements.LightTarget;
import at.technikum.elements.TunnelElement;

public interface Visitor {
    void visit(LightTarget e);

    void visit(TunnelElement e);
}
