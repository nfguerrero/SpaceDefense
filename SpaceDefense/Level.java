import java.awt.Graphics;
import javax.swing.ImageIcon;
import java.awt.Image;

/**
 * Write a description of class Ship here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Level
{
    private int x; //top left x coor
    private int y; //top left y coor
    String level;
    
    public Level(int x, int y, int level)
    {
        this.x = x;
        this.y = y;
        this.level = "" + level;
    }
    
    public void draw(Graphics g)
    {
        int tempX = this.x;
        int tempY = this.y;
        
        ImageIcon levelIcon = new ImageIcon("Images\\level.png");
        Image levelImage = levelIcon.getImage();
        
        g.drawImage(levelImage, tempX, tempY, null);
        tempX += 50;        
        for (int i = 0; i < this.level.length(); i++)
        {
            ImageIcon numberIcon = new ImageIcon("Images\\"+level.charAt(i)+".png");
            Image numberImage = numberIcon.getImage();
            tempX += 13;
            g.drawImage(numberImage, tempX, tempY, null);
        }   
    }
    
    public void setLevel(int level)
    {
        this.level = "" + level;
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
