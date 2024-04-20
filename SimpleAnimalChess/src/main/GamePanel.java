package main;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

public class GamePanel extends JPanel implements Runnable{
    public static final int WIDTH = 1000; 
    public static final int HEIGHT = 800; 
    final int FPS = 60; 
    Thread gameThread; 
    Board board = new Board(); 

    // This is the constructor of this class 
    public GamePanel(){
        setPreferredSize(new Dimension(WIDTH, HEIGHT));
        setBackground(Color.black);
    }

    // Instantiates the thread 
    public void launchGame(){
        gameThread = new Thread(this); 
        gameThread.start();
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

    public void paintComponent(Graphics g){
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D)g; 
        board.draw(g2);
    }

}
