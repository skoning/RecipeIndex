package recipeIndex.entities;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;

import ui.AddRecipe;

public final class Recipe
{

  public Recipe()
  {
    // TODO Auto-generated constructor stub
  }
  
  public int id;
  public String name;
  public int tab;

  public static Recipe CreateNew( String name, int tab, AddRecipe ui ) throws ClassNotFoundException, SQLException, IOException {
    if(null == ui) {
      return CreateNew(name, tab);
    }
    
    
    return null;
  }
  /**
   * @param name
   * @throws ClassNotFoundException
   * @throws IOException 
   * @throws Exception
   */
  public static Recipe CreateNew( String name, int tab ) throws 
    ClassNotFoundException,
    SQLException, 
    IOException
  {
    Class.forName("org.sqlite.JDBC");
    
    Connection conn = DriverManager.getConnection("jdbc:sqlite:RecipeIndex.db");
    DatabaseMetaData stmt = conn.getMetaData();
    ResultSet rs = stmt.getTables(null, null, "%", null);
    boolean found = false;
    while( rs.next() ) {
      if(rs.getString(3).equals("recipes")) {
        found = true;
      }
    }
    if ( !found ) {
      String createTableQuery = "CREATE TABLE recipes ( `name` varchar ( 255 ),`tab` tinyint(1) );";
      Statement cstmt = conn.createStatement();
      cstmt.execute(createTableQuery);
    }

    String query = "INSERT INTO recipes VALUES (?, ?);";
    PreparedStatement pstmt = conn.prepareStatement(query);
    pstmt.setString(1, name);
    pstmt.setInt(2,  tab);
    pstmt.executeUpdate();
    rs = pstmt.getGeneratedKeys();
    rs.next();
    int id = rs.getInt(1);
    Recipe recipe = new Recipe();
    recipe.id = id;
    recipe.name = name;
    recipe.tab = tab;
    Ingredient.CreateNew(recipe);
    extra:
      while( true ) {
        System.out.print("Insert extra ingredient? (yes/ja/1) ");
        String result;
        int  c;
        StringBuilder tempResult = new StringBuilder();
        while( ( c = System.in.read() ) != 13){
          tempResult.append((char)c);
        }
        result = tempResult.toString();
        
        switch(result.toLowerCase()) {
          case "yes":
          case "y":
          case "1":
          case "ja":
          case "j":
            Ingredient.CreateNew(recipe);
          default:
            break extra;
        }
      }
    return recipe;
  }
  
  public static Recipe get(int id) throws 
    ClassNotFoundException, 
    SQLException
  {
    Class.forName("org.sqlite.JDBC");
    
    Connection conn = DriverManager.getConnection("jdbc:sqlite:RecipeIndex.db");
    
    PreparedStatement stmt = conn.prepareStatement("SELECT rowid, * FROM recipes WHERE rowid = ?;");
    stmt.setInt(1, id);
    
    ResultSet rs = stmt.executeQuery();
    rs.next();
    Recipe result = new Recipe();
    result.id = rs.getInt("rowid");
    result.name = rs.getString("name");
    result.tab = rs.getInt("tab");
    
    return result;
  }
  public static ArrayList<Recipe> searchByIngredient(String ingredient) throws 
    ClassNotFoundException, 
    SQLException
  {
    Class.forName("org.sqlite.JDBC");
    
    Connection conn = DriverManager.getConnection("jdbc:sqlite:RecipeIndex.db");
    ingredient = "%" + ingredient + "%";
    
    PreparedStatement stmt = conn.prepareStatement("SELECT recipe FROM ingredients WHERE name LIKE ?");
    stmt.setString(1, ingredient);
    
    ArrayList<Recipe> recipes = new ArrayList<Recipe>();
    
    ResultSet rs = stmt.executeQuery();
    while(rs.next()) {
      recipes.add(Recipe.get(rs.getInt("recipe")));
    }
    
    
    return makeListUnique(recipes);
  }
  
  private static ArrayList<Recipe> makeListUnique(ArrayList<Recipe> recipes)
  {
    ArrayList<Recipe> result = new ArrayList<Recipe>();
    for(Recipe rec : recipes) {
      if( !result.contains(rec)) {
        result.add(rec);
      }
    }
    return result;
  }
}
