package game;

import java.io.PrintStream;
import java.util.InputMismatchException;
import java.util.NoSuchElementException;
import java.util.Scanner;

/**
 * @author Georgiy Korneev (kgeorgiy@kgeorgiy.info)
 */
public class HumanPlayer implements Player {
    private final PrintStream out;
    private final Scanner in; // :NOTE: unused

    public HumanPlayer(final PrintStream out, final Scanner in) {
        this.out = out;
        this.in = in;
    }

    public HumanPlayer() {
        this(System.out, new Scanner(System.in));
    }

    @Override
    public Move move(final Position position, final Cell cell) {
        while (true) {
            out.println("Position");
            out.println(position);
            out.println(cell + "'s move");
            out.println("Enter row and column");
            int i;
            int j;
            while (true) {
                try (Scanner in = new Scanner(System.in)) {
                    try {
                        i = in.nextInt();
                        j = in.nextInt();
                        break;
                    } catch (InputMismatchException e) {
                        System.out.println("enter 2 numbers");
                    }
                } catch (NoSuchElementException e) {
                    return null;
                }
            }
            final Move move = new Move(i, j, cell);
            if (position.isValid(move)) {
                return move;
            }
            out.println("Move " + move + " is invalid");
        }
    }
}
