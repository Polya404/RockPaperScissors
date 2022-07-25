import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Scanner;

public class Service {
    private User user;
    private Computer computer;
    private int userScore;
    private int computerScore;
    private int numberOfGames;
    private static Scanner in = new Scanner(System.in);

    Path pathDir = Paths.get("src\\main\\java");
    String filename = "gameStatistic.txt";
    String s = pathDir.toAbsolutePath().toString();

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

    public void game() throws IOException {
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

        if (Files.exists(Path.of(s + File.separator.concat(filename)))) {
            Files.write(Path.of(s + File.separator.concat(filename)), String.valueOf("USER MOVE : " + userMove + "\n").getBytes(), StandardOpenOption.APPEND);
            Files.write(Path.of(s + File.separator.concat(filename)), String.valueOf("COMPUTER MOVE : " + computerMove + "\n").getBytes(), StandardOpenOption.APPEND);
        } else {
            File file = new File(s, File.separator.concat(filename));
            FileWriter fileWriter = new FileWriter(file);
            fileWriter.write(String.valueOf("USER MOVE : " + userMove + "\n"));
            fileWriter.write(String.valueOf("COMPUTER MOVE : " + computerMove + "\n"));
        }
        numberOfGames++;
    }

    public void printGameStats() throws IOException {
        int wins = userScore;
        int losses = computerScore;
        int ties = numberOfGames - userScore - computerScore;
        double percentageWon = (wins + (double) ties / 2) / numberOfGames;
        String info = ("NAMES : " + user.getName() + "\n" + "WINS : " + +wins + "\n" + "LOSSES : " + losses + "\n" + "TIES : " + ties + "\n" + "GAMES PLAYED : " + numberOfGames + "\n" + "PERCENTAGE WON : " + percentageWon * 100 + "\n" + "\n" + "\n" + "\n");

        printDashes(60);
        System.out.println("+");


        if (Files.exists(Path.of(s + File.separator.concat(filename)))) {
            Files.write(Path.of(s + File.separator.concat(filename)), info.getBytes(), StandardOpenOption.APPEND);
        } else {
            File file = new File(String.valueOf(pathDir), File.separator.concat(filename));
            FileWriter fileWriter = new FileWriter(file);
            fileWriter.write(info);
            fileWriter.close();
        }


        System.out.printf("| %8s | %6s | %6s | %6s | %12s | %14s |\n", "NAMES", "WINS", "LOSSES", "TIES", "GAMES PLAYED", "PERCENTAGE WON");

        printDashes(60);
        System.out.println("+");

        System.out.printf("| %8s | %6d | %6d | %6d | %12d | %13.2f%% | \n", user.getName(), wins, losses, ties, numberOfGames, percentageWon * 100);

        printDashes(60);
        System.out.println("+");
    }

    private void printDashes(int numberOfDashes) {
        for (int i = 0; i < numberOfDashes; i++) {
            System.out.print("-");
        }
    }

}
