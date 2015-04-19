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
    //ship width: 34
    //ship height: 34
    private int width;
    private int height;
    private int x = 0;
    private int y = 0;
    
    private Background background;
    
    private Ship ship;
    
    private ArrayList<Enemy> enemies = new ArrayList<Enemy>();
    
    
    private boolean left = false;
    private boolean right = false;
    
    private boolean shooting = false;
    private ArrayList<Missle> missles = new ArrayList<Missle>();
    int missleDelay = 0;
    
    private boolean pause = false;
    private PauseMenu menu;
    
    private Timer timer = new Timer(10, this);
    
    private Image bufferImage;
    private Graphics bufferGraphics;
    
    public GameComponent()
    {
        this.background = new Background(0, 0);
        this.ship = new Ship(380 ,538);
        this.enemies.add(new Enemy(368, 0));
        this.menu = new PauseMenu(794, 0);
        
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
        this.ship.draw(g);
        for (int i = 0; i < this.enemies.size(); i++)
        {
            this.enemies.get(i).draw(g);
        }
        for (int i = 0; i < this.missles.size(); i++)
        {
            this.missles.get(i).draw(g);
        }
        this.menu.draw(g);
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
            if (this.left)
            {
                if (ship.getX() <= 0)
                {
                    ship.setX(this.width-34);
                }
                ship.moveLeft();
            }
            if (this.right)
            {
                if (ship.getX() + 34 >= this.width)
                {
                    ship.setX(0);
                }
                ship.moveRight();
            }            
            if (this.shooting && missleDelay >= 30)
            {
                missles.add(new Missle(ship.getX()+4, ship.getY()-12));
                missleDelay = 0;
            }
            
            for (int i = 0; i < this.missles.size(); i++)
            {
                Missle missle = this.missles.get(i);                 
                missle.move();
                
                for (int j = 0; j < this.enemies.size(); j++)
                {
                    Enemy enemy = this.enemies.get(j);
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
                    
//                     if (missle.getX() >= enemy.getX() && missle.getX() <= enemy.getX() + 58 ||
//                         missle.getX() + 26 >= enemy.getX() && missle.getX() + 26 <= enemy.getX() + 58)
//                     {
//                         if (missle.getY() >= enemy.getY() && missle.getY() <= enemy.getY() + 29 ||
//                             missle.getY() + 12 >= enemy.getY() && missle.getY() + 12 <= enemy.getY() + 29)
//                         {
//                             this.enemies.remove(i);
//                             this.missles.remove(i);
//                         }
//                     }
                }
            }
            missleDelay++;
            
        }
        repaint();
    }
    
    public void pause()
    {
        this.pause = true;
        this.menu.setX(0);
    }
    
    public void unpause()
    {
        this.pause = false;
        this.menu.setX(this.width);
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
}
