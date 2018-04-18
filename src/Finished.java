import java.io.File;
import java.io.IOException;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.JOptionPane;

public class Finished  {

    public Finished() {  
		new Solver();	
		if (Solver.error == 0) {
			String filepath = "/Users/kohyeezuo/eclipse-workspace/Sudoku/src/victory.wav";
			playSound(filepath);
			JOptionPane.showMessageDialog(null, "You win!");
			SudokuGame.frame.dispose();
			new StartMenu();
		} else if (Solver.error == 1){
			JOptionPane.showMessageDialog(null, "You have " + Solver.error + " Error!");
		} else {
			JOptionPane.showMessageDialog(null, "You have " + Solver.error + " Errors!");
		}
	}
            

            
  public void playSound(String file) {
    try {
        AudioInputStream audio = AudioSystem.getAudioInputStream(new File(
                file));
        Clip clip = AudioSystem.getClip();
        clip.open(audio);
        clip.start();
    }

    catch (UnsupportedAudioFileException uae) {
        System.out.println(uae);
    } catch (IOException ioe) {
        System.out.println(ioe);
    } catch (LineUnavailableException lua) {
        System.out.println(lua);
    }
}

      	  

    }
