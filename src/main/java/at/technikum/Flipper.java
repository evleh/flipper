package at.technikum;

import at.technikum.abstractFactory.AbstractDisplayFactory;
import at.technikum.abstractFactory.FancyDisplayFactory;
import at.technikum.commands.*;
import at.technikum.elements.*;
import at.technikum.elements.external.ExternalLight;
import at.technikum.elements.GroupTarget;
import at.technikum.mediator.TargetGroupMediator;
import at.technikum.elements.TunnelElement;
import at.technikum.state.NoCreditState;
import at.technikum.state.Zustand;
import at.technikum.visitor.PointsVisitor;
import at.technikum.visitor.ResetVisitor;

import java.util.ArrayList;
import java.util.List;


public class Flipper {

    private Zustand zustand;
    private int credits = 0;
    private int remainingGames = 3;
    private int score;
    private List<FlipperElement> elements = new ArrayList<>();
    private AbstractDisplayFactory displayFactory;

    /**
     * Initiate Flipper with this Constructor to enable choosing output formatting.
     * @param displayFactory
     */
    public Flipper(AbstractDisplayFactory displayFactory){
        this.zustand = new NoCreditState(this); // braucht referenz an sich selbst
        this.displayFactory = displayFactory;
        this.score = 0;
    }

    public Flipper(){
        this(new FancyDisplayFactory());
    }
    public static void main(String[] args) {
        Flipper flipper = new Flipper();
        flipper.initialize();
        flipper.play();
    }

    private void initialize() {
        // Command Pattern
        FlipperElement rampe = new Rampe(new BlinkingLightCommand(this,50));
        elements.add(rampe);

        // Composite + Command Pattern
        MakroCommand hitHole = new MakroCommand("hitHole");
        hitHole.addCommand(new BallLostCommand(this));
        hitHole.addCommand(new ReportStatsCommand(this));
        FlipperElement hole = new Hole(hitHole);
        elements.add(hole);


        // Adapter Pattern
        ExternalLight externalLight = new ExternalLight();
        ExternalLightAdapter externalLightAdapter = new ExternalLightAdapter(new BlinkingLightCommand(this, 10), externalLight);
        elements.add(externalLightAdapter);

        // Mediator Pattern
        initializeTargetGroup();

        MakroCommand lightTargetCommand = new MakroCommand("LightTarget");
        lightTargetCommand.addCommand(new BlinkingLightCommand(this, 1));
        lightTargetCommand.addCommand(new ReportHitCommand(this));
        elements.add(new LightTarget(lightTargetCommand, false, "LightTarget", 50));
    }

    /**
     * Initializes components demonstrating the Mediator Pattern
     */
    private void initializeTargetGroup(){
        // define commands
        Command singleBumperHit = new ReportHitCommand(this);
        MakroCommand tunnelOpen = new MakroCommand("- TUNNEL OPEN (mediator pattern) -");
        tunnelOpen.addCommand(new ReportHitCommand(this));
        tunnelOpen.addCommand(new BlinkingLightCommand(this, 111));

        TargetGroupMediator mediator = new TargetGroupMediator();

        // create target elements
        GroupTarget targetA = new GroupTarget(singleBumperHit, mediator, "GroupTarget A", 20);
        GroupTarget targetB = new GroupTarget(singleBumperHit, mediator, "GroupTarget B", 20);
        GroupTarget targetC = new GroupTarget(singleBumperHit, mediator, "GroupTarget C", 20);
        GroupTarget targetZ = new GroupTarget(singleBumperHit, mediator, "GroupTarget - Resetting group count", 5);
        TunnelElement tunnel = new TunnelElement(tunnelOpen , "GroupTarget - Response TunnelElement", 1000);

        // add targets to mediator
        mediator.addToTargetGroup(targetA);
        mediator.addToTargetGroup(targetB);
        mediator.addToTargetGroup(targetC);
        mediator.setResetTarget(targetZ);
        mediator.setResponseElement(tunnel);

        // add targets to flipper
        elements.add(targetA);
        elements.add(targetB);
        elements.add(targetC);
        elements.add(targetZ);
        elements.add(tunnel);
    }


    public void play(){
        zustand.pressStart(); // No credit state.
        zustand.insertCoin(); // insert coin -> switch to credit state
        zustand.flipRight();
        zustand.pressStart(); // press start -> switch to playing state
        zustand.insertCoin();
        zustand.flipRight();
        zustand.pressStart();
        zustand.pressStart();
        zustand.flipLeft();

    }

    public void incrementCredits(){
        credits++;
    }

    public void decrementCredits(){
        credits--;
    }

    public void setZustand(Zustand zustand){
        this.zustand = zustand;
    }


    public void reportHit(){
        System.out.println("\tPoints scored!");
    }

    public List<FlipperElement> getElements(){
        return elements;
    }

    public void blinkLight(int seconds){
        System.out.println("\tblink light for: " + seconds + " sec");
    }

    public int getRemainingGames() {
        return remainingGames;
    }

    public void decrementRemainingGames() {
        this.remainingGames--;
    }

    public int getCredits(){
        return credits;
    }

    public void reset(){
        System.out.println("-------- Reset Elements ---------");
        ResetVisitor visitor = new ResetVisitor();
        for(FlipperElement element : elements){
            if(element != null){
                element.accept(visitor);
            }
        }
        // System.out.println(visitor.getResetReport());
    }

    public void calculateAllPoints(){
        System.out.println("--------- Calculate Scoring ---------");
        PointsVisitor visitor = new PointsVisitor();

        for(FlipperElement element : elements){
            if(element != null){
                element.accept(visitor);
            }
        }
        System.out.println("You scored: " + visitor.getTotalPoints() + " points in this round");
        this.score += visitor.getTotalPoints();
        System.out.println("Current score: " + this.score + "\n");
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public AbstractDisplayFactory getDisplayFactory() {
        return displayFactory;
    }

    public Zustand getZustand() {
        return zustand;
    }
}
