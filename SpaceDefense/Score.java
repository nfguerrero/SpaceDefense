import java.awt.Graphics;
import javax.swing.ImageIcon;
import java.awt.Image;

/**
 * Write a description of class Ship here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Score
{
    private int x; //top right x coor
    private int y; //top right y coor
    String score;
    
    public Score(int x, int y, int score)
    {
        this.x = x - 5;
        this.y = y + 10;
        this.score = "" + score;
    }
    
    public void draw(Graphics g)
    {
        int tempX = this.x;
        int tempY = this.y;
        
        ImageIcon scoreIcon = new ImageIcon("Images\\score.png");
        Image scoreImage = scoreIcon.getImage();
        
        for (int i = 0; i < this.score.length(); i++)
        {
            ImageIcon numberIcon = new ImageIcon("Images\\"+score.charAt(this.score.length()-1-i)+".png");
            Image numberImage = numberIcon.getImage();
            tempX -= 13;
            g.drawImage(numberImage, tempX, tempY, null);
        }
        tempX -= 75;
        g.drawImage(scoreImage, tempX, tempY, null);
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
