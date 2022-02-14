package FirstExamPreparation;

import java.util.Scanner;

public class MouseAndCheese {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = Integer.parseInt(scanner.nextLine());
        char[][] matrix = new char[n][n];

        int mouseRow = -1; //ред на мишката
        int mouseCol = -1; //колона на мишката
        int countCheese = 0;

        //пълним матрицата
        fillMatrix(scanner, n, matrix);

        //намираме мишката
        for (int row = 0; row < n; row++) {
            for (int col = 0; col < n; col++) {
                char currentSymbol = matrix[row][col];
                if (currentSymbol == 'M') {
                    mouseRow = row;
                    mouseCol = col;
                    break;
                }
            }
            
        }

        String direction = scanner.nextLine();
        while (!direction.equals("end")) {
            //directions -> "up", "down", "left", "right"
            matrix[mouseRow][mouseCol] = '-';
            switch (direction) {
                case "up":
                    //намаляме реда с 1
                    mouseRow--;
                    break;
                case "down":
                    //увеличаваме реда с 1
                    mouseRow++;
                    break;
                case "left":
                    //намаляме колоната с 1
                    mouseCol--;
                    break;
                case "right":
                    //увеличаваме колоната с 1
                    mouseCol++;
                    break;
            }
            //преди да поставим мишката на новото й място -> проверка дали реда и колоната са валидни
            if (mouseRow < 0 || mouseRow >= n || mouseCol < 0 || mouseCol >= n) {
                System.out.println("Where is the mouse?");
                break;
            }

            //проверка на мястото, на което ще отиде мишката
            if (matrix[mouseRow][mouseCol] == 'c') {
                //сирене
                countCheese++;
            } else if (matrix[mouseRow][mouseCol] == 'B') {
                //бонус
                continue;
            }

            //поставяне на ново място на мишката
            matrix[mouseRow][mouseCol] = 'M';

            direction = scanner.nextLine();
        }

        //проверка дали е изяла достатъчно сиренца
        if (countCheese >= 5) {
            System.out.printf("Great job, the mouse is fed %d cheeses!%n", countCheese);
        } else {
            System.out.printf("The mouse couldn't eat the cheeses, she needed %d cheeses more.%n", 5 - countCheese);
        }

        //принтиране на матрицата
        printMatrix(n, matrix);
    }

    private static void printMatrix(int n, char[][] matrix) {
        for (int row = 0; row < n; row++) {
            for (int col = 0; col < n; col++) {
                System.out.print(matrix[row][col]);
            }
            System.out.println();
        }
    }

    private static void fillMatrix(Scanner scanner, int n, char[][] matrix) {
        for (int row = 0; row < n; row++) {
            String rowContent = scanner.nextLine();
            char[] rowSymbols = rowContent.toCharArray();
            matrix[row] = rowSymbols;
        }
    }
}
