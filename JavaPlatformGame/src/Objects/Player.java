package Objects;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.Color;
import java.util.LinkedList;

import Framework.GameObject;
import Framework.ObjectId;

public class Player extends GameObject {

    private float width = 48, height = 96;
    private float gravity = 0.1f;
    private final float MAX_SPEED = 10;

    public Player(float x, float y, ObjectId id) {
        super(x, y, id);
    }

    public void tick(LinkedList<GameObject> object) {
        x+=velX;
        //y+=velY;

        if(falling || jumping){
            velY += gravity;

            if(velY > MAX_SPEED){
                velY = MAX_SPEED;
            }
        }
    }

    public void render(Graphics g) {
        g.setColor(Color.blue);
        g.fillRect((int) x, (int) y, (int) width, (int) height);

        Graphics2D g2d = (Graphics2D) g;

        g.setColor(Color.red);
        g2d.draw(getBounds());
        g2d.draw(getBoundsRight());
        g2d.draw(getBoundsLeft());
        g2d.draw(getBoundsTop());
    }

    public Rectangle getBounds() {
        return new Rectangle((int) (x + (width/2) - (width/4)), (int) (y + (height/2)), (int) width/2, (int) height/2);
    }

    public Rectangle getBoundsTop() {
        return new Rectangle((int) (x + (width/2) - (width/4)), (int) y, (int) width/2, (int) height/2 );
    }

    public Rectangle getBoundsRight() {
        return new Rectangle((int) (x+width-5), (int) y+5, 5, (int) height-10);
    }

    public Rectangle getBoundsLeft() {
        return new Rectangle((int) x, (int) y+5, 5, (int) height-10);
    }

}
