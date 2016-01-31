package recipeIndex;

import java.awt.Dimension;
import java.awt.Insets;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
public class RecipeSearch {

	RecipeAdd AddUi;
	
	public RecipeSearch() {
		AddUi = new RecipeAdd();
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		JPanel pane = new JPanel();
		JFrame SearchUI = new JFrame();
		
		JButton searchButton = new JButton("Zoek");
		pane.add(searchButton);
		Insets insets = SearchUI.getInsets();
		Dimension size = searchButton.getSize();
		searchButton.setBounds(insets.left, insets.top, size.width, size.height);
		JTextField search = new JTextField();
		SearchUI.add( search);
		
		new RecipeSearch();
		
		SearchUI.setTitle("Recepten Zoeken");
		SearchUI.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		SearchUI.setContentPane(pane);
		SearchUI.setSize(300, 220);
		SearchUI.setVisible(true);
	}
	

}
