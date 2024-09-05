import java.util.Random;
import java.util.Scanner;

public class NumberGuessingGame {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();
        boolean playAgain = true;
        int score = 0;

        System.out.println("Welcome to the Number Guessing Game!");

        while (playAgain) {
            int attempts = 0;
            int maxAttempts = 7;
            int minRange = 1;
            int maxRange = 100;
            int randomNumber = random.nextInt(maxRange - minRange + 1) + minRange;
            boolean hasGuessedCorrectly = false;

            System.out.println("I'm thinking of a number between " + minRange + " and " + maxRange + ".");
            System.out.println("You have " + maxAttempts + " attempts to guess the correct number.");

            while (attempts < maxAttempts && !hasGuessedCorrectly) {
                System.out.print("Enter your guess: ");
                int guess = scanner.nextInt();
                attempts++;

                if (guess == randomNumber) {
                    System.out.println("Congratulations! You guessed the correct number in " + attempts + " attempts.");
                    score += (maxAttempts - attempts + 1);
                    hasGuessedCorrectly = true;
                } else if (guess < randomNumber) {
                    System.out.println("Your guess is too low.");
                } else {
                    System.out.println("Your guess is too high.");
                }

                if (attempts == maxAttempts && !hasGuessedCorrectly) {
                    System.out.println("You've used all " + maxAttempts + " attempts. The correct number was " + randomNumber + ".");
                }
            }

            System.out.println("Your current score is: " + score);

            System.out.print("Would you like to play again? (yes/no): ");
            String response = scanner.next();

            if (!response.equalsIgnoreCase("yes")) {
                playAgain = false;
                System.out.println("Thanks for playing! Your final score is: " + score);
            }
        }

        scanner.close();
    }
}
