package MineSweeper;

import java.util.Random;

public class MineSweeperGen {
    private char[][] mineSweeper;
    private static final int SWEEPER_SIZE = 20;
    private static final Random rand = new Random();

    public MineSweeperGen() {
        mineSweeper = new char[SWEEPER_SIZE][SWEEPER_SIZE];
        for(int i = 0; i < SWEEPER_SIZE; i++) {
            for(int j = 0; j < SWEEPER_SIZE; j++) {
                mineSweeper[i][j] = ' ';
            }
        }
        createMines();
    }

    private void createMines() {
        for(int i = 0; i < SWEEPER_SIZE; i++) {
            for(int j = 0; j < SWEEPER_SIZE; j++) {
                if(rand.nextInt(10) < 2) {
                    mineSweeper[i][j] = '*';
                }
            }
        }
    }

    private void createNumbers() {
        for(int i = 0; i < SWEEPER_SIZE; i++) {
            for(int j = 0; j < SWEEPER_SIZE; j++) {
                if(mineSweeper[i][j] == ' ') {
                    
                }
            }
        }
    }

    public String toString() {
        StringBuilder res = new StringBuilder("+ ");

        res.append("- ".repeat(SWEEPER_SIZE));
        res.append("+ \n");

        for(int i = 0; i < SWEEPER_SIZE; i++) {
            res.append("| ");
            for(int j = 0; j < SWEEPER_SIZE; j++) {
                res.append(mineSweeper[i][j]);
                res.append(" ");
            }
            res.append("| \n");
        }
        res.append("+ ");
        res.append("- ".repeat(SWEEPER_SIZE));
        res.append("+ \n");

        return res.toString();
    }
}
