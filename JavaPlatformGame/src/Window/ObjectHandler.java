package Window;

import java.awt.Graphics;
import java.util.LinkedList;

import Framework.GameObject;
import Framework.ObjectId;
import Objects.Block;

public class ObjectHandler {

    public LinkedList<GameObject> object = new LinkedList<GameObject>();

    private GameObject tempObject;

    public void tick() {
        for (int i = 0; i < object.size(); i++) {
            tempObject = object.get(i);
            tempObject.tick(object);
        }
    }

    public void render(Graphics g) {
        for (int i = 0; i < object.size(); i++) {
            tempObject = object.get(i);
            tempObject.render(g);
        }
    }

    public void addObject(GameObject object) {
        this.object.add(object);
    }

    public void removeObject(GameObject object) {
        this.object.remove(object);
    }

    public void createLevel(){
        /*
        for(int xx = 0; xx < Game.WIDTH*4; xx+=32){//floor
            addObject(new Block(xx,Game.HEIGHT-32,ObjectId.Block));
        }
        for(int xx = 2*(Game.WIDTH/8); xx < 6*(Game.WIDTH/8); xx+=32){////platformn
            addObject(new Block(xx,Game.HEIGHT-232,ObjectId.Block));
        }
        // for(int yy = 0; yy < Game.HEIGHT-32; yy+=32){
        //     addObject(new Block(Game.WIDTH-33,yy,ObjectId.Block));
        // }
        for(int yy = 0; yy < Game.HEIGHT-32; yy+=32){///left wall
            addObject(new Block(0,yy,ObjectId.Block));
        }
        */
    }

}
