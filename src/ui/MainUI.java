package ui;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.beans.PropertyVetoException;

import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.KeyStroke;
import javax.swing.event.InternalFrameEvent;
import javax.swing.event.InternalFrameListener;

public class MainUI extends JFrame implements ActionListener, KeyListener, InternalFrameListener {

	private static final long serialVersionUID = 1L;
	JDesktopPane desktop;
	
	public MainUI() {

		super("Recipe Index");

		int inset = 50;
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		setBounds(inset, inset, screenSize.width - inset*2, screenSize.height-inset*2);
		desktop = new JDesktopPane();
		desktop.setDragMode(JDesktopPane.OUTLINE_DRAG_MODE);
		createRecipeAddFrame();
		createMenuBar();
		setContentPane(desktop);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
		
	}
	
	public static int openWindows = 0;
	
	
	public void createMenuBar(){
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
	
	public void createRecipeAddFrame () {
		RecipeAdd recipeAddUI = new RecipeAdd();
		recipeAddUI.setVisible(true);
		desktop.add(recipeAddUI);
		try {
			recipeAddUI.setSelected(true);
		} catch (java.beans.PropertyVetoException e){}
		
	}
	
	public void createRecipeSearchFrame() {
		RecipeSearch searchUI = new RecipeSearch();
		searchUI.setVisible(true);
		desktop.add(searchUI);
		try {
			searchUI.setSelected(true);
		} catch (java.beans.PropertyVetoException e){}
		
	}
	
	
	
	public static JInternalFrame activeFrame = null;
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new MainUI();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
		if("newRecipe".equals(e.getActionCommand())){
			createRecipeAddFrame();
		} else if("searchRecipe".equals(e.getActionCommand())){
			createRecipeSearchFrame();
		} else if("sluiten".equals(e.getActionCommand())){
			try{
				if(activeFrame.equals(null)) {
					return;
				} else {
					activeFrame.setClosed(true);
				}
				
			} catch(PropertyVetoException ex) {}
		} else {
			System.exit(1);
		}
	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		System.out.println(e.getKeyCode());
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		if(e.getKeyCode() == KeyEvent.VK_W && e.getModifiers() == KeyEvent.CTRL_DOWN_MASK){
			try{
				System.out.println(e.getKeyCode());
				activeFrame.setClosed(true);
			} catch (PropertyVetoException ex){
				
			}
		}
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		System.out.println(e.getKeyCode());
	}

	@Override
	public void internalFrameActivated(InternalFrameEvent arg0) {
		// TODO Auto-generated method stub
		activeFrame = arg0.getInternalFrame();
	}

	@Override
	public void internalFrameClosed(InternalFrameEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void internalFrameClosing(InternalFrameEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void internalFrameDeactivated(InternalFrameEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void internalFrameDeiconified(InternalFrameEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void internalFrameIconified(InternalFrameEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void internalFrameOpened(InternalFrameEvent arg0) {
		// TODO Auto-generated method stub
		
	}
}
