import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class RunGame {
    private static Scanner in = new Scanner(System.in);
    protected static int number = 0;

    public static void main(String[] args) throws IOException {
        Service game = new Service();
        System.out.println("Enter your name ...");
        game.getUser().setName(in.nextLine());
        try {
            System.out.println("How many time you want game ?");
            number = in.nextInt();
        } catch (InputMismatchException e) {
            System.out.println("Enter numbers");
        }
        do {
            if (number == 0) {
                break;
            }
            System.out.println("Run game ...");
            game.game();
            number--;
        } while (number != 0 && Service.playAgain());
        game.printGameStats();
    }
}
