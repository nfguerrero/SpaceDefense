import java.awt.Graphics;
import javax.swing.ImageIcon;
import java.awt.Image;

/**
 * Write a description of class Ship here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class PlayGameButton
{
    private int x; //top left x coor
    private int y; //top left y coor
    private boolean shadow = false;
    
    public PlayGameButton(int x, int y)
    {
        this.x = x;
        this.y = y;
    }
    
    public void draw(Graphics g)
    {
        ImageIcon buttonIcon = new ImageIcon("Images\\play_game_button.png");
        Image buttonImage = buttonIcon.getImage();
        ImageIcon shadowIcon = new ImageIcon("Images\\play_game_button_shadow.png");
        Image shadowImage = shadowIcon.getImage();
        
        g.drawImage(buttonImage, x, y, null);
        if (this.shadow)
        {
            g.drawImage(shadowImage, x, y, null);
        }
    }
    
    public void setX(int x)
    {
        this.x = x;        
    }
    
    public void setY(int y)
    {
        this.y = y;
    }
    
    public void setShadow(boolean shadow)
    {
        this.shadow = shadow;
    }
}
