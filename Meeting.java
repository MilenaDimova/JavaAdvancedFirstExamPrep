package FirstExamPreparation;

import java.util.*;
import java.util.stream.Collectors;

public class Meeting {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        ArrayDeque<Integer> males = new ArrayDeque<>(); //stack
        Arrays.stream(scanner.nextLine().split("\\s+")).map(Integer::parseInt)
                .forEach(males::push);

        ArrayDeque<Integer> females = Arrays.stream(scanner.nextLine().split("\\s+")) //queue
                .map(Integer::parseInt)
                .collect(Collectors.toCollection(ArrayDeque::new));

        int matchesCount = 0;
        while (!males.isEmpty() && !females.isEmpty()) {
            if (males.peek() <= 0) {
                males.pop();
            } else if (females.peek() <= 0) {
                females.poll();
            } else if (males.peek() == females.peek()) {
                males.pop();
                females.poll();
                matchesCount++;
            } else if (males.peek() % 25 == 0) {
                males.pop();
                males.pop();
            } else if (females.peek() % 25 == 0) {
                females.poll();
                females.poll();
            } else {
                males.push(males.pop() - 2);
                females.poll();
            }

        }

        System.out.println("Matches: " + matchesCount);

        System.out.print("Males left: ");
        if (males.isEmpty()) {
            System.out.println("none");
        } else {
            List<String> malesCollection = new ArrayList<>();
            for (Integer male : males) {
                malesCollection.add(String.valueOf(male));
            }
            System.out.println(String.join(", ", malesCollection));
        }

        System.out.print("Females left: ");
        if (females.isEmpty()) {
            System.out.println("none");
        } else {
            List<String> femalesCollection = new ArrayList<>();
            for (Integer female : females) {
                femalesCollection.add(String.valueOf(female));
            }
            System.out.println(String.join(", ", femalesCollection));
        }
    }
}
