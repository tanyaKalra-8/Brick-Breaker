
package com.mycompany.brickbreakergame;
import javax.swing.JPanel;
import javax.swing.Timer;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.Graphics;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.sql.SQLOutput;

public class GamePlay extends JPanel implements KeyListener, ActionListener
{
    private boolean play = false;
    private float score =0;
    private int totalbrick =21;
    private Timer Timer;
    private int delay =8; // milliseconds
    private int playerx =randomInt(90,550);
    private int ballposx = randomInt(90,550);
    private int ballposy = randomInt(300,400);
    private int ballxdir = -2;
    private int ballydir = -3;
    private MapGenerator map;
    
    public GamePlay(){
        map = new MapGenerator(3,7);
        addKeyListener(this);
        setFocusable(true);
        setFocusTraversalKeysEnabled(false);
        Timer = new Timer(delay,this);
        Timer.start();
    }
    @Override
    public void paint(Graphics g){
        g.setColor(Color.black);
        g.fillRect(1, 1,682, 592);

        map.draw((Graphics2D) g);

        g.setColor(Color.yellow);
        g.fillRect(0, 0, 3, 592); // left border
        g.fillRect(0, 0, 692, 4); // top border
        g.fillRect(681, 0, 3, 592); // right border
        
        g.setColor(Color.white);
        g.setFont(new Font("serif", Font.BOLD,25));
        g.drawString("" + (int)score, 590,30);

        g.setColor(Color.yellow);
        g.fillRect(playerx, 550, 100, 8); //bar

//            ball
        g.setColor(Color.GREEN);
        g.fillOval(ballposx, ballposy, 20, 20);
        if(ballposy>570){
            play = false;
            ballydir = 0;
            ballxdir = 0;
            g.setColor(new Color(225, 30, 39 ));
            g.setFont(new Font("serif", Font.BOLD,30));
            g.drawString(" Game Over!! :(", 190, 260);
            g.drawString(" Score: "+ (int)score, 190, 300);

            g.setFont(new Font("serif", Font.BOLD,30));
            g.drawString("Press Enter to Restart", 195, 340);
        }
        if(totalbrick == 0){
            play = false;
            ballydir = -3;
            ballxdir = -2;
            g.setColor(new Color(81, 186, 57 ));
            g.setFont(new Font("serif", Font.BOLD,30));
            g.drawString("Congratulations, You won!! :)", 190, 260);
            g.drawString(" Score: "+ (int)score, 185, 300);

            g.setFont(new Font("serif", Font.BOLD,30));
            g.drawString("Press Enter to Restart", 190, 340);
        }
        g.dispose();
    }
    
    @Override
    public void actionPerformed(ActionEvent e){
        Timer.start();
        if(play){
            if(new Rectangle(ballposx, ballposy,20,20).intersects(new Rectangle(playerx,550,100,8))){
                ballydir = -ballydir;
            }
            A:
            for(int i=0;i<map.map.length;i++){
                for(int j=0;j<map.map[0].length;j++){
                    if(map.map[i][j]>0){
                        int brickx = j*map.brickWidth+80;
                        int bricky = i*map.brickHeight+50;
                        int brickWidth = map.brickWidth;
                        int brickHeight = map.brickHeight;

                        Rectangle rect = new Rectangle(brickx,bricky,brickWidth, brickHeight);
                        Rectangle ballrect = new Rectangle(ballposx, ballposy,20,20);
                        Rectangle brickrect = rect;

                        if(ballrect.intersects(brickrect)){
                            map.setBrickValue(0, i, j);
                            totalbrick--;
                            score+=4.78571429;
                            if(ballposx+19<= brickrect.x || ballposx+1 >= brickrect.x + brickWidth){
                                ballxdir = -ballxdir;
                            }
                            else{
                                ballydir = -ballydir;
                            }
                            break A;
                        }
                    }
                }
            }
            ballposx += ballxdir;
            ballposy += ballydir;
            if(ballposx <0){
                ballxdir = -ballxdir;
            }
            if(ballposy <0){
                ballydir = -ballydir;
            }
            if(ballposx > 670){
                ballxdir = -ballxdir;
            }
        }
        repaint();
    }
    
    
    
    public void moveright(){
        play = true;
        playerx+=25;
    }
    public void moveleft(){
        play = true;
        playerx-=25;
    }

    @Override
    public void keyTyped(KeyEvent e) {
        
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if(e.getKeyCode() == KeyEvent.VK_RIGHT){
            if(playerx >= 580){
                playerx =580;
            }
            else{
                moveright();
            }
        }
        if(e.getKeyCode() == KeyEvent.VK_LEFT){
            if(playerx <= 0){
                playerx = 0;
            }
            else{
                moveleft();
            }
        }
        if(e.getKeyCode() == KeyEvent.VK_ENTER){
            if(!play){
//                ballposx = 120;
//                ballposy = 350;
                ballxdir = -2;
                ballydir = -3;
                ballposx = randomInt(90,550);
                ballposy= randomInt(300,400);
                score =0;
                playerx = randomInt(90,550);
                totalbrick = 21;
                map = new MapGenerator(3,7);
                
                repaint();
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        
    }
    public int randomInt(int low, int high) {
        return (int) ((Math.random() * (high-low)) + low);
    }
}
