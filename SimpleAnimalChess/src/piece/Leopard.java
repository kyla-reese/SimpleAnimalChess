package piece;

import main.GamePanel;

public class Leopard extends Piece{
    public Leopard(int color, int power, int col, int row) {
        super(color, power, col, row);
        if(color == GamePanel.RED){
            image = getImage("r-leopard"); 
        }
        else{
            image = getImage("b-leopard"); 
        }
    }
}
