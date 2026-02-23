package at.technikum.abstractFactory;

/**
 * Classic Display is display without any fancy formatting
 */
public class ClassicDisplayFactory implements AbstractDisplayFactory{
    @Override
    public void printPressStart(String s) {
        System.out.println("CLASSIC: " + s);
    }
}
