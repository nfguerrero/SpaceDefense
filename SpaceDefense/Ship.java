import java.awt.Graphics;
import javax.swing.ImageIcon;
import java.awt.Image;

/**
 * Ship the player moves
 * 
 * @author nfguerrero
 * @version 6.0
 */
public class Ship
{
    private int x; //top left x coor
    private int y; //top left y coor
    private int move;
    private String ship;
    private int missleDelay;
    
    public Ship(int x, int y, String ship)
    {
        this.x = x;
        this.y = y;
        this.ship = ship;
        int value = 0;
        if (ship.equals("ship15"))
        {
            value = 15;
        }
        else
        {
            value = new Character(ship.charAt(ship.length()-1)).getNumericValue(ship.charAt(ship.length()-1));
        }
        this.move = 3 * value;
        this.missleDelay = 30/value;
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
        int value = 0;
        if (ship.equals("ship15"))
        {
            value = 15;
        }
        else
        {
            value = new Character(ship.charAt(ship.length()-1)).getNumericValue(ship.charAt(ship.length()-1));
        }
        this.move = 3 * value;
        this.missleDelay = 30/value;
    }
    
    public String getShip()
    {
        return this.ship;
    }
    
    public void setMissleDelay(int delay)
    {
        this.missleDelay = delay;
    }
    
    public int getMissleDelay()
    {
        return this.missleDelay;
    }
}
