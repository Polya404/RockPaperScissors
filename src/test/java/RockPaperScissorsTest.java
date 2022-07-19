import junit.framework.TestCase;
import org.junit.Assert;

public class RockPaperScissorsTest extends TestCase {

    public void testCompareMoves() {
        Assert.assertEquals(0,  RockPaperScissors.PAPER.compareMoves(RockPaperScissors.PAPER));
        Assert.assertEquals(1,  RockPaperScissors.PAPER.compareMoves(RockPaperScissors.ROCK));
        Assert.assertEquals(-1,  RockPaperScissors.PAPER.compareMoves(RockPaperScissors.SCISSORS));
    }
}