package recipeIndex;

import javax.swing.JDesktopPane;
import javax.swing.JInternalFrame;

public class RecipeUi {

	public static JDesktopPane desktop = new JDesktopPane();
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		
		
		new RecipeAdd();

	}
	
	public static void mainWindow() {
		desktop.add(new JInternalFrame("Toevoegen"));
		desktop.setVisible(true);
	}

}
