package piece;

import main.GamePanel;

public class Tiger extends Piece{
    public Tiger(int color, int col, int row) {
        super(color, col, row);
        if(color == GamePanel.RED){
            image = getImage("r-tiger"); 
        }
        else{
            image = getImage("b-tiger"); 
        }
    }
}
