package FirstExamPreparation;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Scanner;
import java.util.stream.Collectors;

public class FlowerWreaths {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ArrayDeque<Integer> lilies = new ArrayDeque<>(); //stack
        Arrays.stream(scanner.nextLine().split(", "))
                .map(Integer::parseInt)
                .forEach(lilies::push);

        ArrayDeque<Integer> roses = Arrays.stream(scanner.nextLine().split(", ")) //queue
                .map(Integer::parseInt).collect(Collectors.toCollection(ArrayDeque::new));

        int wreathsCount = 0;
        int storedFlowers = 0;
        while (!lilies.isEmpty() && !roses.isEmpty()) {
            if (lilies.peek() + roses.peek() == 15) {
                wreathsCount++;
                lilies.pop();
                roses.poll();
            } else if (lilies.peek() + roses.peek() > 15) {
                lilies.push(lilies.pop() - 2);
            } else {
                storedFlowers += lilies.pop() + roses.poll();
            }
        }

        int moreWreaths = 0;
        if (storedFlowers > 15) {
            moreWreaths = storedFlowers / 15;
        }

        int allWreaths = wreathsCount + moreWreaths;
        if (allWreaths >= 5) {
            System.out.printf("You made it, you are going to the competition with %d wreaths!", allWreaths);
        } else {
            System.out.printf("You didn't make it, you need %d wreaths more!", 5 - allWreaths);
        }
    }
}
