package tile;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.io.IOException;
import javax.imageio.ImageIO;
import main.Board;

public class Tile {
    public BufferedImage image; 
    public int x, y; 
    public int col, row; 

    public Tile (int col, int row){
        this.col = col; 
        this.row = row; 
        this.image = getImage("grass"); 
        x = getX(col); 
        y = getY(row); 
    }

    public BufferedImage getImage(String imagePath){
        image = null; 
        try{
            image = ImageIO.read(new FileInputStream("SimpleAnimalChess/res/tile/" + imagePath + ".png")); 
        }catch(IOException e){
            e.printStackTrace();
        }
        return image; 
    }

    public int getX(int col){
        return col*Board.SQUARE_SIZE; 
    }

    public int getY(int row){
        return row*Board.SQUARE_SIZE; 
    }

    public void draw(Graphics2D g2){
        g2.drawImage(image, x, y, Board.SQUARE_SIZE, Board.SQUARE_SIZE, null); 
    }
}
