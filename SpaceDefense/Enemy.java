import java.awt.Graphics;
import javax.swing.ImageIcon;
import java.awt.Image;

/**
 * Write a description of class Ship here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Enemy
{
    private int x; //top left x coor
    private int y; //top left y coor
    private int dx; //x vector
    private int dy; //y vector
    
    public Enemy(int x, int y)
    {
        this.x = x;
        this.y = y;
        int sign = (int) (Math.random()*2);
        if (sign == 0)
        {
            sign = -1;
        }
        this.dx = sign * (int) (Math.random()*2 + 1);
        this.dy = 1;
    }
    
    public void draw(Graphics g)
    {
        ImageIcon icon = new ImageIcon("Images\\enemy.png");
        Image image = icon.getImage();
        
        g.drawImage(image, x, y, null);
    }
    
    public void move()
    {
        this.x += dx;
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
