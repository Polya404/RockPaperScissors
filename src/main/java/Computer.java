import java.util.Random;

public class Computer implements Move {
    /**
     * this method returns the result of the computer's
     * move, choosing a random move from the rock-paper-scissors enum
     *
     * @return one of the values from the rock-paper-scissors enum
     */
    @Override
    public RockPaperScissors getMove() {
        RockPaperScissors[] moves = RockPaperScissors.values();
        Random random = new Random();
        int index = random.nextInt(moves.length);
        return moves[index];
    }
}
