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
    private int width;
    private int height;
    private boolean shadow = false;
    private String button;
    
    private boolean locked = false;
    private boolean ship;
    private boolean selected = false;
    
    private ImageIcon buttonIcon;
    private Image buttonImage;
    private ImageIcon shadowIcon;
    private Image shadowImage;
    private ImageIcon lockedIcon;
    private Image lockedImage;
    
    public Button(int x, int y, String button)
    {
        this.x = x;
        this.y = y;
        this.button = button;
        
        this.buttonIcon = new ImageIcon("Images\\"+this.button+"_button.png");
        this.buttonImage = buttonIcon.getImage();
        this.shadowIcon = new ImageIcon("Images\\"+this.button+"_button_shadow.png");
        this.shadowImage = shadowIcon.getImage();
        if (button.length() >= 5 && button.substring(0, 4).equals("ship"))
        {
            if (this.button.length() != 6)
            {
                this.locked = true;
                this.lockedIcon = new ImageIcon("Images\\"+this.button+"_button_locked.png");
                this.lockedImage = lockedIcon.getImage();
            }
            this.ship = true;
        }
        this.width = this.buttonImage.getWidth(this.buttonIcon.getImageObserver());
        this.height = this.buttonImage.getHeight(this.buttonIcon.getImageObserver());
    }
    
    public void draw(Graphics g)
    {     
        g.drawImage(this.buttonImage, x, y, null);
        if (this.shadow)
        {
            g.drawImage(this.shadowImage, x, y, null);
        }
        if (this.locked)
        {
            g.drawImage(this.lockedImage, x, y, null);
        }
        if (this.selected)
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
    
    public int getWidth()
    {
        return this.width;
    }
    
    public int getHeight()
    {
        return this.height;
    }
    
    public void setShadow(boolean shadow)
    {
        this.shadow = shadow;
    }
    
    public boolean getShadow()
    {
        return this.shadow;
    }
    
    public void setLocked(boolean lock)
    {
        this.locked = lock;
    }
    
    public boolean getLocked()
    {
        return this.locked;
    }
    
    public boolean isShip()
    {
        return this.ship;
    }
    
    public void setSelected(boolean select)
    {
        this.selected = select;
    }
    
    public boolean isSelected()
    {
        return this.selected;
    }
}
