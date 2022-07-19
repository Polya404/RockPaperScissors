import java.util.Scanner;

public class User implements Move {
    private Scanner in;

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
        return getMove();
    }

}
