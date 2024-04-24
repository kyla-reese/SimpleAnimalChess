package main;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.FileInputStream;
import java.io.IOException;

public class Board {
    final int MAX_COL = 9; 
    final int MAX_ROW = 7; 
    public static final int SQUARE_SIZE = 100; 
    public static final int HALF_SQUARE_SIZE = SQUARE_SIZE/2; 
    final Color waterBlue = new Color(156, 222, 255); 

    BufferedImage image = null; 

    // public Board(){
    //     try{
    //         image = ImageIO.read(new FileInputStream("SimpleAnimalChess/res/tiles/water.png")); 
    //     }catch(IOException e){
    //         e.printStackTrace();
    //     }
    // }

    public void draw(Graphics2D g2){
        for(int row = 0; row < MAX_ROW; row++){
            for(int col = 0; col < MAX_COL; col++){ 
                
                // if(((row == 2 || row == 4) && (col == 0 || col == 8)) || (row == 3 && (col == 1 || col == 7))){
                //     g2.setColor(Color.RED); // TRAPS
                // }  
                // else if(row == 3 && (col == 0 || col == 8)){
                //     g2.setColor(Color.GREEN); // DENS
                // } 
                // else if((row == 1 || row == 2 || row == 4 || row == 5) && (col == 3 || col == 4 || col == 5)){
                //     g2.setColor(waterBlue); // WATER
                //     g2.drawImage(image, col*Board.SQUARE_SIZE, row*Board.SQUARE_SIZE, Board.SQUARE_SIZE, Board.SQUARE_SIZE, null);
                // }
                // else{
                //     g2.setColor(Color.WHITE); // non speical pieces 
                // }

                // [1] Notes: changing the x and y (first two values in fillRect) changes where in the 
                // jPanel the board can be printed, you can manipulate this to force the board to print in 
                // middle of the screen 
                
                g2.fillRect(col*SQUARE_SIZE, row*SQUARE_SIZE, SQUARE_SIZE, SQUARE_SIZE);
                g2.setColor(Color.PINK);
                // g2.drawRect(col*SQUARE_SIZE, row*SQUARE_SIZE, SQUARE_SIZE, SQUARE_SIZE);
            }
        }
    }
}
