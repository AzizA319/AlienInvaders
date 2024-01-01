import java.awt.*;
public abstract class Sprite  {
    /*
     *
     * Sprite
     *
     * PURPOSE: abstract class of items drawn to the screen
     */

    private int xCoord; // xCoord
    private int yCoord; // yCoord
    private Color[][] colBoard; // The 2D array of Color is a 2D ‘pixel’ representation of an image


    public Sprite(int xCoord, int yCoord){
        /*
         * Intitializes the value of the X and Y coordinates
         */

        this.xCoord = xCoord;
        this.yCoord = yCoord;

    }


    public int getX(){
        /*
         * gets the X Coordinate
         */

        return xCoord;
    }

    public int getY(){
        /*
         * gets the Y coordinate
         */

        return yCoord;
    }

    public Color[][] getColorGrid(){
        /*
         * gets a 2D array of Color which can later be used to draw the Sprite on the window
         */

        return colBoard;
    }


    /////////////////////// increment and decrement methods

    public void incY(int y){
        /*
         * increments the value of the yCoordinate by y
         */

        yCoord = getY() + y;
    }


    public void decY(int y){
        /*
         * decreases the value of the yCoordinate by y
         */

        yCoord = getY() - y;
    }


    public void incX(int x){
        /*
         * increments the value of the xCoord by x
         */

        xCoord = getX() + x;
    }


    public void decX(int x){
        /*
         * decreases the value of the xCoord by x
         */

        xCoord = getX() - x;
    }




}//end of Sprite Class
