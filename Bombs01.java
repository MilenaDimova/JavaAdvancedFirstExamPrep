package FirstExamPreparation;

import java.util.*;
import java.util.stream.Collectors;

public class Bombs01 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ArrayDeque<Integer> bombEffects = Arrays.stream(scanner.nextLine().split(", ")) //queue
                .map(Integer::parseInt).collect(Collectors.toCollection(ArrayDeque::new));

        ArrayDeque<Integer> bombCasing = new ArrayDeque<>(); //stack
        Arrays.stream(scanner.nextLine().split(", "))
                .map(Integer::parseInt)
                .forEach(bombCasing::push);

        Map<String, Integer> bombs = new TreeMap<>();
        bombs.put("Datura Bombs", 0);
        bombs.put("Cherry Bombs", 0);
        bombs.put("Smoke Decoy Bombs", 0);

        boolean collectedEnoughBombs = false;
        while (!bombEffects.isEmpty() && !bombCasing.isEmpty()) {
            if (bombEffects.peek() + bombCasing.peek() == 40) {
                int current = bombs.get("Datura Bombs");
                bombs.put("Datura Bombs", current + 1);
                bombEffects.poll();
                bombCasing.pop();
            } else if (bombEffects.peek() + bombCasing.peek() == 60) {
                int current = bombs.get("Cherry Bombs");
                bombs.put("Cherry Bombs", current + 1);
                bombEffects.poll();
                bombCasing.pop();
            } else if (bombEffects.peek() + bombCasing.peek() == 120) {
                int current = bombs.get("Smoke Decoy Bombs");
                bombs.put("Smoke Decoy Bombs", current + 1);
                bombEffects.poll();
                bombCasing.pop();
            } else {
                bombCasing.push(bombCasing.pop() - 5);
            }

            if (bombs.get("Datura Bombs") >= 3 && bombs.get("Cherry Bombs") >= 3 &&
                    bombs.get("Smoke Decoy Bombs") >= 3) {
                collectedEnoughBombs = true;
                break;
            }
        }

        if (collectedEnoughBombs) {
            System.out.println("Bene! You have successfully filled the bomb pouch!");
        } else {
            System.out.println("You don't have enough materials to fill the bomb pouch.");
        }

        System.out.print("Bomb Effects: ");
        if (bombEffects.isEmpty()) {
            System.out.println("empty");
        } else {
            List<String> leftBombEffects = new ArrayList<>();
            for (Integer bombEffect : bombEffects) {
                leftBombEffects.add(String.valueOf(bombEffect));
            }
            System.out.println(String.join(", ", leftBombEffects));
        }

        System.out.print("Bomb Casings: ");
        if (bombCasing.isEmpty()) {
            System.out.println("empty");
        } else {
            List<String> leftBombCasing = new ArrayList<>();
            for (Integer bomb : bombCasing) {
                leftBombCasing.add(String.valueOf(bomb));
            }
            System.out.println(String.join(", ", leftBombCasing));
        }

        bombs.forEach((key, value) -> System.out.printf("%s: %d%n", key, value));
    }
}
