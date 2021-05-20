package MineSweeper;

import java.util.Random;

import static MineSweeper.Selection.F;

public class MineSweeperGen {
    private static final int SWEEPER_SIZE = 20;
    private static final Random rand = new Random();
    private char[][] mineSweeper;
    private int numSpaces = 0;

    public MineSweeperGen() {
        mineSweeper = new char[SWEEPER_SIZE][SWEEPER_SIZE];
        for(int i = 0; i < SWEEPER_SIZE; i++) {
            for(int j = 0; j < SWEEPER_SIZE; j++) {
                mineSweeper[i][j] = ' ';
            }
        }
        createMines();
        createNumbers();
    }

    private void createMines() {
        for(int i = 0; i < SWEEPER_SIZE; i++) {
            for(int j = 0; j < SWEEPER_SIZE; j++) {
                if(rand.nextInt(10) < 2) {
                    mineSweeper[i][j] = '*';
                } else {
                    ++numSpaces;
                }
            }
        }
    }

    private void createNumbers() {
        for(int i = 0; i < SWEEPER_SIZE; i++) {
            for(int j = 0; j < SWEEPER_SIZE; j++) {
                if(mineSweeper[i][j] == ' ') {
                    mineSweeper[i][j] = ((nearbyMines(mineSweeper[i][j], i, j) == '0') ? ' ' : nearbyMines(mineSweeper[i][j], i ,j));
                }
            }
        }
    }

    private char nearbyMines(char c, int i, int j) {
        int res = 0;
        if(i != 0) {
            if(mineSweeper[i-1][j] == '*') {
                ++res;
            }
            if(j != 0) {
                if(mineSweeper[i-1][j-1] == '*') {
                    ++res;
                }
            }
            if(j != SWEEPER_SIZE - 1) {
                if(mineSweeper[i-1][j+1] == '*') {
                    ++res;
                }
            }
        }
        if (j != 0) {
            if(mineSweeper[i][j-1] == '*') {
                ++res;
            }
            if(i != SWEEPER_SIZE - 1) {
                if(mineSweeper[i+1][j-1] == '*') {
                    ++res;
                }
            }
        }
        if (i != SWEEPER_SIZE - 1) {
            if(mineSweeper[i+1][j] == '*') {
                ++res;
            }
            if(j != SWEEPER_SIZE - 1) {
                if(mineSweeper[i+1][j+1] == '*') {
                    ++res;
                }
            }
        }
        if (j != SWEEPER_SIZE - 1) {
            if(mineSweeper[i][j+1] == '*') {
                ++res;
            }
        }
        return (char) (res + '0');
    }

    public void nextAction(Selection s, int i, int j) {
        if(s = F & [i][j] != ' ') {

        }
    }

    public void decreaseNumSpaces() {
        --numSpaces;
    }

    public int getNumSpaces() {
        return numSpaces;
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
