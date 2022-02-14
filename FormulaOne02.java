package FirstExamPreparation;

import java.util.Scanner;

public class FormulaOne02 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int size = Integer.parseInt(scanner.nextLine());
        int countCommands = Integer.parseInt(scanner.nextLine());
        char[][] matrix = new char[size][size];

        //fillMatrix
        fillMatrix(scanner, size, matrix);

        int playerRow = 0;
        int playerCol = 0;
        for (int row = 0; row < size; row++) {
            for (int col = 0; col < size; col++) {
                char currentElement = matrix[row][col];
                if (currentElement == 'P') {
                    playerRow = row;
                    playerCol = col;
                    break;
                }
            }
        }

        for (int commandCount = 1; commandCount <= countCommands; commandCount++) {
            String direction = scanner.nextLine();
            int newRow = 0;
            int newCol = 0;
            switch (direction) {
                case "up":
                    newRow = playerRow - 1;
                    newCol = playerCol;
                    if (matrix[newRow][newCol] == 'B') {
                        newRow = playerRow - 2;
                        newCol = playerCol;
                    }
                    break;
                case "down":
                    newRow = playerRow + 1;
                    newCol = playerCol;
                    if (matrix[newRow][newCol] == 'B') {
                        newRow = playerRow + 2;
                        newCol = playerCol;
                    }
                    break;
                case "left":
                    newRow = playerRow;
                    newCol = playerCol - 1;
                    if (matrix[newRow][newCol] == 'B') {
                        newRow = playerRow;
                        newCol = playerCol - 2;
                    }
                    break;
                case "right":
                    newRow = playerRow;
                    newCol = playerCol + 1;
                    if (matrix[newRow][newCol] == 'B') {
                        newRow = playerRow;
                        newCol = playerCol + 2;
                    }
                    break;
            }
            //проверка на новата позиция имаме ли T, F
            if (matrix[newRow][newCol] == 'T') {
                newRow = playerRow;
                newCol = playerCol;
            } else {
                matrix[playerRow][playerCol] = '.';
                matrix[newRow][newCol] = 'P';
                playerRow = newRow;
                playerCol = newCol;
            }


        }

        if (matrix[playerRow][playerCol] == 'F') {
            System.out.println("Well done, the player won first place!");
        } else {
            System.out.println("Oh no, the player got lost on the track!");
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

    private static void fillMatrix(Scanner scanner, int size, char[][] matrix) {
        for (int row = 0; row < size; row++) {
            String line = scanner.nextLine();
            matrix[row] = line.toCharArray();
        }
    }
}
