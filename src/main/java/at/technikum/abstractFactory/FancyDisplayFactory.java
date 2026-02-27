package at.technikum.abstractFactory;

public class FancyDisplayFactory implements AbstractDisplayFactory{
    @Override
    public void printPressStart() {
        String start = "                                _             _     _                _             \n" +
                "                               | |           | |   | |              | |            \n" +
                "  _ __  _ __ ___  ___ ___   ___| |_ __ _ _ __| |_  | |_ ___    _ __ | | __ _ _   _ \n" +
                " | '_ \\| '__/ _ \\/ __/ __| / __| __/ _` | '__| __| | __/ _ \\  | '_ \\| |/ _` | | | |\n" +
                " | |_) | | |  __/\\__ \\__ \\ \\__ \\ || (_| | |  | |_  | || (_) | | |_) | | (_| | |_| |\n" +
                " | .__/|_|  \\___||___/___/ |___/\\__\\__,_|_|   \\__|  \\__\\___/  | .__/|_|\\__,_|\\__, |\n" +
                " | |                                                          | |             __/ |\n" +
                " |_|                                                          |_|            |___/ ";

        System.out.println(start);
    }

    @Override
    public void printBallLost() {
        String s = "  _           _ _   _           _   \n" +
                " | |         | | | | |         | |  \n" +
                " | |__   __ _| | | | | ___  ___| |_ \n" +
                " | '_ \\ / _` | | | | |/ _ \\/ __| __|\n" +
                " | |_) | (_| | | | | | (_) \\__ \\ |_ \n" +
                " |_.__/ \\__,_|_|_| |_|\\___/|___/\\__|\n" +
                "                                    \n" +
                "                                    ";
        System.out.println(s);
    }
}
