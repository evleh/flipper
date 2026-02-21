package at.technikum.mediator;

import at.technikum.elements.BumperTarget;
import at.technikum.elements.FlipperElement;
import at.technikum.elements.TunnelElement;

/**
 * Class mediates the following functionality:
 * If targetA, targetB and targetC all have been hit at least once a tunnel element opens.
 *
 * If targetZ is hit, hit-counters for the other targets are reset.
 */
public class BumperTargetGroupMediator implements Mediator{

    private int[] hitCounter = new int[3];
    private BumperTarget targetA;
    private BumperTarget targetB;
    private BumperTarget targetC;

    private BumperTarget targetZ;

    private TunnelElement tunnelElement;

    public BumperTargetGroupMediator(BumperTarget targetA, BumperTarget targetB, BumperTarget targetC,
                                     BumperTarget targetZ, TunnelElement tunnelElement) {
        this.targetA = targetA;
        this.targetB = targetB;
        this.targetC = targetC;
        this.targetZ = targetZ;
        this.tunnelElement = tunnelElement;
    }

    @Override
    public void makeNotification(FlipperElement sender, String event) {
        System.out.println(sender == targetA);
        System.out.println(event);
    }
}
