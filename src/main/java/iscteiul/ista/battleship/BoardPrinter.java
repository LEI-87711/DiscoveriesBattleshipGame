package iscteiul.ista.battleship;

import java.util.List;

public class BoardPrinter {

    public static void print(List<IPosition> positions, char marker, String title) {
        int size = Fleet.BOARD_SIZE;

        System.out.println("\n=== " + title + " ===");

        System.out.print("   ");
        for (int c = 0; c < size; c++)
            System.out.print(c + " ");
        System.out.println();

        char[][] map = new char[size][size];
        for (int r = 0; r < size; r++)
            for (int c = 0; c < size; c++)
                map[r][c] = '.';

        for (IPosition pos : positions)
            map[pos.getRow()][pos.getColumn()] = marker;

        for (int r = 0; r < size; r++) {
            System.out.print(r + "  ");
            for (int c = 0; c < size; c++) {
                System.out.print(map[r][c] + " ");
            }
            System.out.println();
        }
    }

    public static void printPlayerBoard(IFleet fleet) {
        int size = Fleet.BOARD_SIZE;

        System.out.println("\n=== O MEU TABULEIRO ===");
        System.out.print("   ");
        for (int c = 0; c < size; c++)
            System.out.print(c + " ");
        System.out.println();

        for (int r = 0; r < size; r++) {
            System.out.print(r + "  ");
            for (int c = 0; c < size; c++) {
                IPosition p = new Position(r, c);
                if (fleet.shipAt(p) != null) {
                    if (p.isHit()) System.out.print("X ");
                    else System.out.print("# ");
                } else if (p.isHit()) {
                    System.out.print("O ");
                } else {
                    System.out.print(". ");
                }
            }
            System.out.println();
        }
    }
}