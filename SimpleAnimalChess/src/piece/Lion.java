package piece;

import main.GamePanel;

public class Lion extends Piece {
    public Lion(int color, int col, int row) {
        super(color, col, row);
        if(color == GamePanel.RED){
            image = getImage("r-lion"); 
        }
        else{
            image = getImage("b-lion"); 
        }
    }
    
}
