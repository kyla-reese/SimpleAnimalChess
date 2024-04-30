package piece;
import main.GamePanel;

public class Lion extends LeapingPiece {
    public Lion(int color, int power, int col, int row) {
        super(color, power, col, row);
        if(color == GamePanel.RED){
            image = getImage("r-lion"); 
        }
        else{
            image = getImage("b-lion"); 
        }
    }
    
}
