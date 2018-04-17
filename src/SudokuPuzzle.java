import java.util.Random;
 
public class SudokuPuzzle {
   // Name-constants for the game properties
   public final static int GRID_SIZE = 9;    // Size of the board
 
   public static int[][] puzzle = new int[9][9];
   private int[] row1 = {1, 2, 3, 4, 5, 6, 7, 8, 9};
   private int colno = 0;

   public static boolean[][]masks = new boolean[9][9];

   /**
    * Constructor to setup the game
    */
   public SudokuPuzzle() {

	   // randomises col1
	   shufflePuzzle(row1);
	   
	   // does the shifting and put into 2D array row by row
	   for (int col = 1; col <= GRID_SIZE; col++) {
		   if (col == 2 || col == 3 || col == 5 || col == 6 || col == 8 || col == 9) {
			   shift3();
		   } else if (col == 4 || col == 7) {
			   shift1();
		   }
		   putinto2DArray(colno); // put into puzzle from row1
		   generateMasks(colno); //randomises masks
		   colno++;
	   }
	   
   }
   
   
   
   private void swap(int[] a, int i, int change) { // swap i with change
       int temp = a[i];
       a[i] = a[change];
       a[change] = temp;
   }
   
   private void shufflePuzzle(int[] a) {
       int n = a.length;
       Random random = new Random();
       for (int i = 0; i < n; i++) {
           int change = i + random.nextInt(n - i); //max + min
           swap(a, i, change);
       }
   }
   
   private void putinto2DArray(int col) {
	   for (int row = 0; row < GRID_SIZE; row++) {
		   puzzle[col][row] = row1[row];
	   }
   }
   
   private void shift3() { // shift line by 3
	   for (int row = 0; row < GRID_SIZE-3; row++) {
		   swap(row1, row, row+3);
	   }
   }
   
   private void shift1() { // shift line by 1
	   for (int row = 0; row < GRID_SIZE-1; row++) {
		   swap(row1, row, row+1);
	   }
   }
   
   private void generateMasks(int col) {
	   Random random = new Random();
	   random.nextInt();
	   for (int row = 0; row < GRID_SIZE; row++) {
		   switch(Difficulty.difficulty) {
		   case 1:
		   if (random.nextInt(100) + 1 <= 10) { // easy
			   masks[col][row] = true;
		   } else {
			   masks[col][row] = false;
		   }
		   break;
		   case 2:
		   if (random.nextInt(100) + 1 <= 25) { // medium
			   masks[col][row] = true;
		   } else {
			   masks[col][row] = false;
		   }
		   break;
		   case 3:
		   if (random.nextInt(100) + 1 <= 40) { // hard
			   masks[col][row] = true;
		   } else {
			   masks[col][row] = false;
		   }
		   break;

	   }
   }
}
}