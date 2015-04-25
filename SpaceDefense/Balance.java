import java.awt.Graphics;
import javax.swing.ImageIcon;
import java.awt.Image;

/**
 * Write a description of class Ship here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Balance
{
    private int x; //bottom left x coor
    private int y; //bottom left y coor
    String score;
    
    public Balance(int x, int y, int score)
    {
        this.x = x;
        this.y = y - 20;
        this.score = "" + score;
    }
    
    public void draw(Graphics g)
    {
        int tempX = this.x;
        int tempY = this.y;
        
        ImageIcon scoreIcon = new ImageIcon("Images\\balance.png");
        Image scoreImage = scoreIcon.getImage();
                
        g.drawImage(scoreImage, tempX, tempY, null);
        tempX += 90;
        tempY += 2;
        for (int i = 0; i < this.score.length(); i++)
        {
            ImageIcon numberIcon = new ImageIcon("Images\\"+score.charAt(i)+".png");
            Image numberImage = numberIcon.getImage();
            tempX += 13;
            g.drawImage(numberImage, tempX, tempY, null);
        }        
    }
    
    public void setScore(int score)
    {
        this.score = "" + score;
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
