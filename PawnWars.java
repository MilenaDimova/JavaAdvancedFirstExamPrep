package FirstExamPreparation;

import java.util.Scanner;

public class PawnWars {
    static int whiteRow;
    static int whiteCol;
    static int blackRow;
    static int blackCol;
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int size = 8;
        char[][] matrix = new char[size][];

        for (int row = 0; row < size; row++) {
            String line = scanner.nextLine();
            matrix[row] = line.toCharArray();
            if (line.contains("w")) {
                whiteRow = row;
                whiteCol = line.indexOf("w");
            }
            if (line.contains("b")) {
                blackRow = row;
                blackCol = line.indexOf("b");
            }
        }

        boolean whiteWin = false;
        boolean blackWin = false;
        while (whiteRow > 0 && blackRow < matrix.length - 1) {
            if (checkIsInBounds(matrix, whiteRow - 1, whiteCol - 1)) {
                if (matrix[whiteRow - 1][whiteCol - 1] == 'b') {
                    whiteRow--;
                    whiteCol--;
                    whiteWin = true;
                    break;
                }
            }
            if (checkIsInBounds(matrix, whiteRow - 1, whiteCol + 1)) {
                if (matrix[whiteRow - 1][whiteCol + 1] == 'b') {
                    whiteRow--;
                    whiteCol++;
                    whiteWin = true;
                    break;
                }
            }
            matrix[whiteRow][whiteCol] = '-';
            whiteRow--;
            matrix[whiteRow][whiteCol] = 'w';

            if (checkIsInBounds(matrix, blackRow + 1, blackCol - 1)) {
                if (matrix[blackRow + 1][blackCol - 1] == 'w') {
                    blackRow++;
                    blackCol--;
                    blackWin = true;
                    break;
                }
            }
            if (checkIsInBounds(matrix, blackRow + 1, blackCol + 1)) {
                if (matrix[blackRow + 1][blackCol + 1] == 'b') {
                    blackRow++;
                    blackCol++;
                    blackWin = true;
                    break;
                }
            }
            matrix[blackRow][blackCol] = '-';
            blackRow++;
            matrix[blackRow][blackCol] = 'b';
        }

        if (whiteWin) {
            System.out.printf("Game over! White capture on %c%d.%n", (char)(97 + whiteCol), 8 - whiteRow);
        } else if (blackWin) {
            System.out.printf("Game over! Black capture on %c%d.%n", (char)(blackCol + 97), 8 - blackRow);
        } else if (whiteRow == 0) {
            System.out.printf("Game over! White pawn is promoted to a queen at %c%d.%n", (char)(97 + whiteCol), 8 - whiteRow);
        } else if (blackRow == matrix.length - 1) {
            System.out.printf("Game over! Black pawn is promoted to a queen at %c%d.%n", (char)(blackCol + 97), 8 - blackRow);
        }


    }

    public static boolean checkIsInBounds(char[][] matrix, int row, int col) {
        return row >= 0 && row < matrix.length && col >= 0 && col < matrix[row].length;
    }
}
