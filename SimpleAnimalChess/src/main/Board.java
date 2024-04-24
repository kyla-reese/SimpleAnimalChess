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
    final Color lineColor = new Color(94,147,81); 
    BufferedImage water = null; 
    BufferedImage trap = null; 
    BufferedImage den = null; 
    BufferedImage grass = null; 

    public Board(){
        try{
            water = ImageIO.read(new FileInputStream("SimpleAnimalChess/res/tile/water.png")); 
        }catch(IOException e){
            e.printStackTrace();
        }
        try{
            trap = ImageIO.read(new FileInputStream("SimpleAnimalChess/res/tile/trap.png")); 
        }catch(IOException e){
            e.printStackTrace();
        }
        try{
            den = ImageIO.read(new FileInputStream("SimpleAnimalChess/res/tile/den.png")); 
        }catch(IOException e){
            e.printStackTrace();
        }
        try{
            grass = ImageIO.read(new FileInputStream("SimpleAnimalChess/res/tile/grass.png")); 
        }catch(IOException e){
            e.printStackTrace();
        }
    }

    public void draw(Graphics2D g2){
        for(int row = 0; row < MAX_ROW; row++){
            for(int col = 0; col < MAX_COL; col++){ 
                if(((row == 2 || row == 4) && (col == 0 || col == 8)) || (row == 3 && (col == 1 || col == 7))){ // TRAPS 
                    g2.setColor(Color.BLACK);
                    g2.fillRect(col*SQUARE_SIZE, row*SQUARE_SIZE, SQUARE_SIZE, SQUARE_SIZE);
                    g2.drawImage(trap, col*Board.SQUARE_SIZE, row*Board.SQUARE_SIZE, Board.SQUARE_SIZE, Board.SQUARE_SIZE, null);
                }  
                else if(row == 3 && (col == 0 || col == 8)){ // DENS 
                    g2.setColor(Color.BLACK);
                    g2.fillRect(col*SQUARE_SIZE, row*SQUARE_SIZE, SQUARE_SIZE, SQUARE_SIZE);
                    g2.drawImage(den, col*Board.SQUARE_SIZE, row*Board.SQUARE_SIZE, Board.SQUARE_SIZE, Board.SQUARE_SIZE, null);
                } 
                else if((row == 1 || row == 2 || row == 4 || row == 5) && (col == 3 || col == 4 || col == 5)){ // WATER
                    g2.setColor(Color.BLACK); 
                    g2.fillRect(col*SQUARE_SIZE, row*SQUARE_SIZE, SQUARE_SIZE, SQUARE_SIZE);
                    g2.drawImage(water, col*Board.SQUARE_SIZE, row*Board.SQUARE_SIZE, Board.SQUARE_SIZE, Board.SQUARE_SIZE, null);
                }
                else{ // non speical tiles
                    g2.setColor(Color.BLACK); 
                    g2.fillRect(col*SQUARE_SIZE, row*SQUARE_SIZE, SQUARE_SIZE, SQUARE_SIZE);
                    g2.drawImage(grass, col*Board.SQUARE_SIZE, row*Board.SQUARE_SIZE, Board.SQUARE_SIZE, Board.SQUARE_SIZE, null);
                }
                g2.setColor(lineColor);
                g2.drawRect(col*SQUARE_SIZE, row*SQUARE_SIZE, SQUARE_SIZE, SQUARE_SIZE);
            }
        }
    }
}
