package main;
import java.awt.Color;
import java.awt.Graphics2D;

public class Board {
    public static final int MAX_COL = 9; 
    public static final int MAX_ROW = 7; 
    public static final int SQUARE_SIZE = 100; 
    public static final int HALF_SQUARE_SIZE = SQUARE_SIZE/2;  
    public static final int WATER_HEIGHT = 2; 
    public static final int WATER_WIDTH = 3; 
    final Color lineColor = new Color(94,147,81); 

    public void draw(Graphics2D g2){
        for(int row = 0; row < MAX_ROW; row++){
            for(int col = 0; col < MAX_COL; col++){ 
                g2.setColor(Color.GREEN); 
                g2.setColor(lineColor);
                g2.drawRect(col*SQUARE_SIZE, row*SQUARE_SIZE, SQUARE_SIZE, SQUARE_SIZE);
            }
        }
    }
}
