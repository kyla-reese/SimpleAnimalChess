package main;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;
import javax.swing.JPanel;
import piece.Piece;
import piece.Rat ; 
import piece.Cat; 
import piece.Wolf; 
import piece.Dog; 
import piece.Leopard; 
import piece.Tiger; 
import piece.Lion; 
import piece.Elephant; 

public class GamePanel extends JPanel implements Runnable{
    final int FPS = 60; 

    // Height and width of the board
    public static final int WIDTH = 1200; 
    public static final int HEIGHT = 700; 

    // Color of the Pieces
    public static final int RED = 0; 
    public static final int BLUE = 1; 
    int currentColor = RED; 

    // Arraylists of Pieces currently on the board
    public static ArrayList<Piece> pieces = new ArrayList<>(); // <-- works like a back up list in case we wanna reset the changes the player made
    public static ArrayList<Piece> simPieces = new ArrayList<>(); 

    Thread gameThread; 
    Board board = new Board(); // <-- instantiates the board 

    // The constructor of this class 
    public GamePanel(){
        setPreferredSize(new Dimension(WIDTH, HEIGHT));
        setBackground(Color.black);
        setPieces();
        copyPieces(pieces, simPieces);
    }

    // Instantiates the thread 
    public void launchGame(){
        gameThread = new Thread(this); 
        gameThread.start();
    }

    public void setPieces(){

        // // Red team 
        // pieces.add(new Rat(RED, 2, 6));
        // pieces.add(new Cat(RED, 1, 1));
        // pieces.add(new Wolf(RED, 2, 2)); 
        // pieces.add(new Dog(RED, 1, 5));
        // pieces.add(new Leopard(RED, 2, 4));
        // pieces.add(new Tiger(RED, 0, 0));
        // pieces.add(new Lion(RED, 0, 6));
        // pieces.add(new Elephant(RED, 2, 0));

        // // // Blue team 
        // pieces.add(new Rat(BLUE, 6, 0)); 
        // pieces.add(new Cat(BLUE, 7, 5));
        // pieces.add(new Wolf(BLUE, 6, 4)); 
        // pieces.add(new Dog(BLUE, 7, 1));
        // pieces.add(new Leopard(BLUE, 6, 2));
        // pieces.add(new Tiger(BLUE, 8, 6));
        // pieces.add(new Lion(BLUE, 8, 0));
        // pieces.add(new Elephant(BLUE, 6, 6)); 
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
    }

    // This is the thing that you go to when you call repaint() 
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D)g; 
        // Draws the Board
        board.draw(g2);
        // Draws the Pieces 
        for(Piece p: simPieces){
            p.draw(g2); 
        }

    }

}
