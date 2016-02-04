package ui;

import java.awt.Dimension;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyVetoException;
import java.sql.ResultSet;

import javax.swing.JButton;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;
import javax.swing.event.InternalFrameEvent;
import javax.swing.event.InternalFrameListener;


public class RecipeAdd extends JInternalFrame implements InternalFrameListener, ActionListener {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public int left = 20;
	public int defaultTab = 1;
	public int defaultTaste = 1;
	
	public JTextField nameField = new JTextField(255);
	public JSpinner tabSpinner = new JSpinner();
	public JSpinner tasteSpinner = new JSpinner();
	public JButton addButton = new JButton();
	
	public ResultSet rs;
	
	public static int openWindows = 1;
	public static int location = 0;
	
	JPanel contentPane = new JPanel();
	
	public RecipeAdd() {
		super( "Recept toevoegen", true, true, true, true);
		location = MainUI.openWindows * 20; 
		
		setContentPane(contentPane);
		addInternalFrameListener(this);
		SpinnerNumberModel tabSpinnerModel = new SpinnerNumberModel(defaultTab, 1, 11, 1);
		SpinnerNumberModel tasteSpinnerModel = new SpinnerNumberModel(defaultTaste, 1, 10, 1);
		tabSpinner.setModel(tabSpinnerModel);
		tasteSpinner.setModel(tasteSpinnerModel);

		CreateUI();
		setSize(300,300);
		
		setLocation(location, location);
		setVisible(true);
	}
		
	private void CreateUI() {
		
		JLabel nameLabel = new JLabel("Naam");
		contentPane.add(nameLabel);
		
		Insets insets = contentPane.getInsets();
		insets.top = 10;
		insets.left = 10;
		
		Dimension size = nameLabel.getPreferredSize();
		nameLabel.setBounds(left, insets.top, size.width, size.height);
		
		// Name Field
		nameField.setPreferredSize(getPreferredSize());
		contentPane.add(nameField);
		nameField.setBounds(left + 10 + size.width, insets.top, 200, size.height);
		
		// Tab Label
		JLabel tabLabel = new JLabel("Tab");
		contentPane.add(tabLabel);
		Dimension tablabelSize = tabLabel.getPreferredSize();
		tabLabel.setBounds(left, insets.top + nameField.getHeight() + 2, tablabelSize.width, tablabelSize.height);
		
		// Spinner
		contentPane.add(tabSpinner);
		Dimension tabSize = tabSpinner.getPreferredSize();
		tabSpinner.setBounds(nameField.getBounds().x, insets.top + nameField.getHeight(), tabSize.width, tabSize.height);
		
		// Taste
		contentPane.add(tasteSpinner);
		Dimension spinnerSize = tasteSpinner.getPreferredSize();
		tasteSpinner.setBounds(nameField.getBounds().x, insets.top + nameField.getHeight() + tabSpinner.getHeight(), spinnerSize.width, spinnerSize.height);
		
		// taste Label
		JLabel tasteLabel = new JLabel("Smaak");
		contentPane.add(tasteLabel);
		Dimension labelSize = tasteLabel.getPreferredSize();
		tasteLabel.setBounds(left, tabSpinner.getBounds().y + tasteSpinner.getHeight(), labelSize.width, labelSize.height);
		
		contentPane.add(tasteSpinner);
		tasteSpinner.setBounds(nameField.getBounds().x, tabSpinner.getBounds().y + tabSpinner.getHeight(), tasteSpinner.getSize().width, tasteSpinner.getSize().height);
		
		addButton.setText("Toevoegen");
		contentPane.add(addButton);
		addButton.addActionListener(this);
		Dimension addButtonSizeDimension = addButton.getPreferredSize();
		addButton.setBounds(100 - addButton.getWidth(), 100 - addButton.getHeight(),addButtonSizeDimension.width, addButtonSizeDimension.height);
				
	}
	
	

	@Override
	public void internalFrameActivated(InternalFrameEvent arg0) {
		// TODO Auto-generated method stub
		MainUI.openWindows++;
	}

	@Override
	public void internalFrameClosed(InternalFrameEvent arg0) {
		// TODO Auto-generated method stub
		MainUI.openWindows--;
		if(MainUI.openWindows <= 0) {
			MainUI.openWindows = 0;
		}
	}

	@Override
	public void internalFrameClosing(InternalFrameEvent arg0) {
		// TODO Auto-generated method stub
		MainUI.openWindows--;
		if(MainUI.openWindows <= 0) {
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
	
	@Override
	public void actionPerformed(ActionEvent e) {
		
		// TODO Auto-generated method stub
		addButton.setEnabled(false);
		try{
			setClosed(true);
		} catch (PropertyVetoException ex) {}
		
	}
}