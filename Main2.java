
package brickbreaker;

import brickBreaker.MenuGui2;
import javax.swing.JFrame;

public class Main2 {

    public static void main(String[] args) {
       music mu = new music();
       mu.playMusic("C:\\Users\\tedro\\Documents\\NetBeansProjects\\brickBreaker\\src\\brickbreaker\\meme_song.wav");
       new MenuGui2().setVisible(true);
       
       
    }
    
}
