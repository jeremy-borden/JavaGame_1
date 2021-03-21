package Window;

import java.awt.Canvas;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;

import Framework.KeyInput;
import Framework.ObjectId;
import Framework.Texture;
import Objects.Block;
//import Objects.Player;
import Objects.Player;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Color;

public class Game extends Canvas implements Runnable {

    private static final long serialVersionUID = 8536431251891368679L;

    private boolean running = false;
    private Thread thread;

    public static int WIDTH, HEIGHT;

    private BufferedImage level = null;

    ObjectHandler handler; // Object
    Camera cam;
    static Texture tex;

    private void init() {
        requestFocus();
        WIDTH = getWidth();
        HEIGHT = getHeight();

        tex = new Texture();

        BufferedImageLoader loader = new BufferedImageLoader();
        
        level = loader.loadImage("/level.png");//Loading level

        handler = new ObjectHandler();

        cam = new Camera(0, 0);

        loadImageLevel(level);

        //handler.addObject(new Player(100, 100, handler, ObjectId.Player));
        //handler.createLevel();

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
        for(int i = 0; i < handler.object.size(); i++){
            if(handler.object.get(i).getId() == ObjectId.Player){
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
        g.setColor(Color.black);
        g.fillRect(0, 0, getWidth(), getHeight());

        g2d.translate(cam.getX(), cam.getY());///BEGIN OF CAM

        handler.render(g);

        g2d.translate(-cam.getX(), -cam.getY());//END OF CAM
        ////////
        g.dispose();
        bs.show();
    }

    private void loadImageLevel(BufferedImage image){
        int w =image.getWidth();
        int h = image.getHeight();

        for(int xx = 0; xx < h; xx++){
            for(int yy = 0; yy < w; yy++){
                int pixel  = image.getRGB(xx,yy);
                int red = (pixel >> 16) & 0xff;
                int green = (pixel >> 8) & 0xff;
                int blue = (pixel) & 0xff;

                if(red == 255 && green == 255 && blue == 255)
                    handler.addObject(new Block(xx*32, yy*32, 0, ObjectId.Block));
                if(red == 0 && green == 0 && blue == 255)
                    handler.addObject(new Player(xx*32, yy*32, handler, ObjectId.Player));
                
            }
        }
    }

    public static Texture getInstance(){
        return tex;
    }

    public static void main(String[] args) {
        new Window(800, 600, "Platform Prototype", new Game());
    }

}
