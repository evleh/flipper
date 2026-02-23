package at.technikum.abstractFactory;

public class FancyDisplayFactory implements AbstractDisplayFactory{
    @Override
    public void printPressStart(String s) {
        System.out.println("FANCY: " + s);
    }
}
