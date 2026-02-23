package at.technikum.elements;

import at.technikum.commands.Command;
import at.technikum.elements.external.ExternalLight;

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



}
