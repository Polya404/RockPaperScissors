import java.util.Scanner;

public class Service {
    private User user;
    private Computer computer;
    private int userScore;
    private int computerScore;
    private int numberOfGames;
    private static Scanner in = new Scanner(System.in);

    public User getUser() {
        return user;
    }

    public Service() {
        user = new User();
        computer = new Computer();
        userScore = 0;
        computerScore = 0;
        numberOfGames = 0;
    }

    public static boolean playAgain() {
        System.out.println("Do you want play again ? ... y/n");
        String userInput = in.nextLine();
        userInput = userInput.toLowerCase();
        return userInput.charAt(0) == 'y';
    }

    public void game() {
        RockPaperScissors userMove = user.getMove();
        RockPaperScissors computerMove = computer.getMove();
        System.out.println("Your choose " + userMove);
        System.out.println("Computer " + computerMove);
        int compareMoves = userMove.compareMoves(computerMove);
        switch (compareMoves) {
            case 0:
                System.out.println("Ничья");
                break;
            case 1:
                System.out.println(userMove + " beats " + computerMove + " win -> " + user.getName());
                userScore++;
                break;
            case -1:
                System.out.println(compareMoves + " beats " + userMove + "  loss -> " + user.getName());
                computerScore++;
                break;
        }
        numberOfGames++;

    }

    public void printGameStats() {
        int wins = userScore;
        int losses = computerScore;
        int ties = numberOfGames - userScore - computerScore;
        double percentageWon = (wins + (double) ties / 2) / numberOfGames;

        printDashes(60);
        System.out.println("+");

        System.out.printf("| %6s | %6s | %6s | %12s | %14s |\n", "WINS", "LOSSES", "TIES", "GAMES PLAYED", "PERCENTAGE WON");

        printDashes(60);
        System.out.println("+");

        System.out.printf("| %6d | %6d | %6d | %12d | %13.2f%% | \n", wins, losses, ties, numberOfGames, percentageWon * 100);

        printDashes(60);
        System.out.println("+");
    }

    private void printDashes(int numberOfDashes) {
        for (int i = 0; i < numberOfDashes; i++) {
            System.out.print("-");
        }
    }

}
