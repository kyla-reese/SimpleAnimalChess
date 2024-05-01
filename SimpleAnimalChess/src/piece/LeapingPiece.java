package piece;
import java.util.ArrayList;
import main.Board;
import main.GamePanel;
import tile.Tile;
import tile.Water;

public class LeapingPiece extends Piece{

    public LeapingPiece(int color, int power, int col, int row) {
        super(color, power, col, row);
    }
    
    public boolean canMove(int targetCol, int targetRow){ 
        ArrayList<Tile> closestWaterTile = startedMoveNearWater(preCol, preRow); 
        if(isWithinBoard(targetCol, targetRow)){
            if(!isGoingToWater(targetCol, targetRow)){
                if(!isGoingToInvalidDen(targetCol, targetRow)){
                    // checks of see if piece is beside water tiles 
                    if(closestWaterTile.size() != 0){
                        for (Tile tile: closestWaterTile){ 
                            // checks if the closest water tile is to its left or right 
                            if(tile.row == preRow && (Math.abs(targetRow - preRow) == 0 && Math.abs(targetCol - preCol) == (Board.WATER_WIDTH + 1))){
                                if(isValidSquare(targetCol, targetRow) && !hasRat(tile, targetCol, targetRow)){
                                    return true;
                                }
                            }
                            // checks if the closest water tile above or below it 
                            else if (tile.col == preCol && (Math.abs(targetCol - preCol) == 0 && Math.abs(targetRow - preRow) == (Board.WATER_HEIGHT + 1))){
                                if(isValidSquare(targetCol, targetRow) && !hasRat(tile, targetCol, targetRow)){
                                    return true;
                                }
                            }
                        }
                        // allows the piece to move in ways that aren't jumping over water despite being near water
                        if(Math.abs(targetCol - preCol) + Math.abs(targetRow - preRow) == 1){
                            if(isValidSquare(targetCol, targetRow)){
                                return true;
                            }
                        }
                    }
                    else{
                        // accounts for movement that happens far (more than one square away) from water
                        if(Math.abs(targetCol - preCol) + Math.abs(targetRow - preRow) == 1){
                            if(isValidSquare(targetCol, targetRow)){
                                return true;
                            }
                        }
                    }
                }
            }
        }
        return false; 
    }

    public ArrayList<Tile> startedMoveNearWater(int preCol, int preRow){ 
        ArrayList<Tile> closestWaterTiles = new ArrayList<>(); 
        for(Tile tile: GamePanel.tiles){
            if((Math.abs(tile.col - preCol) + Math.abs(tile.row - preRow) == 1) && tile instanceof Water){
                closestWaterTiles.add(tile); 
            } 
        }
        return closestWaterTiles; 
    }

    public boolean hasRat(Tile tile, int targetCol, int targetRow){
        boolean notStop = true; 
        if(tile.row == preRow){ // left and right 
            int col = preCol; 
            while(notStop){
                if(col < targetCol){  
                    col++;  
                }
                else{  
                    col--; 
                }
                for(Piece piece: GamePanel.simPieces){
                    if(piece.row == preRow && piece.col == col && piece instanceof Rat){
                        return true; 
                    }
                }
                if(col == targetCol - 1){
                    notStop = false; 
                }
            }
        }
        else if (tile.col == preCol){ // up and down
            int row = preRow;
            while(notStop){
                if(row < targetRow){  
                    row++;  
                }
                else{  
                    row--; 
                }
                for(Piece piece: GamePanel.simPieces){
                    if(piece.col == preCol && piece.row == row && piece instanceof Rat){
                        return true; 
                    }
                }
                if(row == targetRow - 1){
                    notStop = false; 
                }
            }
        }
        return false; 
    }
}
