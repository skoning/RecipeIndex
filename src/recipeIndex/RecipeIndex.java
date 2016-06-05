package recipeIndex;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

import recipeIndex.entities.Recipe;

public class RecipeIndex
{

  Scanner input = new Scanner(System.in);
  String[] menuitems = { 
    "1. New Recipe",
    "2. Search Recipe"
  };
  public RecipeIndex() throws 
    IOException, 
    InputMismatchException, 
    ClassNotFoundException,
    Exception
  {
    outputMenu();
    int option = input.nextInt();
    switch (option)
    {
      case 1:
        newRecipe();
        break;
      case 2:
        searchRecipe();
      default:
        break;
    }
  }
  

  private void outputMenu() {
    for( String menuItem : menuitems ) {
      System.out.println( menuItem );
    }
    System.out.print("$ ");
  }
  
  private void newRecipe() throws 
    IOException, 
    ClassNotFoundException,
    InputMismatchException,
    Exception
  {
    StringBuilder name = new StringBuilder();
    Scanner in = new Scanner(System.in);
    int c;
    System.out.println("New Recipe");
    
    System.out.print( "Enter a name: ");
    while( ( c = System.in.read() ) != 13 ) {
      name.append((char)c);
    }
    
    int tab;
    System.out.print( "Enter tab: ");
    tab = in.nextInt();
    Recipe result = Recipe.CreateNew(name.toString(), tab);
    in.close();
    System.out.println();
    System.out.print(result.id);
    System.exit(0);
  }
  
  private void searchRecipe() throws 
    IOException, 
    ClassNotFoundException, 
    SQLException
  {
    StringBuilder ingredient = new StringBuilder();
    int c;
    System.out.println("Search for a recipe");
    System.out.print("Enter a ingredient to searchh for: ");
    while( ( c = System.in.read() ) != 13 ) {
      ingredient.append((char)c);
    }
    
    ArrayList<Recipe> recipes = Recipe.searchByIngredient(ingredient.toString());
    for(Recipe rec : recipes) {
      System.out.print("Recipe: ");
      System.out.print(rec.name);
      System.out.print(", tab( ");
      System.out.print(rec.tab);
      System.out.println(")");
    }
  }

  public static void main(String[] args)
  {
    try {
      new RecipeIndex();
    } catch( ClassNotFoundException e ) {
      e.printStackTrace();
    } catch (IOException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    } catch ( InputMismatchException e ) {
      e.printStackTrace();
    } catch ( Exception e ) {
      e.printStackTrace();
    }
    
    
  }
  

}
