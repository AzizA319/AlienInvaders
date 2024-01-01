import java.util.ArrayList;
import java.util.Random;
public class SpaceInvaders {
    /*
     *
     * SpaceInvaders
     *
     * PURPOSE: This is the object that controls the game state
     */


    ArrayList<Sprite> items = new ArrayList<Sprite>();

    private int alienDirection; // stores the direction the aliens are moving
    private static final int LEFT = 1; // direction the aliens are moving
    private static final int RIGHT = 2; // direction the aliens are moving

    private static final int XMAX = 400; // Maximimum x coord
    private static final int XMIN = 0; // Minimum x coord


    public SpaceInvaders(int height, int width) {
        /*
         * This method initializes the aliens and PlayerShip the game begins with.
         * The height and width of the screen are inputted by the user
         */

        Sprite alien1 = new Alien(70, 30);
        Sprite alien2 = new Alien(70, 60);
        Sprite alien3 = new Alien(100, 30);
        Sprite alien4 = new Alien(100, 60);
        Sprite alien5 = new Alien(130, 30);
        Sprite alien6 = new Alien(130, 60);

        Sprite ship = new PlayerShip(200, 400); //starting point of the player ship

        items.add(alien1);
        items.add(alien2);
        items.add(alien3);
        items.add(alien4);
        items.add(alien5);
        items.add(alien6);

        items.add(ship);

        alienDirection = SpaceInvaders.LEFT;

    }


    public void update() {
        /*
         * This method updates the placement of all your items 30 times per second
         * it moves bullets and checks if they have come in contact with any alien or playerShip
         * it also moves all the aliens on the screen accordingly
         */

        ////////////////this Block of code moves the  alienBullets and check if they are still in the boundary

        // this block gets the number of aliens on the screen
        int alienCount = 0;
        for (int index = items.size() - 1; index >= 0; index--) {
            if (items.get(index) instanceof Alien) {
                alienCount++;
            }
        }

        Random rand = new Random();
        int n = rand.nextInt(1000 );

        // if the random number is less than the number of aliens on the screen it creates an AlienBullet for the alien at that position
        if (n < alienCount) {

            if (items.get(n) instanceof Alien) {

                int bulletXCoord = items.get(n).getX();
                int bulletyCoord = items.get(n).getY();

                Sprite shoot = new AlienBullet(bulletXCoord, bulletyCoord);
                items.add(shoot); //adds the bullet to the list of items to be drawn
            }
        }

        // moves all the alienBullets still on the screen
        for (int index = items.size() - 1; index >= 0; index--) {
            if (items.get(index) instanceof AlienBullet) {
                (items.get(index)).incY(2);
            }
        }

        // this block checks to see if the bullet is still in the screen boundary and removes if not
        for (int index = items.size() - 1; index >= 0; index--) {
            if (items.get(index) instanceof AlienBullet) {

                if (items.get(index).getY() < 0 || items.get(index).getY() > 400) {
                    items.remove(items.get(index));
                }

            }
        }

        // this block to check if the AlienBullet has come in contact with the PlayerShip //


        // gets a list of all the alien items on the screen
        ArrayList<Sprite> alBulletList = new ArrayList<>();  //stores the current alien items on the screen
        for (int index = items.size() - 1; index >= 0; index--) {
            if (items.get(index) instanceof AlienBullet) {
                alBulletList.add((items.get(index)));
            }
        }

        // gets the list of all the ship items on screeen
        ArrayList<Sprite> shipList = new ArrayList<>();  //stores the current alien items on the screen
        for (int index = items.size() - 1; index >= 0; index--) {
            if (items.get(index) instanceof PlayerShip) {
                shipList.add((items.get(index)));
            }
        }


        for (int index = alBulletList.size() - 1; index >= 0; index--) {
            for (int i = shipList.size() - 1; i >= 0; i--) {
                // double for loop that goes through for each bullet and compares it to each alien on the screen ( by calculating the distance between them)

                int distance = getDistance((alBulletList.get(index)).getX(), (alBulletList.get(index).getY()), shipList.get(i).getX(), shipList.get(i).getY());

                if (distance < 20) {

                    items.remove(alBulletList.get(index));
                    items.remove(shipList.get(i));
                }
            }
        }

        ////////////////////////////////// END OF AlienBullet movement BLOCK


        // move every bullet on screen by 2
        for (int index = items.size() - 1; index >= 0; index--) {
            if (items.get(index) instanceof Bullet) {

                (items.get(index)).decY(2);
            }
        }


        // this block checks to see if the bullet is still in the screen boundary and removes if not
        for (int index = items.size() - 1; index >= 0; index--) {
            if (items.get(index) instanceof Bullet) {

                if (items.get(index).getY() < 0 || items.get(index).getY() > 400) {
                    items.remove(items.get(index));
                }
            }
        }


        // gets a list of all the alien items on the screen
        ArrayList<Sprite> alienItems = new ArrayList<>();  //stores the current alien items on the screen
        for (int index = items.size() - 1; index >= 0; index--) {
            if (items.get(index) instanceof Alien) {

                alienItems.add((items.get(index)));
            }
        }

        // gets the list of all the bullets items on the screen
        ArrayList<Sprite> bulletItems = new ArrayList<>(); // stores the current bulletItems on the screen
        for (int index = items.size() - 1; index >= 0; index--) {
            if (items.get(index) instanceof Bullet) {

                bulletItems.add((items.get(index)));
            }
        }


        for (int index = bulletItems.size() - 1; index >= 0; index--) {
            for (int i = alienItems.size() - 1; i >= 0; i--) {
                // double for loop that goes through for each bullet and compares it to each alien on the screen
                int distance = getDistance((bulletItems.get(index)).getX(), (bulletItems.get(index).getY()), alienItems.get(i).getX(), alienItems.get(i).getY());

                if (distance < 20) {

                    items.remove(bulletItems.get(index));
                    items.remove(alienItems.get(i));
                }
            }

        }

        moveAliens(); // this method call moves all the aliens on the screen
    }


    public ArrayList<Sprite> getItems() {
        /*
         * This method returns the list of all the items  that are to be drawn onto the screen
         * returns an ArrayList of type Sprite of all the items that are to be drawn on the screen
         */

        return items;
    }

    public int status() {
        /*
         * This method returns the status of the game to tell the display class what action to take
         * Return the appropriate constant to tell the Display class what action to take.
         * returns the status of the game.
         */

        int status;
        int numAliens = 0; // keeps count of the aliens still on the screen
        int numplayerShip = 0; // keeps count of the playerShip still on the screen

        for (int i = items.size() - 1; i >= 0; i--) {
            if (items.get(i) instanceof Alien) {
                numAliens++;

            } else if (items.get(i) instanceof PlayerShip) {

                numplayerShip++;
            }
        }

        ///////// checks and decides of a player has won or lost or continues the game
        if (numplayerShip == 0) {

            status = Display.LOSE;
        } else if (numAliens == 0) {

            status = Display.WIN;
        } else {

            status = Display.CONTINUE;
        }


        for( int i = items.size() - 1; i >= 0; i--){
            if (items.get(i) instanceof Alien){

                if( items.get(i).getY() >= 400){

                    status = Display.LOSE;
                }

            }
        }


        return status;
    }


    public void move(int direction) {
        /*
         * This method is called when the player presses the left or right arrows and moves the PlayerShip accordingly
         * The direction we want the ship moved is inputted by the user (RIGHT OR LEFT)
         */

        if( direction == Display.MOVE_LEFT) { // moves the ship to the left

            for(int i = items.size() - 1; i >= 0; i--){

                if(items.get(i) instanceof PlayerShip){

                    items.get(i).decX(10);
                }
            }

        }else if(direction == Display.MOVE_RIGHT){ // moves the ship to the right

            for(int i = items.size() - 1; i >= 0; i--){

                if(items.get(i) instanceof PlayerShip){

                    items.get(i).incX(10);
                }
            }
        }

    }


    public void shoot() {
        /*
         * This method is called when the player presses the spacebar, checks to see if a bullet can be shot and acts accordingly.
         * checks if it can shoot a new bullet. They player is allowed to have a maximum of 2 bullets. If there are 2 bullets
         */

        int bulletXCoord = 0; //xCoord of the bullet if the condition is reached
        int bulletyCoord = 0 ; //yCoord of the bullet if the condition is reached

        int bulletCount = 0; // contains the number of bullets on the screen
        for (int i = items.size() - 1; i >= 0; i--) {

            if (items.get(i) instanceof Bullet) {
                bulletCount++;
            }
        }


        if (bulletCount > 1) {
            // if there are two bullets do nothing

        } else {

            for(int i = items.size() - 1; i >= 0; i--){
                if(items.get(i) instanceof PlayerShip){

                    bulletXCoord = (items.get(i)).getX();
                    bulletyCoord = (items.get(i)).getY();

                }
            }

            Sprite shoot = new Bullet(bulletXCoord, bulletyCoord);
            items.add(shoot); //adds the bullet to the list of items to br drawn
        }

    }


    public int getDistance(int x1, int y1, int x2, int y2) {
        /*
         * This method calculates the distance between two objects on the screen
         * The X and Y Coordinates of the objects are inputted by the user
         * The distance between two objects is returned as an Integar
         */

        double distance = Math.sqrt((x2 - x1) * (x2 - x1) + (y2 - y1) * (y2 - y1));

        return (int) distance; //returns the distance between the points
    }


    public void moveAliens() {
        /*
         * This method controls the movement of all the aliens on the screen at any given moment
         */

        for (int i = items.size() - 1; i >= 0; i--) {

            if (items.get(i) instanceof Alien) {


                if (alienDirection == SpaceInvaders.LEFT && (items.get(0).getX() != XMIN)) { // if the direction is left and the first element != 0

                    for (int curr = items.size() - 1; curr >= 0; curr--) {
                        if (items.get(curr) instanceof Alien) {

                            items.get(curr).decX(1);
                        }
                    }


                } else if (alienDirection == SpaceInvaders.LEFT && (items.get(0).getX() == XMIN)) {

                    for (int curr = items.size() - 1; curr >= 0; curr--) {
                        if (items.get(curr) instanceof Alien) {

                            items.get(curr).incY(5);
                        }
                    }

                    alienDirection = SpaceInvaders.RIGHT;

                } else if (alienDirection == SpaceInvaders.RIGHT && (items.get(0).getX() >= XMIN)) {

                    for (int curr = items.size() - 1; curr >= 0; curr--) {
                        if (items.get(curr) instanceof Alien) {

                            items.get(curr).incX(1);
                        }
                    }

                }

                //////////seperate if block to to control the bullets movement to the right and reverse the movement

                if( alienDirection == SpaceInvaders.RIGHT && items.get(0).getX() == XMAX){

                    for (int curr = items.size() - 1; curr >= 0; curr--) {
                        if (items.get(curr) instanceof Alien) {

                            items.get(curr).incY(5);
                            items.get(curr).decX(1);
                        }
                    }

                    alienDirection = SpaceInvaders.LEFT;
                }

            }

        }


    }//end of moveAliens method

}




