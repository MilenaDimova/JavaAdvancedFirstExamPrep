package FirstExamPreparation;

import java.util.Scanner;

public class Bee {
    static int beeRow;
    static int beeCol;
    static int bonusRow;
    static int bonusCol;
    static int pollinatedFlowers = 0;
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int size = Integer.parseInt(scanner.nextLine());
        char[][] matrix = new char[size][];

        for (int row = 0; row < size; row++) {
            String line = scanner.nextLine();
            matrix[row] = line.toCharArray();
            if (line.contains("B")) {
                beeRow = row;
                beeCol = line.indexOf("B");
            }
            if (line.contains("O")) {
                bonusRow = row;
                bonusCol = line.indexOf("O");
            }
        }

        String command = scanner.nextLine();
        while (!command.equals("End")) {
            matrix = move(matrix, command);
            if (!isInBounds(matrix, beeRow, beeCol)) {
                break;
            }
            if (beeRow == bonusRow && beeCol == bonusCol) {
                command = command;
            } else {
                command = scanner.nextLine();
            }
        }

        if (!isInBounds(matrix, beeRow, beeCol)) {
            System.out.println("The bee got lost!");
        }
        if (pollinatedFlowers >= 5) {
            System.out.printf("Great job, the bee manage to pollinate %d flowers!%n", pollinatedFlowers);
        } else {
            System.out.printf("The bee couldn't pollinate the flowers, she needed %d flowers more%n", 5 - pollinatedFlowers);
        }

        printMatrix(matrix);

    }

    private static char[][] move(char[][] matrix, String command) {
        if (command.equals("up")) {
            if (isInBounds(matrix, beeRow - 1, beeCol)) {
                matrix[beeRow][beeCol] = '.';
                beeRow--;
                if (checkForFlower(matrix)) {
                    matrix[beeRow][beeCol] = 'B';
                    pollinatedFlowers++;
                } else if (beeRow == bonusRow && beeCol == bonusCol) {
                    matrix[bonusRow][bonusCol] = '.';
                } else {
                    matrix[beeRow][beeCol] = 'B';
                }
            } else {
                matrix[beeRow][beeCol] = '.';
                beeRow--;
            }
        } else if (command.equals("down")) {
            if (isInBounds(matrix, beeRow + 1, beeCol)) {
                matrix[beeRow][beeCol] = '.';
                beeRow++;
                if (checkForFlower(matrix)) {
                    matrix[beeRow][beeCol] = 'B';
                    pollinatedFlowers++;
                } else if (beeRow == bonusRow && beeCol == bonusCol) {
                    matrix[bonusRow][bonusCol] = '.';
                } else {
                    matrix[beeRow][beeCol] = 'B';
                }
            } else {
                matrix[beeRow][beeCol] = '.';
                beeRow++;
            }
        } else if (command.equals("left")) {
            if (isInBounds(matrix, beeRow, beeCol - 1)) {
                matrix[beeRow][beeCol] = '.';
                beeCol--;
                if (checkForFlower(matrix)) {
                    matrix[beeRow][beeCol] = 'B';
                    pollinatedFlowers++;
                } else if (beeRow == bonusRow && beeCol == bonusCol) {
                    matrix[bonusRow][bonusCol] = '.';
                } else {
                    matrix[beeRow][beeCol] = 'B';
                }
            } else {
                matrix[beeRow][beeCol] = '.';
                beeCol--;
            }
        } else if (command.equals("right")) {
            if (isInBounds(matrix, beeRow, beeCol + 1)) {
                matrix[beeRow][beeCol] = '.';
                beeCol++;
                if (checkForFlower(matrix)) {
                    matrix[beeRow][beeCol] = 'B';
                    pollinatedFlowers++;
                } else if (beeRow == bonusRow && beeCol == bonusCol) {
                    matrix[bonusRow][bonusCol] = '.';
                } else {
                    matrix[beeRow][beeCol] = 'B';
                }
            } else {
                matrix[beeRow][beeCol] = '.';
                beeCol++;
            }
        }

        return matrix;
    }

    public static boolean isInBounds(char[][] matrix, int row, int col) {
        return row >= 0 && row < matrix.length && col >= 0 && col < matrix[row].length;
    }

    public static boolean checkForFlower(char[][] matrix) {
        return matrix[beeRow][beeCol] ==  'f';
    }

    private static void printMatrix(char[][] matrix) {
        for (char[] arr : matrix) {
            for (char symbol : arr) {
                System.out.print(symbol);
            }
            System.out.println();
        }
    }
}
