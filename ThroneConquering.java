package FirstExamPreparation;

import java.util.Scanner;

public class ThroneConquering {
    static int energy;
    static int parisRow;
    static int parisCol;
    static int helenRow;
    static int helenCol;
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        energy = Integer.parseInt(scanner.nextLine());
        int rows = Integer.parseInt(scanner.nextLine());
        char[][] matrix = new char[rows][];
        for (int row = 0; row < rows; row++) {
            String line = scanner.nextLine();
            matrix[row] = line.toCharArray();
            if (line.contains("P")) {
                parisRow = row;
                parisCol = line.indexOf("P");
            }
            if (line.contains("H")) {
                helenRow = row;
                helenCol = line.indexOf("H");
            }
        }

        while (energy > 0 && !(parisRow == helenRow && parisCol == helenCol)) {
            String[] input = scanner.nextLine().split("\\s+");
            String direction = input[0];
            int enemyRow = Integer.parseInt(input[1]);
            int enemyCol = Integer.parseInt(input[2]);
            matrix = placeEnemy(matrix, enemyRow, enemyCol);
            matrix = moveParis(matrix, direction);
        }

        if (parisRow == helenRow && parisCol == helenCol) {
            matrix[helenRow][helenCol] = '-';
            System.out.printf("Paris has successfully abducted Helen! Energy left: %d%n", energy);
        } else if (energy <= 0) {
            matrix[parisRow][parisCol] = 'X';
            System.out.printf("Paris died at %d;%d.%n", parisRow, parisCol);
        }

        printMatrix(matrix);
    }

    public static char[][] moveParis(char[][] matrix, String direction) {
        switch (direction) {
            case "up":
                if (isInBounds(matrix, parisRow - 1, parisCol)) {
                    matrix[parisRow][parisCol] = '-';
                    parisRow--;
                    if (checkForEnemy(matrix)) {
                        energy -= 2;
                    }
                }
                break;
            case "down":
                if (isInBounds(matrix, parisRow + 1, parisCol)) {
                    matrix[parisRow][parisCol] = '-';
                    parisRow++;
                    if (checkForEnemy(matrix)) {
                        energy -= 2;
                    }
                }
                break;
            case "left":
                if (isInBounds(matrix, parisRow, parisCol - 1)) {
                    matrix[parisRow][parisCol] = '-';
                    parisCol--;
                    if (checkForEnemy(matrix)) {
                        energy -= 2;
                    }
                }
                break;
            case "right":
                if (isInBounds(matrix, parisRow, parisCol + 1)) {
                    matrix[parisRow][parisCol] = '-';
                    parisCol++;
                    if (checkForEnemy(matrix)) {
                        energy -= 2;
                    }
                }
                break;

        }
        energy--;
        return matrix;
    }

    public static boolean checkForEnemy(char[][] matrix) {
        return matrix[parisRow][parisCol] == 'S';
    }

    public static boolean isInBounds(char[][] matrix, int row, int col) {
        return row >= 0 && row < matrix.length && col >= 0 && col < matrix[row].length;
    }

    public static char[][] placeEnemy(char[][] matrix, int enemyRow, int enemyCol) {
        matrix[enemyRow][enemyCol] = 'S';
        return matrix;
    }

    private static void printMatrix(char[][] matrix) {
        for (int row = 0; row < matrix.length; row++) {
            for (int col = 0; col < matrix[row].length; col++) {
                System.out.print(matrix[row][col]);
            }
            System.out.println();
        }
    }
}
