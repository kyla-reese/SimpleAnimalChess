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
        boolean hittingPIsInTrap = isInTrap(targetCol, targetRow); 
        // if the elphant isn't tryig to get anything, its a valid square
        if(hittingP == null){
            return true;
        }
        else{
            // if the elephant is trying to consume something, check if the piece is of the same color, lesser power, AND not a rat
            if(hittingP.color != this.color && ((hittingP.power <= this.power || hittingPIsInTrap) && !(hittingP instanceof Rat))){
                return true; 
            }
            else{
                hittingP = null; 
            }
        }
        return false; 
    }
    
}
