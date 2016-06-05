package recipeIndex.entities;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class Ingredient
{

  public String name;
  public int recipe;
  
  public Ingredient()
  {
    // TODO Auto-generated constructor stub
  }
  
  public static Ingredient CreateNew( Recipe recipe ) throws 
    ClassNotFoundException, 
    SQLException
  {
   
    Class.forName("org.sqlite.JDBC");
    
    Connection conn = DriverManager.getConnection("jdbc:sqlite:RecipeIndex.db");
    DatabaseMetaData stmt = conn.getMetaData();
    ResultSet rs = stmt.getTables(null, null, "%", null);
    boolean found = false;
    while( rs.next() ) {
      if(rs.getString(3).equals("ingredients")) {
        found = true;
      }
    }
    if ( !found ) {
      String createTableQuery = "CREATE TABLE ingredients ( `name` varchar ( 255 ),`recipe` int (64) );";
      Statement cstmt = conn.createStatement();
      cstmt.execute(createTableQuery);
    }
    Ingredient i = new Ingredient();
    System.out.println("Add ingredient");
    System.out.print("Name: ");
    Scanner in = new Scanner(System.in);
    while(in.hasNext()) {
      i.name = in.next();
      break;
    }
    i.recipe = recipe.id;
    i.save();
    
    return i;
  }

  private void save() throws 
    ClassNotFoundException,
    SQLException
  {
    Class.forName("org.sqlite.JDBC");
    
    Connection conn = DriverManager.getConnection("jdbc:sqlite:RecipeIndex.db");
    PreparedStatement stmt = conn.prepareStatement("INSERT INTO ingredients VALUES ( ?, ? )");
    stmt.setString(1, name);
    stmt.setInt(2, recipe);
    stmt.executeUpdate();
    
    
  }

}
