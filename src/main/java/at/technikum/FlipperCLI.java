package at.technikum;

import java.io.IOException;
import java.util.*;

public class FlipperCLI {

    static final int WIDTH = 40;
    static final int HEIGHT = 20;

    static char[][] screen = new char[HEIGHT][WIDTH];

    static double ballX = WIDTH / 2.0;
    static double ballY = HEIGHT / 2.0;
    static double velX = 0.5;
    static double velY = 0.0;

    static boolean leftFlipper = false;
    static boolean rightFlipper = false;

    static int score = 0;
    static boolean running = true;

    static List<int[]> bumpers = List.of(
            new int[]{10, 6},
            new int[]{20, 8},
            new int[]{30, 6}
    );

    public static void main(String[] args) throws Exception {
        Thread input = new Thread(FlipperCLI::handleInput);
        input.setDaemon(true);
        input.start();

        while (running) {
            update();
            render();
            Thread.sleep(80);
        }

        System.out.println("Game Over! Score: " + score);
    }

    static void handleInput() {
        try {
            while (running) {
                int c = System.in.read();
                if (c == 'a') leftFlipper = true;
                if (c == 'd') rightFlipper = true;
                if (c == 'q') running = false;
            }
        } catch (IOException ignored) {}
    }

    static void update() {
        // gravity
        velY += 0.15;

        // move ball
        ballX += velX;
        ballY += velY;

        // wall collisions
        if (ballX <= 1 || ballX >= WIDTH - 2) velX *= -1;
        if (ballY <= 1) velY *= -1;

        // bumpers
        for (int[] b : bumpers) {
            if (Math.round(ballX) == b[0] && Math.round(ballY) == b[1]) {
                velY = -2.5;
                velX += (Math.random() - 0.5);
                score += 10;
            }
        }

        // flippers
        int flipperY = HEIGHT - 3;

        if (Math.round(ballY) == flipperY) {
            if (leftFlipper && ballX < WIDTH / 2) {
                velY = -3;
                velX = -1.5;
            }
            if (rightFlipper && ballX >= WIDTH / 2) {
                velY = -3;
                velX = 1.5;
            }
        }

        leftFlipper = false;
        rightFlipper = false;

        // ball lost
        if (ballY >= HEIGHT - 1) {
            running = false;
        }
    }

    static void render() {
        clearScreen();

        for (char[] row : screen)
            Arrays.fill(row, ' ');

        // borders
        for (int x = 0; x < WIDTH; x++) {
            screen[0][x] = '#';
            screen[HEIGHT - 1][x] = '#';
        }
        for (int y = 0; y < HEIGHT; y++) {
            screen[y][0] = '#';
            screen[y][WIDTH - 1] = '#';
        }

        // bumpers
        for (int[] b : bumpers)
            screen[b[1]][b[0]] = 'O';

        // flippers
        screen[HEIGHT - 2][WIDTH / 2 - 5] = '/';
        screen[HEIGHT - 2][WIDTH / 2 + 4] = '\\';

        // ball
        screen[(int) Math.round(ballY)][(int) Math.round(ballX)] = '*';

        // draw
        StringBuilder sb = new StringBuilder();
        sb.append("Score: ").append(score).append('\n');
        for (char[] row : screen)
            sb.append(row).append('\n');

        System.out.print(sb);
    }

    static void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }
}

