package at.technikum.mediator;

import at.technikum.elements.BumperTarget;
import at.technikum.elements.FlipperElement;
import at.technikum.elements.TunnelElement;

import java.util.ArrayList;
import java.util.List;

/**
 * Class mediates the following functionality:
 * If targetA, targetB and targetC all have been hit at least once a tunnel element opens.
 *
 * If targetZ is hit, hit-counters for the other targets are reset.
 */
public class TargetGroupMediator implements Mediator{

    private int[] hitCounter = new int[3];

    private List<BumperTarget> targetGroup = new ArrayList<>();

    private BumperTarget resetTarget;

    private TunnelElement responseElement;


    public TargetGroupMediator() {}

    @Override
    public void makeNotification(FlipperElement sender, String event) {
        System.out.println(sender == targetGroup.getFirst());
        System.out.println(event);
    }

    public void addToTargetGroup(BumperTarget newTarget){
        targetGroup.add(newTarget);
    }

    public void setResetTarget(BumperTarget resetTarget){
        this.resetTarget = resetTarget;
    }

    public void setResponseElement(TunnelElement responseElement) {
        this.responseElement = responseElement;
    }
}
