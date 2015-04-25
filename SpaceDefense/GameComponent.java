
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JComponent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.Timer;
import java.awt.Image;
import java.util.ArrayList;

/**
 * Runs the game
 * 
 * @author nfguerrero 
 * @version Ver: 6.0
 */
public class GameComponent extends JComponent implements ActionListener
{
    private int width;
    private int height;
    private int x = 0;
    private int y = 0;
    
    private boolean play = false;
    
    private boolean godMode = false;    
    
    private int balance = 0;
    private Balance balanceGraphics = new Balance(0, 572, 0);
    private int score = 0;
    private Score scoreGraphics = new Score(794, 0, 0);
    
    private boolean mainMenu = true;
    private Menu menu = new Menu(0, 0, "main_menu");
    
    private boolean pause = false;
    private Menu pauseMenu = new Menu(0, 0, "pause_menu");
    
    private boolean gameOver = false;
    private Menu endMenu = new Menu(0, 0, "game_over");
    
    private boolean controls = false;
    private Menu controlsMenu = new Menu(0, 0, "controls");
    
    private boolean upgrade = false;
    private Menu upgradeMenu = new Menu(0, 0, "upgrade");
    
    private boolean secret = false;
    private Menu secretMenu = new Menu(0, 0, "secret");
    
    private boolean correct = false;
    private int correctCounter = 0;
    private Menu correctMenu = new Menu(0, 0, "correct");
    
    private boolean wrong = false;
    private Menu wrongMenu = new Menu(0, 0, "wrong");
    
    private int lvl = 1;
    private int lvlCounter;
    private Level levelGraphics = new Level(0, 0, this.lvl);
    
    private Background background;
    
    private Ship ship;
    
    private ArrayList<Enemy> enemies = new ArrayList<Enemy>();    
    
    private boolean left = false;
    private boolean right = false;
    
    private boolean shooting = false;
    private ArrayList<Missle> missles = new ArrayList<Missle>();
    int missleDelay = 0;       
    
    private Button mainMenuButton = new Button(322, 261, "main_menu");
    private Button playGameButton = new Button(322, 261, "play_game");
    private Button controlsButton = new Button(123, 379, "controls");
    private Button upgradeButton = new Button(520, 379, "upgrade");
    private Button backButton = new Button(0, 0, "back");
    private Button ship1Button = new Button(50, 218, "ship1");
    private Button ship2Button = new Button(236, 218, "ship2");
    private Button ship3Button = new Button(422, 218, "ship3");
    private Button ship15Button = new Button(608, 218, "ship15");
    private Button secretButton = new Button(609, 542, "secret");
    private Button yesButton = new Button(149, 249, "yes");
    private Button noButton = new Button(545, 249, "no");
    
    private ArrayList<Button> buttons = new ArrayList<Button>(0);
    
    private Timer timer = new Timer(15, this);
    
    /**
     * Initializes needed instance variables
     */
    public GameComponent()
    {
        this.background = new Background(0, 0);
        this.ship = new Ship(380 ,538, "ship1");
        this.level(this.enemies, this.lvl);
        this.setFocusable(true);
        this.ship1Button.setSelected(true);
    }
    
    /**
     * Sets the variables that aren't available at startup and need the window to be opened
     */
    public void setVariables()
    {
        this.width = this.getWidth();
        this.height = this.getHeight();
    }
    
    /**
     * Draws all graphics
     * 
     * @param Graphics g    Graphics object
     */
    public void paintComponent(Graphics g)
    {
        this.background.draw(g);
        if (this.play || this.pause)
        {
            this.ship.draw(g);
            this.scoreGraphics.draw(g);
            
            if (this.lvlCounter <= 133)
            {
                this.levelGraphics.draw(g);
            }
        }
        for (int i = 0; i < this.enemies.size(); i++)
        {
            this.enemies.get(i).draw(g);
        }
        for (int i = 0; i < this.missles.size(); i++)
        {
            this.missles.get(i).draw(g);
        }
        if (this.pause && this.play)
        {
            this.pauseMenu.draw(g);
            this.scoreGraphics.draw(g);
            this.balanceGraphics.draw(g);
            
            this.buttons.add(this.mainMenuButton);
            this.mainMenuButton.draw(g);
        }
        else if (this.gameOver)
        {
            this.endMenu.draw(g);
            this.scoreGraphics.draw(g);
            this.balanceGraphics.draw(g);
            
            this.buttons.add(this.mainMenuButton);
            this.mainMenuButton.draw(g);            
        }
        else if (this.mainMenu)
        {
            this.menu.draw(g);
            this.scoreGraphics.draw(g);
            this.balanceGraphics.draw(g);
            
            this.buttons.add(this.secretButton);
            this.buttons.add(this.playGameButton);
            this.buttons.add(this.controlsButton);
            this.buttons.add(this.upgradeButton);
            this.playGameButton.draw(g);
            this.controlsButton.draw(g);
            this.upgradeButton.draw(g);
            this.secretButton.draw(g);
        }
        else if (this.controls)
        {
            this.controlsMenu.draw(g);
            
            this.buttons.add(this.backButton);
            this.backButton.draw(g);
        }
        else if (this.upgrade)
        {
            this.upgradeMenu.draw(g);
            this.balanceGraphics.draw(g);
            
            this.buttons.add(this.ship1Button);
            this.buttons.add(this.ship2Button);
            this.buttons.add(this.ship3Button);
            this.buttons.add(this.backButton);            
            this.ship1Button.draw(g);
            this.ship2Button.draw(g);
            this.ship3Button.draw(g);
            this.backButton.draw(g);
            
            if (this.godMode)
            {
                this.buttons.add(this.ship15Button);
                this.ship15Button.draw(g);
            }
        }
        else if (this.secret)
        {
            this.secretMenu.draw(g);
            
            this.buttons.add(this.yesButton);
            this.buttons.add(this.noButton);
            this.yesButton.draw(g);
            this.noButton.draw(g);
        }
        else if (this.correct)
        {
            this.correctMenu.draw(g);
            if (this.correctCounter >= 140)
            {
                this.setCorrect(false);
                this.setMenu(true);
                this.correctCounter = 0;
            }
            this.correctCounter++;
        }
        else if (this.wrong)
        {
            this.wrongMenu.draw(g);
        }
        timer.start();
    }    
   
    /**
     * Method called every 15 miliseconds to update all aspects of the game
     * 
     * @param ActionEvent event     event that attains information on the action
     */
    public void actionPerformed(ActionEvent event)
    {
        if (!this.pause)
        {
            if (this.left && this.play)
            {
                if (ship.getX() <= 0)
                {
                    ship.setX(this.width-34);
                }
                ship.moveLeft();
            }
            if (this.right && this.play)
            {
                if (ship.getX() + 34 >= this.width)
                {
                    ship.setX(0);
                }
                ship.moveRight();
            }            
            if (this.shooting && missleDelay >= ship.getMissleDelay() && this.play)
            {
                missles.add(new Missle(ship.getX()+4, ship.getY()-12, this.ship.getShip()));
                missleDelay = 0;
            }
            
            for (int i = 0; i < this.enemies.size(); i++)
            {
                Enemy enemy = this.enemies.get(i);
                
                if (enemy.getX() <= 0)
                {
                    enemy.setX(this.width-58);
                } 
                else if (enemy.getX() + 58 >= this.width) 
                {
                    enemy.setX(0);
                }
                if (enemy.getY() + 29 >= this.height)
                {
                    enemy.setY(0);
                }
                enemy.move();
                
                if (this.play)
                {
                    if (enemy.getX() >= this.ship.getX() && enemy.getX() <= this.ship.getX() + 34 ||
                        enemy.getX() + 58 >= this.ship.getX() && enemy.getX() + 58 <= this.ship.getX() + 34)
                    {
                        if (enemy.getY() >= this.ship.getY() && enemy.getY() <= this.ship.getY() + 34 ||
                            enemy.getY() + 29 >= this.ship.getY() && enemy.getY() + 29 <= this.ship.getY() + 34)
                        {
                            this.gameOver = true;
                            this.play = false;
                        }
                    }
                }
            }
            boolean[] misslesLeft = new boolean[this.missles.size()];
            for (int i = 0; i < this.missles.size(); i++)
            {
                Missle missle = this.missles.get(i);                 
                missle.move();
                
                misslesLeft[i] = true;
                for (int j = 0; j < this.enemies.size(); j++)
                {
                    Enemy enemy = this.enemies.get(j);

                    if (missle.getX() >= enemy.getX() && missle.getX() <= enemy.getX() + 58 ||
                        missle.getX() + 26 >= enemy.getX() && missle.getX() + 26 <= enemy.getX() + 58)
                    {
                        if (missle.getY() >= enemy.getY() && missle.getY() <= enemy.getY() + 29 ||
                            missle.getY() + 12 >= enemy.getY() && missle.getY() + 12 <= enemy.getY() + 29)
                        {
                            this.enemies.remove(j);
                            this.score += 1;
                            this.balance += 1;
                            this.scoreGraphics.setScore(this.score);
                            this.balanceGraphics.setScore(this.balance);
                            if (misslesLeft[i])
                            {
                                this.missles.remove(i);
                                misslesLeft[i] = false;
                            }                     
                        }
                    }
                }
            }
            missleDelay++;        
            
            if (this.enemies.size() == 0)
            {
                this.lvl++;
                this.level(this.enemies, this.lvl);
                this.levelGraphics.setLevel(this.lvl);
                this.lvlCounter = 0;
            }
        }
        for (int i = this.buttons.size()-1; i >= 0; i--)
        {
            this.buttons.remove(i);
        }
        this.balanceGraphics.setScore(this.balance);
        this.lvlCounter++;
        repaint();
    }
    
    /**
     * Sets the amount of enemies based on the level
     * 
     * @param ArrayList<Enemy> enemies      ArrayList to put the enemies in
     * @param int lvl                       Level to go into the algorithm to determine the amount of enemies
     */
    private void level(ArrayList<Enemy> enemies, int lvl)
    {
        for (int i = 0; i < lvl*5; i++)
        {
            int x = (int) (Math.random()*736);
            enemies.add(new Enemy(x, 0));
        }
    }
    
    /**
     * Sets the ship instance variable to the parameter
     * 
     * @param String ship       name of image file to set the ship to
     */
    public void setShip(String ship)
    {
        this.ship.setShip(ship);
    }
    
    /**
     * Retrieves the current ship's file name
     * 
     * @return      String of ship's file name
     */
    public String getShip()
    {
        return this.ship.getShip();
    }
    
    /**
     * Pauses the game
     */
    public void pause()
    {
        if (!this.gameOver)
        {
            this.pause = true;
        }
    }
    
    /**
     * Unpauses the game
     */
    public void unpause()
    {
        this.pause = false;
    }
    
    /**
     * @return pause state of the game
     */
    public boolean getPause()
    {
        return this.pause;
    }
    
    /**
     * @param boolean set   set the control menu to come up or go away
     */
    public void setControls(boolean set)
    {
        this.controls = set;
    }
    
    /**
     * @return boolean of state of control menu being up or away
     */
    public boolean getControls()
    {
        return this.controls;
    }
    
    /**
     * @param boolean set   set the upgrade menu to come up or go away
     */
    public void setUpgrade(boolean set)
    {
        this.upgrade = set;
    }
    
    /**
     * @return boolean of state of upgrade menu being up or away
     */
    public boolean getUpgrade()
    {
        return this.upgrade;
    }
    
    /**
     * @param boolean set   set the instance variable that tells whether the game is playing or not
     */
    public void setPlay(boolean set)
    {
        this.play = set;
    }
    
    /**
     * @return boolean of state of the game playing or not
     */
    public boolean getPlay()
    {
        return this.play;
    }
    
    /**
     * @param boolean set   sets the secret menu to be up or away
     */
    public void setSecret(boolean set)
    {
        this.secret = set;
    }
    
    /**
     * @return boolean of state of secret menu being up or away
     */
    public boolean getSecret()
    {
        return this.secret;
    }
    
    /**
     * @param boolean set   sets the god mode on or off
     */
    public void setGodMode(boolean set)
    {
        this.godMode = set;
    }
    
    /**
     * @param boolean set   sets the 'correct' instance variable
     */
    public void setCorrect(boolean set)
    {
        this.correct = set;
    }
    
    /**
     * @param boolean set   sets the 'wrong' instance variable
     */
    public void setWrong(boolean set)
    {
        this.wrong = set;
    }
    
    /**
     * @return ArrayList<Button>    returns list of all buttons on screen
     */
    public ArrayList<Button> getButtons()
    {
        return this.buttons;
    }
    
    /**
     * Moves the ship left
     */
    public void startMoveLeft()
    {
        this.left = true;
    }
    
    /**
     * Stops the ship moving left
     */
    public void stopMoveLeft()
    {
        this.left = false;
    }
   
    /**
     * Moves the ship right
     */
    public void startMoveRight()
    {
        this.right = true;
    }
    
    /**
     * Stops the ship moving right
     */
    public void stopMoveRight()
    {
        this.right = false;
    }
    
    /**
     * Shoots
     */
    public void startShooting()
    {
        this.shooting = true;
    }
    
    /**
     * Stops shooting
     */
    public void stopShooting()
    {
        this.shooting = false;
    }
    
    /**
     * @param boolean menu  sets the state of the main menu
     */
    public void setMenu(boolean menu)
    {
        this.mainMenu = menu;
    }
    
    /**
     * @return boolean of main menu state
     */
    public boolean getMenu()
    {
        return this.mainMenu;
    }
    
    /**
     * @return boolean of game ove state
     */
    public boolean getGameOver()
    {
        return this.gameOver;
    }
    
    /**
     * @param int balance   sets the player's balance
     */
    public void setBalance(int balance)
    {
        this.balance = balance;
    }
    
    /**
     * @return int  of player's current balance
     */
    public int getBalance()
    {
        return this.balance;
    }
    
    /**
     * Resets the gameOver instance variable so game can be played again
     */
    public void resetGameOver()
    {
        this.gameOver = false;
    }
    
    /**
     * Resets neccasary instance variable to play again
     */
    public void reset()
    {
        int size = this.enemies.size();
        for (int i = 0; i < size; i++)
        {
            enemies.remove(0);
        }
        this.lvl = 0;
        this.score = 0;
        this.scoreGraphics.setScore(0);
        this.ship.setX(380);
        this.ship.setY(538);
    }
}
