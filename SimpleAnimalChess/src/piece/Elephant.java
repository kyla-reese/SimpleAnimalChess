package piece;

import main.GamePanel;

public class Elephant extends Piece{
    public Elephant(int color, int col, int row) {
        super(color, col, row);
        if(color == GamePanel.RED){
            image = getImage("r-elephant"); 
        }
        else{
            image = getImage("b-elephant"); 
        }
    }
    
}
