import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class RunGame {
    private static Scanner in = new Scanner(System.in);
    protected static int number = 0;

    private static final Logger loggerDebug = LoggerFactory.getLogger("logger.debug");
    private static final Logger loggerResult = LoggerFactory.getLogger("logger.result");

    public static void main(String[] args) throws IOException {
        Service game = new Service();
        System.out.println("Enter your name ...");
        game.getUser().setName(in.nextLine());
        int num = 0;
        try {
            System.out.println("How many time you want game ?");
            number = in.nextInt();
            num = number;
            loggerResult.debug("Сколько игр выбрал игрок : " + number);
        } catch (InputMismatchException e) {
            System.out.println("Enter numbers");
            loggerResult.debug("Введено неверное значение колличества игр");
        }
        do {
            if (number == 0) {
                break;
            }
            System.out.println("Run game ...");
            game.game();
            number--;
            loggerResult.debug("Сколько игр осталось : " + number);
        } while (number != 0 && Service.playAgain());
        loggerResult.debug("Игр сыграно : " + num);
        game.printGameStats();
        game.writeFile();
        loggerResult.debug("Игрок вышел с игры");
        loggerResult.debug("===============================================");
    }
}
