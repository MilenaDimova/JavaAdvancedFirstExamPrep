package FirstExamPreparation;

import java.util.*;

public class AutumnCocktails {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        ArrayDeque<Integer> ingredients = new ArrayDeque<>(); //queue
        ArrayDeque<Integer> freshnessValue = new ArrayDeque<>(); //stack

        int[] firstLineNumbers = Arrays.stream(scanner.nextLine().split("\\s+"))
                .mapToInt(Integer::parseInt)
                .toArray();

        int[] secondLineNumbers = Arrays.stream(scanner.nextLine().split("\\s+"))
                .mapToInt(Integer::parseInt)
                .toArray();

        for (int number : firstLineNumbers) {
            ingredients.offer(number);
        }


        for (int number : secondLineNumbers) {
            freshnessValue.push(number);
        }

        Map<String, Integer> cocktails = new TreeMap<>();

        while (!ingredients.isEmpty() && !freshnessValue.isEmpty()) {
            if (ingredients.peek() == 0) {
                ingredients.poll();
                continue;
            }

            if (ingredients.peek() * freshnessValue.peek() == 150) {
                if (!cocktails.containsKey("Pear Sour")) {
                    cocktails.put("Pear Sour", 1);
                } else {
                    int currentCount = cocktails.get("Pear Sour");
                    cocktails.put("Pear Sour", currentCount + 1);
                }
                ingredients.poll();
                freshnessValue.pop();

            } else if (ingredients.peek() * freshnessValue.peek() == 250) {
                if (!cocktails.containsKey("The Harvest")) {
                    cocktails.put("The Harvest", 1);
                } else {
                    int currentCount = cocktails.get("The Harvest");
                    cocktails.put("The Harvest", currentCount + 1);
                }
                ingredients.poll();
                freshnessValue.pop();

            } else if (ingredients.peek() * freshnessValue.peek() == 300) {
                if (!cocktails.containsKey("Apple Hinny")) {
                    cocktails.put("Apple Hinny", 1);
                } else {
                    int currentCount = cocktails.get("The Harvest");
                    cocktails.put("Apple Hinny", currentCount + 1);
                }
                ingredients.poll();
                freshnessValue.pop();

            } else if (ingredients.peek() * freshnessValue.peek() == 400) {
                if (!cocktails.containsKey("High Fashion")) {
                    cocktails.put("High Fashion", 1);
                } else {
                    int currentCount = cocktails.get("High Fashion");
                    cocktails.put("High Fashion", currentCount + 1);
                }
                ingredients.poll();
                freshnessValue.pop();

            } else {
                ingredients.offer(ingredients.poll() + 5);
                freshnessValue.pop();
            }

        }

        if (cocktails.size() == 4) {
            System.out.println("It's party time! The cocktails are ready!");
        } else {
            System.out.println("What a pity! You didn't manage to prepare all cocktails.");
        }

        if (!ingredients.isEmpty()) {
            int sumIngredients = 0;
            for (int number : ingredients) {
                sumIngredients += number;
            }
            System.out.println("Ingredients left: " + sumIngredients);
        }

        cocktails.forEach((key, value) -> System.out.printf("# %s --> %d%n", key, value));
    }
}
