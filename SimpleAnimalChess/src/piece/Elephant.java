package piece;

import main.GamePanel;

public class Elephant extends Piece{
    public Elephant(int color, int power, int col, int row) {
        super(color, power, col, row);
        if(color == GamePanel.RED){
            image = getImage("r-elephant"); 
        }
        else{
            image = getImage("b-elephant"); 
        }
    }

    public boolean isValidSquare(int targetCol, int targetRow){
        hittingP = getHittingP(targetCol, targetRow); 
        if(hittingP == null){
            return true;
        }
        else{
            if(hittingP.color != this.color && (hittingP.power <= this.power && !(hittingP instanceof Rat))){
                return true; 
            }
            else{
                hittingP = null; 
            }
        }
        return false; 
    }
    
}
