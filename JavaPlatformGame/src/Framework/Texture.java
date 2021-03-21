package Framework;

import Window.BufferedImageLoader;

import java.awt.image.BufferedImage;

public class Texture {
    SpriteSheet bs;
    SpriteSheet ps;
    private BufferedImage block_sheet;
    private BufferedImage player_sheet;

    public BufferedImage[] block = new BufferedImage[2];
    public BufferedImage[] player = new BufferedImage[8];

    public Texture(){
        BufferedImageLoader loader = new BufferedImageLoader();
        block_sheet = loader.loadImage("/blockSheet.png");
        player_sheet = loader.loadImage("/player_sheet.png");
        // try{
        //     block_sheet = loader.loadImage("/block_sheet.png");
        //     player_sheet = loader.loadImage("/player_sheet.png");
        // }catch(Exception e){
        //     e.printStackTrace();
        // }
        bs = new SpriteSheet(block_sheet);
        ps = new SpriteSheet(player_sheet);
        getTextures();
    }
    private void getTextures(){
        block[0] = bs.grabImage(1, 1, 32, 32);
        block[1] = bs.grabImage(2, 1, 32, 32);

        player[0] = ps.grabImage(1, 1, 32, 32);
        player[1] = ps.grabImage(1, 2, 32, 32);
        player[2] = ps.grabImage(1, 3, 32, 32);
        player[3] = ps.grabImage(1, 4, 32, 32);
        player[4] = ps.grabImage(1, 5, 32, 32);
        player[5] = ps.grabImage(1, 6, 32, 32);
        player[6] = ps.grabImage(1, 7, 32, 32);
        player[7] = ps.grabImage(1, 8, 32, 32);
    }
}
