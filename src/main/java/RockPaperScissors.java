public enum RockPaperScissors {

    ROCK,
    PAPER,
    SCISSORS;

    RockPaperScissors() {
    }

    public int compareMoves(RockPaperScissors otherMove) {
        if (this == otherMove) {
            return 0;
        }
        switch (this) {
            case ROCK:
                return (otherMove == SCISSORS ? 1 : -1);
            case PAPER:
                return (otherMove == ROCK ? 1 : -1);
            case SCISSORS:
                return (otherMove == PAPER ? 1 : -1);
            default:
                throw new IllegalArgumentException();
        }
    }
}
