import java.util.Random;

public class Computer implements Move{
    @Override
    public RockPaperScissors getMove() {
        RockPaperScissors[] moves = RockPaperScissors.values();
        Random random = new Random();
        int index = random.nextInt(moves.length);
        return moves[index];
    }
}
