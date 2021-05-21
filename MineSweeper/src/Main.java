import MineSweeper.MineSweeperGen;
import MineSweeper.Selection;

import static MineSweeper.Selection.R;

public class Main {
    public static void main(String [] args) {
        MineSweeperGen gen = new MineSweeperGen();
        Selection s = R;
        System.out.println(gen.toString());
        System.out.println(gen.getNumSpaces() + "\n");
        System.out.println(gen.display());
        gen.nextAction(s, 10, 10);
        System.out.println(gen.display());
    }
}
