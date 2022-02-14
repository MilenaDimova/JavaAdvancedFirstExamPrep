package FirstExamPreparation;

import java.util.Scanner;

public class Snake {
    static int snakeRow;
    static int snakeCol;
    static int food;
    private static final int ENOUGH_FOOD = 10;
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int size = Integer.parseInt(scanner.nextLine());
        char[][] matrix = new char[size][];
        for (int row = 0; row < size; row++) {
            String line = scanner.nextLine();
            matrix[row] = line.toCharArray();
            if (line.contains("S")) {
                snakeRow = row;
                snakeCol = line.indexOf("S");
            }
        }

        while (isInBounds(matrix, snakeRow, snakeCol) && food < ENOUGH_FOOD) {
            String command = scanner.nextLine();
            moveSnake(matrix, command);
        }

        if (!isInBounds(matrix, snakeRow, snakeCol)) {
            System.out.println("Game over!");
        } else if (food >= ENOUGH_FOOD) {
            System.out.println("You won! You fed the snake.");
        }
        System.out.printf("Food eaten: %d%n", food);
        printMatrix(matrix);

    }

    private static void moveSnake(char[][] matrix, String command) {
        switch (command) {
            case "up":
                if (isInBounds(matrix, snakeRow - 1, snakeCol)) {
                    matrix[snakeRow][snakeCol] = '.';
                    snakeRow--;
                    if (checkForFood(matrix)) {
                        food++;
                        matrix[snakeRow][snakeCol] = 'S';
                    } else if (checkForLair(matrix)) {
                        matrix[snakeRow][snakeCol] = '.';
                        snakeGoesToSecondLair(matrix);
                    } else {
                        matrix[snakeRow][snakeCol] = 'S';
                    }
                } else {
                    matrix[snakeRow][snakeCol] = '.';
                    snakeRow--;
                }
                break;
            case "down":
                if (isInBounds(matrix, snakeRow + 1, snakeCol)) {
                    matrix[snakeRow][snakeCol] = '.';
                    snakeRow++;
                    if (checkForFood(matrix)) {
                        food++;
                        matrix[snakeRow][snakeCol] = 'S';
                    } else if (checkForLair(matrix)) {
                        matrix[snakeRow][snakeCol] = '.';
                        snakeGoesToSecondLair(matrix);
                    } else {
                        matrix[snakeRow][snakeCol] = 'S';
                    }
                } else {
                    matrix[snakeRow][snakeCol] = '.';
                    snakeRow++;
                }
                break;
            case "left":
                if (isInBounds(matrix, snakeRow, snakeCol - 1)) {
                    matrix[snakeRow][snakeCol] = '.';
                    snakeCol--;
                    if (checkForFood(matrix)) {
                        food++;
                        matrix[snakeRow][snakeCol] = 'S';
                    } else if (checkForLair(matrix)) {
                        matrix[snakeRow][snakeCol] = '.';
                        snakeGoesToSecondLair(matrix);
                    } else {
                        matrix[snakeRow][snakeCol] = 'S';
                    }
                } else {
                    matrix[snakeRow][snakeCol] = '.';
                    snakeCol--;
                }
                break;
            case "right":
                if (isInBounds(matrix, snakeRow, snakeCol + 1)) {
                    matrix[snakeRow][snakeCol] = '.';
                    snakeCol++;
                    if (checkForFood(matrix)) {
                        food++;
                        matrix[snakeRow][snakeCol] = 'S';
                    } else if (checkForLair(matrix)) {
                        matrix[snakeRow][snakeCol] = '.';
                        snakeGoesToSecondLair(matrix);
                    } else {
                        matrix[snakeRow][snakeCol] = 'S';
                    }
                } else {
                    matrix[snakeRow][snakeCol] = '.';
                    snakeCol++;
                }
                break;
        }
    }

    private static void snakeGoesToSecondLair(char[][] matrix) {
        for (int row = 0; row < matrix.length; row++) {
            for (int col = 0; col < matrix[row].length; col++) {
                if (matrix[row][col] == 'B') {
                    snakeRow = row;
                    snakeCol = col;
                    break;
                }
            }
        }
        matrix[snakeRow][snakeCol] = 'S';
    }

    public static boolean isInBounds(char[][] matrix, int row, int col) {
        return row >= 0 && row < matrix.length && col >= 0 && col < matrix[row].length;
    }

    public static boolean checkForFood(char[][] matrix) {
        return matrix[snakeRow][snakeCol] == '*';
    }

    public static boolean checkForLair(char[][] matrix) {
        return matrix[snakeRow][snakeCol] == 'B';
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
