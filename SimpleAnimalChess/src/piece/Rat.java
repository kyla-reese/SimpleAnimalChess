package piece;

import main.GamePanel;
import tile.Tile;
import tile.Trap;
import tile.Water;

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

    public boolean isValidSquare(int targetCol, int targetRow){
        hittingP = getHittingP(targetCol, targetRow); 
        boolean hittingPIsInTrap = otherPIsInTrap(targetCol, targetRow); 
        if(hittingP == null){
            return true;
        }
        else{
            if(hittingP.color != this.color && 
                (hittingP.power <= this.power || hittingPIsInTrap || (hittingP instanceof Elephant))){
                return true; 
            }
            else{
                hittingP = null; 
            }
        }
        return false; 
    }

    // public boolean isInWater(int col, int row){
    //     for(Tile tile: GamePanel.tiles){
    //         if(tile.col == col && tile.row == row && tile instanceof Water){
    //             return true; 
    //         } 
    //     }
    //     return false; 
    // }
}
