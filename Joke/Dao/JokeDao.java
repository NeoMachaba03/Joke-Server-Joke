package JokeServer.Joke.Dao;

import JokeServer.Joke.Model.Joke;

public interface JokeDao {

    void addJoke(Joke joke);
    void retrieveJokes();
    void updateJoke(int jokeID, String description);
    void deleteJoke(int jokeID);
}
