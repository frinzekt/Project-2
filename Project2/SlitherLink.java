

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

    private int row_size;
    private int col_size;

    private int radius;
    private int border; //UP AND DOWN, LEFT AND RIGHT

    private int rect_width;
    private int rect_height ;
    private int square_size ;

    private void InitializeGraphicValues(){

        row_size = game.getRowSize();
        col_size = game.getColSize();
        
        border = screen_height * 5 /100; //UP AND DOWN, LEFT AND RIGHT

        rect_width = (screen_width - 2 * border) / col_size;
        rect_height = (screen_height - 2 * border) / row_size;
        
        square_size = 0;
        //Equating square_size to the minimum dimension to fit screen
        if (rect_width <= rect_height){ 
            square_size = rect_width;
        }
        else{
            square_size = rect_height;
        }
        
        radius = square_size * 5 /100;
    }

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
        sc.addMouseListener(this); 
        InitializeGraphicValues();
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
        
        
        
        displayInitial();

    }

    public void displayInitial(){
        //Display Blank Canvas
        sc.drawRectangle(0,0,screen_width,screen_height,Color.white);
        
        int x = border; //holds current x,y coordinate drawer
        int y = border;

        int x_next = 0;
        int y_next = 0;

        int xave = 0; //holds average of initial x and final x coordinate for middle/center
        int yave = 0;

        int[][] puzzle = game.getPuzzle();
        //Dots: the board will have n+1 dots across and down.
        for (int i=0; i<=row_size; i++){
            y = i * square_size + border;
            y_next = y + square_size;
            yave = (y+y_next)/2;

            for (int j=0; j<=col_size; j++){
                x = j * square_size + border;
                x_next = x + square_size;
                xave = (x+x_next)/2;

                sc.drawCircle(x,y,radius,Color.black);
                
                //drawRectangle(x1,y1,x2,y2,color);
                if(i<row_size){ //#vertical_line depends on row_size
                    if (game.getVertical()[i][j] == true){ //WILL DRAW ONLY IF THE LINE IS ACTIVATED
                        sc.drawRectangle(x-radius,y-radius,x+radius,y_next+radius, Color.black);// Draw Vertical Line
                    }
                }
                
                if(j<col_size){ //#horizontal_line depends on col_size
                    if (game.getHorizontal()[i][j] == true){ //WILL DRAW ONLY IF THE LINE IS ACTIVATED
                        sc.drawRectangle(x-radius,y-radius,x_next+radius,y+radius, Color.black); // Draw Horizontal Line
                    }
                }
                
                
                
                if ((i<row_size) && (j<col_size)){ // Puzzle is 1 less than the dot numbers
                    if (puzzle[i][j]!=-1){ // -1 is a free puzzle, does not display any number
                        sc.drawString(puzzle[i][j],xave,yave,Color.black);

                    }
                }

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
        
        int square_center = square_size/2;
        
        //GETS x -> col equivalent to a box/square
        //     y -> row
        int square_x = e.getX()/square_size ;
        int square_y = e.getY()/square_size;
        
        //Conversion of an xy coordinate to single square AKA Origin Square
        //Origin Square - the square (0,0)
        
        int origin_x = e.getX() - square_size * square_x;
        int origin_y = e.getY() - square_size * square_y;
        
        //Figure out where click refers to -> vertical or horizontal click
        //by looking at the minimum distance to the bound of the origin
        // 0 <= x <= square_size
        // Minimum distance to bound (2 calculation)= MAX distance from center (1 calculation)
        // MAX distance using absolute value
        
        //if the distance of x from center is more than y
        //then its a conversion in x -> col
        //else conversion in y -> row
        
        if (Math.abs(origin_x-square_center) > Math.abs(origin_y - square_center)){
            if(origin_x <= square_center){ // if x is closer to the first col
                verticalClick(square_y, square_x);
            }
            else{ // if x is closer to the second col
                verticalClick(square_y, square_x + 1);  //+1 refers to the second col  
            }
        }
        else{
            if(origin_y <= square_center){ // if x is closer to the first col
                horizontalClick(square_y, square_x);
            }
            else{ // if x is closer to the second col
                horizontalClick(square_y + 1, square_x);  //+1 refers to the second col  
            }
        }

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
