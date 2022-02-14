package FirstExamPreparation;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Scanner;
import java.util.stream.Collectors;


public class Bouquets {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ArrayDeque<Integer> tulipsStack = new ArrayDeque<>();

        Arrays.stream(scanner.nextLine().split(", "))
                .map(Integer::parseInt)
                .forEach(tulipsStack::push);

        ArrayDeque<Integer> daffodilsQueue = Arrays.stream(scanner.nextLine().split(", "))
                .map(Integer::parseInt).collect(Collectors.toCollection(ArrayDeque::new));

        int bouquetsCount = 0;
        int storedFlowers = 0;
        while (!tulipsStack.isEmpty() && !daffodilsQueue.isEmpty()) {
            if (tulipsStack.peek() + daffodilsQueue.peek() == 15) {
                bouquetsCount++;
                tulipsStack.pop();
                daffodilsQueue.poll();
            } else if (tulipsStack.peek() + daffodilsQueue.peek() > 15) {
                tulipsStack.push(tulipsStack.pop() - 2);
            } else if (tulipsStack.peek() + daffodilsQueue.peek() < 15) {
                storedFlowers = storedFlowers + (tulipsStack.pop() + daffodilsQueue.poll());
            }
        }

        int moreBouguets = 0;
        if (storedFlowers > 15) {
            moreBouguets = storedFlowers / 15;
        }

        int allBouquets = bouquetsCount + moreBouguets;
        if (allBouquets >= 5) {
            System.out.printf("You made it! You go to the competition with %d bouquets!", allBouquets);
        } else {
            System.out.printf("You failed... You need more %d bouquets.", 5 - allBouquets);
        }
    }
}
