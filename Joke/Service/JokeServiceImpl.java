package JokeServer.Joke.Service;

import JokeServer.Joke.Dao.JokeDaoImpl;
import JokeServer.Joke.Model.Joke;
import JokeServer.JokeVerifier.Verifier.VerifierImpl;

import java.util.InputMismatchException;
import java.util.Scanner;

public class JokeServiceImpl implements JokeService{

    private JokeDaoImpl jokeDao = new JokeDaoImpl();
    private Scanner input = new Scanner(System.in);

    VerifierImpl verifier = new VerifierImpl();

    @Override
    public void addJoke() {
        while (true){
            try {
                System.out.println("\nEnter the joke: ");
                String joke = input.nextLine();
                System.out.print("\nEnter the Category ID: ");
                int ID = Integer.parseInt(input.nextLine());
                System.out.print("\nEnter your User ID: ");
                int userID = Integer.parseInt(input.nextLine());
                verifier.retrieveAndVerifyWords(joke);

                if (verifier.retrieveAndVerifyWords(joke) == true){
                    System.out.println("\nYour joke does not comply with our guidlines!!! Please try again!!");
                }else {
                    Joke joke1 = new Joke(0,joke,ID,userID);
                    jokeDao.addJoke(joke1);
                    break;
                }
            }catch (InputMismatchException e){
                System.out.println("\nInvalid entry!!! Please try again");
            }
        }
    }

    @Override
    public void retrieveJokes() {
        jokeDao.retrieveJokes();
    }

    public void retrieveAJoke(){
        while (true){
            try {
                System.out.print("\nEnter the joke ID: ");
                int id = Integer.parseInt(input.nextLine());

                jokeDao.retrieveAJoke(id);
                break;
            }catch (InputMismatchException e){
                System.out.println("\nInvalid entry!!! Please try again");
            }
        }

    }

    @Override
    public void updateJoke() {
        while (true){
            try {
                System.out.print("\nEnter the joke you want to update's ID: ");
                int id = Integer.parseInt(input.nextLine());

                System.out.println("\nEnter the updated joke: ");
                String joke = input.nextLine();
                verifier.retrieveAndVerifyWords(joke);

                if (verifier.retrieveAndVerifyWords(joke) == true){
                    System.out.println("\nYour joke does not comply with our guidlines!!! Please try again!!");
                }else {
                    jokeDao.updateJoke(id,joke);
                    break;
                }
                }catch (InputMismatchException e){
                System.out.println("\nInvalid entry!!! Please try again");
            }
        }
    }

    @Override
    public void deleteJoke() {
        while (true){
            try {
                System.out.print("\nEnter the joke ID: ");
                int id = Integer.parseInt(input.nextLine());

                jokeDao.deleteJoke(id);
                break;
            }catch (InputMismatchException e){
                System.out.println("\nInvalid entry!!! Please try again");
            }
        }
    }
}
