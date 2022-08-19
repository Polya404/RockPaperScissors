import org.slf4j.LoggerFactory;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.*;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Scanner;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Service {
    private User user;
    private Computer computer;
    private int userScore;
    private int computerScore;
    private int numberOfGames;
    private static Scanner in = new Scanner(System.in);
    private static List<String> info = new ArrayList<>();
    static ResourceBundle resourceBundle = ResourceBundle.getBundle("communicationWithPlayer", RunGame.locale);

    private static final Logger loggerDebug = LoggerFactory.getLogger("logger.debug");
    private static final Logger loggerResult = LoggerFactory.getLogger("logger.result");


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
        System.out.println(resourceBundle.getString("playAgain")); //playAgain
        String userInput = in.nextLine();
        userInput = userInput.toLowerCase();
        return userInput.equals(resourceBundle.getString("yes"));//yes
    }

    public void game() throws IOException {
        loggerResult.debug("===============================================");
        loggerResult.debug("Start game");
        loggerResult.debug("Game statistic for gamer : " + user.getName());
        RockPaperScissors userMove = user.getMove();
        RockPaperScissors computerMove = computer.getMove();

        System.out.println(resourceBundle.getString("choose") + " " + resourceBundle.getString(userMove.toString())); //choose
        System.out.println(resourceBundle.getString("computer") + " " + resourceBundle.getString(computerMove.toString())); //computer

        int compareMoves = userMove.compareMoves(computerMove);
        switch (compareMoves) {
            case 0 -> {
                info.add("USER MOVE : " + userMove + " " + "COMPUTER MOVE : " + computerMove + " -> TIES" + "\n");
                System.out.println(resourceBundle.getString("ties")); //ties
                loggerResult.debug("User move : " + userMove);
                loggerResult.debug("Computer move : " + computerMove);
            }
            case 1 -> {
                info.add(userMove + " beats " + computerMove + " win -> " + user.getName() + "\n");
                System.out.println(resourceBundle.getString(userMove.toString()) + " " + resourceBundle.getString("beats") + " " + resourceBundle.getString(computerMove.toString()) + " " + resourceBundle.getString("win") + " -> " + user.getName()); //beats, win
                loggerResult.debug("User move : " + userMove);
                loggerResult.debug("Computer move : " + computerMove);
                userScore++;
            }
            case -1 -> {
                info.add(computerMove + " beats " + userMove + "  loss -> " + user.getName() + "\n");
                System.out.println(resourceBundle.getString(computerMove.toString()) + " " + resourceBundle.getString("beats") + " " + resourceBundle.getString(userMove.toString()) + " " + resourceBundle.getString("loss") + " -> " + user.getName()); //beats, loss
                loggerResult.debug("User move : " + userMove);
                loggerResult.debug("Computer move : " + computerMove);
                computerScore++;
            }
        }
        numberOfGames++;
    }

    public void printGameStats() throws IOException {
        int wins = userScore;
        int losses = computerScore;
        int ties = numberOfGames - userScore - computerScore;
        double percentageWon = (wins + (double) ties / 2) / numberOfGames;
        info.add("NAMES : " + user.getName() + "\n" + "WINS : " + wins + "\n" + "LOSSES : " + losses + "\n" + "TIES : " + ties + "\n" + "GAMES PLAYED : " + numberOfGames + "\n" + "PERCENTAGE WON : " + percentageWon * 100 + "\n" + "\n" + "\n" + "\n");
        loggerResult.debug("Wins : " + wins);
        loggerResult.debug("Losses : " + losses);
        loggerResult.debug("Ties : " + ties);
        loggerResult.debug("Percent wins : " + percentageWon * 100);

        printDashes(60);
        System.out.println("+");


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

    public void writeFile() throws IOException {
        Path pathDir = FileSystems.getDefault().getPath("").toAbsolutePath();
        String filename = "gameStatistic.log";
        String s = pathDir.toAbsolutePath().toString();
        File file = new File(s, File.separator.concat(filename));

        if (file.exists()) {
            for (String str : info) {
                Files.write(Path.of(s + File.separator.concat(filename)), str.getBytes(), StandardOpenOption.APPEND);
            }
        } else {
            file.createNewFile();
            for (String str : info) {
                Files.write(Path.of(s + File.separator.concat(filename)), str.getBytes(), StandardOpenOption.APPEND);
            }
        }

    }

}
