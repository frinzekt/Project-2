

/**
 * SlitherLink does the user interaction for a square Slither Link puzzle.
 * 
 * @author Frinze Lapuz & Ethan Pui
 * @version 1.0
 */
import java.awt.*;
import java.awt.event.*;

public class SlitherLink implements MouseListener
{    
    private Puzzle game;     // internal representation of the game
    private SimpleCanvas sc; // the display window

    private int screen_width;
    private int screen_height;

    /**
     * Creates a display for playing the puzzle p.
     */
    public SlitherLink(Puzzle p)
    {
        // COMPLETE THIS 4b
        this.game = p;

        screen_width = 1280;
        screen_height = 720;

        sc = new SimpleCanvas("Slither Link - Ethan & Frinze", screen_width, screen_height, Color.white);
        displayPuzzle();

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

        int row_size = game.getRowSize();
        int col_size = game.getColSize();

        //Dimension in PX
        int border = 5; //UP AND DOWN, LEFT AND RIGHT
        int rect_width = (screen_width - 2 * border) / col_size;
        int rect_height = (screen_height - 2 * border) / row_size;
        int square_size = 0;

        //Equating square_size to the minimum dimension to fit screen
        if (rect_width <= rect_height){ 
            square_size = rect_width;
        }
        else{
            square_size = rect_height;
        }
        
        displayDots(square_size);
        
    }

    public void displayDots(int square_size){
        
        int row_size = game.getRowSize();
        int col_size = game.getColSize();
        int radius = 5;
        int border = 5;
        
        int x = border; //holds current x,y coordinate drawer
        int y = border;
        
        //Dots: the board will have n+1 dots across and down.
        for (int i=0; i<=row_size; i++){
            y = i * square_size + border;
            for (int j=0; j<=col_size; j++){
                x = j * square_size + border;
                sc.drawCircle(x,y,radius,Color.black);

            }
            
        }

    }
    /**
     * Makes a horizontal click to the right of Dot r,c.
     * Update game and the display, if the indices are legal; otherwise do nothing.
     */
    public void horizontalClick(int r, int c)
    {
        // COMPLETE THIS 5a
        //Horizontal lines: the board can have n horizontal lines across, but n+1 rows of these down.

        if( (0<=r) && (r<game.getRowSize()+1) ){ //ROW CHECK
            if( (0<=c) && (c<game.getColSize()) ){ //COL CHECK
                game.horizontalClick(r,c);
                displayPuzzle();
            }
        }
    }

    /**
     * Makes a vertical click below Dot r,c. 
     * Update game and the display, if the indices are legal; otherwise do nothing. 
     */
    public void verticalClick(int r, int c)
    {
        // COMPLETE THIS 5b
        //Vertical lines: the board can have n+1 vertical lines across, but only n rows of these down.
            
        if( (0<=r) && (r<game.getRowSize()) ){ //ROW CHECK
            if( (0<=c) && (c<game.getColSize()+1) ){ //COL CHECK
                game.verticalClick(r,c);
                displayPuzzle();
            }
        }
    }

    /**
     * Actions for a mouse press.
     */
    public void mousePressed(MouseEvent e) 
    {
        // COMPLETE THIS 6
        mousePressed(e);
    }

    public void mouseClicked(MouseEvent e) {
    }

    public void mouseReleased(MouseEvent e) {   
    }

    public void mouseEntered(MouseEvent e) { 
    }

    public void mouseExited(MouseEvent e) {
    }
}
