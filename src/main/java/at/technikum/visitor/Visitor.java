package at.technikum.visitor;

import at.technikum.elements.*;
import at.technikum.elements.grouped.GroupTarget;

public interface Visitor {
    void visit(LightTarget e);

    void visit(TunnelElement e);

    void accept(ExternalLightAdapter externalLightAdapter);

    void accept(Hole hole);

    void accept(Rampe rampe);

    void accept(GroupTarget groupTarget);
}
