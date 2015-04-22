import java.awt.Graphics;
import javax.swing.ImageIcon;
import java.awt.Image;

/**
 * Write a description of class Ship here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class GameOver
{
    private int x; //top left x coor
    private int y; //top left y coor
    
    public GameOver(int x, int y)
    {
        this.x = x;
        this.y = y;
    }
    
    public void draw(Graphics g)
    {
        ImageIcon icon = new ImageIcon("Images\\game_over.png");
        Image image = icon.getImage();

        g.drawImage(image, x, y, null);        
    }
    
    public void setX(int x)
    {
        this.x = x;        
    }
    
    public void setY(int y)
    {
        this.y = y;
    }
}
