
/**
 * AnalyzeSolution methods are used to analyze the state of a Slither Link puzzle, 
 * to determine if the puzzle is finished. 
 * 
 * @author  Ethan Pui & Frinze Lapuz
 * @version v1.0
 */
import java.util.*;
import java.util.ArrayList;

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

    public static boolean IsBetween_Inclusive(int lower, int upper, int target){
        if((lower<=target) && (target<=upper)){
            return true;
        }
        return false;

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
        ArrayList<int[]> lonelyDot = new ArrayList<int[]>();
        int sizePuzzle = p.size();
        //int coord = {r,c}

        boolean[][] horiz = p.getHorizontal();
        boolean[][] veriz = p.getVertical();

        if(IsBetween_Inclusive(0,p.getRowSize()+1,r) && IsBetween_Inclusive(0,p.getColSize()+1,c)){
            try{ //RIGHT
                if(horiz[r][c]){
                    int[] coord = {r, c+1};
                    lonelyDot.add(coord);
                }
            }
            catch(IndexOutOfBoundsException e){}

            try{ //LEFT
                if(horiz[r][c-1]){
                    int[] coord = {r, c-1};
                    lonelyDot.add(coord);
                }
            }
            catch(IndexOutOfBoundsException e){}

            try{  //DOWN
                if(veriz[r][c]){
                    int[] coord = {r+1, c};
                    lonelyDot.add(coord);
                }
            }
            catch(IndexOutOfBoundsException e){}

            try{ //UP
                if(veriz[r-1][c]){
                    int[] coord = {r-1, c};
                    lonelyDot.add(coord);
                }
            }
            catch(IndexOutOfBoundsException e){}

            return lonelyDot;
        }
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
                    if(p.getHorizontal()[i][j]){
                        sum++;
                        r=i;
                        c=j;
                    }
                }

                if(i<p.getRowSize()){
                    if(p.getVertical()[i][j]){
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

        int[] lineSegments_Returns = lineSegments(p);
        int lineSegments = lineSegments_Returns[0];

        int count_loop =1;

        if(getConnections(p,r,c).size()==0){
            return "No path";
        }

        int[] current_coord ={r,c}; //initial coordinate

        ArrayList<int[]> Connections = getConnections(p,current_coord[0],current_coord[1]);
        int [] previous_coord = current_coord;
        current_coord = Connections.get(0); 

        do{ 
            count_loop++;
            Connections = getConnections(p,current_coord[0],current_coord[1]); //fetches 1-4 coordinates

            //REMOVE PREVIOUS COORDINATE IN LIST SO THAT IT DOES NOT BACKTRACK 
            int delete_index = 0;
            for (int[] coord : Connections){
                if((coord[0] == previous_coord[0]) && (coord[1] == previous_coord[1])){
                    Connections.remove(delete_index);
                    break;
                }
                delete_index++;
            }
            try{
                previous_coord = current_coord;
                current_coord = Connections.get(0);

                if(Connections.size()>=2){ //2 or 3 possible branching
                    return "Branching line";
                }
            }
            catch(IndexOutOfBoundsException e){
                return "Dangling end";
            } // ENDS OF THE LOOP AS current_coord = null
        }while (!((current_coord[0]==r)&&(current_coord[1]==c)));//Repeat until it goes to back to the start

        return count_loop+"";
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
        int[] array_Lines = lineSegments(p);

        int r = array_Lines[1];
        int c = array_Lines[2];

        if(badSquares(p).size() > 0){
            return "Wrong number";
        } 

        String s = tracePath(p, r, c);
        try{
            int int_tracePath =  Integer.parseInt(s);

            if(int_tracePath == array_Lines[0]){
                return"Finished";
            }

            return"Disconnected lines";
        }

        catch(NumberFormatException e){
            return s;
        }

        //NumberFormatException e
        // Recieve String from tracePath -> into Int, if Int then Puzzle Completed
        //try
        // String tracePath_return = tracePath(p,r,c); //TRACEPATH CAN RETURN NUMBER IN STRING FORMAT OR ERROR

        // if(Character.isDigit(tracePath_return.charAt(0))){
        //     int int_tracePath =  Integer.parseInt(tracePath_return);
        //     if(int_tracePath < array_Lines[0]) { // number of lines 
        //         return "Disconnected lines"; 
        //     }
        // }

        // if(badSquares(p).size() > 0){
        //     return "Wrong number";
        // }

        //     return "";
        // } if lineSegments = tracePath then puzzle is complete
        // }else{
        //     return tracePath_return;
        // } 

    }
}

