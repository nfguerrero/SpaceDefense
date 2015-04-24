import javax.swing.JFrame;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;
import javax.swing.KeyStroke;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseMotionAdapter;
import java.awt.event.MouseEvent;

import java.util.ArrayList;

/**
 * Frame for the game that holds the Listeners and tells the component what to do
 * 
 * @author nfguerrero
 * @version Ver: 1.0
 */
public class GameFrame extends JFrame
{
   private static final int FRAME_WIDTH = 800;// inside width: 794
   private static final int FRAME_HEIGHT = 600;// inside height: 572

   private GameComponent scene;
   
   class KeyStrokeListener implements KeyListener
   {
      public void keyPressed(KeyEvent event) 
      {
         String key = KeyStroke.getKeyStrokeForEvent(event).toString().replace("pressed ", ""); 

         if (key.equals("LEFT"))
         {
            scene.startMoveLeft();            
         }
         else if (key.equals("RIGHT"))
         {
            scene.startMoveRight();            
         }
         
         if (key.equals("SPACE"))
         {
             scene.startShooting();
         }
         
         if (key.equals("ESCAPE"))
         {
            if (scene.getPlay())
            {
                if (!scene.getPause())
                {
                    scene.pause();
                }
                else
                {
                    scene.unpause();
                }
            }
         }
      }
      public void keyTyped(KeyEvent event) {}
      public void keyReleased(KeyEvent event)
      {
          String key = KeyStroke.getKeyStrokeForEvent(event).toString().replace("released ", "");
          
          if (key.equals("LEFT"))
          {
              scene.stopMoveLeft();
          }
          else if (key.equals("RIGHT"))
          {
              scene.stopMoveRight();
          }
          
          if (key.equals("SPACE"))
          {
              scene.stopShooting();
          }
      }
   }
   
   private boolean inRange;
   private Button buttonInRange;
   class MouseMovementListener extends MouseMotionAdapter
   {       
       public void mouseMoved(MouseEvent event)
       {
           int x = event.getX();
           int y = event.getY();
           ArrayList<Button> buttons = scene.getButtons();
           
           for (int i = 0; i < buttons.size(); i++)
           {
               Button button = buttons.get(i);
               int buttonX = button.getX();
               int buttonY = button.getY();               
               
               if (x >= buttonX && x <= buttonX+150 && y >= buttonY && y <= buttonY+50)
               {
                   if (!button.getShadow())
                   {
                       button.setShadow(true);
                   }
                   inRange = true;
                   buttonInRange = button;
                   i = buttons.size();
               }
               else
               {
                   if (button.getShadow())
                   {
                       button.setShadow(false);
                   }
                   inRange = false;
               }
           }
       }
   }
   class MouseClickListener extends MouseAdapter
   {
       public void mouseClicked(MouseEvent event)
       {
           if (inRange)
           {
               if (scene.getMenu())
               {
                   if (buttonInRange.getButton().equals("play_game"))
                   {
                       scene.reset();
                       scene.setMenu(false);     
                       scene.setPlay(true);
                   }
                   else if (buttonInRange.getButton().equals("controls"))
                   {
                       scene.setMenu(false);
                       scene.setControls(true);
                   }
                   else if (buttonInRange.getButton().equals("upgrade"))
                   {
                       scene.setMenu(false);
                       scene.setUpgrade(true);
                   }
               }
               else if (scene.getPause())
               {
                   scene.setMenu(true);
                   scene.setPlay(false);
                   scene.unpause();
               }
               else if (scene.getGameOver())
               {
                   scene.setMenu(true);
                   scene.setPlay(false);
                   scene.resetGameOver();                  
               }
               else if (scene.getControls())
               {
                   scene.setMenu(true);
                   scene.setControls(false);
               }
               else if (scene.getUpgrade())
               {
                   scene.setMenu(true);
                   scene.setUpgrade(false);
               }
               buttonInRange.setShadow(false);
           }
       }
   }
   
   class FrameWindowListener extends WindowAdapter
   {
       public void windowOpened(WindowEvent event)
       {
           scene.requestFocusInWindow();
           scene.setVariables();
       }
   }
   
   public GameFrame()
   {
      scene = new GameComponent();
      this.add(scene);

      scene.addKeyListener(new KeyStrokeListener());
      this.addWindowListener(new FrameWindowListener());
      scene.addMouseListener(new MouseClickListener());
      scene.addMouseMotionListener(new MouseMovementListener());
      
      this.setSize(FRAME_WIDTH, FRAME_HEIGHT);
      this.setTitle("Space Invaders");      
      this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      this.setResizable(false);
      this.setVisible(true);  
   }
} 
