package piece;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;
import java.io.FileInputStream;
import main.Board;
import main.GamePanel;
import tile.Tile;
import tile.Water;

public class Piece {
    public BufferedImage image; 
    public int x, y; 
    public int col, row, preCol, preRow; 
    public int color; 
    public Piece hittingP; 
    public int power; 

    public Piece (int color, int power, int col, int row){
        this.color = color; 
        this.col = col; 
        this.row = row; 
        this.power = power; 
        x = getX(col); 
        y = getY(row); 
        preCol = col; 
        preRow = row; 
    }

    public BufferedImage getImage(String imagePath){
        BufferedImage image = null; 
        try{
            image = ImageIO.read(new FileInputStream("SimpleAnimalChess/res/piece/" + imagePath + ".png")); 
        }catch(IOException e){
            e.printStackTrace();
        }
        return image; 
    }

    public int getX(int col){
        return col*Board.SQUARE_SIZE; 
    }

    public int getY(int row){
        return row*Board.SQUARE_SIZE; 
    }

    public int getCol(int x){
        return (x + Board.HALF_SQUARE_SIZE)/Board.SQUARE_SIZE; 
    }

    public int getRow(int y){
        return (y + Board.HALF_SQUARE_SIZE)/Board.SQUARE_SIZE; 
    }

    public int getIndex(){
        for(int index = 0; index < GamePanel.simPieces.size(); index++){
            if(GamePanel.simPieces.get(index) == this){
                return index; 
            }
        }
        return 0; 
    }

    public void updatePosition(){
        x = getX(col); 
        y = getY(row); 
        preCol = getCol(x);
        preRow = getRow(y); 
    }

    public void resetPosition(){
        col = preCol; 
        row = preRow; 
        x = getX(col); 
        y = getY(row); 
    }

    public boolean canMove(int targetCol, int targetRow){ 
        if(isWithinBoard(targetCol, targetRow)){
            // checks if move is within one space: up, down, left, or right 
            if(Math.abs(targetCol - preCol) + Math.abs(targetRow - preRow) == 1){
                if(!isGoingToWater(targetCol, targetRow)){
                    if(!isGoingToInvalidDen(targetCol, targetRow)){
                        if(isValidSquare(targetCol, targetRow)){
                            return true;
                        }
                    }
                }
            }
        }
        return false; 
    }

    public boolean isWithinBoard(int targetCol, int targetRow){
        if(targetCol >= 0 && targetCol < Board.MAX_COL && targetRow >= 0 && targetRow < Board.MAX_ROW){
            return true; 
        }
        return false; 
    }

    public Piece getHittingP(int targetCol, int targetRow){
        for(Piece piece: GamePanel.simPieces){
            if(piece.col == targetCol && piece.row == targetRow && piece != this){
                return piece; 
            }
        }
        return null; 
    }

    public boolean isValidSquare(int targetCol, int targetRow){
        hittingP = getHittingP(targetCol, targetRow); 
        if(hittingP == null){
            return true;
        }
        else{
            if(hittingP.color != this.color && hittingP.power <= this.power){
                return true; 
            }
            else{
                hittingP = null; 
            }
        }
        return false; 
    }

    public boolean isGoingToWater(int targetCol, int targetRow){
        for(Tile tile: GamePanel.tiles){
            if(tile.col == targetCol && tile.row == targetRow && tile instanceof Water)
            return true; 
        }
        return false; 
    }

    public boolean isGoingToInvalidDen(int targetCol, int targetRow){
        if(this.color == GamePanel.RED && targetRow == 3 && targetCol == 0){ // red going into red den
            return true; 
        }
        else if(this.color == GamePanel.BLUE && targetRow == 3 && targetCol == 8){ // blue going into blue den
            return true; 
        }
        return false; 
    }

    public void draw(Graphics2D g2){
        g2.drawImage(image, x, y, Board.SQUARE_SIZE, Board.SQUARE_SIZE, null); 
    }
}
