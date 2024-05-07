package main;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.ArrayList; 
import javax.swing.JPanel;
import javax.imageio.ImageIO;
import piece.Piece;
import piece.Rat ; 
import piece.Cat; 
import piece.Wolf;
import tile.Den;
import tile.Tile;
import tile.Trap;
import tile.Water;
import piece.Dog; 
import piece.Leopard; 
import piece.Tiger; 
import piece.Lion; 
import piece.Elephant; 


public class GamePanel extends JPanel implements Runnable{
    final int FPS = 60;

    // Side Panel Colors 
    final Color sidePanelColor = new Color(225,234,169);

    // Side Panel Images 
    BufferedImage vines = null; 
    BufferedImage backgrass = null; 
    BufferedImage frontgrass = null; 

    // Height and width of the board
    public static final int WIDTH = 1300; 
    public static final int HEIGHT = 700; 

    // Color of the Pieces
    public static final int RED = 0; 
    public static final int BLUE = 1; 
    int currentColor = RED; 
    
    // Booleans
    boolean canMove; 
    boolean isValidSquare; 

    // Arraylists
    public static ArrayList<Piece> pieces = new ArrayList<>(); // <-- works like a back up list in case we wanna reset the changes the player made
    public static ArrayList<Piece> simPieces = new ArrayList<>(); 
    public static ArrayList<Tile> tiles = new ArrayList<>(); 
    Piece activeP; 

    // Instantiations 
    Thread gameThread; 
    Board board = new Board(); 
    Mouse mouse = new Mouse(); 

    // The constructor of this class 
    public GamePanel(){
        setPreferredSize(new Dimension(WIDTH, HEIGHT));
        setBackground(sidePanelColor);
        // allows program to detect the players mouse movements 
        addMouseMotionListener(mouse);
        addMouseListener(mouse);
        // sets up the pieces (initially)
        setPieces();
        copyPieces(pieces, simPieces);
        // sets up board 
        setBoard();
        // gets all the images needed for the side panel 
        getPanelImages(); 
    }

    // Instantiates the thread 
    public void launchGame(){
        gameThread = new Thread(this); 
        gameThread.start();
    }

    public void setBoard(){ 
        for(int col = 0; col < Board.MAX_COL; col++){
            for(int row = 0; row < Board.MAX_ROW; row++){
                if(((row == 2 || row == 4) && (col == 0 || col == 8)) || (row == 3 && (col == 1 || col == 7))){
                    tiles.add(new Trap(col, row));     
                }
                else if((row == 1 || row == 2 || row == 4 || row == 5) && (col == 3 || col == 4 || col == 5)){
                    tiles.add(new Water(col, row)); 
                }
                else if(row == 3 && col == 0){
                    tiles.add(new Den(col, row)); 
                }
                else if(row == 3 && col == 8){
                    tiles.add(new Den(col, row)); 
                }
                else{
                    tiles.add(new Tile(col, row));
                } 
            }
        }
    }

    public void setPieces(){
        // Red team 
        pieces.add(new Rat  (RED, 1, 2, 6));
        pieces.add(new Cat  (RED, 2, 1, 1));
        pieces.add(new Wolf (RED, 3, 2, 2)); 
        pieces.add(new Dog  (RED, 4, 1, 5));
        pieces.add(new Leopard  (RED, 5, 2, 4));
        pieces.add(new Tiger    (RED, 6, 0, 0));
        pieces.add(new Lion     (RED, 7, 0, 6));
        pieces.add(new Elephant (RED, 8, 2, 0)); 
        // Blue team 
        pieces.add(new Rat  (BLUE, 1, 6, 0)); 
        pieces.add(new Cat  (BLUE, 2, 7, 5));
        pieces.add(new Wolf (BLUE, 3, 6, 4)); 
        pieces.add(new Dog  (BLUE, 4, 7, 1));
        pieces.add(new Leopard  (BLUE, 5, 6, 2));
        pieces.add(new Tiger    (BLUE, 6, 8, 6));
        pieces.add(new Lion     (BLUE, 7, 8, 0));
        pieces.add(new Elephant (BLUE, 8, 6, 6)); 
    }

    private void copyPieces(ArrayList<Piece> source, ArrayList<Piece> target){
        target.clear(); 
        for(int i = 0; i < source.size(); i++){
            target.add(source.get(i)); 
        }
    }

    // When we run launch game, it goes to here, this is where the game loop happens 
    @Override
    public void run() {
        //GAME LOOP -- there's more than one way to make a game loop for this 
        double drawInterval = 1000000000/FPS; 
        double delta = 0; 
        long lastTime = System.nanoTime(); 
        long currentTime; 
        while(gameThread != null){
            currentTime = System.nanoTime(); 
            delta += (currentTime - lastTime)/drawInterval;
            lastTime = currentTime; 
            if(delta >= 1){
                update(); 
                repaint(); // <-- repaint calls the paintComponent method 
                delta--; 
            }
        }
    }

    private void update(){ 
        // [1] IF MOUSE IS PRESSED 
        if (mouse.pressed){ 
            // if no active piece, check if player can pick up a piece 
            if(activeP == null){ 
                for(Piece piece: simPieces){
                    // if the mouse is on an ally piece, pick it up as activeP 
                    if(piece.color == currentColor && piece.col == mouse.x/Board.SQUARE_SIZE &&
                                                        piece.row == mouse.y/Board.SQUARE_SIZE){
                        activeP = piece; 
                    }
                }
            }
            else{
                // if the player is holding a piece, simulate the move 
                simulate(); 
            }
        }

        // [2] IF MOUSE IS RELEASED 
        if (mouse.pressed == false){
            if(activeP != null){
                if(isValidSquare){ 
                    // MOVE IS CONFIRMED
                    copyPieces(simPieces, pieces); // update the list in case piece has been captured and removed during the simulation 
                    activeP.updatePosition();
                    changePlayer();
                }
                else{
                    copyPieces(pieces, simPieces); // the move is not valid so reset everything 
                    activeP.resetPosition(); 
                    activeP = null;
                } 
            }
        }
    }

    public void simulate(){
        canMove = false; 
        isValidSquare = false; 

        // reset the piece list in every loop
        // this is basically for restoring the remove piece during the simulation
        copyPieces(pieces, simPieces); 

        // If a piece is being held, update its position 
        activeP.x = mouse.x - Board.HALF_SQUARE_SIZE; 
        activeP.y = mouse.y - Board.HALF_SQUARE_SIZE; 
        activeP.col = activeP.getCol(activeP.x); 
        activeP.row = activeP.getRow(activeP.y); 

        // Check if piece is hovering over a reachable square
        if(activeP.canMove(activeP.col, activeP.row)){
            canMove = true; 
            // if hitting a piece, remove it from the list 
            if(activeP.hittingP != null){
                // note: this removes the piece as you hover over it, this does not remove the piece upon release of mouse which is kinda what i want to do
                simPieces.remove(activeP.hittingP); //<-- this is the part that lets them remove pieces 
            }
            isValidSquare = true; 
        }
    }

    public void changePlayer(){
        if(currentColor == RED){
            currentColor = BLUE; 
        }
        else{
            currentColor = RED; 
        }
        activeP = null; 
    }

    public void getPanelImages(){
        try{
            vines = ImageIO.read(new FileInputStream("SimpleAnimalChess/res/sidepanel/vines.png"));
            backgrass = ImageIO.read(new FileInputStream("SimpleAnimalChess/res/sidepanel/backgrass.png")); 
            frontgrass = ImageIO.read(new FileInputStream("SimpleAnimalChess/res/sidepanel/frontgrass.png"));
        }catch(IOException e){
            e.printStackTrace();
        }
    }

    // This is the thing that you go to when you call repaint() 
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D)g; 

        // Draw the Tiles on the board
        for(Tile t: tiles){
            t.draw(g2); 
        }
        // Draws the Board
        board.draw(g2);

        // Draw the side panel
        if(vines != null){
            g2.drawImage(vines, 900, 0, (Board.SQUARE_SIZE*4), (Board.SQUARE_SIZE*2), null);
        }
        if(backgrass != null){ 
            g2.drawImage(backgrass, 900, 200, (Board.SQUARE_SIZE*4), (Board.SQUARE_SIZE*5), null); 
        }
        if(frontgrass != null){
            g2.drawImage(frontgrass, 900, 200, (Board.SQUARE_SIZE*4), (Board.SQUARE_SIZE*5), null); 
        }

        // Draws the Pieces (initially)
        for(Piece p: simPieces){
            p.draw(g2); 
        }

        if(activeP != null){
            if(canMove){
                g2.setColor(Color.WHITE); 
                g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.20f));
                g2.fillRect(activeP.col*Board.SQUARE_SIZE, activeP.row*Board.SQUARE_SIZE, Board.SQUARE_SIZE, Board.SQUARE_SIZE);
                g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f)); // <-- rests transparency 
            }
            // draw the active piece at the end so it won't be hidden by the board or the colored square
            activeP.draw(g2);
        }
        

        // SIDE PANEL TURN IMAGES  
        // BufferedImage turnImage = null; 
        // String imagePath;
        // if(currentColor == RED){
        //     imagePath = "redturn"; 
        // }
        // else{
        //     imagePath = "blueTurn"; 
        // }
        // try{
        //     turnImage = ImageIO.read(new FileInputStream("SimpleAnimalChess/res/sidepanel/" + imagePath + ".png")); 
        // }catch(IOException e){
        //     e.printStackTrace();
        // }
        // g2.drawImage(turnImage, 900, 200, (Board.SQUARE_SIZE*4), (Board.SQUARE_SIZE*5), null); 

    }
}
