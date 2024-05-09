package JokeServer.Joke.Dao;

import JokeServer.Database.DBConfig;
import JokeServer.Joke.Model.Joke;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class JokeDaoImpl extends DBConfig implements JokeDao{

    private Connection c = getCon();

    @Override
    public void addJoke(Joke joke) {
        try (PreparedStatement ps = c.prepareStatement("INSERT INTO jokes(Description, CategoryID, UserID) " +
                "VALUES(?, ?, ?)")) {

            ps.setString(1, joke.getDescription());
            ps.setInt(2, joke.getCategoryID());
            ps.setInt(3, joke.getUserID());

            int rowsAffected = ps.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("\nJoke added");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        try (PreparedStatement ps = c.prepareStatement("SELECT jokes.JokeID, jokes.Description," +
                " categories.CategoryName, users.Name FROM " +
                "((jokes INNER JOIN categories ON jokes.CategoryID = categories.CategoryID)" +
                "INNER JOIN users ON jokes.UserID = users.UserID)");
             PreparedStatement insertStatement = c.prepareStatement( "INSERT INTO joke_category_user " +
                     "(JokeID, Description, CategoryName, UserName) VALUES (?, ?, ?, ?)");
             ResultSet rs = ps.executeQuery()){

            while (rs.next()) {
                int jokeID = rs.getInt("JokeID");
                String description = rs.getString("Description");
                String categoryName = rs.getString("CategoryName");
                String userName = rs.getString("Name");

                insertStatement.setInt(1, jokeID);
                insertStatement.setString(2, description);
                insertStatement.setString(3, categoryName);
                insertStatement.setString(4, userName);

                insertStatement.executeUpdate();
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void retrieveJokes() {
        ResultSet rs = null;

        try (PreparedStatement ps = c.prepareStatement("SELECT * FROM joke_category_user")){
            rs = ps.executeQuery();

            System.out.println("\nJokes: ");
            while (rs.next()){
                int jokeID = rs.getInt("JokeID");
                String description = rs.getString("Description");
                String categoryName = rs.getString("CategoryName");
                String userName = rs.getString("Name");

                System.out.println("\nJoke ID: " + jokeID + "\tDescription: " + description +
                        "\tCategory Name: " + categoryName + "\tUser name: " + userName);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally{
            if(rs!=null){
                try {
                    rs.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public void retrieveAJoke(int jokeID){
        ResultSet rs = null;

        try (PreparedStatement ps = c.prepareStatement("SELECT * FROM joke_category_user WHERE JokeID = ?")){

            ps.setInt(1,jokeID);
            rs = ps.executeQuery();

            System.out.println("\nJokes: ");
            while (rs.next()){
                int JokeID = rs.getInt("JokeID");
                String description = rs.getString("Description");
                String categoryName = rs.getString("CategoryName");
                String userName = rs.getString("UserName");

                System.out.println("\nJoke ID: " + JokeID + "\tDescription: " + description + "\tCategory: " + categoryName +
                        "\tCreator: " + userName);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally{
            if(rs!=null){
                try {
                    rs.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @Override
    public void updateJoke(int jokeID, String description) {
        try (PreparedStatement ps = c.prepareStatement("UPDATE joke_category_user SET Description = ? WHERE JokeID = ?");
             PreparedStatement update = c.prepareStatement("UPDATE jokes SET Description = ? WHERE JokeID = ?")){

            ps.setString(1,description);
            ps.setInt(2,jokeID);

            update.setString(1,description);
            update.setInt(2,jokeID);

            int rowsAffected = ps.executeUpdate();
            int rowsAffected2 = update.executeUpdate();

            if (rowsAffected > 0 && rowsAffected2 > 0) {
                System.out.println("\nJoke has successfully been updated!!!");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteJoke(int jokeID) {
        try (PreparedStatement ps = c.prepareStatement("DELETE FROM jokes WHERE JokeID = ?");
             PreparedStatement delete = c.prepareStatement("DELETE FROM joke_category_user WHERE JokeID = ?")) {

            ps.setInt(1,jokeID);
            delete.setInt(2,jokeID);

            int rowsAffected = ps.executeUpdate();
            int rowsAffected2 = delete.executeUpdate();
            if (rowsAffected > 0 && rowsAffected2 > 0) {
                System.out.println("\nJoke has successfully been deleted!!!");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
