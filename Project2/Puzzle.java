
/**
 * Puzzle maintains the internal representation of a square Slither Link puzzle.
 * 
 * @author Frinze Lapuz & Ethan Pui
 * @version 1.0
 */
import java.util.ArrayList;

public class Puzzle
{
    private int[][] puzzle;         // the numbers in the squares, i.e. the puzzle definition
    // -1 if the square is empty, 0-3 otherwise
    private boolean[][] horizontal; // the horizontal line segments in the current solution
    // true if the segment is on, false otherwise
    private boolean[][] vertical;   // the vertical line segments in the current solution
    // true if the segment is on, false otherwise

    /**
     * Creates the puzzle from file filename, and an empty solution.
     * filename is assumed to hold a valid puzzle.
     */
    public Puzzle(String filename)
    {
        // COMPLETE THIS 1b
        FileIO file = new FileIO(filename);
        //str = file getLines(); 

        //Initialize Arrays
        this.parseFile(file.getLines()); //Puzzle
        horizontal = new boolean[this.getRowSize()+1][this.getColSize()];
        vertical = new boolean[this.getRowSize()][this.getColSize()+1]; //Horizontal and Vertical
        

    }

    /**
     * Creates the puzzle from "eg3_1.txt".
     */
    public Puzzle()
    {
        this("eg3_1.txt");
    }

    /**
     * Returns the size of the puzzle.
     */
    public int size()
    {
        return puzzle.length;
    }

    //Custom getter
    public int getRowSize(){
        return puzzle.length;
    };

    public int getColSize(){
        return puzzle[0].length;
    };

    /**
     * Returns the number layout of the puzzle.
     */
    public int[][] getPuzzle()
    {
        return puzzle;
    }

    /**
     * Returns the state of the current solution, horizontally.
     */
    public boolean[][] getHorizontal()
    {
        return horizontal;
    }

    /**
     * Returns the state of the current solution, vertically.
     */
    public boolean[][] getVertical()
    {
        return vertical;
    }

    /**
     * Turns lines into a Slither Link puzzle.
     * The first String in the argument goes into puzzle[0], 
     * The second String goes into puzzle[1], etc. 
     * lines is assumed to hold a valid square puzzle; see eg3_1.txt and eg5_1.txt for examples.
     */
    public void parseFile(ArrayList<String> lines)
    {
        // COMPLETE THIS 1a
        String[] line = lines.get(0).split("\\s+"); // Used to allow count of col

        int row_num = lines.size();
        int col_num = line.length;

        puzzle = new int[row_num][col_num]; //initialize empty value with row and col num

        for (int j = 0; j<row_num;j++){
            line = lines.get(j).split("\\s+"); // \\s+ removes space 
            for(int i = 0; i<col_num;i++){
                puzzle[j][i] = Integer.parseInt(line[i]);
            }
        }
    }

    /**
     * Toggles the horizontal line segment to the right of Dot r,c, if the indices are legal.
     * Otherwise do nothing.
     */
    public void horizontalClick(int r, int c)
    {
        // COMPLETE THIS 2a
        
        if( (0<=r) && (r<horizontal.length) ){ //ROW CHECK
            if( (0<=c) && (c<horizontal[0].length) ){ //COL CHECK
                horizontal[r][c] = horizontal[r][c] != true;
            }
        }
    }

    /**
     * Toggles the vertical line segment below Dot r,c, if the indices are legal.
     * Otherwise do nothing.
     */
    public void verticalClick(int r, int c)
    {
        // COMPLETE THIS 2b
        
        if( (0<=r) && (r<vertical.length) ){ //ROW CHECK
            if( (0<=c) && (c<vertical[0].length) ){ //COL CHECK
                vertical[r][c] = vertical[r][c] != true;
            }
            
        }
    }

    /**
     * Clears all line segments out of the current solution.
     */
    public void clear()
    {
        // COMPLETE THIS 3
        
        /* Initialization of Boolean Horizontal and Vertical
         * Dots: the board will have n+1 dots across and down.
         * Horizontal lines: the board can have n horizontal lines across, but n+1 rows of these down.
         * Vertical lines: the board can have n+1 vertical lines across, but only n rows of these down.
         */

        horizontal = new boolean[this.getRowSize()+1][this.getColSize()];
        vertical = new boolean[this.getRowSize()][this.getColSize()+1];
    }

	public int tracePath(int r) {
		return 0;
	}
}
