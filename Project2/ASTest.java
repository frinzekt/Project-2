package Project2;

import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import java.util.ArrayList;

/**
 * This class provides unit test cases for the AnalyzeSolution class. 
 * @author Lyndon While
 * @version 1.0
 */
public class ASTest
{
    private Puzzle p, q;
    int[][] eg3 = {{-1,-1,3},{-1,-1,1},{3,-1,2}};

    /**
     * Sets up the test fixture.
     *
     * Called before every test case method.
     */
    @Before
    public void setUp()
    {
        p = new Puzzle("eg3_1.txt");
        q = new Puzzle("eg5_2.txt"); // this is the one from the web page
    }
    
    // Returns "true" iff xs and ys contain the same elements, even in different orders,
    // otherwise returns an error msg.
    // Assumes that all arrays have length 2.
    // A call to sameElements clears its second argument, 
    // so always make that the actual result NOT the expected result. 
    private String sameElements(ArrayList<int[]> xs, ArrayList<int[]> ys)
    {
        if (xs == null || ys == null) return "One of the lists is null";
        if (xs.size() != ys.size())   return "The lists have different sizes";
        for (int[] x : xs)
        {
            boolean b = false;
            int i = 0;
            while (i < ys.size() && !b)
                if (java.util.Arrays.equals(x, ys.get(i)))
                     {ys.remove(i); b = true;}
                else i++;
            if (!b) return x[0] + "," + x[1] + " not found"; 
        }
        return "true";
    }
    
    // returns either the substring s[a..b], or the whole of s if appropriate
    private String substring(String s, int a, int b)
    {
        if (b >= s.length()) return s;
        else                 return s.substring(a, b);
    }
    
    // Returns "true" iff ls[0] has the appropriate value and 
    // (ls[1],ls2[]) denotes one of the locations in zs (if any),
    // otherwise returns an error msg.
    private String checkSegments(ArrayList<int[]> zs, int[] ls)
    {
        if (zs.size() == 0)         
           if (ls[0] == 0 && ls[1] == 0 && ls[2] == 0) return "true";
           else                                        return "Wrong when board empty";
        if (2 * ls[0] != zs.size())                    return "Wrong number of segments";
        for (int[] z : zs) 
            if (z[0] == ls[1] && z[1] == ls[2]) return "true";
        return ls[1] + "," + ls[2] + " not found";
    }
    
    @Test
    public void testlinesAroundSquare()
    {
        assertEquals(3, p.getPuzzle().length);
        assertTrue(java.util.Arrays.equals(p.getPuzzle()[0],eg3[0]));
        assertTrue(java.util.Arrays.equals(p.getPuzzle()[1],eg3[1]));
        assertTrue(java.util.Arrays.equals(p.getPuzzle()[2],eg3[2]));
        for (int r = 0; r < 3; r++)
            for (int c = 0; c < 3; c++)
                assertEquals(0, AnalyzeSolution.linesAroundSquare(p, r, c));
        
        p.horizontalClick(1, 1);
        for (int r = 0; r < 3; r++)
            for (int c = 0; c < 3; c++)
                if ((r == 0 || r == 1) && c == 1)
                   assertEquals(1, AnalyzeSolution.linesAroundSquare(p, r, c));
                else
                   assertEquals(0, AnalyzeSolution.linesAroundSquare(p, r, c));
        
        p.verticalClick(0, 2);
        for (int r = 0; r < 3; r++)
            for (int c = 0; c < 3; c++)
                if (r == 0 && c == 1)
                   assertEquals(2, AnalyzeSolution.linesAroundSquare(p, r, c));
                else if (r == 1 && c == 1 || r == 0 && c == 2)
                   assertEquals(1, AnalyzeSolution.linesAroundSquare(p, r, c));
                else
                   assertEquals(0, AnalyzeSolution.linesAroundSquare(p, r, c));
        
        p.horizontalClick(0, 1);
        for (int r = 0; r < 3; r++)
            for (int c = 0; c < 3; c++)
                if (r == 0 && c == 1)
                   assertEquals(3, AnalyzeSolution.linesAroundSquare(p, r, c));
                else if (r == 1 && c == 1 || r == 0 && c == 2)
                   assertEquals(1, AnalyzeSolution.linesAroundSquare(p, r, c));
                else
                   assertEquals(0, AnalyzeSolution.linesAroundSquare(p, r, c));
        
        p.verticalClick(0, 0);
        for (int r = 0; r < 3; r++)
            for (int c = 0; c < 3; c++)
                if (r == 0 && c == 1)
                   assertEquals(3, AnalyzeSolution.linesAroundSquare(p, r, c));
                else if (r == 1 && c == 1 || r == 0)
                   assertEquals(1, AnalyzeSolution.linesAroundSquare(p, r, c));
                else
                   assertEquals(0, AnalyzeSolution.linesAroundSquare(p, r, c));
    }
    
    @Test
    public void testbadSquares()
    {
        ArrayList<int[]> zs = new ArrayList<>(); 
        zs.add(new int[] {0,2});
        zs.add(new int[] {1,2});
        zs.add(new int[] {2,0});
        zs.add(new int[] {2,2});
        assertEquals("true", sameElements(zs, AnalyzeSolution.badSquares(p)));
        
        p.horizontalClick(1,2);
        zs.remove(1);
        assertEquals("true", sameElements(zs, AnalyzeSolution.badSquares(p)));
        
        p.horizontalClick(3,2);
        assertEquals("true", sameElements(zs, AnalyzeSolution.badSquares(p)));
        p.verticalClick(2,2);
        zs.remove(2);
        assertEquals("true", sameElements(zs, AnalyzeSolution.badSquares(p)));
        
        p.verticalClick(0,2);
        assertEquals("true", sameElements(zs, AnalyzeSolution.badSquares(p)));
        p.verticalClick(0,3);
        zs.remove(0);
        assertEquals("true", sameElements(zs, AnalyzeSolution.badSquares(p)));
        
        p.verticalClick(2,1);
        assertEquals("true", sameElements(zs, AnalyzeSolution.badSquares(p)));
        p.verticalClick(2,0);
        assertEquals("true", sameElements(zs, AnalyzeSolution.badSquares(p)));
        p.horizontalClick(3,0);
        zs.remove(0);
        assertEquals("true", sameElements(zs, AnalyzeSolution.badSquares(p)));
    }
    
    @Test
    public void testgetConnections()
    {
        ArrayList<int[]> empty    = new ArrayList<>();
        ArrayList<int[]> nonempty = new ArrayList<>();
        for (int i = 0; i <= p.size(); i++)
            for (int j = 0; j <= p.size(); j++)
                assertEquals("true", sameElements(empty, AnalyzeSolution.getConnections(p, i, j)));
        
        p.horizontalClick(3, 1);
        for (int i = 0; i <= p.size(); i++)
            for (int j = 0; j <= p.size(); j++)
                if (i == 3 && j == 1)
                {
                   nonempty.add(new int[] {3,2});
                   assertEquals("true", sameElements(nonempty, AnalyzeSolution.getConnections(p, i, j)));
                   nonempty.clear();
                }
                else if (i == 3 && j == 2)
                {
                   nonempty.add(new int[] {3,1});
                   assertEquals("true", sameElements(nonempty, AnalyzeSolution.getConnections(p, i, j)));
                   nonempty.clear();
                }
                else
                   assertEquals("true", sameElements(empty, AnalyzeSolution.getConnections(p, i, j)));
        
        p.verticalClick(2, 1);
        for (int i = 0; i <= p.size(); i++)
            for (int j = 0; j <= p.size(); j++)
                if (i == 3 && j == 1)
                {
                   nonempty.add(new int[] {2,1});
                   nonempty.add(new int[] {3,2});
                   assertEquals("true", sameElements(nonempty, AnalyzeSolution.getConnections(p, i, j)));
                   nonempty.clear();
                }
                else if (i == 2 && j == 1 || i == 3 && j == 2)
                {
                   nonempty.add(new int[] {3,1});
                   assertEquals("true", sameElements(nonempty, AnalyzeSolution.getConnections(p, i, j)));
                   nonempty.clear();
                }
                else
                   assertEquals("true", sameElements(empty, AnalyzeSolution.getConnections(p, i, j)));
        
        p.horizontalClick(3, 0);
        for (int i = 0; i <= p.size(); i++)
            for (int j = 0; j <= p.size(); j++)
                if (i == 3 && j == 1)
                {
                   nonempty.add(new int[] {2,1});
                   nonempty.add(new int[] {3,0});
                   nonempty.add(new int[] {3,2});
                   assertEquals("true", sameElements(nonempty, AnalyzeSolution.getConnections(p, i, j)));
                   nonempty.clear();
                }
                else if (i == 2 && j == 1 || i == 3 && (j == 0 || j == 2))
                {
                   nonempty.add(new int[] {3,1});
                   assertEquals("true", sameElements(nonempty, AnalyzeSolution.getConnections(p, i, j)));
                   nonempty.clear();
                }
                else
                   assertEquals("true", sameElements(empty, AnalyzeSolution.getConnections(p, i, j)));
    }
    
    @Test
    public void testtracePath()
    {
        // using substring allows for any suffix information
        assertEquals("No path", substring(AnalyzeSolution.tracePath(q, 0, 0), 0, 7));
        
        for (int c : new int[] {1,2,3,4}) q.horizontalClick(0, c);
        for (int c : new int[] {0,2,3,4}) q.horizontalClick(1, c);
        for (int c : new int[] {0,3})     q.horizontalClick(2, c);
        for (int c : new int[] {2,4})     q.horizontalClick(3, c);
        for (int c : new int[] {0,2,4})   q.horizontalClick(4, c);
        for (int c : new int[] {0,1,3})   q.horizontalClick(5, c);
        for (int r : new int[] {1,4})     q.verticalClick(r, 0);
        for (int r : new int[] {0,2,3})   q.verticalClick(r, 1);
        for (int r : new int[] {1,2,4})   q.verticalClick(r, 2);
        for (int r : new int[] {2,4})     q.verticalClick(r, 3);
        for (int r : new int[] {2,4})     q.verticalClick(r, 4);
        for (int r : new int[] {0,3})     q.verticalClick(r, 5);
        assertEquals("32",      substring(AnalyzeSolution.tracePath(q, 1, 1), 0, 2));
        assertEquals("32",      substring(AnalyzeSolution.tracePath(q, 5, 2), 0, 2));
        assertEquals("No path", substring(AnalyzeSolution.tracePath(q, 5, 5), 0, 7));
        
        q.horizontalClick(0, 4);
        assertEquals("Dangling end", substring(AnalyzeSolution.tracePath(q, 1, 1), 0, 12));
        assertEquals("Dangling end", substring(AnalyzeSolution.tracePath(q, 4, 0), 0, 12));
        assertEquals("No path",      substring(AnalyzeSolution.tracePath(q, 2, 5), 0, 7));
        
        q.horizontalClick(0, 4);
        q.horizontalClick(4, 3);
        assertEquals("Branching line", substring(AnalyzeSolution.tracePath(q, 1, 1), 0, 14));
        assertEquals("Branching line", substring(AnalyzeSolution.tracePath(q, 1, 5), 0, 14));
        assertEquals("No path",        substring(AnalyzeSolution.tracePath(q, 3, 0), 0, 7));
        
        q.horizontalClick(1, 0);
        q.verticalClick(0, 1);
        assertEquals("No path", substring(AnalyzeSolution.tracePath(q, 1, 1), 0, 7));
    }
    
    @Test
    public void testlineSegments()
    {
        ArrayList<int[]> zs = new ArrayList<>();
        assertEquals("true", checkSegments(zs, AnalyzeSolution.lineSegments(p)));
        
        p.horizontalClick(1,1);
        zs.add(new int[] {1,1});
        zs.add(new int[] {1,2});
        assertEquals("true", checkSegments(zs, AnalyzeSolution.lineSegments(p)));
        
        p.verticalClick(0,3);
        zs.add(new int[] {0,3});
        zs.add(new int[] {1,3});
        assertEquals("true", checkSegments(zs, AnalyzeSolution.lineSegments(p)));
        
        p.verticalClick(1,1);
        zs.add(new int[] {1,1});
        zs.add(new int[] {2,1});
        assertEquals("true", checkSegments(zs, AnalyzeSolution.lineSegments(p)));
        
        p.horizontalClick(1,2);
        zs.add(new int[] {1,2});
        zs.add(new int[] {1,3});
        assertEquals("true", checkSegments(zs, AnalyzeSolution.lineSegments(p)));
    }
    
    @Test
    public void testfinished()
    {
        // using substring allows for any suffix information
        // all of these tests are pictured on the web page
        assertEquals("Wrong number", substring(AnalyzeSolution.finished(q), 0, 12));
        
        for (int c : new int[] {1,2,3,4}) q.horizontalClick(0, c);
        for (int c : new int[] {0,2,3,4}) q.horizontalClick(1, c);
        for (int c : new int[] {0,3})     q.horizontalClick(2, c);
        for (int c : new int[] {2,4})     q.horizontalClick(3, c);
        for (int c : new int[] {0,2,4})   q.horizontalClick(4, c);
        for (int c : new int[] {0,1,3})   q.horizontalClick(5, c);
        for (int r : new int[] {1,4})     q.verticalClick(r, 0);
        for (int r : new int[] {0,2,3})   q.verticalClick(r, 1);
        for (int r : new int[] {1,2,4})   q.verticalClick(r, 2);
        for (int r : new int[] {2,4})     q.verticalClick(r, 3);
        for (int r : new int[] {2,4})     q.verticalClick(r, 4);
        for (int r : new int[] {0,3})     q.verticalClick(r, 5);
        assertEquals("Finished", substring(AnalyzeSolution.finished(q), 0, 8));
        
        q.horizontalClick(0, 4);
        q.horizontalClick(1, 4);
        q.verticalClick(0, 4);
        q.verticalClick(0, 5);
        assertEquals("Wrong number", substring(AnalyzeSolution.finished(q), 0, 12));
        
        q.verticalClick(0, 4);
        assertEquals("Dangling end", substring(AnalyzeSolution.finished(q), 0, 12));
        
        q.horizontalClick(0, 4);
        q.horizontalClick(1, 4);
        q.verticalClick(0, 5);
        assertEquals("Finished", substring(AnalyzeSolution.finished(q), 0, 8));
        
        q.horizontalClick(0, 0);
        q.verticalClick(0, 0);
        q.verticalClick(0, 1);
        q.horizontalClick(1, 1);
        assertEquals("Branching line", substring(AnalyzeSolution.finished(q), 0, 14));
        
        q.horizontalClick(0, 0);
        q.verticalClick(0, 0);
        q.verticalClick(0, 1);
        q.horizontalClick(1, 1);
        assertEquals("Finished", substring(AnalyzeSolution.finished(q), 0, 8)); 
        
        q.horizontalClick(0, 3);
        q.horizontalClick(1, 3);
        q.verticalClick(0, 3);
        q.verticalClick(0, 4);
        assertEquals("Disconnected lines", substring(AnalyzeSolution.finished(q), 0, 18));
        
        q.horizontalClick(0, 3);
        q.horizontalClick(1, 3);
        q.verticalClick(0, 3);
        q.verticalClick(0, 4);
        assertEquals("Finished", substring(AnalyzeSolution.finished(q), 0, 8));
        
        q.clear();
        assertEquals("Wrong number", substring(AnalyzeSolution.finished(q), 0, 12));
    }
}
