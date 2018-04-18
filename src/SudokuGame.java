import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JPanel;

import java.awt.BasicStroke;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.Stroke;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class SudokuGame {
	public static final int GRID_SIZE = 9;  // Size of the board
	 
	  // Name-constants for UI control (sizes, colors and fonts)
	private static final int CELL_SIZE = 60;  // Cell width/height in pixels
	private static final int CANVAS_WIDTH = CELL_SIZE * GRID_SIZE;
	private static final int CANVAS_HEIGHT = CELL_SIZE * GRID_SIZE;
	  
	  
	private static final Font FONT_NUMBERS = new Font("Monospaced", Font.BOLD, 20);
	
	Color btn1 = new Color(234, 207, 125);
  	Color btn2 = new Color(215,92,72);
  	Color btn3 = new Color(190, 44, 69);
  	
	Color TextColor = new Color(118, 79, 58);
	Color dark = new Color(222, 142, 123);
	Color light = new Color(241, 212, 178);
	Color lighter = new Color(236, 230, 207);
  	
	static JFrame frame= new JFrame();
	
	JPanel puzzlePanel, buttonPanel, instructionsPanel, checkCorrectPanel, newGamePanel;
	JButton btnInstructions, btnCheckCorrect, btnNewGame;
	int number = 1;
	public static JTextField[][] tfCells = new JTextField[GRID_SIZE][GRID_SIZE];
	public int[][] data = new int[9][9];
	public static int timer = 0; 
	
	@SuppressWarnings("serial")
	public SudokuGame(){
		
		new SudokuPuzzle();
//		String filepath = "/Users/kohyeezuo/eclipse-workspace/Sudoku/src/.wav";
//		playSound(filepath);

		frame.getContentPane().setBackground( Color.white );
		frame.pack();
		frame.setVisible(true);
		frame.setLayout(new BorderLayout(20, 20)); //frame with 3 columns and 3 rows contains 9 panels
		frame.setBounds(250, 250, 0, 0);
		frame.setTitle("Sudoku by YeeZuo & Joyce");


		frame.setLayout(new BorderLayout(3, 20));

		frame.setBounds(250, 250, 0, 0);

		frame.setTitle("Sudoku by YeeZuo & Joyce");





		puzzlePanel = new JPanel(){

		protected void paintComponent(Graphics g) {
	        super.paintComponent(g);
	        Graphics2D g2 = (Graphics2D) g;
	        g2.setColor(btn1); 
	        float thickness = 2;
	        Stroke oldStroke = g2.getStroke();
	        g2.setStroke(new BasicStroke(thickness));
	        g2.drawRect(3,3,175,175);  //first col first
	       // g2.drawRect(x, y, width, height);
	        g2.drawRect(3, 179, 175, 178);//first col second
	        g2.drawRect(3, 358, 175, 178);// first col last
	        g2.drawRect(179, 3, 179, 175);//first row second
	        g2.drawRect(179, 179, 179, 179);//second row second
	        g2.drawRect(179, 357, 179, 179);//
	        g2.drawRect(359, 3, 179, 175);//second

	        g2.drawRect(359, 179, 179, 179);

	        g2.drawRect(359, 357, 179, 179);

	       

	    //    g2.fillRect(3,3,175,175);  

	        }

	    };		puzzlePanel.setBackground(Color.WHITE);
		puzzlePanel.setLayout(new GridLayout(9,3,5,5));
		 for (int row = 0; row < GRID_SIZE; ++row) {
	     for (int col = 0; col < GRID_SIZE; ++col) {
	      tfCells[row][col] = new JTextField(); // Allocate element of array
	      puzzlePanel.add(tfCells[row][col]);      // ContentPane adds JTextField
	      if (SudokuPuzzle.masks[row][col]) {
	        tfCells[row][col].setText("");   // set to empty string
	        tfCells[row][col].setEditable(true);
	        tfCells[row][col].setBackground(dark);
	 
	        // Add ActionEvent listener to process the input
	        // ... [TODO 4] (Later) ...
	      } else {
	        tfCells[row][col].setText(SudokuPuzzle.puzzle[row][col] + "");
	        tfCells[row][col].setEditable(false);
	        tfCells[row][col].setBackground(lighter);
	        tfCells[row][col].setForeground(TextColor);
	      }
	      // Beautify all the cells
	      tfCells[row][col].setHorizontalAlignment(JTextField.CENTER);
	      tfCells[row][col].setFont(FONT_NUMBERS);
		  tfCells[row][col].addKeyListener(new KeyAdapter() {
			  @Override
			  public void keyTyped(KeyEvent e) {
				  char c = e.getKeyChar();
				  if(!(Character.isDigit(c) || c == KeyEvent.VK_BACK_SPACE || c == KeyEvent.VK_DELETE)) {
					  e.consume();
				  }
				  
			  }
		  });
	     }
	   }

		 puzzlePanel.setPreferredSize(new Dimension(CANVAS_WIDTH,CANVAS_HEIGHT));
	   
		 
		 
		frame.add(puzzlePanel, BorderLayout.WEST);

		
		buttonPanel = new JPanel();
		buttonPanel.setLayout(new GridLayout(5,1));
		buttonPanel.setBackground(light);

		/*
		 * Show instructions
		 */
		btnInstructions = new JButton("Instructions");
     
		btnInstructions.setPreferredSize(new Dimension(150,65));
	
    
		btnInstructions.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(null, "The objective of sudoku is to enter a digit from 1 through 9 in every cell, in such a way that:\n"
						+ "\n" + "Each horizontal column contains each digit only once\n" + "Each vertical row contains each digit only once\n"
						+ "Each 3 by 3 square contains each digit only once\n\n" + "Several digits have already been entered and cannot be changed");
			}
		});
		


		instructionsPanel = new JPanel();
		instructionsPanel.setBounds(155, 65, 155, 65);
		//instructionsPanel.setBackground(btn1);
		instructionsPanel.add(btnInstructions);
		instructionsPanel.setBackground(light);
		buttonPanel.add(instructionsPanel,BorderLayout.WEST);
		//btnInstructions.setOpaque(true);
		btnInstructions.setBackground(Color.BLACK);//Black By Default
		btnInstructions.setForeground(dark);//

		/*
		 * Check to see if the puzzle was solved successfully
		 */
		btnCheckCorrect = new JButton("Finished");
		btnCheckCorrect.setPreferredSize(new Dimension(150,65));
		
		checkCorrectPanel = new JPanel();
		checkCorrectPanel.setBounds(155, 65, 155, 65);
		checkCorrectPanel.add(btnCheckCorrect);
		checkCorrectPanel.setBackground(light);
		buttonPanel.add(checkCorrectPanel);
		btnCheckCorrect.setForeground(btn2);
		btnCheckCorrect.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				//Check
				new Finished();

			}
		});
		
		/*
		 * Start a new game
		 */
		btnNewGame = new JButton("New Game");
		btnNewGame.setPreferredSize(new Dimension(150,65));
	
		newGamePanel = new JPanel();
		newGamePanel.setBounds(155, 65, 155, 65);
		newGamePanel.add(btnNewGame);
		btnNewGame.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				new Difficulty();
			}
		});

		newGamePanel = new JPanel();
		newGamePanel.setBounds(155, 65, 155, 65);
		newGamePanel.add(btnNewGame);
		buttonPanel.add(newGamePanel);
		

		frame.add(buttonPanel, BorderLayout.EAST);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.pack();
		frame.setVisible(true);
		frame.setResizable(false);
		newGamePanel.setBackground(light);
		buttonPanel.add(newGamePanel);
		btnNewGame.setForeground(btn3);//

		frame.add(buttonPanel, BorderLayout.EAST);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.pack();
		frame.setVisible(true);
		frame.setResizable(false);
	}
//		  
//	  public void playSound(String file) {
//	    try {
//	        AudioInputStream audio = AudioSystem.getAudioInputStream(new File(
//	                file));
//	        Clip clip = AudioSystem.getClip();
//	        clip.open(audio);
//	        clip.start();
//	    }
//
//	    catch (UnsupportedAudioFileException uae) {
//	        System.out.println(uae);
//	    } catch (IOException ioe) {
//	        System.out.println(ioe);
//	    } catch (LineUnavailableException lua) {
//	        System.out.println(lua);
//	    }
//	}
	  
}