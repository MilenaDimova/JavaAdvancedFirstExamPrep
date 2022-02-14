package FirstExamPreparation;

import java.util.*;
import java.util.stream.Collectors;

public class Problem1OSPlaning {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        ArrayDeque<Integer> stack = new ArrayDeque<>(); //tasks

        Arrays.stream(scanner.nextLine().split(", "))
                .map(Integer::parseInt)
                .forEach(stack::push);

        ArrayDeque<Integer> queue = Arrays.stream(scanner.nextLine().split("\\s+")) //threads
                .map(Integer::parseInt)
                .collect(Collectors.toCollection(ArrayDeque::new));

        int taskToBeKilled = Integer.parseInt(scanner.nextLine());

        while (stack.peek() != taskToBeKilled) {
            if (queue.peek() >= stack.peek()) {
                queue.poll();
                stack.pop();
            } else {
                 queue.poll();
            }

        }

        int valueKilled = queue.peek();

        System.out.printf("Thread with value %d killed task %d%n", valueKilled, taskToBeKilled);
        List<String> numbers = new ArrayList<>();
        for (Integer q : queue) {
            numbers.add(String.valueOf(q));
        }
        System.out.println(String.join(" ", numbers));

    }
}
