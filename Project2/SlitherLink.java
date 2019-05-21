
/**
 * SlitherLink does the user interaction for a square Slither Link puzzle.
 * 
 * @author Frinze Lapuz & Ethan Pui
 * @version 1.0
 */

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class SlitherLink extends JFrame implements MouseListener//, ActionListener
{    
    JMenuBar menuBar;
    JMenuItem CheckSolution, Clear, LoadGame;

    private Puzzle game;     // internal representation of the game
    private SimpleCanvas sc; // the display window

    //Crosses
    private boolean[][] horizontal_cross; 
    private boolean[][] vertical_cross;  

    //Screen dimension initialization
    private final int screen_width =1280;
    private final int screen_height = 720;
    private int font_size;
    private Font screen_font;
    private final Color screen_color = new Color(84, 84, 84); //grey

    private final Color LineColor = new Color(0, 172, 226); //blue
    private final Color DotColor = new Color(251, 251, 251); //white
    private final Color FontColor = new Color(135, 195, 47); //green

    private int row_size;
    private int col_size;

    private int radius;
    private int border; //UP AND DOWN, LEFT AND RIGHT
    private int btn_size;
    private final int btn_y_space = 50;
    
    private int rect_width;
    private int rect_height ;
    private int square_size ;

    private void InitializeGraphicValues(){

        row_size = game.getRowSize();
        col_size = game.getColSize();

        border = screen_height * 5 /100; //UP AND DOWN, LEFT AND RIGHT
        btn_size = 200;

        rect_width = (screen_width - btn_size - 2 * border) / col_size;
        rect_height = (screen_height - 2 * border) / row_size;

        square_size = 0;
        //Equating square_size to the minimum dimension to fit screen
        if (rect_width <= rect_height){ 
            square_size = rect_width;
        }
        else{
            square_size = rect_height;
        }

        radius = square_size * 5 /100; //5% of square size

        font_size = square_size * 20/100;
        screen_font =  new Font("Verdana", Font.BOLD, font_size);
        sc.setFont(screen_font);

        menuBar = new JMenuBar();

        //Build menu itmes
        CheckSolution = new JMenuItem("Check Solution");
        //CheckSolution.addMenuListener(new thisMenuListener());
        //Clear;
        //LoadGame;

        menuBar.add(CheckSolution);
        this.setJMenuBar(menuBar);

    }

    /**
     * Creates a display for playing the puzzle p.
     */
    public SlitherLink(Puzzle p)
    {
        // COMPLETE THIS 4b
        this.game = p;

        sc = new SimpleCanvas("Slither Link - Ethan & Frinze", screen_width, screen_height, screen_color);
        sc.drawRectangle(0,0,screen_width,screen_height,screen_color); //Blank
        sc.addMouseListener(this); 
        // sc.addMenuListener(this);

        //Initialize Cross Array
        horizontal_cross = new boolean[game.getRowSize()+1][game.getColSize()];
        vertical_cross = new boolean[game.getRowSize()][game.getColSize()+1];

        //Initialize Graphic Values
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

        displaySLelements();
        displaybtn(0,"Clear"); //Button Clear
        displaybtn(1,"Check");
    }

    public void displaySLelements(){
        //Display Blank Canvas
        sc.drawRectangle(0,0,screen_width,screen_height,screen_color);

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

            for (int j = 0; j<=col_size; j++){
                x = j * square_size + border;
                x_next = x + square_size;
                xave = (x+x_next)/2;

                //LINE AND CROSS DRAWING -> drawRectangle(x1,y1,x2,y2,color);
                if(i<row_size){ //#vertical_line depends on row_size
                    if (game.getVertical()[i][j] == true){ //WILL DRAW ONLY IF THE LINE IS ACTIVATED
                        sc.drawRectangle(x-(radius/2),y,x+(radius/2),y_next, LineColor);// Draw Vertical Line

                    }

                    if(vertical_cross[i][j]==true){
                        sc.drawString("x",x- font_size/4 ,yave + font_size/4,FontColor);
                    }
                }

                if(j<col_size){ //#horizontal_line depends on col_size
                    if (game.getHorizontal()[i][j] == true){ //WILL DRAW ONLY IF THE LINE IS ACTIVATED
                        sc.drawRectangle(x,y-(radius/2),x_next,y+(radius/2), LineColor); // Draw Horizontal Line
                    }

                    if(horizontal_cross[i][j]==true){
                        sc.drawString("x",xave - font_size/4,y + font_size/4,FontColor);
                    }
                }

                if ((i<row_size) && (j<col_size)){ // Puzzle is 1 less than the dot numbers
                    if (puzzle[i][j]!=-1){ // -1 is a free puzzle, does not display any number
                        sc.drawString(puzzle[i][j],xave - font_size/4,yave + font_size/4,FontColor);
                    }
                }

                //DOT DRAWING
                sc.drawDisc(x,y,radius,DotColor);
                sc.drawCircle(x,y,radius,LineColor);

            }

        }

    }

    public void displaybtn(int order, String text){
        
        int btn_x_start = screen_width - btn_size - border;
        int btn_x_end = btn_x_start + btn_size;
        int xave = (btn_x_start + btn_x_end ) /2;

        int btn_y_start =border + btn_y_space*(order+1) + btn_size*(order);
        int btn_y_end =btn_y_start+ btn_size/2;
        int yave = (btn_y_start + btn_y_end ) /2;

        sc.drawRectangle(btn_x_start,btn_y_start,btn_x_end,btn_y_end, LineColor);
        sc.drawString(text,xave - font_size,yave + font_size/4,FontColor);

    }

    public void ClearScreen(){
        game.clear();   //CLEAR INTERNAL VIEW
        displayPuzzle();
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
                horizontal_cross[r][c] = false;//REMOVE CROSS
                displayPuzzle();
            }
        }
    }

    public void horizontalRightClick(int r, int c){
        if( (0<=r) && (r<horizontal_cross.length) ){ //ROW CHECK
            if( (0<=c) && (c<horizontal_cross[0].length) ){ //COL CHECK
                if(!game.getHorizontal()[r][c]){//CAN ONLY PUT CROSS IF NO LINE EXIST
                    horizontal_cross[r][c] = horizontal_cross[r][c] != true;
                    displayPuzzle();
                }
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
                vertical_cross[r][c] = false;//REMOVE CROSS
                displayPuzzle();
            }
        }
    }

    public void verticalRightClick(int r, int c)
    {
        // COMPLETE THIS 5b
        //Vertical lines: the board can have n+1 vertical lines across, but only n rows of these down.
        if( (0<=r) && (r<vertical_cross.length) ){ //ROW CHECK
            if( (0<=c) && (c<vertical_cross[0].length) ){ //COL CHECK
                if(!game.getVertical()[r][c]){
                    vertical_cross[r][c] = vertical_cross[r][c] != true;
                    displayPuzzle();
                }
            }

        }
    }

    /**
     * Actions for a mouse press.
     */
    public void mousePressed(MouseEvent e) 
    {
        // COMPLETE THIS 6
        if (e.getX()<(screen_width-border-btn_size)){ //GRID CLICKS
            if(e.getButton() == MouseEvent.BUTTON1) {
                mouseLeftPressed(e);
            }

            if(e.getButton() == MouseEvent.BUTTON3) {
                mouseRightPressed(e);
            }

        }
        else{ //BTN Clicks
            int y = e.getY() - border - btn_y_space;
            int order = y / (btn_size + btn_y_space);
            
            int btn_y_start =border + btn_y_space*(order+1) + btn_size*(order);
            int btn_y_end =btn_y_start+ btn_size/2;
        
            if(!(AnalyzeSolution.IsBetween_Inclusive(btn_y_start,btn_y_end,e.getY()))){ //Click outside btn
                order=-1;
            }
            
            if(order == 0){ //Clear btn
                ClearScreen();
            }
            else if (order == 1){ //Check Solutions
                String return_string = AnalyzeSolution.finished(game);
                infoBox(return_string, "Check Solution");
            }
            
        }
    }

    public void mouseLeftPressed(MouseEvent e){
        if(AnalyzeSolution.finished(game) != "Finished"){
            int square_center = square_size/2;

            //ACOUNTS FOR THE BORDER
            int x = e.getX()-border;
            int y = e.getY()-border;

            //GETS x -> col equivalent to a box/square
            //     y -> row
            int square_x = x/square_size;
            int square_y = y/square_size;

            //Conversion of an xy coordinate to single square AKA Origin Square
            //Origin Square - the square (0,0)

            int origin_x = x - square_size * square_x;
            int origin_y = y - square_size * square_y;

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

            //MessageBox
            if(AnalyzeSolution.finished(game) == "Finished"){
                infoBox("You have finished this puzzle", "Congratulations!");
            }
        }

    }

    public void mouseRightPressed(MouseEvent e){
        if(AnalyzeSolution.finished(game) != "Finished"){
            int square_center = square_size/2;

            //ACOUNTS FOR THE BORDER
            int x = e.getX()-border;
            int y = e.getY()-border;

            //GETS x -> col equivalent to a box/square
            //     y -> row
            int square_x = x/square_size;
            int square_y = y/square_size;

            //Conversion of an xy coordinate to single square AKA Origin Square
            //Origin Square - the square (0,0)

            int origin_x = x - square_size * square_x;
            int origin_y = y - square_size * square_y;

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
                    verticalRightClick(square_y, square_x);
                }
                else{ // if x is closer to the second col
                    verticalRightClick(square_y, square_x + 1);  //+1 refers to the second col  
                }
            }
            else{
                if(origin_y <= square_center){ // if x is closer to the first col
                    horizontalRightClick(square_y, square_x);
                }
                else{ // if x is closer to the second col
                    horizontalRightClick(square_y + 1, square_x);  //+1 refers to the second col  
                }
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

    public static void infoBox(String infoMessage, String titleBar)
    {
        JOptionPane.showMessageDialog(null, infoMessage,titleBar, JOptionPane.INFORMATION_MESSAGE);
    }
}
