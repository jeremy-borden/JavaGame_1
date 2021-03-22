package Objects;

import java.awt.Graphics;
import java.awt.Color;
import java.awt.Rectangle;
import java.util.LinkedList;

import Framework.GameObject;
import Framework.ObjectId;

public class Bullet extends GameObject {

    public Bullet(float x, float y, ObjectId id) {
        super(x, y, id);
    }

    public void tick(LinkedList<GameObject> object) {
        x += velX;
    }

    public void render(Graphics g) {
        g.setColor(Color.red);
        g.fillRect((int)x,(int)y, 16, 16);

    }

    public Rectangle getBounds() {
        return new Rectangle((int)x,(int)y,16,16);
    }
    
}
