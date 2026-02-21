package at.technikum.mediator;

import at.technikum.elements.BumperTarget;
import at.technikum.elements.FlipperElement;
import at.technikum.elements.TunnelElement;

import java.util.ArrayList;
import java.util.List;

/**
 * Class mediates the following functionality:
 * If all targets in the targetGroup have been hit at least once, the responseElement is triggert and
 * counter is reset.
 *
 * If resetTarget is hit, counter is reset without triggering the response Element
 *
 * Note: Functionality mediated with this class, provides additional functionality to the basic command registered
 * in target group elements. todo check this
 */
public class TargetGroupMediator implements Mediator{

    private List<Integer> hitCounter = new ArrayList<>();

    private List<BumperTarget> targetGroup = new ArrayList<>();

    private BumperTarget resetTarget;

    private TunnelElement responseElement;


    public TargetGroupMediator() {}

    @Override
    public void makeNotification(FlipperElement sender, String event) {

        for (int i = 0; i < targetGroup.size(); i++) {
            if(sender == targetGroup.get(i)){
                System.out.println("\tTarget: " +  event);
                hitCounter.set(i, hitCounter.get(i) + 1);
            }

        }
        if(sender == resetTarget){
            resetCounter();
            return;
        }

        if(allTargetsHit()) {
            responseElement.hit();
            resetCounter();
        }

    }

    public boolean allTargetsHit(){

        for(Integer i : hitCounter){
            if(i == 0){
                return false;
            }
        }
        return true;
    }

    public void addToTargetGroup(BumperTarget newTarget){
        targetGroup.add(newTarget);
        hitCounter.add(0);
    }

    public void setResetTarget(BumperTarget resetTarget){
        this.resetTarget = resetTarget;
    }

    public void setResponseElement(TunnelElement responseElement) {
        this.responseElement = responseElement;
    }

    public void resetCounter(){
        System.out.println("- RESET TARGET GROUP -");
        for (int i = 0; i < hitCounter.size(); i++) {
            hitCounter.set(i, 0);
        }
    }
}
