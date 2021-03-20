package Objects;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.Color;
import java.util.LinkedList;

import Framework.GameObject;
import Framework.ObjectId;

public class Player extends GameObject {

    private float width = 32, height = 64;
    private float gravity = 0.1f;
    private final float MAX_SPEED = 10;

    public Player(float x, float y, ObjectId id) {
        super(x, y, id);
    }

    public void tick(LinkedList<GameObject> object) {
        x+=velX;
        y+=velY;

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
    }

    public Rectangle getBounds() {
        return new Rectangle((int) x, (int) y, (int) width, (int) height);
    }

}
