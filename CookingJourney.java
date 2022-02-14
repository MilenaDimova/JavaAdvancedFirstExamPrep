package FirstExamPreparation;

import java.util.Scanner;

public class CookingJourney {
    static int cookerRow;
    static int cookerCol;
    static int savedMoney = 0;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int size = Integer.parseInt(scanner.nextLine());
        char[][] matrix = new char[size][size];
        for (int row = 0; row < size; row++) {
            String line = scanner.nextLine();
            matrix[row] = line.toCharArray();
            if (line.contains("S")) {
                cookerRow = row;
                cookerCol = line.indexOf("S");
            }
        }

        while (savedMoney < 50 && isInBounds(matrix, cookerRow, cookerCol)) {
            String direction = scanner.nextLine();
            matrix = moveCooker(matrix, direction);
        }

        if (!isInBounds(matrix, cookerRow, cookerCol)) {
            System.out.println("Bad news! You are out of the pastry shop.");
        } else if (savedMoney >= 50) {
            System.out.println("Good news! You succeeded in collecting enough money!");
        }
        System.out.printf("Money: %d%n", savedMoney);
        printMatrix(matrix);

    }

    public static char[][] moveCooker(char[][] matrix, String direction) {
        switch (direction) {
            case "up":
                if (isInBounds(matrix, cookerRow - 1, cookerCol)) {
                    matrix[cookerRow][cookerCol] = '-';
                    cookerRow--;
                    if (chekForPillar(matrix)) {
                        matrix = moveCookerAndRemovePillars(matrix, cookerRow, cookerCol);
                    } else if (matrix[cookerRow][cookerCol] != '-') {
                        savedMoney += matrix[cookerRow][cookerCol] - '0';
                        matrix[cookerRow][cookerCol] = 'S';
                    } else {
                        matrix[cookerRow][cookerCol] = 'S';
                    }
                } else {
                    matrix[cookerRow][cookerCol] = '-';
                    cookerRow--;
                }
                break;
            case "down":
                if (isInBounds(matrix, cookerRow + 1, cookerCol)) {
                    matrix[cookerRow][cookerCol] = '-';
                    cookerRow++;
                    if (chekForPillar(matrix)) {
                        matrix = moveCookerAndRemovePillars(matrix, cookerRow, cookerCol);
                    } else if (matrix[cookerRow][cookerCol] != '-') {
                        savedMoney += matrix[cookerRow][cookerCol] - '0';
                        matrix[cookerRow][cookerCol] = 'S';
                    } else {
                        matrix[cookerRow][cookerCol] = 'S';
                    }
                } else {
                    matrix[cookerRow][cookerCol] = '-';
                    cookerRow++;
                }
                break;
            case "left":
                if (isInBounds(matrix, cookerRow, cookerCol - 1)) {
                    matrix[cookerRow][cookerCol] = '-';
                    cookerCol--;
                    if (chekForPillar(matrix)) {
                        matrix = moveCookerAndRemovePillars(matrix, cookerRow, cookerCol);
                    } else if (matrix[cookerRow][cookerCol] != '-') {
                        savedMoney += matrix[cookerRow][cookerCol] - '0';
                        matrix[cookerRow][cookerCol] = 'S';
                    } else {
                        matrix[cookerRow][cookerCol] = 'S';
                    }
                } else {
                    matrix[cookerRow][cookerCol] = '-';
                    cookerCol--;
                }
                break;
            case "right":
                if (isInBounds(matrix, cookerRow, cookerCol + 1)) {
                    matrix[cookerRow][cookerCol] = '-';
                    cookerCol++;
                    if (chekForPillar(matrix)) {
                        matrix = moveCookerAndRemovePillars(matrix, cookerRow, cookerCol);
                    } else if (matrix[cookerRow][cookerCol] != '-') {
                        savedMoney += matrix[cookerRow][cookerCol] - '0';
                        matrix[cookerRow][cookerCol] = 'S';
                    } else {
                        matrix[cookerRow][cookerCol] = 'S';
                    }
                } else {
                    matrix[cookerRow][cookerCol] = '-';
                    cookerCol++;
                }
                break;
        }
        return matrix;
    }

    public static boolean isInBounds(char[][] matrix, int row, int col) {
        return row >= 0 && row < matrix.length && col >= 0 && col < matrix[row].length;
    }

    public static boolean chekForPillar(char[][] matrix) {
        return matrix[cookerRow][cookerCol] == 'P';
    }

    public static char[][] moveCookerAndRemovePillars(char[][] matrix, int currentRow, int currentCol) {
        matrix[currentRow][currentCol] = '-';
        for (int row = 0; row < matrix.length; row++) {
            for (int col = 0; col < matrix.length; col++) {
                if (matrix[row][col] == 'P') {
                    cookerRow = row;
                    cookerCol = col;
                    matrix[cookerRow][cookerCol] = 'S';
                }
            }
        }
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
