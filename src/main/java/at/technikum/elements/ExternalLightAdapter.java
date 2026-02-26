package at.technikum.elements;

import at.technikum.commands.Command;
import at.technikum.elements.external.ExternalLight;
import at.technikum.visitor.Visitor;

public class ExternalLightAdapter extends FlipperElement{
    private ExternalLight externalLight;

    public ExternalLightAdapter(Command command, ExternalLight externalLight) {
        super(command);
        this.externalLight = externalLight;
    }

    @Override
    public void hit(){
        this.externalLight.onHit();
        super.hit();
    }


    @Override
    public void accept(Visitor v) {
        v.visit(this);
    }

    public boolean isOn() {
        return externalLight.isOn();
    }

    public void setOn(boolean on) {
        externalLight.setOn(on);
    }
}
