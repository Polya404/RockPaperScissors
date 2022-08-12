import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Scanner;

public class User implements Move {
    private static final Logger loggerDebug = LoggerFactory.getLogger("logger.debug");
    private static final Logger loggerResult = LoggerFactory.getLogger("logger.result");
    private String name;
    private Scanner in;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public User() {
        in = new Scanner(System.in);
    }

    @Override
    public RockPaperScissors getMove() {
        System.out.println("What do you choose: paper, scissors or rock ?");
        String userInput = in.nextLine();
        userInput = userInput.toLowerCase();
        char firstLetter = userInput.charAt(0);
        if(firstLetter == 'p' || firstLetter == 's' || firstLetter == 'r'){
            switch (firstLetter){
                case 'p': return RockPaperScissors.PAPER;
                case 's' : return RockPaperScissors.SCISSORS;
                case 'r' : return RockPaperScissors.ROCK;
            }
        }
        loggerDebug.debug("Неверное значение хода");
        return getMove();
    }

}
