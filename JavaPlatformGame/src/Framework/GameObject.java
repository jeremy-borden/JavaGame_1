package Framework;

import java.util.LinkedList;
import java.awt.Graphics;
import java.awt.Rectangle;

public abstract class GameObject {

    protected float x, y;
    protected float velX = 0, velY = 0;
    protected ObjectId id;
    protected boolean falling = true;
    protected boolean jumping = false;

    public GameObject(float x, float y, ObjectId id) {
        this.x = x;
        this.y = y;
        this.id = id;
    }

    public abstract void tick(LinkedList<GameObject> object);

    public abstract void render(Graphics g);

    public abstract Rectangle getBounds();

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }

    public void setX(float x) {
        this.x = x;
    }

    public void setY(float y) {
        this.y = y;
    }

    public float getVelX() {
        return velX;
    }

    public float getVelY() {
        return velY;
    }

    public void setVelX(float velX) {
        this.velX = velX;
    }

    public void setVelY(float velY) {
        this.velY = velY;
    }

    public boolean getJumping() {
        return jumping;
    }

    public boolean getFalling() {
        return jumping;
    }

    public void setJumping(boolean jumping) {
        this.jumping = jumping;
    }

    public void setFalling(boolean falling) {
        this.falling = falling;
    }

    public ObjectId getId() {
        return id;
    }

}
