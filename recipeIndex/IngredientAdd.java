package recipeIndex;

import java.awt.Dimension;
import java.awt.Insets;

import javax.swing.JButton;
import javax.swing.JFrame;

public class IngredientAdd {
	
	public JFrame frame = new JFrame();
	
	public JButton AddButton = new JButton();
	
	
	public static void main(String[] args ){
		
		new IngredientAdd(0);
		
	}
	
	public void CreateUI() {
		
		AddButton.setText("Toevoegen");
		
		Insets insets = AddButton.getInsets();
		Dimension size = AddButton.getPreferredSize();
		frame.add(AddButton);
		AddButton.setBounds(insets.left, insets.top, size.width, size.height);
		frame.setTitle("Ingrediënten toevoegen");
		frame.setSize(200, 220);
		frame.setVisible(true);
	}
	
	public int lastInsertId = 0;
	public IngredientAdd(int lastRecipeId) {
		lastInsertId = lastRecipeId;
		CreateUI();
		
	}
}