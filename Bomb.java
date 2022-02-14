package FirstExamPreparation;

import java.util.Scanner;

public class Bomb {
    static int sapperRow;
    static int sapperCol;
    static int bombsCount = 0;
    static int endRouteRow;
    static int endRouteCol;
    static int sapperFoundBombs = 0;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int size = Integer.parseInt(scanner.nextLine());
        String[] directions = scanner.nextLine().split(",");
        char[][] matrix = new char[size][];

        for (int row = 0; row < size ; row++) {
            String line = scanner.nextLine().replaceAll(" ", "");
            matrix[row] = line.toCharArray();
            if (line.contains("s")) {
                sapperRow = row;
                sapperCol = line.indexOf("s");
            }
            for (char symbol : line.toCharArray()) {
                if (symbol == 'B') {
                    bombsCount++;
                }
            }
        }

        boolean sapperFoundRoute = false;
        boolean findAllBombs = false;
        for (String direction : directions) {
            matrix = moveSapper(matrix, direction);
            if (sapperRow == endRouteRow && sapperCol == endRouteCol) {
                sapperFoundRoute = true;
                break;
            }
            if (bombsCount == sapperFoundBombs && bombsCount > 0) {
                findAllBombs = true;
                break;
            }
        }

        if (sapperFoundRoute) {
            System.out.printf("END! %d bombs left on the field", bombsCount - sapperFoundBombs);
        } else if (findAllBombs) {
            System.out.println("Congratulations! You found all bombs!");
        } else {
            System.out.printf("%d bombs left on the field. Sapper position: (%d,%d)",
                    bombsCount - sapperFoundBombs, sapperRow, sapperCol);
        }
    }

    public static char[][] moveSapper(char[][] matrix, String direction) {
        switch (direction) {
            case "up":
                if (isInBounds(matrix, sapperRow - 1, sapperCol)) {
                    sapperRow--;
                    if (checkForBomb(matrix)) {
                        matrix[sapperRow][sapperCol] = '+';
                        sapperFoundBombs++;
                        System.out.println("You found a bomb!");
                    } else if (checkForEndRoute(matrix)) {
                        endRouteRow = sapperRow;
                        endRouteCol = sapperCol;
                    }
                }
                break;
            case "down":
                if (isInBounds(matrix, sapperRow + 1, sapperCol)) {
                    sapperRow++;
                    if (checkForBomb(matrix)) {
                        matrix[sapperRow][sapperCol] = '+';
                        sapperFoundBombs++;
                        System.out.println("You found a bomb!");
                    } else if (checkForEndRoute(matrix)) {
                        endRouteRow = sapperRow;
                        endRouteCol = sapperCol;
                    }
                }
                break;
            case "left":
                if (isInBounds(matrix, sapperRow, sapperCol - 1)) {
                    sapperCol--;
                    if (checkForBomb(matrix)) {
                        matrix[sapperRow][sapperCol] = '+';
                        sapperFoundBombs++;
                        System.out.println("You found a bomb!");
                    } else if (checkForEndRoute(matrix)) {
                        endRouteRow = sapperRow;
                        endRouteCol = sapperCol;
                    }
                }
                break;
            case "right":
                if (isInBounds(matrix, sapperRow, sapperCol + 1)) {
                    sapperCol++;
                    if (checkForBomb(matrix)) {
                        matrix[sapperRow][sapperCol] = '+';
                        sapperFoundBombs++;
                        System.out.println("You found a bomb!");
                    } else if (checkForEndRoute(matrix)) {
                        endRouteRow = sapperRow;
                        endRouteCol = sapperCol;
                    }
                }
                break;
        }
        return matrix;
    }


    private static boolean checkForEndRoute(char[][] matrix) {
        return matrix [sapperRow][sapperCol] == 'e';
    }


    public static boolean isInBounds(char[][] matrix, int row, int col) {
        return row >= 0 && row < matrix.length && col >= 0 && col < matrix[row].length;
    }

    public static boolean checkForBomb(char[][] matrix) {
        return matrix[sapperRow][sapperCol] == 'B';
    }

}
