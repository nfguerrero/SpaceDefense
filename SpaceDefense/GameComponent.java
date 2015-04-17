import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JComponent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.Timer;
import java.awt.Image;

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
    
    private Ship ship;
    
    private boolean left = false;
    private boolean right = false;
    
    private boolean pause = false;
    private PauseMenu menu;
    
    private Timer timer = new Timer(2, this);
    
    private Image bufferImage;
    private Graphics bufferGraphics;
    
    public GameComponent()
    {
        this.ship = new Ship(0 ,0);
        this.menu = new PauseMenu(0, 0);
        this.setFocusable(true);
    }
    
    public void setVariables()
    {
        this.width = this.getWidth();
        this.height = this.getHeight();
        
        this.ship = new Ship(this.width/2, this.height-34);
        this.menu = new PauseMenu(this.getWidth(), 0);
    }
    
    public void paintComponent(Graphics g)
    {
        this.ship.draw(g);
        this.menu.draw(g);
        timer.start();
    }
    
    public void update(Graphics g)
    {
        if (bufferImage == null)
        {
            bufferImage = createImage(this.getSize().width, this.getSize().height);
            bufferGraphics = bufferImage.getGraphics();
        }
        
        paint(bufferGraphics);
        
        g.drawImage(bufferImage, 0, 0, this);
    }
    
    public void actionPerformed(ActionEvent event)
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
        repaint();
    }
    
    public void pause()
    {
        this.menu.setX(0);
    }
    
    public void unpause()
    {
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
    
    
}
