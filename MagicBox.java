package FirstExamPreparation;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Scanner;
import java.util.stream.Collectors;

public class MagicBox {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ArrayDeque<Integer> firstBox = Arrays.stream(scanner.nextLine().split("\\s+"))
                .map(Integer::parseInt)
                .collect(Collectors.toCollection(ArrayDeque::new));

        ArrayDeque<Integer> secondBox = new ArrayDeque<>();
        Arrays.stream(scanner.nextLine().split("\\s+")).map(Integer::parseInt)
                .forEach(secondBox::push);

        int claimedItems = 0;
        while (!firstBox.isEmpty() && !secondBox.isEmpty()) {
            if ((firstBox.peek() + secondBox.peek()) % 2 == 0 ) {
                claimedItems += firstBox.poll();
                claimedItems += secondBox.pop();
            } else {
                firstBox.offer(secondBox.pop());
            }
        }

        if (firstBox.isEmpty()) {
            System.out.println("First magic box is empty.");
        } else {
            System.out.println("Second magic box is empty.");
        }

        if (claimedItems >= 90) {
            System.out.printf("Wow, your prey was epic! Value: %d", claimedItems);
        } else {
            System.out.printf("Poor prey... Value: %d", claimedItems);
        }

    }
}
