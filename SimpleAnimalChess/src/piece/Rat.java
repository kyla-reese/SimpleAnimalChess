package piece;

import main.GamePanel;

public class Rat extends Piece {
    public Rat(int color, int power, int col, int row) {
        super(color, power, col, row);
        if(color == GamePanel.RED){
            image = getImage("r-rat"); 
        }
        else{
            image = getImage("b-rat"); 
        }
    }

    public boolean canMove(int targetCol, int targetRow){ 
        if(isWithinBoard(targetCol, targetRow)){
            if(!isGoingToInvalidDen(targetCol, targetRow)){
                // checks if move is within one space: up, down, left, or right 
                if(Math.abs(targetCol - preCol) + Math.abs(targetRow - preRow) == 1){
                    if(isValidSquare(targetCol, targetRow)){
                        return true;
                    }
                }
            }
        }
        return false; 
    }
}
