public enum RockPaperScissors {

    ROCK,
    PAPER,
    SCISSORS;

    RockPaperScissors() {
    }

    /**
     * this method compares two moves and returns an integer value
     *
     * @param otherMove one of the values from enum rock paper scissors
     * @return int value
     */
    public int compareMoves(RockPaperScissors otherMove) {
        if (this == otherMove) {
            return 0;
        }
        return switch (this) {
            case ROCK -> (otherMove == SCISSORS ? 1 : -1);
            case PAPER -> (otherMove == ROCK ? 1 : -1);
            case SCISSORS -> (otherMove == PAPER ? 1 : -1);
            default -> throw new IllegalArgumentException();
        };
    }
}
