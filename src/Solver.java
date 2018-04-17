import java.awt.Color;

public class Solver {
	public static int error;
	Color Green = new Color(50,205,50);
	Color TextColor = new Color(255, 255, 255);
	Color dark = new Color(222, 142, 123);
	Color black = new Color(0, 0, 0);
	
	public Solver() {
		error = 0;
		for (int row = 0; row < SudokuPuzzle.GRID_SIZE; row++) {
			for (int col = 0; col < SudokuPuzzle.GRID_SIZE; col++) {
				if (SudokuGame.tfCells[row][col].getText().equals("")) {
					SudokuGame.tfCells[row][col].setText("0");
				}
				if (SudokuPuzzle.puzzle[row][col] != Integer.parseInt(SudokuGame.tfCells[row][col].getText())) {
					error++;
				}
				if(SudokuPuzzle.masks[row][col]) {
				if (SudokuPuzzle.puzzle[row][col] == Integer.parseInt(SudokuGame.tfCells[row][col].getText())) {
					SudokuGame.tfCells[row][col].setBackground(Green);
					SudokuGame.tfCells[row][col].setForeground(TextColor);
				} else {
					SudokuGame.tfCells[row][col].setBackground(dark);
					SudokuGame.tfCells[row][col].setForeground(black);
				}
				}
				if (SudokuGame.tfCells[row][col].getText().equals("0")) {
					SudokuGame.tfCells[row][col].setText("");
				}

				}
		}
		
			
	}

}
