package piece;

import main.GamePanel;

public class Dog extends Piece {
    public Dog(int color, int col, int row) {
        super(color, col, row);
        if(color == GamePanel.RED){
            image = getImage("r-dog"); 
        }
        else{
            image = getImage("b-dog"); 
        }
    }
}
