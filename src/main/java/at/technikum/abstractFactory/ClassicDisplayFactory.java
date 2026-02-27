package at.technikum.abstractFactory;

/**
 * Classic Display is display without any fancy formatting
 */
public class ClassicDisplayFactory implements AbstractDisplayFactory{
    @Override
    public void printPressStart() {
        String start = "------------- PRESS START TO PLAY ------------";
        System.out.println(start);
    }

    @Override
    public void printBallLost(){
        String start = "------------- BALL LOST ------------";
        System.out.println(start);
    }
}
