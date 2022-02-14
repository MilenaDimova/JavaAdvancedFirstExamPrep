package FirstExamPreparation;

import java.util.Scanner;

public class ReVolt {
    static int rowPlayer;
    static int colPlayer;
    static int finishRow;
    static int finishCol;
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int size = Integer.parseInt(scanner.nextLine());
        int commandsCount = Integer.parseInt(scanner.nextLine());
        char[][] matrix = new char[size][size];
        for (int row = 0; row < size; row++) {
            String line = scanner.nextLine();
            matrix[row] = line.toCharArray();
            if (line.contains("f")) {
                rowPlayer = row;
                colPlayer = line.indexOf("f");
            }
            if (line.contains("F")) {
                finishRow = row;
                finishCol = line.indexOf("F");
            }
        }

        boolean playerReachesTheFinish = false;
        matrix[rowPlayer][colPlayer] = '-';
        for (int i = 0; i < commandsCount; i++) {
            String command = scanner.nextLine();
            int oldRowPlayer = rowPlayer;
            int oldColPlayer = colPlayer;
            movePlayer(matrix, command);
            if (checkForBonus(matrix)) {
                movePlayer(matrix, command);
            }
            if (checkForTrap(matrix)) {
                rowPlayer = oldRowPlayer;
                colPlayer = oldColPlayer;
            }
            if (rowPlayer == finishRow && colPlayer == finishCol) {
                matrix[rowPlayer][colPlayer] = 'f';
                playerReachesTheFinish = true;
                break;
            }
            if (i == commandsCount - 1) {
                matrix[rowPlayer][colPlayer] = 'f';
            }
        }

        if (playerReachesTheFinish) {
            System.out.println("Player won!");
        } else {
            System.out.println("Player lost!");
        }

        printMatrix(matrix);

    }

    private static void movePlayer(char[][] matrix, String command) {
        switch (command) {
            case "up" -> rowPlayer--;
            case "down" -> rowPlayer++;
            case "left" -> colPlayer--;
            case "right" -> colPlayer++;
        }
        if (rowPlayer < 0 || rowPlayer >= matrix.length) {
            if (rowPlayer < 0 ) {
                rowPlayer = matrix.length - 1;
            }
            if (rowPlayer >= matrix.length) {
                rowPlayer = 0;
            }
        }

        if (colPlayer < 0 || colPlayer >= matrix.length) {
            if (colPlayer < 0) {
                colPlayer = matrix.length - 1;
            }
            if (colPlayer >= matrix.length) {
                colPlayer = 0;
            }
        }

    }

    private static boolean checkForBonus(char[][] matrix) {
         return matrix[rowPlayer][colPlayer] == 'B';
    }

    private static boolean checkForTrap(char[][] matrix) {
        return matrix[rowPlayer][colPlayer] == 'T';
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
