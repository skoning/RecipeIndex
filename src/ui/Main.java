/**
 * RecipeIndexUI
 * Extension of the console applet.
 * 
 * @author Sander Koning
 * @package ui
 */
package ui;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.beans.PropertyVetoException;

import javax.swing.JButton;
import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.KeyStroke;
import javax.swing.event.InternalFrameEvent;
import javax.swing.event.InternalFrameListener;


/**
 * @author Sander
 *
 */
public class Main 
  extends 
    JFrame 
  implements 
    InternalFrameListener, 
    ActionListener
{
  
  private static final long serialVersionUID = -6604330634528476617L;

  private JDesktopPane desktop;
  
  public static JInternalFrame activeFrame = null;

  /**
   * 
   */
  public Main(String title)
  {
    super(title);
    
    int buttonInset = 20;
    JButton addRecipe = new JButton("Recept toevoegen");
    addRecipe.addActionListener(this);
    addRecipe.setActionCommand("newRecipe");
    Dimension buttonSize = addRecipe.getPreferredSize();
    addRecipe.setBounds(buttonInset, buttonInset, buttonSize.width, buttonSize.height);
    addRecipe.setMnemonic(
        KeyEvent.VK_N);
    
    JButton searchRecipe = new JButton("Recept zoeken");
    searchRecipe.addActionListener(this);
    searchRecipe.setActionCommand("searchRecipe");
    buttonSize = addRecipe.getPreferredSize();
    searchRecipe.setBounds(buttonInset, buttonInset + buttonSize.height + 5, buttonSize.width, buttonSize.height);
    searchRecipe.setMnemonic(
        KeyEvent.VK_S);
    
    setBounds(100, 100, 1000, 500);
    
    desktop = new JDesktopPane();

    desktop.add(addRecipe);
    desktop.add(searchRecipe);
    
    createMenuBar();
    
    setContentPane(desktop);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setVisible(true);
  }
  
  private void newRecipe() throws PropertyVetoException
  {
    AddRecipe window = new AddRecipe("Recept Toevoegen");
    window.addInternalFrameListener(this);
    window.setVisible(true);
    desktop.add(window);
    window.setSelected(true);
  }
  
  private void searchRecipe()
  {
    
  }

  private void createMenuBar(){
    JMenuBar menuBar = new JMenuBar();
    
    JMenu menu = new JMenu("Menu");
    
    menuBar.add(menu);
    JMenuItem newRecipe = new JMenuItem("Nieuw recept");
    newRecipe.setAccelerator(KeyStroke.getKeyStroke(
        KeyEvent.VK_N,
        ActionEvent.CTRL_MASK));
    newRecipe.setActionCommand("newRecipe");
    newRecipe.addActionListener(this);
    menu.add(newRecipe);

    JMenuItem searchRecipe = new JMenuItem( "Zoek recept");
    searchRecipe.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, 
        ActionEvent.CTRL_MASK));
    searchRecipe.setActionCommand("searchRecipe");
    searchRecipe.addActionListener(this);
    menu.add(searchRecipe);
    
    JMenuItem closeWindow = new JMenuItem( "Sluiten");
    closeWindow.setAccelerator(KeyStroke.getKeyStroke(
        KeyEvent.VK_W,
        ActionEvent.CTRL_MASK));
    closeWindow.setActionCommand("sluiten");
    closeWindow.addActionListener(this);
    menu.add(closeWindow);
    
    setJMenuBar(menuBar);
  }
  
  /* (non-Javadoc)
   * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
   */
  @Override
  public void actionPerformed(ActionEvent e)
  {
    if("newRecipe".equals(e.getActionCommand())){
      try {
        newRecipe();
      } catch (PropertyVetoException e1) {
        e1.printStackTrace();
      }
    } else if("searchRecipe".equals(e.getActionCommand())){
      searchRecipe();
    } else if("sluiten".equals(e.getActionCommand())){
      try{
        if(activeFrame == null) {
          return;
        } else {
          activeFrame.setClosed(true);
        }
        
      } catch(PropertyVetoException ex) {}
    } else if("ingredient".equals(e.getActionCommand())) {
        try { 
          activeFrame.setClosed(true);
          activeFrame = null;
        } catch(PropertyVetoException ex) {}
      //openIngredientAddFrame(0);
    } else {
      System.out.println("closing");
      System.exit(1);
    }
  }

  /* (non-Javadoc)
   * @see javax.swing.event.InternalFrameListener#internalFrameActivated(javax.swing.event.InternalFrameEvent)
   */
  @Override
  public void internalFrameActivated(InternalFrameEvent e)
  {
    activeFrame = e.getInternalFrame();
  }

  /* (non-Javadoc)
   * @see javax.swing.event.InternalFrameListener#internalFrameClosed(javax.swing.event.InternalFrameEvent)
   */
  @Override
  public void internalFrameClosed(InternalFrameEvent e)
  {
  }

  /* (non-Javadoc)
   * @see javax.swing.event.InternalFrameListener#internalFrameClosing(javax.swing.event.InternalFrameEvent)
   */
  @Override
  public void internalFrameClosing(InternalFrameEvent e)
  {
    activeFrame = null;
  }

  /* (non-Javadoc)
   * @see javax.swing.event.InternalFrameListener#internalFrameDeactivated(javax.swing.event.InternalFrameEvent)
   */
  @Override
  public void internalFrameDeactivated(InternalFrameEvent e)
  {
    activeFrame = null;
  }

  /* (non-Javadoc)
   * @see javax.swing.event.InternalFrameListener#internalFrameDeiconified(javax.swing.event.InternalFrameEvent)
   */
  @Override
  public void internalFrameDeiconified(InternalFrameEvent e)
  {
    activeFrame = e.getInternalFrame();
  }

  /* (non-Javadoc)
   * @see javax.swing.event.InternalFrameListener#internalFrameIconified(javax.swing.event.InternalFrameEvent)
   */
  @Override
  public void internalFrameIconified(InternalFrameEvent e)
  {
    activeFrame = null;
  }

  /* (non-Javadoc)
   * @see javax.swing.event.InternalFrameListener#internalFrameOpened(javax.swing.event.InternalFrameEvent)
   */
  @Override
  public void internalFrameOpened(InternalFrameEvent e)
  {
    activeFrame = e.getInternalFrame();
  }

  /**
   * @param args
   */
  public static void main(String[] args)
  {
    new Main("Recipe Index");
  }

}
