import java.util.Scanner;

public class RunGame {
    private static Scanner in = new Scanner(System.in);
    protected static int number;

    public static void main(String[] args) {
        Service game = new Service();
        System.out.println("How many time you want game ?");
        number = in.nextInt();
        do {
            System.out.println("Run game ...");
            game.game();
            number--;
        } while (number > 0 && Service.playAgain());
        game.printGameStats();
    }
}
