package Project2;


/**
 * SlitherLink does the user interaction for a square Slither Link puzzle.
 * 
 * @author Lyndon While 
 * @version 1.0
 */
import java.awt.*;
import java.awt.event.*;

public class SlitherLink implements MouseListener
{    
    private Puzzle game;     // internal representation of the game
    private SimpleCanvas sc; // the display window

    /**
     * Creates a display for playing the puzzle p.
     */
    public SlitherLink(Puzzle p)
    {
        // COMPLETE THIS 4b
    }
    
    /**
     * Returns the current state of the game.
     */
    public Puzzle getGame()
    {
        return game;
    }

    /**
     * Returns the current state of the canvas.
     */
    public SimpleCanvas getCanvas()
    {
        return sc;
    }

    /**
     * Displays the initial puzzle on sc. 
     * Have a look at puzzle-loop.com for a basic display, or use your imagination. 
     */
    public void displayPuzzle()
    {
        // COMPLETE THIS 4a
    }
    
    /**
     * Makes a horizontal click to the right of Dot r,c.
     * Update game and the display, if the indices are legal; otherwise do nothing.
     */
    public void horizontalClick(int r, int c)
    {
        // COMPLETE THIS 5a
    }
    
    /**
     * Makes a vertical click below Dot r,c. 
     * Update game and the display, if the indices are legal; otherwise do nothing. 
     */
    public void verticalClick(int r, int c)
    {
        // COMPLETE THIS 5b
    }
    
    /**
     * Actions for a mouse press.
     */
    public void mousePressed(MouseEvent e) 
    {
        // COMPLETE THIS 6
    }
    public void mouseClicked(MouseEvent e) {}
    public void mouseReleased(MouseEvent e) {}
    public void mouseEntered(MouseEvent e) {}
    public void mouseExited(MouseEvent e) {}
}
