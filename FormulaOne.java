package FirstExamPreparation;

import java.util.Scanner;

public class FormulaOne {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int size = Integer.parseInt(scanner.nextLine());
        int commandCount = Integer.parseInt(scanner.nextLine());

        char[][] matrix = new char[size][];
        for (int row = 0; row < size; row++) {
            String input = scanner.nextLine();
            matrix[row] = input.toCharArray();
        }

        int playerRow = -1;
        int playerCol = -1;

        for (int row = 0; row < size; row++) {
            for (int col = 0; col < size; col++) {
                char currentSymbol = matrix[row][col];
                if (currentSymbol == 'P') {
                    playerRow = row;
                    playerCol = col;
                    matrix[row][col] = '.';
                    break;
                }
            }
        }

        while (commandCount > 0) {
            String direction = scanner.nextLine();
            int[] currentPositions;
            currentPositions = playerMove(direction, playerRow, playerCol, matrix);
            playerRow = currentPositions[0];
            playerCol = currentPositions[1];

            if (matrix[playerRow][playerCol] == 'F') {
                System.out.println("Well done, the player won first place!");
                matrix[playerRow][playerCol] = 'P';
                break;
            }

            if (matrix[playerRow][playerCol] == 'B') {
                currentPositions = playerMove(direction, playerRow, playerCol, matrix);
                playerRow = currentPositions[0];
                playerCol = currentPositions[1];
            }

            if (matrix[playerRow][playerCol] == 'F') {
                System.out.println("Well done, the player won first place!");
                matrix[playerRow][playerCol] = 'P';
                break;
            }

            if (matrix[playerRow][playerCol] == 'T') {
                switch (direction) {
                    case "up":
                        direction = "down";
                        break;
                    case "down":
                        direction = "up";
                        break;
                    case "left":
                        direction = "right";
                        break;
                    case "right":
                        direction = "left";
                        break;
                }
                currentPositions = playerMove(direction, playerRow, playerCol, matrix);
                playerRow = currentPositions[0];
                playerCol = currentPositions[1];
            }

            commandCount--;
        }

        if (commandCount == 0) {
            System.out.println("Oh no, the player got lost on the track!");
            matrix[playerRow][playerCol] = 'P';
        }

        printMatrix(matrix);

    }

    private static void printMatrix(char[][] matrix) {
        for (int row = 0; row < matrix.length; row++) {
            for (int col = 0; col < matrix.length; col++) {
                System.out.print(matrix[row][col]);
            }
            System.out.println();
        }
    }

    private static boolean isInBounds(int row, int col, char[][] matrix) {
        return row >= 0 && row < matrix.length && col >= 0 && col < matrix.length;
    }

    private static int[] playerMove(String direction, int playerRow, int playerCol, char[][] matrix) {
        switch (direction) {
            case "up":
                playerRow--;
                if (!isInBounds(playerRow, playerCol, matrix)) {
                    playerRow = matrix.length - 1;
                }
                break;
            case "down":
                playerRow++;
                if (!isInBounds(playerRow, playerCol, matrix)) {
                    playerRow = 0;
                }
                break;
            case "left":
                playerCol--;
                if (!isInBounds(playerRow, playerCol, matrix)) {
                    playerCol = matrix.length - 1;
                }
                break;
            case "right":
                playerCol++;
                if (!isInBounds(playerRow, playerCol, matrix)) {
                    playerCol = 0;
                }
                break;
        }

        return new int[] { playerRow, playerCol };
    }
}
