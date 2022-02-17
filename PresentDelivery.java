package FirstExamPreparation;

import java.util.Scanner;

public class PresentDelivery {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int presents = Integer.parseInt(scanner.nextLine());
        int size = Integer.parseInt(scanner.nextLine());
        char[][] neighborhood = new char[size][size];

        int niceKids = 0;
        int giftedKids = 0;

        chekNeighborhood(scanner, size, neighborhood);
        niceKids = getNiceKids(size, neighborhood, niceKids);

        int countOfNiceKids = niceKids;

        int santaRow = 0;
        int santaCol = 0;
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (neighborhood[i][j] == 'S') {
                    santaRow = i;
                    santaCol = j;
                    break;
                }
            }
        }
        while (true) {
            String command = scanner.nextLine();
            if (command.equals("Christmas morning")) {
                break;
            }
            neighborhood[santaRow][santaCol] = '-';
            switch (command) {
                case "up" -> santaRow--;
                case "down" -> santaRow++;
                case "left" -> santaCol--;
                case "right" -> santaCol++;
            }
            if (santaRow < 0 || santaRow > size - 1 || santaCol < 0 || santaCol > size - 1) {
                break;

            }
            if (neighborhood[santaRow][santaCol] == 'V') {
                neighborhood[santaRow][santaCol] = '-';
                presents--;
                giftedKids++;
                niceKids--;

            } else if (neighborhood[santaRow][santaCol] == 'X') {
                neighborhood[santaRow][santaCol] = '-';

            } else if (neighborhood[santaRow][santaCol] == 'C') {

                char[] cells = new char[4];
                char up = neighborhood[santaRow - 1][santaCol];
                char down = neighborhood[santaRow + 1][santaCol];
                char left = neighborhood[santaRow][santaCol - 1];
                char right = neighborhood[santaRow][santaCol + 1];


                cells[0] = up;
                cells[1] = down;
                cells[2] = left;
                cells[3] = right;

                for (char cell : cells) {
                    if ((cell == 'V') || (cell == 'X')) {
                        if (cell == 'V') {
                            niceKids--;
                        }
                        giftedKids++;
                        presents--;

                        if (presents <= 0) {
                            break;
                        }
                    }
                }
                neighborhood[santaRow - 1][santaCol] = '-';
                neighborhood[santaRow + 1][santaCol] = '-';
                neighborhood[santaRow][santaCol - 1] = '-';
                neighborhood[santaRow][santaCol + 1] = '-';
            }
            neighborhood[santaRow][santaCol] = 'S';

            if (presents == 0) {
                System.out.println("Santa ran out of presents!");
                break;
            }
        }
        for (char[] chars : neighborhood) {
            for (char a : chars) {
                System.out.print(a + " ");
            }
            System.out.println();
        }
        if (niceKids == 0) {
            System.out.printf("Good job, Santa! %d happy nice kid/s.", giftedKids);
        } else {
            System.out.printf("No presents for %d nice kid/s.", countOfNiceKids - giftedKids);
        }
    }

    private static void chekNeighborhood(Scanner s, int size, char[][] neighborhood) {
        for (int i = 0; i < size; i++) {
            String[] input = s.nextLine().split(" ");
            for (int j = 0; j < size; j++) {
                neighborhood[i][j] = input[j].charAt(0);
            }
        }
    }

    private static int getNiceKids(int size, char[][] neighborhood, int niceKids) {
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (neighborhood[i][j] == 'V') {
                    niceKids++;
                }
            }
        }
        return niceKids;
    }
}
