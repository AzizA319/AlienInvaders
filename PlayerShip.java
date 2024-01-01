import java.awt.*;

public class PlayerShip extends Sprite {
    /*
     *
     * PlayerShip
     *
     *
     * PURPOSE:  controls the PlayerShip that is drawn to the screen
     */

    public PlayerShip(int xCoord1, int yCoord1){

        super(xCoord1, yCoord1);
    }

    public int getX(){
        /*
         * gets the X Coordinate
         */

        return super.getX();
    }

    public int getY(){
        /*
         * gets the Y coordinate
         */

        return super.getY();
    }

    public Color[][] getColorGrid() {
        /*
         * gets a 2D array of Color which can later be used to draw the Sprite on the window
         */

        return Display.SHIP_SHAPE;
    }




    ///////////// increment and decrement methods for ship movement

    public void incY(int y){
        /*
         * increments the value of the yCoordinate by y
         */

        super.incY(y);
    }

    public void decY(int y){
        /*
         * decreases the value of the yCoordinate by y
         */

        super.decY(y);
    }


    public void incX(int x){
        /*
         * increments the value of the xCoord by x
         */

        super.incX(x);
    }


    public void decX(int x){
        /*
         * decreases the value of the xCoord by x
         */

        super.decX(x);
    }



}
