import MineSweeper.MineSweeperGen;
import MineSweeper.Selection;

import java.util.Scanner;

import static MineSweeper.Selection.R;

public class Main {
    public static void main(String [] args) {
        MineSweeperGen gen = new MineSweeperGen();
        Scanner sc = new Scanner(System.in);
        Selection s = R;
        // System.out.println(gen.toString());
        // System.out.println(gen.getNumSpaces() + "\n");
        boolean isAlive = true;
        while(gen.getNumSpaces() > 0 && isAlive) {
            System.out.println(gen.display());

            System.out.println("Enter decision type ('R' or 'F'):");
            Selection sel = Selection.valueOf(sc.nextLine());

            int i = -1;
            int temp;
            while(i == -1) {
                System.out.println("Enter i coordinate:");
                temp = Integer.parseInt(sc.nextLine());
                if(temp >= 0 && temp < MineSweeperGen.SWEEPER_SIZE)
                    i = temp;
                else
                    System.out.println("ERROR -> i coordinates must be between 0 and " + (MineSweeperGen.SWEEPER_SIZE - 1));
            }

            int j = -1;
            while(j == -1) {
                System.out.println("Enter j coordinate:");
                temp = Integer.parseInt(sc.nextLine());
                if(temp >= 0 && temp < MineSweeperGen.SWEEPER_SIZE)
                    j = temp;
                else
                    System.out.println("ERROR -> j coordinates must be between 0 and " + (MineSweeperGen.SWEEPER_SIZE - 1));
            }
            isAlive = gen.nextAction(sel, i, j);
        }
        if(gen.getNumSpaces() <= 0) {
            System.out.println("Congratulations! You win!\n");
        }
        System.out.println("Your game finished like this:");
        System.out.println(gen.display());
        System.out.println("The minesweeper solution:");
        System.out.println(gen);
        // gen.nextAction(s, 10, 10);
        // System.out.println(gen.display());
    }
}
