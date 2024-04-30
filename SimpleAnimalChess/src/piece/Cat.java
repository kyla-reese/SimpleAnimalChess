package piece;
import main.GamePanel;

public class Cat extends Piece {
    public Cat(int color, int power, int col, int row) {
        super(color, power, col, row);
        if(color == GamePanel.RED){
            image = getImage("r-cat"); 
        }
        else{
            image = getImage("b-cat"); 
        }
    }

    
}
