package main;

import javax.swing.JFrame;

public class Main {
    public static final String title = "Simple Animal Chess"; 

    public static void main(String[] args){
        // Creates the JFrame / window 
        JFrame window = new JFrame(title);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setResizable(false);

        // Adds the game panel to the window 
        GamePanel gp = new GamePanel(); 
        window.add(gp); 
        window.pack(); 
        window.setLocationRelativeTo(null);
        window.setVisible(true); 

        // Once the window is created, we launch the game as well (strats the thread)
        gp.launchGame();
    }
}
