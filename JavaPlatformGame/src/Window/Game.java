package Window;

import java.awt.Canvas;

public class Game extends Canvas implements Runnable {

    /**
     *
     */
    private static final long serialVersionUID = 8536431251891368679L;

    public void run() {

    }

    public static void main(String[] args) {
        new Window(800, 600, "Platform Prototype", new Game());
    }

}
