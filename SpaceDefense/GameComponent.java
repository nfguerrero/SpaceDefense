import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JComponent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.Timer;
import java.awt.Image;
import java.util.ArrayList;

/**
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
    
    private boolean mainMenu = true;
    private MainMenu menu = new MainMenu(0, 0);
    
    private boolean gameOver = false;
    private GameOver endMenu = new GameOver(0, 0);
    
    private int lvl = 1;
    
    private Background background;
    
    private Ship ship;
    
    private ArrayList<Enemy> enemies = new ArrayList<Enemy>();
    
    
    private boolean left = false;
    private boolean right = false;
    
    private boolean shooting = false;
    private ArrayList<Missle> missles = new ArrayList<Missle>();
    int missleDelay = 0;
    
    private boolean pause = false;
    private PauseMenu pauseMenu;
    private MainMenuButton mainMenuButton;
    private boolean mainMenuShadow = false;
    private PlayGameButton playGameButton;
    private boolean playGameShadow = false;
    
    private Timer timer = new Timer(10, this);
    
    //private Image bufferImage;
    //private Graphics bufferGraphics;
    
    public GameComponent()
    {
        this.background = new Background(0, 0);
        this.ship = new Ship(380 ,538);
        this.level(this.enemies, this.lvl);
        this.pauseMenu = new PauseMenu(0, 0);
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
        if (!this.gameOver && !this.mainMenu)
        {
            this.ship.draw(g);
        }
        for (int i = 0; i < this.enemies.size(); i++)
        {
            this.enemies.get(i).draw(g);
        }
        for (int i = 0; i < this.missles.size(); i++)
        {
            this.missles.get(i).draw(g);
        }
        if (this.pause && !this.gameOver && !this.mainMenu)
        {
            this.pauseMenu.draw(g);
            
            this.mainMenuButton = new MainMenuButton(322, 261);
            this.mainMenuButton.setShadow(this.mainMenuShadow);
            this.mainMenuButton.draw(g);
        }
        if (this.gameOver)
        {
            this.endMenu.draw(g);
            
            this.mainMenuButton = new MainMenuButton(322, 261);
            this.mainMenuButton.setShadow(this.mainMenuShadow);
            this.mainMenuButton.draw(g);
        }
        if (this.mainMenu)
        {
            this.menu.draw(g);
            
            this.playGameButton = new PlayGameButton(322, 261);
            this.playGameButton.setShadow(this.playGameShadow);
            this.playGameButton.draw(g);
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
            if (this.left && !this.gameOver && !this.mainMenu)
            {
                if (ship.getX() <= 0)
                {
                    ship.setX(this.width-34);
                }
                ship.moveLeft();
            }
            if (this.right && !this.gameOver && !this.mainMenu)
            {
                if (ship.getX() + 34 >= this.width)
                {
                    ship.setX(0);
                }
                ship.moveRight();
            }            
            if (this.shooting && missleDelay >= 30 && !this.gameOver && !this.mainMenu)
            {
                missles.add(new Missle(ship.getX()+4, ship.getY()-12));
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
                
                if (!this.mainMenu)
                {
                    if (enemy.getX() >= this.ship.getX() && enemy.getX() <= this.ship.getX() + 34 ||
                        enemy.getX() + 58 >= this.ship.getX() && enemy.getX() + 58 <= this.ship.getX() + 34)
                    {
                        if (enemy.getY() >= this.ship.getY() && enemy.getY() <= this.ship.getY() + 34 ||
                            enemy.getY() + 29 >= this.ship.getY() && enemy.getY() + 29 <= this.ship.getY() + 34)
                        {
                            this.gameOver = true;
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
    
    public void setShadow(boolean shadow)
    {
        this.mainMenuShadow = shadow;
        this.playGameShadow = shadow;
    }
    
    public boolean getShadow()
    {
        return this.mainMenuShadow;
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
    
    public void play(boolean menu)
    {
        this.mainMenu = !menu;
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
        this.ship.setX(380);
        this.ship.setY(538);
    }
}
