
package com.mycompany.brickbreakergame;
import javax.swing.JFrame;

public class BrickBreakerGame {

    public static void main(String[] args) {
        JFrame obj = new JFrame();
///      creating a new class where implementation of game is done
        GamePlay gameplay = new GamePlay();
        obj.setBounds(10, 10, 700, 600);
        obj.setTitle("Brick Breaker");
        obj.setLocationRelativeTo(null);
        obj.setResizable(false);
        obj.setVisible(true);
        obj.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        obj.add(gameplay);
    }
}
