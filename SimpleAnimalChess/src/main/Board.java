package main;

import java.awt.Color;
import java.awt.Graphics2D;

public class Board {
    final int MAX_COL = 9; 
    final int MAX_ROW = 7; 
    public static final int SQUARE_SIZE = 100; 
    public static final int HALF_SQUARE_SIZE = SQUARE_SIZE/2; 

    public void draw(Graphics2D g2){
        for(int row = 0; row < MAX_ROW; row++){
            for(int col = 0; col < MAX_COL; col++){ 
                // [1] Notes: changing the x and y (first two values in fillRect) changes where in the 
                // jPanel the board can be printed, you can manipulate this to force the board to print in 
                // middle of the screen 
                g2.setColor(Color.WHITE); 
                g2.fillRect(col*SQUARE_SIZE, row*SQUARE_SIZE, SQUARE_SIZE, SQUARE_SIZE);
                g2.setColor(Color.BLACK);
                g2.drawRect(col*SQUARE_SIZE, row*SQUARE_SIZE, SQUARE_SIZE, SQUARE_SIZE);
            }
        }
    }
}
