 

import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * This class provides unit test cases for the SlitherLink class. 
 * Note that visual displays are not tested in JUnit.
 * @author Lyndon While
 * @version 1.0
 */
public class SLTest
{
    private SlitherLink s;
    int[][] eg3 = {{-1,-1,3},{-1,-1,1},{3,-1,2}};

    /**
     * Sets up the test fixture.
     *
     * Called before every test case method.
     */
    @Before
    public void setUp()
    {
        s = new SlitherLink(new Puzzle("eg3_1.txt"));
    }

    @Test
    public void testSlitherLink() 
    {
        assertEquals(3, s.getGame().getPuzzle().length);
        assertTrue(java.util.Arrays.equals(s.getGame().getPuzzle()[0],eg3[0]));
        assertTrue(java.util.Arrays.equals(s.getGame().getPuzzle()[1],eg3[1]));
        assertTrue(java.util.Arrays.equals(s.getGame().getPuzzle()[2],eg3[2]));
        
        assertEquals(4, s.getGame().getHorizontal().length);
        for (int i = 0; i < 4; i++)
        {
            assertEquals(3, s.getGame().getHorizontal()[i].length);
            for (int j = 0; j < 3; j++)
                assertEquals(false, s.getGame().getHorizontal()[i][j]);
        }
        
        assertEquals(3, s.getGame().getVertical().length);
        for (int i = 0; i < 3; i++)
        {
            assertEquals(4, s.getGame().getVertical()[i].length);
            for (int j = 0; j < 4; j++)
                assertEquals(false, s.getGame().getVertical()[i][j]);
        }
        
        assertFalse(s.getCanvas() == null);
    }

    @Test
    public void testhorizontalClick() 
    {
        s.horizontalClick(1,2);
        assertEquals(4, s.getGame().getHorizontal().length);
        for (int i = 0; i < 4; i++)
        {
            assertEquals(3, s.getGame().getHorizontal()[i].length);
            for (int j = 0; j < 3; j++)
                assertEquals(i == 1 && j == 2, s.getGame().getHorizontal()[i][j]);
        }
        
        s.horizontalClick(2,0);
        assertEquals(4, s.getGame().getHorizontal().length);
        for (int i = 0; i < 4; i++)
        {
            assertEquals(3, s.getGame().getHorizontal()[i].length);
            for (int j = 0; j < 3; j++)
                assertEquals(i == 1 && j == 2 || i == 2 && j == 0, s.getGame().getHorizontal()[i][j]);
        }
        
        s.horizontalClick(1,2);
        assertEquals(4, s.getGame().getHorizontal().length);
        for (int i = 0; i < 4; i++)
        {
            assertEquals(3, s.getGame().getHorizontal()[i].length);
            for (int j = 0; j < 3; j++)
                assertEquals(i == 2 && j == 0, s.getGame().getHorizontal()[i][j]);
        }
        
        assertEquals(3, s.getGame().getVertical().length);
        for (int i = 0; i < 3; i++)
        {
            assertEquals(4, s.getGame().getVertical()[i].length);
            for (int j = 0; j < 4; j++)
                assertEquals(false, s.getGame().getVertical()[i][j]);
        }
        assertEquals(3, s.getGame().getPuzzle().length);
        assertTrue(java.util.Arrays.equals(s.getGame().getPuzzle()[0],eg3[0]));
        assertTrue(java.util.Arrays.equals(s.getGame().getPuzzle()[1],eg3[1]));
        assertTrue(java.util.Arrays.equals(s.getGame().getPuzzle()[2],eg3[2]));
    }

    @Test
    public void testverticalClick() 
    {
        s.verticalClick(1,0);
        assertEquals(3, s.getGame().getVertical().length);
        for (int i = 0; i < 3; i++)
        {
            assertEquals(4, s.getGame().getVertical()[i].length);
            for (int j = 0; j < 4; j++)
                assertEquals(i == 1 && j == 0, s.getGame().getVertical()[i][j]);
        }
        
        s.verticalClick(1,3);
        assertEquals(3, s.getGame().getVertical().length);
        for (int i = 0; i < 3; i++)
        {
            assertEquals(4, s.getGame().getVertical()[i].length);
            for (int j = 0; j < 4; j++)
                assertEquals(i == 1 && j == 0 || i == 1 && j == 3, s.getGame().getVertical()[i][j]);
        }
        
        s.verticalClick(1,3);
        assertEquals(3, s.getGame().getVertical().length);
        for (int i = 0; i < 3; i++)
        {
            assertEquals(4, s.getGame().getVertical()[i].length);
            for (int j = 0; j < 4; j++)
                assertEquals(i == 1 && j == 0, s.getGame().getVertical()[i][j]);
        }
        
        assertEquals(4, s.getGame().getHorizontal().length);
        for (int i = 0; i < 4; i++)
        {
            assertEquals(3, s.getGame().getHorizontal()[i].length);
            for (int j = 0; j < 3; j++)
                assertEquals(false, s.getGame().getHorizontal()[i][j]); 
        }
        assertEquals(3, s.getGame().getPuzzle().length);
        assertTrue(java.util.Arrays.equals(s.getGame().getPuzzle()[0],eg3[0]));
        assertTrue(java.util.Arrays.equals(s.getGame().getPuzzle()[1],eg3[1]));
        assertTrue(java.util.Arrays.equals(s.getGame().getPuzzle()[2],eg3[2]));
    }
}
