package Objects;

import java.awt.Graphics;
//import java.awt.Graphics2D;
import java.awt.Rectangle;
//import java.awt.Color;
import java.util.LinkedList;

import Framework.GameObject;
import Framework.ObjectId;
import Framework.Texture;
import Window.Animation;
import Window.Game;
import Window.ObjectHandler;

public class Player extends GameObject {

    private float width = 48, height = 96;
    private float gravity = 0.5f;
    private final float MAX_SPEED = 10;

    private ObjectHandler handler;

    Texture tex = Game.getInstance();

    private Animation playerWalk;

    public Player(float x, float y, ObjectHandler handler, ObjectId id) {
        super(x, y, id);
        this.handler = handler;
        playerWalk = new Animation(1, tex.player[1], tex.player[2], tex.player[3], tex.player[4], tex.player[5],
                tex.player[6], tex.player[7]);
    }

    public void tick(LinkedList<GameObject> object) {
        x += velX;
        y += velY;

        if (falling || jumping) {
            velY += gravity;

            if (velY > MAX_SPEED) {
                velY = MAX_SPEED;
            }
        }

        Collision(object);

        playerWalk.runAnimation();
    }

    private void Collision(LinkedList<GameObject> object) {
        for (int i = 0; i < handler.object.size(); i++) {
            GameObject tempObject = handler.object.get(i);
            if (tempObject.getId() == ObjectId.Block) {
                // TOP COLLISION
                if (getBoundsTop().intersects(tempObject.getBounds())) {
                    y = tempObject.getY() + 32;
                    velY = 0;
                }
                // BOTTOM COLLISION
                if (getBounds().intersects(tempObject.getBounds())) {
                    y = tempObject.getY() - height;
                    velY = 0;
                    falling = false;
                    jumping = false;
                } else {
                    falling = true;
                }
                // RIGHT COLLISION
                if (getBoundsRight().intersects(tempObject.getBounds())) {
                    x = tempObject.getX() - (width + 1);
                }
                // LEFT COLLISION
                if (getBoundsLeft().intersects(tempObject.getBounds())) {
                    x = tempObject.getX() + 33;
                }
            }

        }
    }

    public void render(Graphics g) {
        // g.setColor(Color.blue);
        // g.fillRect((int) x, (int) y, (int) width, (int) height);
        if (velX != 0)
            playerWalk.drawAnimation(g, (int) x, (int) y, 48, 96);
        else
            g.drawImage(tex.player[0], (int) x, (int) y, 48, 96, null);

        // Graphics2D g2d = (Graphics2D) g;

    }

    public Rectangle getBounds() {
        return new Rectangle((int) (x + (width / 2) - (width / 4)), (int) (y + (height / 2)), (int) width / 2,
                (int) height / 2);
    }

    public Rectangle getBoundsTop() {
        return new Rectangle((int) (x + (width / 2) - (width / 4)), (int) y, (int) width / 2, (int) height / 2);
    }

    public Rectangle getBoundsRight() {
        return new Rectangle((int) (x + width - 5), (int) y + 5, 5, (int) height - 10);
    }

    public Rectangle getBoundsLeft() {
        return new Rectangle((int) x, (int) y + 5, 5, (int) height - 10);
    }

}
