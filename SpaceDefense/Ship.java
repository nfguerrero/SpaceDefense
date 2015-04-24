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
    private int move;
    private String ship;
    
    public Ship(int x, int y, String ship)
    {
        this.x = x;
        this.y = y;
        this.ship = ship;
        int value = new Character(ship.charAt(ship.length()-1)).getNumericValue(ship.charAt(ship.length()-1));
        this.move = 3 * value;
    }
    
    public void draw(Graphics g)
    {
        ImageIcon icon = new ImageIcon("Images\\"+this.ship+".png");
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
    
    public void setShip(String ship)
    {
        this.ship = ship;
    }
    
    public String getShip()
    {
        return this.ship;
    }
}
