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
 * @version Ver: 6.0
 */
public class GameFrame extends JFrame
{
   private static final int FRAME_WIDTH = 800;// inside width: 794
   private static final int FRAME_HEIGHT = 600;// inside height: 572

   private GameComponent scene;
   
   /**
    * KeyListener to listen for keys detected and change the state of the game accordingly
    */
   class KeyStrokeListener implements KeyListener
   {
       /**
        * Method called when a key is pressed
        * 
        * @param KeyEvent event     the event that is being listened and can get information from of what keys are
        *                           pressed
        */
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
      /**
       *Method called when a key is released
       * 
       *@param KeyEvent event     the event that is being listened and can get information from of what keys are
       *                           released
       */
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
   private ArrayList<Button> buttons;
   /**
    * MouseMotionListener that detects when the mouse is moved on the component
    */
   class MouseMovementListener extends MouseMotionAdapter
   {       
       /**
        * Detects when the mouse is moved
        * 
        * @param MouseEvent event       event that is listened on and can get information on the movement of the mouse
        */
       public void mouseMoved(MouseEvent event)
       {
           int x = event.getX();
           int y = event.getY();
           buttons = scene.getButtons();
           
           for (int i = 0; i < buttons.size(); i++)
           {
               Button button = buttons.get(i);
               int buttonX = button.getX();
               int buttonY = button.getY();               
               
               if (x >= buttonX && x <= buttonX+button.getWidth() && y >= buttonY && y <= buttonY+button.getHeight())
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
                   if (button.getShadow() && !button.isSelected())
                   {
                       button.setShadow(false);
                   }
                   inRange = false;
               }
           }
       }
   }
   /**
    * MouseListener that detects the clicking of the mouse
    */
   class MouseClickListener extends MouseAdapter
   {
       /**
        * Detects the mouse being clicked
        * 
        * @param MouseEvent event       event being listened to attain info on the mouse
        */
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
                   else if (buttonInRange.getButton().equals("secret"))
                   {
                       scene.setMenu(false);
                       scene.setSecret(true);
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
                   if (buttonInRange.getButton().equals("back"))
                   {
                       scene.setMenu(true);
                       scene.setUpgrade(false);
                   }
                   else if (buttonInRange.getButton().equals("ship1"))
                   {
                       scene.setShip("ship1");
                       buttonInRange.setSelected(true);
                   }
                   else if (buttonInRange.getButton().equals("ship2") && (scene.getBalance() >= 100 || !buttonInRange.getLocked()))
                   {
                       scene.setShip("ship2");
                       if (buttonInRange.getLocked())
                       {
                           scene.setBalance(scene.getBalance()-100);
                       }
                       buttonInRange.setLocked(false);
                       buttonInRange.setSelected(true);                       
                   }
                   else if (buttonInRange.getButton().equals("ship3") && (scene.getBalance() >= 300 || !buttonInRange.getLocked()))
                   {
                       scene.setShip("ship3");
                       if (buttonInRange.getLocked())
                       {
                           scene.setBalance(scene.getBalance()-300);
                       }
                       buttonInRange.setLocked(false);
                       buttonInRange.setSelected(true);                       
                   }
                   else if (buttonInRange.getButton().equals("ship15"))
                   {
                       scene.setShip("ship15");
                       buttonInRange.setSelected(true);
                   }
                   for (int i = 0; i < buttons.size(); i++)
                   {
                       if (!buttons.get(i).getButton().equals(scene.getShip()))
                       {
                           buttons.get(i).setSelected(false);
                        }
                   }
               }
               else if (scene.getSecret())
               {
                   if (buttonInRange.getButton().equals("yes"))
                   {
                       scene.setGodMode(true);
                       scene.setSecret(false);
                       scene.setCorrect(true);                       
                   }
                   else if (buttonInRange.getButton().equals("no"))
                   {
                       scene.setSecret(false);
                       scene.setWrong(true);
                   }
               }
               buttonInRange.setShadow(false);
           }
       }
   }   
   /**
    * WindowListener that detects the window
    */
   class FrameWindowListener extends WindowAdapter
   {
       /**
        * Detects the window being opened
        * 
        * @param WindowEvent event      event that detects the the window
        */
       public void windowOpened(WindowEvent event)
       {
           scene.requestFocusInWindow();
           scene.setVariables();
       }
   }
   
   /**
    * Creates a component and adds the listeners to it
    */
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
