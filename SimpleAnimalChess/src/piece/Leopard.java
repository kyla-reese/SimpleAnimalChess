package piece;

import main.GamePanel;

public class Leopard extends Piece{
    public Leopard(int color, int col, int row) {
        super(color, col, row);
        if(color == GamePanel.RED){
            image = getImage("r-leopard"); 
        }
        else{
            image = getImage("b-leopard"); 
        }
    }
}
