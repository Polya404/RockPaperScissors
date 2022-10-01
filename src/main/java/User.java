import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ResourceBundle;
import java.util.Scanner;

public class User implements Move {
    private static final Logger loggerDebug = LoggerFactory.getLogger("logger.debug");
    private static final Logger loggerResult = LoggerFactory.getLogger("logger.result");
    private String name;
    private Scanner in;
    ResourceBundle resourceBundle = ResourceBundle.getBundle("communicationWithPlayer", RunGame.locale);

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public User() {
        in = new Scanner(System.in);
    }

    /**
     * this method asks the player what move he chooses and if
     * such a move is possible, then returns it, if not, asks
     * to repeat the choice
     *
     * @return valid move
     */
    @Override
    public RockPaperScissors getMove() {
        System.out.println(resourceBundle.getString("move"));
        String userInput = in.nextLine();
        userInput = userInput.toLowerCase();
        String firstLetter = userInput;
        if (firstLetter.equals(resourceBundle.getString("paper")) || firstLetter.equals(resourceBundle.getString("scissors")) || firstLetter.equals(resourceBundle.getString("rock"))) {
            if (firstLetter.equals(resourceBundle.getString("paper"))) {
                return RockPaperScissors.PAPER;
            } else if (firstLetter.equals(resourceBundle.getString("scissors"))) {
                return RockPaperScissors.SCISSORS;
            } else if (firstLetter.equals(resourceBundle.getString("rock"))) {
                return RockPaperScissors.ROCK;
            }
        }
        loggerDebug.debug("Invalid data move");
        return getMove();
    }

}
