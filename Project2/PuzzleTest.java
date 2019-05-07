 

import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * This class provides unit test cases for the Puzzle class.
 * @author Lyndon While
 * @version 1.0
 */
public class PuzzleTest
{
    private Puzzle p;
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
    }

    @Test
    public void testPuzzle() 
    {
        assertEquals(3, p.getPuzzle().length);
        assertTrue(java.util.Arrays.equals(p.getPuzzle()[0],eg3[0]));
        assertTrue(java.util.Arrays.equals(p.getPuzzle()[1],eg3[1]));
        assertTrue(java.util.Arrays.equals(p.getPuzzle()[2],eg3[2]));
        
        assertEquals(4, p.getHorizontal().length);
        for (int i = 0; i < 4; i++)
        {
            assertEquals(3, p.getHorizontal()[i].length);
            for (int j = 0; j < 3; j++)
                assertEquals(false, p.getHorizontal()[i][j]);
        }
        
        assertEquals(3, p.getVertical().length);
        for (int i = 0; i < 3; i++)
        {
            assertEquals(4, p.getVertical()[i].length);
            for (int j = 0; j < 4; j++)
                assertEquals(false, p.getVertical()[i][j]);
        }
    }

    @Test
    public void testhorizontalClick() 
    {
        p.horizontalClick(1,2);
        assertEquals(4, p.getHorizontal().length);
        for (int i = 0; i < 4; i++)
        {
            assertEquals(3, p.getHorizontal()[i].length);
            for (int j = 0; j < 3; j++)
                assertEquals(i == 1 && j == 2, p.getHorizontal()[i][j]);
        }
        
        p.horizontalClick(2,0);
        assertEquals(4, p.getHorizontal().length);
        for (int i = 0; i < 4; i++)
        {
            assertEquals(3, p.getHorizontal()[i].length);
            for (int j = 0; j < 3; j++)
                assertEquals(i == 1 && j == 2 || i == 2 && j == 0, p.getHorizontal()[i][j]);
        }
        
        p.horizontalClick(0,3);
        assertEquals(4, p.getHorizontal().length);
        for (int i = 0; i < 4; i++)
        {
            assertEquals(3, p.getHorizontal()[i].length);
            for (int j = 0; j < 3; j++)
                assertEquals(i == 1 && j == 2 || i == 2 && j == 0, p.getHorizontal()[i][j]);
        }
        
        p.horizontalClick(1,2);
        assertEquals(4, p.getHorizontal().length);
        for (int i = 0; i < 4; i++)
        {
            assertEquals(3, p.getHorizontal()[i].length);
            for (int j = 0; j < 3; j++)
                assertEquals(i == 2 && j == 0, p.getHorizontal()[i][j]);
        }
        
        assertEquals(3, p.getVertical().length);
        for (int i = 0; i < 3; i++)
        {
            assertEquals(4, p.getVertical()[i].length);
            for (int j = 0; j < 4; j++)
                assertEquals(false, p.getVertical()[i][j]);
        }
        assertEquals(3, p.getPuzzle().length);
        assertTrue(java.util.Arrays.equals(p.getPuzzle()[0],eg3[0]));
        assertTrue(java.util.Arrays.equals(p.getPuzzle()[1],eg3[1]));
        assertTrue(java.util.Arrays.equals(p.getPuzzle()[2],eg3[2])); 
    }

    @Test
    public void testverticalClick() 
    {
        p.verticalClick(1,0);
        assertEquals(3, p.getVertical().length);
        for (int i = 0; i < 3; i++)
        {
            assertEquals(4, p.getVertical()[i].length);
            for (int j = 0; j < 4; j++)
                assertEquals(i == 1 && j == 0, p.getVertical()[i][j]);
        }
        
        p.verticalClick(1,4);
        assertEquals(3, p.getVertical().length);
        for (int i = 0; i < 3; i++)
        {
            assertEquals(4, p.getVertical()[i].length);
            for (int j = 0; j < 4; j++)
                assertEquals(i == 1 && j == 0, p.getVertical()[i][j]);
        }
        
        p.verticalClick(1,3);
        assertEquals(3, p.getVertical().length);
        for (int i = 0; i < 3; i++)
        {
            assertEquals(4, p.getVertical()[i].length);
            for (int j = 0; j < 4; j++)
                assertEquals(i == 1 && j == 0 || i == 1 && j == 3, p.getVertical()[i][j]);
        }
        
        p.verticalClick(1,3);
        assertEquals(3, p.getVertical().length);
        for (int i = 0; i < 3; i++)
        {
            assertEquals(4, p.getVertical()[i].length);
            for (int j = 0; j < 4; j++)
                assertEquals(i == 1 && j == 0, p.getVertical()[i][j]);
        }
        
        assertEquals(4, p.getHorizontal().length);
        for (int i = 0; i < 4; i++)
        {
            assertEquals(3, p.getHorizontal()[i].length);
            for (int j = 0; j < 3; j++)
                assertEquals(false, p.getHorizontal()[i][j]);
        }
        assertEquals(3, p.getPuzzle().length);
        assertTrue(java.util.Arrays.equals(p.getPuzzle()[0],eg3[0]));
        assertTrue(java.util.Arrays.equals(p.getPuzzle()[1],eg3[1]));
        assertTrue(java.util.Arrays.equals(p.getPuzzle()[2],eg3[2])); 
    }

    @Test
    public void testclear() 
    {
        testPuzzle();
    }
}
