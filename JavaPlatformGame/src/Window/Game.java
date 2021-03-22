package Window;

import java.awt.Canvas;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;

import Framework.KeyInput;
import Framework.ObjectId;
import Framework.Texture;
//import Objects.Block;
//import Objects.Flag;
//import Objects.Player;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Color;

public class Game extends Canvas implements Runnable {

    private static final long serialVersionUID = 8536431251891368679L;

    private boolean running = false;
    private Thread thread;

    public static int WIDTH, HEIGHT;

    public BufferedImage level = null, level2 = null, clouds = null;

    ObjectHandler handler; // Object
    Camera cam;
    public static Texture tex;

    public static int LEVEL = 1;

    private void init() {
        requestFocus();
        WIDTH = getWidth();
        HEIGHT = getHeight();

        BufferedImageLoader loader = new BufferedImageLoader();
        tex = new Texture();

        level = loader.loadImage("/level.png");// Loading level
        // level2 = loader.loadImage("/level2.png");// Loading level
        // clouds = loader.loadImage("/bkg_cloud.png"); // load clound backgoubnd
        cam = new Camera(0, 0);
        handler = new ObjectHandler(cam);


        handler.loadImageLevel(level);

        // handler.addObject(new Player(100, 100, handler, ObjectId.Player));
        // handler.createLevel();

        this.addKeyListener(new KeyInput(handler));
    }

    public synchronized void start() {
        if (running)
            return;
        running = true;
        thread = new Thread(this);
        thread.start();
    }

    public void run() {
        init();
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
                System.out.println("FPS: " + frames + " | Ticks: " + updates);
                updates = 0;
                frames = 0;
            }
        }
    }

    private void tick() {
        handler.tick();
        for (int i = 0; i < handler.object.size(); i++) {
            if (handler.object.get(i).getId() == ObjectId.Player) {
                cam.tick(handler.object.get(i));
            }
        }
    }

    private void render() {
        BufferStrategy bs = this.getBufferStrategy();
        if (bs == null) {
            this.createBufferStrategy(3);
            return;
        }
        Graphics g = bs.getDrawGraphics();
        Graphics2D g2d = (Graphics2D) g;
        ////////
        g.setColor(new Color(25, 191, 224));
        g.fillRect(0, 0, getWidth(), getHeight());

        // g.drawImage(clouds, 0, 0, 128,128,this);

        g2d.translate(cam.getX(), cam.getY());/// BEGIN OF CAM
        for (int xx = 0; xx < 128 * 5; xx += 128 * 3)
            g.drawImage(clouds, xx, 10, 128, 128, this);
        handler.render(g);

        g2d.translate(-cam.getX(), -cam.getY());// END OF CAM
        ////////
        g.dispose();
        bs.show();
    }

    public static Texture getInstance() {
        return tex;
    }

    public static void main(String[] args) {
        new Window(800, 600, "Platform Prototype", new Game());
    }

}
