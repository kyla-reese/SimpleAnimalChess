package piece;

import main.GamePanel;

public class Wolf extends Piece{
    public Wolf(int color, int power, int col, int row) {
        super(color, power, col, row);
        if(color == GamePanel.RED){
            image = getImage("r-wolf"); 
        }
        else{
            image = getImage("b-wolf"); 
        }
    }
}
