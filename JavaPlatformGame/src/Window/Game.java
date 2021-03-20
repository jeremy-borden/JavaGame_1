package Window;

import java.awt.Canvas;
import java.awt.image.BufferStrategy;
import java.awt.Graphics;
import java.awt.Color;

public class Game extends Canvas implements Runnable {

    /**
     *
     */
    private static final long serialVersionUID = 8536431251891368679L;

    private boolean running = false;
    private Thread thread;

    public synchronized void start() {
        if (running)
            return;
        running = true;
        thread = new Thread(this);
        thread.start();
    }

    public void run() {
        long lastTime = System.nanoTime();
        final double tickAmount = 60.0;
        double ns = 1000000000 / tickAmount;
        double delta = 0;
        int updates = 0;
        int frames = 0;
        long timer = System.currentTimeMillis();
        while (running) {
            long now = System.nanoTime();
            delta += (now - lastTime) / ns;
            lastTime = now;
            if (delta >= 1) {
                tick();
                updates++;
                delta--;
            }
            render();
            frames++;
            if (System.currentTimeMillis() - timer > 1000) {
                timer += 1000;
                System.out.println("FPS: "+frames+" | Ticks: "+updates);
                updates = 0;
                frames = 0;
            }
        }
    }

    private void tick(){

    }

    private void render(){
        BufferStrategy bs = this.getBufferStrategy();
        if(bs == null){
            this.createBufferStrategy(3);
            return;
        }
        Graphics g = bs.getDrawGraphics();
        ////////
        g.setColor(Color.black);
        g.fillRect(0, 0, getWidth(), getHeight());
        ////////
        g.dispose();
        bs.show();
    }

    public static void main(String[] args) {
        new Window(800, 600, "Platform Prototype", new Game());
    }

}
