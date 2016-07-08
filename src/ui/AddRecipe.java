/**
 * 
 */
package ui;

import java.awt.Dimension;

import javax.swing.JInternalFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 * @author Sander
 *
 */
public class AddRecipe extends JInternalFrame
{

  /**
   * 
   */
  private static final long serialVersionUID = 4374391619227422831L;

  private JPanel contentPane = new JPanel();
  
  public JTextField name = new JTextField();
  
  private void createUI()
  {
    setSize(300,300);
    setLayout(null);
    
    createLayout();
  }
  
  private void createLayout()
  {
    System.out.println("Creating layout");
    setContentPane(contentPane);
    
    Dimension size = name.getSize();
    
    name.setBounds(50, 50, 200, size.height);
    
    contentPane.add(name);
    
    
  }
  
  /**
   * 
   */
  public AddRecipe()
  {
    super("Add Recipe", true, true, true, true);
    createUI();
  }

  /**
   * @param title The description on the titlebar
   */
  public AddRecipe(String title)
  {
    super(title, true, true, true, true);
    createUI();
  }

  /**
   * @param arg0
   * @param arg1
   */
  public AddRecipe(String arg0, boolean arg1)
  {
    super(arg0, arg1, true, true, true);
    createUI();
  }

  /**
   * @param arg0
   * @param arg1
   * @param arg2
   */
  public AddRecipe(String arg0, boolean arg1, boolean arg2)
  {
    super(arg0, arg1, arg2, true, true);
    createUI();
  }

  /**
   * @param arg0
   * @param arg1
   * @param arg2
   * @param arg3
   */
  public AddRecipe(String arg0, boolean arg1, boolean arg2, boolean arg3)
  {
    super(arg0, arg1, arg2, arg3, true);
    createUI();
  }

  /**
   * @param arg0
   * @param arg1
   * @param arg2
   * @param arg3
   * @param arg4
   */
  public AddRecipe(String arg0, boolean arg1, boolean arg2, boolean arg3, boolean arg4)
  {
    super(arg0, arg1, arg2, arg3, arg4);
    createUI();
  }

}
