import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JComponent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.Timer;

/**
 * TEMP DESCRIPTION:  does everything
 * 
 * @author nfguerrero 
 * @version Ver: 1.0
 */
public class GameComponent extends JComponent implements ActionListener
{
    //frame width:  784
    //frame height: 562
    //ship width: 34
    //ship height: 34
    //ship reset coors: (x, y) = (375, 528)
    private int x = 0;
    private int y = 0;
    
    private Ship ship;
    
    private boolean left = false;
    private boolean right = false;
    
    private boolean pause = false;
    
    private Timer timer = new Timer(17, this);
    
    public GameComponent()
    {
        ship = new Ship(375, 528);
        this.setFocusable(true);
    }
    
    public void paintComponent(Graphics g)
    {
        this.ship.draw(g);
        
        timer.start();
        
        requestFocusInWindow();
    }
    
    public void actionPerformed(ActionEvent event)
    {
        if (this.left)
        {
            ship.moveLeft();
        }
        if (this.right)
        {
            ship.moveRight();
        }
        repaint();
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
