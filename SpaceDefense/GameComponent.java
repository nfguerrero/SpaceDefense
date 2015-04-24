
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JComponent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.Timer;
import java.awt.Image;
import java.util.ArrayList;

/**i
 * TEMP DESCRIPTION:  does everything
 * 
 * @author nfguerrero 
 * @version Ver: 1.0
 */
public class GameComponent extends JComponent implements ActionListener
{
    private int width;
    private int height;
    private int x = 0;
    private int y = 0;
    
    private boolean play = false;
    
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
    
    private int lvl = 1;
    
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
    
    private ArrayList<Button> buttons = new ArrayList<Button>(0);
    
    private Timer timer = new Timer(10, this);
    
    //private Image bufferImage;
    //private Graphics bufferGraphics;
    
    public GameComponent()
    {
        this.background = new Background(0, 0);
        this.ship = new Ship(380 ,538, "ship1");
        this.level(this.enemies, this.lvl);
        this.setFocusable(true);
    }
    
    public void setVariables()
    {
        this.width = this.getWidth();
        this.height = this.getHeight();
        //this.ship = new Ship(this.width/2-17, this.height-34);
        //this.menu = new PauseMenu(this.getWidth(), 0);
    }
    
    public void paintComponent(Graphics g)
    {
        this.background.draw(g);
        if (this.play || this.pause)
        {
            this.ship.draw(g);
            this.scoreGraphics.draw(g);
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
        if (this.gameOver)
        {
            this.endMenu.draw(g);
            this.scoreGraphics.draw(g);
            this.balanceGraphics.draw(g);
            
            this.buttons.add(this.mainMenuButton);
            this.mainMenuButton.draw(g);            
        }
        if (this.mainMenu)
        {
            this.menu.draw(g);
            this.scoreGraphics.draw(g);
            this.balanceGraphics.draw(g);
            
            this.buttons.add(this.playGameButton);
            this.buttons.add(this.controlsButton);
            this.buttons.add(this.upgradeButton);
            this.playGameButton.draw(g);
            this.controlsButton.draw(g);
            this.upgradeButton.draw(g);
        }
        if (this.controls)
        {
            this.controlsMenu.draw(g);
            
            this.buttons.add(this.backButton);
            this.backButton.draw(g);
        }
        if (this.upgrade)
        {
            this.upgradeMenu.draw(g);
            this.balanceGraphics.draw(g);
            
            this.buttons.add(this.backButton);
            this.backButton.draw(g);
        }
        timer.start();
    }
    
    //public void update(Graphics g)
    //{
    //   if (bufferImage == null)
    //    {
    //        bufferImage = createImage(this.getSize().width, this.getSize().height);
    //        bufferGraphics = bufferImage.getGraphics();
    //    }
    //   
    //    paint(bufferGraphics);
    //    
    //    g.drawImage(bufferImage, 0, 0, this);
    //}
    
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
            if (this.shooting && missleDelay >= 30 && this.play)
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
            }
        }
        for (int i = this.buttons.size()-1; i >= 0; i--)
        {
            this.buttons.remove(i);
        }
        repaint();
    }
    
    private void level(ArrayList<Enemy> enemies, int lvl)
    {
        for (int i = 0; i < lvl*5; i++)
        {
            int x = (int) (Math.random()*736);
            enemies.add(new Enemy(x, 0));
        }
    }
    
    public void pause()
    {
        if (!this.gameOver)
        {
            this.pause = true;
        }
    }
    
    public void unpause()
    {
        this.pause = false;
    }
    
    public boolean getPause()
    {
        return this.pause;
    }
    
    public void setControls(boolean set)
    {
        this.controls = set;
    }
    
    public boolean getControls()
    {
        return this.controls;
    }
    
    public void setUpgrade(boolean set)
    {
        this.upgrade = set;
    }
    
    public boolean getUpgrade()
    {
        return this.upgrade;
    }
    
    public void setPlay(boolean set)
    {
        this.play = set;
    }
    
    public boolean getPlay()
    {
        return this.play;
    }
    
    public ArrayList<Button> getButtons()
    {
        return this.buttons;
    }
    
    public void startMoveLeft()
    {
        this.left = true;
    }
    
    public void stopMoveLeft()
    {
        this.left = false;
    }
   
    public void startMoveRight()
    {
        this.right = true;
    }
    
    public void stopMoveRight()
    {
        this.right = false;
    }
    
    public void startShooting()
    {
        this.shooting = true;
    }
    
    public void stopShooting()
    {
        this.shooting = false;
    }
    
    public void setMenu(boolean menu)
    {
        this.mainMenu = menu;
    }
    
    public boolean getMenu()
    {
        return this.mainMenu;
    }
    
    public boolean getGameOver()
    {
        return this.gameOver;
    }
    
    public void resetGameOver()
    {
        this.gameOver = false;
    }
    
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
