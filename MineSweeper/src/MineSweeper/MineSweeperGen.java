package MineSweeper;

import java.util.Random;

import static MineSweeper.Selection.F;
import static MineSweeper.Selection.R;

public class MineSweeperGen {
    public static final int SWEEPER_SIZE = 5;
    private static final Random rand = new Random();
    private char[][] mineSweeper;
    private char[][] displaySweeper;
    private int numSpaces = 0;

    public MineSweeperGen() {
        mineSweeper = new char[SWEEPER_SIZE][SWEEPER_SIZE];
        displaySweeper = new char[SWEEPER_SIZE][SWEEPER_SIZE];
        for(int i = 0; i < SWEEPER_SIZE; i++) {
            for(int j = 0; j < SWEEPER_SIZE; j++) {
                mineSweeper[i][j] = ' ';
                displaySweeper[i][j] = ' ';
            }
        }

        createMines();
        createNumbers();
    }

    private void createMines() {
        for(int i = 0; i < SWEEPER_SIZE; i++) {
            for(int j = 0; j < SWEEPER_SIZE; j++) {
                if(rand.nextInt(100) < 14) {
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
                    mineSweeper[i][j] = ((nearbyMines(i, j) == '0') ? ' ' : nearbyMines(i ,j));
                }
            }
        }
    }

    private char nearbyMines(int i, int j) {
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


    public boolean nextAction(Selection s, int i, int j) {
        if(s == F) {
            if(displaySweeper[i][j] == ' ') {
                displaySweeper[i][j] = 'F';
            } else if(displaySweeper[i][j] == 'F') {
                displaySweeper[i][j] = ' ';
            } else {
                System.out.println("ERROR -> Usage for F flag: in spaces (' ') or in Fs ('F').");
            }
        } else if(s == R) {
            if(displaySweeper[i][j] == ' ') {
                if(mineSweeper[i][j] == ' ') {
                    displaySweeper[i][j] = '/';
                    decreaseNumSpaces();
                    revealAdjacent(i, j);
                } else if(isANumber(i, j)) {
                    displaySweeper[i][j] = mineSweeper[i][j];
                    decreaseNumSpaces();
                } else if(mineSweeper[i][j] == '*') {
                    System.out.println("GAME OVER! Mine exploded in position i: " + i + " j: " + j);
                    return false;
                }
            }
        }
        return true;
    }

    private boolean isANumber(int i, int j) {
        return mineSweeper[i][j] == '1' | mineSweeper[i][j] == '2' | mineSweeper[i][j] == '3' |
                mineSweeper[i][j] == '4' | mineSweeper[i][j] == '5' | mineSweeper[i][j] == '6' |
                mineSweeper[i][j] == '7' | mineSweeper[i][j] == '8';
    }

    private void revealAdjacent(int i, int j) {
        if(i != 0) {
            if(mineSweeper[i-1][j] == ' ' | isANumber(i - 1, j)) {
                nextAction(R, i - 1, j);
            }
            if(j != 0) {
                if(mineSweeper[i-1][j-1] == ' '  | isANumber(i - 1, j - 1)) {
                    nextAction(R, i - 1, j - 1);
                }
            }
            if(j != SWEEPER_SIZE - 1) {
                if(mineSweeper[i-1][j+1] == ' '  | isANumber(i - 1, j + 1)) {
                    nextAction(R, i - 1, j + 1);
                }
            }
        }
        if (j != 0) {
            if(mineSweeper[i][j-1] == ' '  | isANumber(i, j - 1)) {
                nextAction(R, i, j - 1);
            }
            if(i != SWEEPER_SIZE - 1) {
                if(mineSweeper[i+1][j-1] == ' '  | isANumber(i + 1, j - 1)) {
                    nextAction(R, i + 1, j - 1);
                }
            }
        }
        if (i != SWEEPER_SIZE - 1) {
            if(mineSweeper[i+1][j] == ' '  | isANumber(i + 1, j)) {
                nextAction(R, i + 1, j);
            }
            if(j != SWEEPER_SIZE - 1) {
                if(mineSweeper[i+1][j+1] == ' '  | isANumber(i + 1, j + 1)) {
                    nextAction(R, i + 1, j + 1);
                }
            }
        }
        if (j != SWEEPER_SIZE - 1) {
            if(mineSweeper[i][j+1] == ' '  | isANumber(i, j + 1)) {
                nextAction(R, i, j + 1);
            }
        }
    }

    private void decreaseNumSpaces() {
        --numSpaces;
    }

    public int getNumSpaces() {
        return numSpaces;
    }

    public String display() {
        StringBuilder res = new StringBuilder("+ ");

        res.append("- ".repeat(SWEEPER_SIZE));
        res.append("+ \n");

        for(int i = 0; i < SWEEPER_SIZE; i++) {
            res.append("| ");
            for(int j = 0; j < SWEEPER_SIZE; j++) {
                res.append(displaySweeper[i][j]);
                res.append(" ");
            }
            res.append("| \n");
        }
        res.append("+ ");
        res.append("- ".repeat(SWEEPER_SIZE));
        res.append("+ \n");

        return res.toString();
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
