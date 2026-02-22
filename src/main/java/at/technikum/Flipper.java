package at.technikum;

import at.technikum.commands.*;
import at.technikum.elements.*;
import at.technikum.elements.external.ExternalLight;
import at.technikum.elements.grouped.GroupTarget;
import at.technikum.mediator.TargetGroupMediator;
import at.technikum.elements.TunnelElement;
import at.technikum.state.NoCreditState;
import at.technikum.state.Zustand;
import at.technikum.visitor.ResetVisitor;
import at.technikum.visitor.Resettable;

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
    private List<FlipperElement> elements = new ArrayList<>();


    public Flipper(){
        this.zustand = new NoCreditState(this); // braucht referenz an sich selbst
    }

    public static void main(String[] args) {
        Flipper flipper = new Flipper();
        flipper.initialize();
        flipper.play();
    }

    private void initialize() {
        // Composite + command pattern
        MakroCommand hitRampe = new MakroCommand("hitRampe");
        hitRampe.addCommand(new AddPointsCommand(this, 20));
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
        ExternalLightAdapter externalLightAdapter = new ExternalLightAdapter(new BlinkingLightCommand(this, 10),
                externalLight);
        elements.add(externalLightAdapter);

        initializeTargetGroup();

        elements.add(new LightTarget(new BlinkingLightCommand(this, 1), false, "LightTarget"));
    }

    /**
     * Initializes components demonstrating the Mediator Pattern
     */
    private void initializeTargetGroup(){
        // define commands
        Command singleBumperHit = new AddPointsCommand(this, 5);
        MakroCommand tunnelOpen = new MakroCommand("- TUNNEL OPEN (mediator pattern) -");
        tunnelOpen.addCommand(new AddPointsCommand(this, 1000));
        tunnelOpen.addCommand(new BlinkingLightCommand(this, 111));

        TargetGroupMediator mediator = new TargetGroupMediator();

        // create target elements
        GroupTarget targetA = new GroupTarget(singleBumperHit, mediator, "A");
        GroupTarget targetB = new GroupTarget(singleBumperHit, mediator, "B");
        GroupTarget targetC = new GroupTarget(singleBumperHit, mediator, "C");
        GroupTarget targetZ = new GroupTarget(singleBumperHit, mediator, "Z");
        TunnelElement tunnel = new TunnelElement(tunnelOpen , "TunnelElement");

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

    public void addPoints(int points){
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

    }

    public int getCredits(){
        return credits;
    }

    public void reset(){
        System.out.println("--------- RESET VISITOR: START VISITING --------");
        ResetVisitor visitor = new ResetVisitor();
        for(FlipperElement element : elements){
            if(element instanceof Resettable){
                ((Resettable) element).accept(visitor);
            }
        }
        System.out.println("--------- RESET VISITOR: DONE VISITING --------");
    }
}
