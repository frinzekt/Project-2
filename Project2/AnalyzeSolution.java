
/**
 * AnalyzeSolution methods are used to analyze the state of a Slither Link puzzle, 
 * to determine if the puzzle is finished. 
 * 
 * @author  Ethan Pui & Frinze Lapuz
 * @version v1.0
 */
import java.util.*;

public class AnalyzeSolution
{
    /**
     * We don't need to create any objects of class AnalyzeSolution; all of its methods are static.
     */
    private AnalyzeSolution() {}

    /**
     * Returns the number of line segments surrounding Square r,c in p.
     * Returns 0 if the indices are illegal.
     */
    public static int linesAroundSquare(Puzzle p, int r, int c)
    {
        // COMPLETE THIS 7
        int lines_num = 0;
        boolean[][] horiz = p.getHorizontal();
        boolean[][] veriz = p.getVertical();

        if(horiz[r][c] == true){
            lines_num += 1;
        }
        if(horiz[r + 1][c] == true){
            lines_num += 1;
        }
        if(veriz[r][c] == true){
            lines_num += 1;
        }
        if(veriz[r][c+1] == true){
            lines_num += 1;
        }
        if(lines_num > 0){
            return lines_num;
        }
        return 0; //returns 0 if index is illegal
    }

    /**
     * Returns all squares in p that are surrounded by the wrong number of line segments.
     * Each item on the result will be an int[2] containing the indices of a square.
     * The order of the items on the result is unimportant.
     */
    public static ArrayList<int[]> badSquares(Puzzle p)
    {
        // COMPLETE THIS 8
        //Initialization, ArrayList<int[2]>
        ArrayList<int[]> BadSquares = new ArrayList<>();

        for (int i=0; i<p.getRowSize(); i++){
            for(int j =0; j<p.getColSize();j++){
                if((linesAroundSquare(p,i,j)!= p.getPuzzle()[i][j]) && p.getPuzzle()[i][j]!=-1){
                    int[] coordinates = {i,j};
                    BadSquares.add(coordinates);
                }
            }
        }

        return BadSquares;
    }

    /**
     * Returns all dots connected by a single line segment to Dot r,c in p.
     * Each item on the result will be an int[2] containing the indices of a dot.
     * The order of the items on the result is unimportant.
     * Returns null if the indices are illegal.
     */
    public static ArrayList<int[]> getConnections(Puzzle p, int r, int c)
    {
        // COMPLETE THIS 9
        
        
        
        
        return null;
    }

    /**
     * Returns an array of length 3 whose first element is the number of line segments in the puzzle p, 
     * and whose other elements are the indices of a dot on any one of those segments. 
     * Returns {0,0,0} if there are no line segments on the board. 
     */
    public static int[] lineSegments(Puzzle p)
    {
        // COMPLETE THIS 10
        int sum = 0;
        int r = 0;
        int c = 0;

        for(int i=0; i<=p.getRowSize(); i++){
            for(int j=0; j<=p.getColSize();j++){
                if(j<p.getColSize()){
                    if(p.getHorizontal()[i][j] == true){
                        sum++;
                        r=i;
                        c=j;
                    }
                }
                
                if(i<p.getRowSize()){
                    if(p.getVertical()[i][j] == true){
                        sum++;
                    }
                }
            }
        }
        int[] segment_return = {sum,r,c};
        return segment_return;
    }

    /**
     * Tries to trace a closed loop starting from Dot r,c in p. 
     * Returns either an appropriate error message, or 
     * the number of steps in the closed loop (as a String). 
     * See the project page and the JUnit for a description of the messages expected. 
     */
    public static String tracePath(Puzzle p, int r, int c)
    {
        // COMPLETE THIS 11
        return "";
    }

    /**
     * Returns a message on whether the puzzle p is finished. 
     * p is finished iff all squares are good, and all line segments form a single closed loop. 
     * An algorithm is given on the project page. 
     * See the project page and the JUnit for a description of the messages expected.
     */
    public static String finished(Puzzle p)
    {
        // COMPLETE THIS 12
        return "";
    }
}
