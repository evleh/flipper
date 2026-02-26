package at.technikum.elements.external;

public class ExternalLight {
    private boolean isOn = false;

    public void onHit(){
        System.out.print("\tEXTERNAL LIGHT: ");
    }

    public boolean isOn() {
        return isOn;
    }

    public void setOn(boolean on) {
        isOn = on;
    }
}
