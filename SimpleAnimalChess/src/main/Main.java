package main;

import javax.swing.JFrame;

public class Main {
    public static void main(String[] args){
        JFrame window = new JFrame("Simple Animal Chess");
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setResizable(false);
        window.setLocationRelativeTo(null);
        window.setVisible(true); 
    }
}