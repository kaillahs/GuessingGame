// Kaillah Selvaretnam
// CSE 145 1736
// Jeremiah Ramsey
// Lab 1
// This program allows the user to play Guessing Game. In this game, the program picks a random
// while the user tried to guess the number. The program guides the user by giving feedback by
// stating weather the target number is higher or lower than the user's guess.
import java.util.*;
import java.util.Random;

public class GuessingGame {
    public static void main(String[] args) {
        int max = 5;
        int min = 1;
        if (max <= min) {
            throw new IllegalArgumentException("Invalid bounds");
        }
        Random rand = new Random();
        Scanner console = new Scanner(System.in);
        char again = 'y';
        int totalGames = 0;
        int totalGuesses = 0;
        int bestGame = 1;
        System.out.println(gameRules(max, min));
        System.out.println();

        while (again == 'y') {
            int targetNum = rand.nextInt(max - min + 1) + min;
            System.out.println();
            System.out.println("I'm thinking of a number between " + min + " and " + max + "...");
            totalGames ++;
            Map<String, List<Integer>> stats = playGame(console, targetNum, totalGuesses);
            totalGuesses = stats.get("totalGuesses").get(0);
            bestGame = stats.get("guessNums").get(0);
            System.out.print("Do you want to play again? ");
            String answer = console.next().toLowerCase();
            again = answer.charAt(0);
        }
        reportStats(totalGames, totalGuesses, bestGame);
    }

    // This method states the rules of the game. The parameter of the method is an integer
    // representing the maximum of the range in which the target number can be as well as an
    // integer representing the minimum of the range in which the target number can be in.
    // The return is a String representing the rules of the game.
    public static String gameRules(int max, int min) {
        return("This program allows you to play a guessing game.\nI will think of a" +
                "number between " + min + " and\n" + max + " and will allow you to guess until\n" +
                "you get it. For " +
                "each guess, I will tell you\nwhether the right answer is higher or lower\n" +
                "than your guess.");
    }

    // This method prompts the user for a guess and gives feedback on weather the target number
    // is higher, lower, or if the user guessed correctly. The method also keeps track of the
    // players statistics. The parameters are a Scanner to allow for user input, an integer
    // representing the random target number, as well as an integer representing the users
    // current total guesses over the course of the program (not per individual game). The
    // return is a Map representing the users game statistics.
    public static Map<String, List<Integer>> playGame(Scanner console, int targetNum,
                                                      int totalGuesses) {
        Map<String, List<Integer>> stats = new HashMap<>();
        List<Integer> totalGuessList = new ArrayList<>();
        List<Integer> guessNumList = new ArrayList<>();
        int guessNum = 1;
        System.out.print("Your guess? ");
        int guess = console.nextInt();

        while (guess != targetNum) {
            if (guess > targetNum) {
                System.out.println("It's lower.");
            }

            if (guess < targetNum) {
                System.out.println("It's higher.");
            }

            System.out.print("Your guess? ");
            guess = console.nextInt();
            guessNum++;
        }

        if (guess == targetNum) {
            if (guessNum == 1) {
                System.out.println("You got it right in 1 guess");
            } else {
                System.out.println("You got it right in " + guessNum + " guesses");
            }

        }
        totalGuesses += guessNum;
        totalGuessList.add(0);
        totalGuessList.set(0,totalGuesses);
        guessNumList.add(guessNum);
        stats.put("totalGuesses", totalGuessList);
        stats.put("guessNums", guessNumList);

        return(stats);
    }

    // This method reports the game stats to the user after the user has indicated that they
    // want to stop playing the game. The parameters are three integers representing the total
    // number of games played, the total number of guesses, as well as the fewest guesses it
    // took the user to win.
    public static void reportStats(int totalGames, int totalGuesses, int bestGame) {
        System.out.println("Overall results: ");
        System.out.println("total games = " + totalGames);
        System.out.println("total guesses = " + totalGuesses);
        System.out.println("guesses/game = " + (double)totalGuesses/totalGames);
        System.out.println("best game = " + bestGame);
    }
}

