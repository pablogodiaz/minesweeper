import MineSweeper.MineSweeperGen;

public class Main {
    public static void main(String [] args) {
        MineSweeperGen gen = new MineSweeperGen();
        System.out.println(gen.toString());
        System.out.println(gen.getNumSpaces() + "\n");
        System.out.println(gen.display());
    }
}
