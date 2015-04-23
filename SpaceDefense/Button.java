import java.awt.Graphics;
import javax.swing.ImageIcon;
import java.awt.Image;

/**
 * Write a description of class Ship here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Button
{
    private int x; //top left x coor
    private int y; //top left y coor
    private boolean shadow = false;
    private String button;
    
    private ImageIcon buttonIcon;
    private Image buttonImage;
    private ImageIcon shadowIcon;
    private Image shadowImage;
    
    public Button(int x, int y, String button)
    {
        this.x = x;
        this.y = y;
        this.button = button;
        
        this.buttonIcon = new ImageIcon("Images\\"+this.button+"_button.png");
        this.buttonImage = buttonIcon.getImage();
        this.shadowIcon = new ImageIcon("Images\\"+this.button+"_button_shadow.png");
        this.shadowImage = shadowIcon.getImage();
    }
    
    public void draw(Graphics g)
    {     
        g.drawImage(this.buttonImage, x, y, null);
        if (this.shadow)
        {
            g.drawImage(this.shadowImage, x, y, null);
        }
    }
    
    public String getButton()
    {
        return this.button;
    }
    
    public void setX(int x)
    {
        this.x = x;        
    }
    
    public int getX()
    {
        return this.x;
    }
    
    public void setY(int y)
    {
        this.y = y;
    }
    
    public int getY()
    {
        return this.y;
    }
    
    public void setShadow(boolean shadow)
    {
        this.shadow = shadow;
    }
    
    public boolean getShadow()
    {
        return this.shadow;
    }
}
