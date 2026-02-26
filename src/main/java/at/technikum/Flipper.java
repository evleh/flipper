package at.technikum;

import at.technikum.abstractFactory.AbstractDisplayFactory;
import at.technikum.abstractFactory.ClassicDisplayFactory;
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

/**
 * Kontext
 * Zustand ist als abstrakte klasse definiert. Zur laufzeit ist es eine Implementation
 *
 *
 * Kompositum: anderes Muster um Komplexe Objekte zusammen zu führen e.g.
 * gibt flipper Elemente die unterschiedliche sachen machen
 *
 * Tutorium: kompositum, Befehl 
 */

// todo: examples for makrocommands: Hole konfigurieren, sodass ein Befehl für die Punktevergabe zuständig ist und ein weiterer Befehl den Spieler bzw. die Spielerin zwischen 1, 2 und 3 wählen lässt, wobei es beim Erraten Zusatzpunkte gibt.
// todo adpter durchdenken: ist es genug das Methoden namen inkompatibel sind ??
// Zusätzliche Patterns: Mediator, Factory
public class Flipper {

    private Zustand zustand;
    private int credits = 0;
    private int remainingGames = 3;
    private int score;
    private List<FlipperElement> elements = new ArrayList<>();
    AbstractDisplayFactory displayFactory;


    public Flipper(){

        this.zustand = new NoCreditState(this); // braucht referenz an sich selbst
        this.displayFactory = new ClassicDisplayFactory();
        this.score = 0;
    }

    public static void main(String[] args) {
        Flipper flipper = new Flipper();
        flipper.initialize();
        flipper.play();
    }

    private void initialize() {
        // Composite + command pattern
        MakroCommand hitRampe = new MakroCommand("hitRampe");
        hitRampe.addCommand(new ReportHitCommand(this, 20));
        hitRampe.addCommand(new BlinkingLightCommand(this,50));

        FlipperElement rampe = new Rampe(hitRampe);
        elements.add(rampe);


        MakroCommand hitHole = new MakroCommand("hitHole");
        hitHole.addCommand(new GameLostCommand(this));
        hitHole.addCommand(new ReportStatsCommand(this));
        FlipperElement hole = new Hole(hitHole);
        elements.add(hole);

        // Adapter Pattern
        ExternalLight externalLight = new ExternalLight();
        ExternalLightAdapter externalLightAdapter = new ExternalLightAdapter(new BlinkingLightCommand(this, 10), externalLight);
        elements.add(externalLightAdapter);

        initializeTargetGroup();

        Command blink = new BlinkingLightCommand(this, 1);
        elements.add(new LightTarget(blink, false, "LightTarget", 50));
    }

    /**
     * Initializes components demonstrating the Mediator Pattern
     */
    private void initializeTargetGroup(){
        // define commands
        Command singleBumperHit = new ReportHitCommand(this, 5);
        MakroCommand tunnelOpen = new MakroCommand("- TUNNEL OPEN (mediator pattern) -");
        tunnelOpen.addCommand(new ReportHitCommand(this, 1000));
        tunnelOpen.addCommand(new BlinkingLightCommand(this, 111));

        TargetGroupMediator mediator = new TargetGroupMediator();

        // create target elements
        GroupTarget targetA = new GroupTarget(singleBumperHit, mediator, "GroupTarget A", 10);
        GroupTarget targetB = new GroupTarget(singleBumperHit, mediator, "GroupTarget B", 20);
        GroupTarget targetC = new GroupTarget(singleBumperHit, mediator, "GroupTarget C", 30);
        GroupTarget targetZ = new GroupTarget(singleBumperHit, mediator, "GroupTarget - Resetting group count", 0);
        TunnelElement tunnel = new TunnelElement(tunnelOpen , "GroupTarget - Response TunnelElement");

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

    public void tryClassicDisplay(){
        System.out.println("------ CLASSIC DISPLAY: START ------");
        this.displayFactory.printPressStart(zustand.pressStart());
        System.out.println("------ CLASSIC DISPLAY: STOP ------\n");
    }

    public void tryFancyDisplay(){
        this.displayFactory = new FancyDisplayFactory();
        System.out.println("------ FANCY DISPLAY: START ------");
        this.displayFactory.printPressStart(zustand.pressStart());
        System.out.println("------ FANCY DISPLAY: STOP ------\n");
    }

    public void play(){
        System.out.println(zustand.pressStart()); // No credit state.
        zustand.insertCoin(); // insert coin -> switch to credit state
        zustand.pressStart(); // press start -> switch to playing state
        zustand.insertCoin();
        zustand.flipRight();
        zustand.pressStart();
        zustand.pressStart();
        zustand.flipLeft();

        tryClassicDisplay();
        tryFancyDisplay();
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


    public void reportHit(int points){
        System.out.println("\tPunkte erhalten: "+ points);
        // sout sie haben punkte erhalten
    }

    // to do getElements Methode
    public List<FlipperElement> getElements(){
        return elements;
    }

    public void blinkLight(int seconds){
        System.out.println("\tblink light for: " + seconds + " sec");
    }

    public int getRemainingGames() {
        return remainingGames;
    }

    public void onGameLost() {
        this.remainingGames--;
        System.out.println("You lost a game. Remaining games: " + this.remainingGames);
        this.calculateAllPoints();

    }

    public int getCredits(){
        return credits;
    }

    public void reset(){
        System.out.println("--------- RESET VISITOR: START VISITING --------");
        ResetVisitor visitor = new ResetVisitor();
        for(FlipperElement element : elements){
            if(element != null){
                element.accept(visitor);
            }
        }
        System.out.println("--------- RESET VISITOR: DONE VISITING --------");
    }

    public void calculateAllPoints(){
        System.out.println("--------- POINTS VISITOR: START VISITING --------");
        PointsVisitor visitor = new PointsVisitor();

        for(FlipperElement element : elements){
            if(element != null){
                element.accept(visitor);
            }
        }
        System.out.println("You scored: " + visitor.getTotalPoints() + " points in this round");
        this.score += visitor.getTotalPoints();
        System.out.println("Current score: " + this.score);
        System.out.println("--------- POINTS VISITOR: DONE VISITING --------");
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }
}
