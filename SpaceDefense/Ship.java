import java.awt.Graphics;
import javax.swing.ImageIcon;
import java.awt.Image;

/**
 * Write a description of class Ship here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Ship
{
    private int x; //top left x coor
    private int y; //top left y coor
    private int move = 10;
    
    public Ship(int x, int y)
    {
        this.x = x;
        this.y = y;
    }
    
    public void draw(Graphics g)
    {
        ImageIcon icon = new ImageIcon("ship.png");
        Image image = icon.getImage();
        
        g.drawImage(image, x, y, null);
    }
    
    public void moveLeft()
    {
        this.x -= this.move;
    }
    
    public void moveRight()
    {
        this.x += this.move;
    }
}
