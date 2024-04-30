package piece;

import main.GamePanel;

public class Dog extends Piece {
    public Dog(int color, int power, int col, int row) {
        super(color, power, col, row);
        if(color == GamePanel.RED){
            image = getImage("r-dog"); 
        }
        else{
            image = getImage("b-dog"); 
        }
    }
}
