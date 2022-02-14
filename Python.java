package FirstExamPreparation;

import java.util.Scanner;

public class Python {
    static int food = 0;
    static int pythonRow = 0;
    static int pythonCol = 0;
    static int length = 1;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int size = Integer.parseInt(scanner.nextLine());
        String[] directions = scanner.nextLine().split(", ");
        char[][] matrix = new char[size][size];

        for (int row = 0; row < size; row++) {
            String matrixRow = scanner.nextLine().replaceAll(" ", "");
            matrix[row] = matrixRow.toCharArray();
            if (matrixRow.contains("s")) {
                //found the snake
                pythonRow = row;
                pythonCol = matrixRow.indexOf("s");
            }

            for (char currentSymbol : matrix[row]) {
                if (currentSymbol == 'f') {
                    food++;
                }
            }
        }

        for (String direction : directions) {
            if (direction.equals("up")) {
                movePython(matrix, pythonRow - 1, pythonCol);
            } else if (direction.equals("down")) {
                movePython(matrix, pythonRow + 1, pythonCol);
            } else if (direction.equals("left")) {
                movePython(matrix, pythonRow, pythonCol - 1);
            } else if (direction.equals("right")) {
                movePython(matrix, pythonRow, pythonCol + 1);
            }

            if (length == -1 || food == 0) {
                break;
            }
        }

        if (food == 0) {
            System.out.printf("You win! Final python length is %d", length);
        } else if (food > 0 && length != -1) {
            System.out.printf("You lose! There is still %d food to be eaten.", food);
        } else {
            System.out.println("You lose! Killed by an enemy!");
        }

        
    }

    private static void movePython(char[][] matrix, int newRow, int newCol) {
        if (isOutOfBounds(matrix, newRow, newCol)) {
           int[] newIndexes = flipSnakeSide(matrix.length, newRow, newCol);
           newRow = newIndexes[0];
           newCol = newIndexes[1];
        }

        if (matrix[newRow][newCol] == 'e') {
            length = -1;
        } else if (matrix[newRow][newCol] == 'f') {
            length++;
            food--;
        }

        pythonRow = newRow;
        pythonCol = newCol;
    }

    private static int[] flipSnakeSide(int length, int row, int col) {
        int[] result = new int[2];
        if (row < 0) {
            result[0] = length - 1;
            result[1] = col;
        } else if (row >= length) {
            result[1] = col;
        } else if (col < 0) {
            result[0] = row;
            result[1] = length - 1;
        } else {
            result[0] = row;
        }

        return result;
    }

    private static boolean isOutOfBounds(char[][] matrix, int row, int col) {
        return row < 0 || row >= matrix.length || col < 0 || col >= matrix[row].length;
    }
}
