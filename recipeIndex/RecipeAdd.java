package recipeIndex;

import java.awt.event.*;
import java.awt.Dimension;
import java.awt.Insets;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.*;

import com.mysql.jdbc.Statement;


public class RecipeAdd implements ActionListener {

	boolean hasEvent = false;
	JFrame frame = new JFrame();
	JTextField nameField = new JTextField();
	JSpinner tabSpinner = new JSpinner();
	JSpinner tasteSpinner = new JSpinner();
	JButton addButton = new JButton();
	public void ReturnPressed () {
		try {
			
			if(nameField.getText().isEmpty()) {
				JOptionPane.showMessageDialog(frame, "Geen naam opgegeven", "Missend veld", JOptionPane.ERROR_MESSAGE);
				System.exit(0);
			}
			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/recipeIndex", "root", "Silverta_84");
			PreparedStatement query = conn.prepareStatement("INSERT INTO recipies VALUES (0, ?, ?, ?)", Statement.RETURN_GENERATED_KEYS);
			query.setString(1, nameField.getText());
			SpinnerNumberModel model = (SpinnerNumberModel)tabSpinner.getModel();
			query.setInt(2, model.getNumber().intValue());
			SpinnerNumberModel tastemodel = (SpinnerNumberModel)tasteSpinner.getModel();
			query.setInt(3, tastemodel.getNumber().intValue() );
			query.executeUpdate();
			ResultSet rs = query.getGeneratedKeys();

			rs.beforeFirst();
			if( rs.next() ){
				
				System.out.println(rs.getInt(1));
			}

			new IngredientAdd(rs.getInt(1));
			query.close();
		} catch ( SQLException e ) {
			System.out.println(e.getMessage());
		}
	}
	
	public RecipeAdd() {
		// TODO Auto-generated constructor stub
		int left = 20;
			
		JPanel pane = new JPanel(null);
		SpinnerNumberModel tabSpinnerModel = new SpinnerNumberModel(1, 1, 10, 1);
		SpinnerNumberModel tasteSpinnerModel = new SpinnerNumberModel(1, 1, 10, 1);
		tabSpinner.setModel(tabSpinnerModel);
		tasteSpinner.setModel(tasteSpinnerModel);
		
		
		JLabel nameLabel = new JLabel("Naam");
		pane.add(nameLabel);
		
		Insets insets = pane.getInsets();
		insets.top = 10;
		insets.left = 10;
		
		Dimension size = nameLabel.getPreferredSize();
		nameLabel.setBounds(left, insets.top, size.width, size.height);
		
		// Name Field
		pane.add(nameField);
		nameField.setBounds(left + 10 + size.width, insets.top, 200, size.height);
		
		// Tab Label
		JLabel tabLabel = new JLabel("Tab");
		pane.add(tabLabel);
		Dimension tablabelSize = tabLabel.getPreferredSize();
		tabLabel.setBounds(left, insets.top + nameField.getHeight() + 2, tablabelSize.width, tablabelSize.height);
		
		// Spinner
		pane.add(tabSpinner);
		Dimension tabSize = tabSpinner.getPreferredSize();
		tabSpinner.setBounds(nameField.getBounds().x, insets.top + nameField.getHeight(), tabSize.width, tabSize.height);
		
		// Taste
		pane.add(tasteSpinner);
		Dimension spinnerSize = tasteSpinner.getPreferredSize();
		tasteSpinner.setBounds(nameField.getBounds().x, insets.top + nameField.getHeight() + tabSpinner.getHeight(), spinnerSize.width, spinnerSize.height);
		
		// taste Label
		JLabel tasteLabel = new JLabel("Smaak");
		pane.add(tasteLabel);
		Dimension labelSize = tasteLabel.getPreferredSize();
		tasteLabel.setBounds(left, tabSpinner.getBounds().y + tasteSpinner.getHeight(), labelSize.width, labelSize.height);
		
		pane.add(tasteSpinner);
		tasteSpinner.setBounds(nameField.getBounds().x, tabSpinner.getBounds().y + tabSpinner.getHeight(), tasteSpinner.getSize().width, tasteSpinner.getSize().height);
		
		addButton.setText("Toevoegen");
		pane.add(addButton);
		addButton.addActionListener(this);
		Dimension addButtonSizeDimension = addButton.getPreferredSize();
		addButton.setBounds(100 - addButton.getWidth(), 100 - addButton.getHeight(),addButtonSizeDimension.width, addButtonSizeDimension.height);
		
		frame.getRootPane().setDefaultButton(addButton);
		
		// Frame
		frame.setTitle("Recipe Index : Toevoegen");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setContentPane(pane);
		frame.setSize(300, 220);
		frame.setVisible(true);
		
		
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		
		// TODO Auto-generated method stub
		ReturnPressed();
		addButton.setEnabled(false);
		hasEvent = true;
		System.exit(1);
		
	}

}
