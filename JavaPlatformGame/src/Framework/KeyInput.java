package Framework;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import Window.ObjectHandler;

public class KeyInput extends KeyAdapter {

    ObjectHandler handler;
    private boolean pressing_A = false;
    private boolean pressing_D = false;

    public KeyInput(ObjectHandler handler) {
        this.handler = handler;
    }

    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();

        for(int i = 0; i<handler.object.size(); i++){
            GameObject tempObject = handler.object.get(i);

            if(tempObject.getId() == ObjectId.Player){
                if(key == KeyEvent.VK_D){
                    tempObject.setVelX(5);
                    pressing_D = true;
                }
                if(key == KeyEvent.VK_A){
                    pressing_A = true;
                    tempObject.setVelX(-5);
                }
                if(key == KeyEvent.VK_SPACE && !tempObject.getJumping()){
                    tempObject.setJumping(true);
                    tempObject.setVelY(-15);
                }
                    
            }
        }

        if(key == KeyEvent.VK_ESCAPE){
            System.exit(1);
        }
    }

    public void keyReleased(KeyEvent e) {
        int key = e.getKeyCode();

        for(int i = 0; i<handler.object.size(); i++){
            GameObject tempObject = handler.object.get(i);

            if(tempObject.getId() == ObjectId.Player){
                if(key == KeyEvent.VK_D){
                    if(pressing_A == true)
                        tempObject.setVelX(-5);
                    else
                        tempObject.setVelX(0);
                    pressing_D = false;
                }
                if(key == KeyEvent.VK_A){
                    if(pressing_D == true)
                        tempObject.setVelX(5);
                    else
                        tempObject.setVelX(0);
                    pressing_A = false;
                }
                    
            }
        }
    }
}