package at.technikum.mediator;

import at.technikum.elements.FlipperElement;
import at.technikum.elements.GroupTarget;
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

    private List<GroupTarget> targetGroup = new ArrayList<>();

    private GroupTarget resetTarget;

    private TunnelElement responseElement;


    public TargetGroupMediator() {}

    @Override
    public void makeNotification(FlipperElement sender, String event) {

        for (int i = 0; i < targetGroup.size(); i++) {
            if(sender == targetGroup.get(i)){
                System.out.println("\tTarget: " +  event);
                targetGroup.get(i).setHitCount(targetGroup.get(i).getHitCount() + 1);
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

        for(GroupTarget target : targetGroup){
            if(target.getHitCount() == 0){
                return false;
            }
        }
        return true;
    }

    public void addToTargetGroup(GroupTarget newTarget){
        targetGroup.add(newTarget);
    }

    public void setResetTarget(GroupTarget resetTarget){
        this.resetTarget = resetTarget;
    }

    public void setResponseElement(TunnelElement responseElement) {
        this.responseElement = responseElement;
    }

    public void resetCounter(){
        System.out.println("- MEDIATOR: RESET TARGET GROUP -");
        for(GroupTarget target : targetGroup){
            target.setHitCount(0);
        }
    }
}
