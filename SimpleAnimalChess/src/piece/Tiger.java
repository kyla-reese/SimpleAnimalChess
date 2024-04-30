package piece;
import main.GamePanel;

public class Tiger extends LeapingPiece{
    public Tiger(int color, int power, int col, int row) {
        super(color, power, col, row);
        if(color == GamePanel.RED){
            image = getImage("r-tiger"); 
        }
        else{
            image = getImage("b-tiger"); 
        }
    }
}
