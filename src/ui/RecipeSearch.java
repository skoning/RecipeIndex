package ui;

import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JInternalFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.event.InternalFrameEvent;
import javax.swing.event.InternalFrameListener;

public class RecipeSearch extends JInternalFrame implements InternalFrameListener, ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private JPanel contentPane = null;
	public RecipeSearch() {
		super("Recept zoeken", true, true, true, true);
		
		contentPane = new JPanel(null);
		setContentPane(contentPane);
		addInternalFrameListener(this);
		
		addUIComponents();
		setSize(300, 300);
		setLocation(MainUI.openWindows * 20, MainUI.openWindows * 20);
		setVisible(true);
	}
	
	private void addUIComponents (){
		
		
		JTextField searchField = new JTextField();
		Insets insets = contentPane.getInsets();
		searchField.setBounds(insets.left, insets.top, searchField.getSize().width, searchField.getSize().height);
		
		contentPane.add(searchField);
		
		
	}
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub

	}

	public void internalFrameActivated(InternalFrameEvent arg0) {
		// TODO Auto-generated method stub
		MainUI.openWindows++;
	}

	@Override
	public void internalFrameClosed(InternalFrameEvent arg0) {
		// TODO Auto-generated method stub
		MainUI.openWindows--;
		if(MainUI.openWindows <= 1) {
			MainUI.openWindows = 0;
		}
	}

	@Override
	public void internalFrameClosing(InternalFrameEvent arg0) {
		// TODO Auto-generated method stub
		MainUI.openWindows--;
		if(MainUI.openWindows <= 1) {
			MainUI.openWindows = 0;
		}
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
		MainUI.openWindows++;

		MainUI.activeFrame = this;
	}
}
