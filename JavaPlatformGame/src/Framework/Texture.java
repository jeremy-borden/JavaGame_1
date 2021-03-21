package Framework;

import Window.BufferedImageLoader;

import java.awt.image.BufferedImage;

public class Texture {
    SpriteSheet bs;
    SpriteSheet ps;
    private BufferedImage block_sheet;
    private BufferedImage player_sheet;

    public BufferedImage[] block = new BufferedImage[2];

    public Texture(){
        BufferedImageLoader loader = new BufferedImageLoader();
        try{
            block_sheet  = loader.loadImage("block_sheet.png");
            player_sheet  = loader.loadImage("player_sheet.png");
        }catch(Exception e){
            e.printStackTrace();
        }
        bs = new SpriteSheet(block_sheet);
        ps = new SpriteSheet(player_sheet);
        getTextures();
    }
    private void getTextures(){
        block[0] = bs.grabImage(1, 1, 32, 32);
        block[1] = bs.grabImage(2, 1, 32, 32);
    }
}
