import java.awt.Graphics;
import javax.swing.ImageIcon;
import java.awt.Image;

/**
 * Write a description of class Ship here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Missle
{
    private int x; //top left x coor
    private int y; //top left y coor
    private int dy; //y vector
    private int value;
    
    public Missle(int x, int y, String ship)
    {
        this.x = x;
        this.y = y;
        this.value = new Character(ship.charAt(ship.length()-1)).getNumericValue(ship.charAt(ship.length()-1));
        this.dy = -2 * this.value;
    }
    
    public void draw(Graphics g)
    {
        ImageIcon icon = new ImageIcon("Images\\missile_double"+this.value+".png");
        Image image = icon.getImage();
        
        g.drawImage(image, x, y, null);
    }
    
    public void move()
    {
        this.y += dy;
    }
    
    public int getX()
    {
        return this.x;
    }
    
    public void setX(int x)
    {
        this.x = x;
    }
    
    public int getY()
    {
        return this.y;
    }
    
    public void setY(int y)
    {
        this.y = y;
    }
}
