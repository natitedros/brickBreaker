
package brickbreaker;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JPanel;
import javax.swing.Timer;


public class SinglePlay2 extends JPanel implements KeyListener, ActionListener {
    
    private boolean play = false;
    private Timer time;
    private int delay = 8;
    private int score = 0;//score counter
    private int totalBricks = 21;
    private int paddlePosX = 60;
    private int ballPosX = 120;//position coordinates
    private int ballPosY = 350;
    private int ballDirX = 1;   //speed in the x direction
    private int ballDirY = 4;   //Speed in the y direction
    private boolean moveRight = false;
    private boolean moveLeft = false;
    //Colors
    private Color ballColor;
    private Color paddleColor;
    
    private MapGenerator2 map;
    
    public SinglePlay2(Color ballColor, Color paddleColor){
        this.ballColor = ballColor;
        this.paddleColor = paddleColor;
        map = new MapGenerator2(3, 7); //instantiating object of MapGenerator2 to draw the bricks and giving values of row and column
        addKeyListener(this);
        setFocusable(true);
        setTraversalKeysEnabled(false);
        time = new Timer(delay, this);
        time.start();
    }
    
    
    
    
    public void paint(Graphics g){
        //background
        g.setColor(Color.black);
        g.fillRect(5, 5, 989, 797);
        
        map.draw((Graphics2D) g); //calling the method to draw the bricks
        //borders
        g.setColor(Color.red);
        g.fillRect(0, 5, 1000, 3);
        g.fillRect(0, 0, 5, 800);
        g.fillRect(990, 0, 5, 800);
        //score label
        g.setColor(Color.white);
        g.setFont(new Font("Segoe UI", Font.BOLD, 35));
        g.drawString("" + score, 930, 40);
        //paddle rectangles generated 
        g.setColor(paddleColor);
        g.fillRect(paddlePosX, 690, 150, 15);  
        
        //ball
        g.setColor(ballColor);
        g.fillOval(ballPosX, ballPosY, 20, 20);
        
        //if statement to finish the game when the ball touches the ground i.e. Game Over
        if (ballPosY > 778) {
            play = false;
            ballDirX = 0;
            ballDirY = 0;
            g.setColor(Color.red);
            g.setFont(new Font("Segoe UI", Font.BOLD, 50));
            g.drawString("Game Over! Score: " + score, 300, 350);

            g.setFont(new Font("Segoe UI", Font.BOLD, 30));
            g.drawString("Press Enter to Restart", 350, 390);
        }
        
        //if statement to stop the game if all the bricks are broken and the game is over
        if(totalBricks == 0){
            play = false;
            ballDirX = 0;
            ballDirY = 0;
            g.setColor(Color.green);
            g.setFont(new Font("serif",Font.BOLD,50));
            g.drawString("Game Over! Score: "+score,300,350);

            g.setFont(new Font("serif", Font.BOLD, 30));
            g.drawString("Press Enter to Restart", 350, 390);//command to restart the game
        }
        
        g.dispose();
    }
    @Override
    public void actionPerformed(ActionEvent ae) {
        time.start();
        if(play){
            //incrementing ball position by the direction value to make the ball move
            ballPosX += ballDirX;
            ballPosY += ballDirY;
            
            //if statement to let the ball obunce back from the paddle when it touches it.
            if(new Rectangle(ballPosX, ballPosY, 20, 20).intersects(new Rectangle(paddlePosX, 690, 150, 10))){
                ballDirY = -ballDirY;
            }
                
            
            
            A: //for loop accessing the values of the 2d array to change their values if they intersect with the ball, to "break" them.
            for (int i = 0; i < map.map.length; i++) {
                for (int j = 0; j < map.map[0].length; j++) {
                    if (map.map[i][j] > 0) {
                        int brickX = j * map.bricksWidth + 150;
                        int brickY = i * map.bricksHeight + 80;
                        int bricksWidth = map.bricksWidth;
                        int bricksHeight = map.bricksHeight;

                        Rectangle rect = new Rectangle(brickX, brickY, bricksWidth, bricksHeight);//rectangle objects representing the ball and the bricks
                        Rectangle ballrect = new Rectangle(ballPosX, ballPosY, 20, 20);
                        Rectangle brickrect = rect;
                         //if statement dealing with the reaction of ball bouncing back from the bricks
                        if (brickrect.intersects(ballrect)) {
                            map.setBricksValue(0, i, j);
                            totalBricks--;
                            score += 5;
                            if ((ballrect.x + 19 <= brickrect.x) || (ballrect.x + 1 >= brickrect.x + bricksWidth)) {
                                //statement for when the ball intersects with the brick from the left or right sides
                                this.ballDirX = -ballDirX;
                                this.ballDirY = ballDirY;
                            } 
                            else {
                                ballDirY = -ballDirY;
                            }
                            
                            break A;
                        }
                    }


                }
            }
            
            //statements telling the ball to bounce back when they encounter the borders
            if(ballPosX < 0){
                ballDirX = -ballDirX;
            }
            if(ballPosY < 0){
                ballDirY = -ballDirY;
            }
            if(ballPosX >= 977){
                ballDirX = -ballDirX;
            }
            
            //statements telling the paddle to move 10 pixels every time the repaint method is called if and only if the paddle position didn't reach the borders and the move booleans are true
            if(moveRight && (paddlePosX < 850)){paddlePosX += 10;} 
            if(moveLeft && (paddlePosX > 0)){paddlePosX -= 10;}
        }
        repaint();
    }
    
    @Override
    public void keyTyped(KeyEvent ke) { }
     @Override
    public void keyReleased(KeyEvent ke) {    //implemented from the keylistener interface to make the move booleans false when the keys are released
        if(ke.getKeyCode() == KeyEvent.VK_RIGHT){
            moveRight = false;
            }
        if(ke.getKeyCode() == KeyEvent.VK_LEFT){
            moveLeft = false;
        }
    }

    @Override
    public void keyPressed(KeyEvent ke) {    //implemented from the keylistener interface to start the game if the keys are pressed
        if(ke.getKeyCode() == KeyEvent.VK_RIGHT){
            play = true;
            
            if(paddlePosX >= 850){
                paddlePosX = 850;
                moveRight = false;    //making move variables false when paddle reaches the borders
            }
            else{
                moveRight = true;
            }
        }
        if(ke.getKeyCode() == KeyEvent.VK_LEFT){
            play = true;
            
            if(paddlePosX <= 0){
                paddlePosX = 0;
                moveLeft = false;
            }
            else{
                moveLeft = true;
            }
        }
        if (ke.getKeyCode() == KeyEvent.VK_ENTER) {//returns back the initial values of the ball and paddle and regenerates the bricks when key is entered
            if (!play) {
                ballPosX = 120;
                ballPosY = 350;
                ballDirX = 2;
                ballDirY = 5;
                score = 0;
                paddlePosX = 310;
                totalBricks = 21;
                map = new MapGenerator2(3, 7);

                repaint();//repainting the code again
            }
        }
    }
    

    private void setTraversalKeysEnabled(boolean b) {
        
    }
    
}
