//Optimize imports is on, so we won't add imports for now.

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class GuessMovie {

    /*
    //OBJECT 1?
    Take in file
    Use "split" to separate lines into an array
    Use math.Random to select a movie to guess, store in variable = Movie
    Generate a user-displayed "hangman":
    Iterate through the movie variable and check if each character is an empty space
        if it is
            add an empty space to the hangman
        else
            add a dash/underscore to the hangman
        print the hangman


    //

    Create a wrongLetters array
    Create sentinelValue -> guesses for while loop
        While, guesses<10,
            Have the user guess a letter
            Iterate through the movie characters and check if (the character is an empty space)
                If that's true
                    do nothing
                else
                    check if(userLetter=movieCharacter).
                        If that's true,
                            find the index # where the character lives
                            Replace the dash at that index in hangman with the letter that the user guesses
                                if (hangman.equals(Movie),
                                    break the loop and end the program
                        Else
                            say, "sorry that letter is not in the movie name
                            add this letter to the wrongLetters array
                            print wrong letters so far

    */

    public static void main(String[] args) {
        //public: accessible by any other class
        //static: accessible at the class level, rather than the instance level. We don't have a constructor for the main method.
        //void: does not return anything.

        File movieList = new File(args[0]);
        try {
            Scanner scanner = new Scanner(movieList);
            ArrayList<String> movieArray = new ArrayList<String>();

            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                movieArray.add(line);
            }

            int random = (int) (Math.random() * movieArray.size());
            String movie = movieArray.get(random);
            ArrayList<String> hangmanArray = new ArrayList<String>();
            ArrayList<String> movieChar = new ArrayList<String>();


            for (int i = 0; i < movie.length(); i++) {
                //for (intialize counter | limit counter | iterate)
                if (Character.isLetter(movie.charAt(i))) {
                    //System.out.println("Character: " + movie.charAt(i));
                    hangmanArray.add("-");
                    char c = movie.charAt(i);
                    String cString = Character.toString(c);
                    String cStringLower = cString.toLowerCase();
                    movieChar.add(cStringLower);
                    //System.out.println(movieChar);
                } else {
                    //System.out.println("Not Character: " + movie.charAt(i));
                    hangmanArray.add(" ");
                    char c = movie.charAt(i);
                    String cString = Character.toString(c);
                    movieChar.add(cString);
                    //System.out.println(movieChar);
                }
                //System.out.println(hangmanArray);

            }

            ArrayList<String> joinedHangman = new ArrayList<String>();
            joinedHangman.addAll(hangmanArray);

            System.out.println(String.join("", hangmanArray));
            //System.out.println(movieChar);

            ArrayList<String> wrongLetters = new ArrayList<String>();
            Scanner user = new Scanner(System.in);
            int sentinel = 0;
            boolean win = false;

            while (sentinel < movie.length() * 2) {
                //user input guess a letter
                System.out.println("Guess a letter!");
                String guess = user.next();

                boolean correct = false;

                for (int i = 0; i < movieChar.size(); i++) {

                    if (movieChar.get(i).equals(":")) {
                        hangmanArray.set(i, ":");
                    } else if (movieChar.get(i).equals(guess)) {
                        hangmanArray.set(i, guess);
                        //joinedHangman.clear();
                        //joinedHangman.addAll(hangmanArray);
                        //System.out.println(hangmanArray);
                        String joined = String.join("", hangmanArray);
                        movieChar.set(i, "-");
                        System.out.println("You guessed correctly!");
                        System.out.println(joined);
                        correct = true;
                    }
                }

                if (!correct) {
                    System.out.println("You guessed wrong.");
                    wrongLetters.add(guess);
                    System.out.println("Wrong letters: " + wrongLetters);
                } 

                String joined = String.join("", hangmanArray);

                if (joined.equals(movie.toLowerCase())) {
                    win = true;
                    break;
                }

                sentinel += 1;

            }

            if (!win) {
                System.out.println("You're out of guesses!");
            } else {
                System.out.println("You've won!");


            }


        } catch (
                FileNotFoundException e) {
            e.printStackTrace();
            System.out.println("File not found.");
        }
    }


}
