package piece;

import main.GamePanel;

public class Rat extends Piece {

    public Rat(int color, int col, int row) {
        super(color, col, row);

        if(color == GamePanel.RED){
            image = getImage("/piece/r-rat"); // same image despite color 
        }
        else{
            image = getImage("/piece/b-rat"); // same image despite color 
        }
    }
    
}
