/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package brickbreaker;

import java.io.File;
import java.io.IOException;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.JOptionPane;

/**
 *
 * @author tedro
 */
public class music {
    
   public Clip clip;
    public void playMusic(String filePath){
		
		 
        try{
            File musicPath = new File(filePath);
            if(musicPath.exists()){
                AudioInputStream audioInput = AudioSystem.getAudioInputStream(musicPath);
                clip = AudioSystem.getClip();
                clip.open(audioInput);
//                clip.start();
                clip.loop(100);
                
                //JOptionPane.showMessageDialog(null, "Press OK to stop playing");
            }
            else{
                System.out.print("Cant find the file");
            }
        }
        catch(Exception e){
            System.out.println("Error");
        }
        
    }
}
