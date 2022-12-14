
package com.mycompany.brickbreakergame;
import javax.swing.*;

public class BrickBreakerGame {

    public static void main(String[] args) {
        JFrame obj = new JFrame();
///      creating a new class where implementation of game is done
        GamePlay gameplay = new GamePlay();
        obj.setBounds(10, 10, 700, 600);
        obj.setTitle("Brick Breaker");
        obj.setLocationRelativeTo(null);
        obj.setResizable(false);

        ImageIcon img = new ImageIcon("C:\\Users\\Admin\\OneDrive\\Documents\\NetBeansProjects\\BrickBreakerGame\\src\\main\\java\\Icon\\Game.png");
        obj.setIconImage(img.getImage());

        obj.setVisible(true);

        obj.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        obj.add(gameplay);
    }
}
