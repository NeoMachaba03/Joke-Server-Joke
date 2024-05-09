package JokeServer.Joke.Model;

public class Joke {

    private int jokeID;
    private String description;
    private int categoryID;
    private int userID;

    public Joke(int jokeID, String description, int categoryID, int userID) {
        this.jokeID = jokeID;
        this.description = description;
        this.categoryID = categoryID;
        this.userID = userID;
    }

    public int getJokeID() {
        return jokeID;
    }

    public void setJokeID(int jokeID) {
        this.jokeID = jokeID;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getCategoryID() {
        return categoryID;
    }

    public void setCategoryID(int categoryID) {
        this.categoryID = categoryID;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }
}

