package at.technikum.visitor;

import at.technikum.elements.*;
import at.technikum.elements.grouped.GroupTarget;

public interface Visitor {
    void visit(LightTarget e);

    void visit(TunnelElement e);

    void visit(ExternalLightAdapter externalLightAdapter);

    void visit(Hole hole);

    void visit(Rampe rampe);

    void visit(GroupTarget groupTarget);
}
