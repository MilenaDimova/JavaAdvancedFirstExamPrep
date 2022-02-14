package FirstExamPreparation;

import java.util.*;
import java.util.stream.Collectors;

public class Cooking {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ArrayDeque<Integer> liquids = Arrays.stream(scanner.nextLine().split("\\s+")) //queue
                .map(Integer::parseInt).collect(Collectors.toCollection(ArrayDeque::new));

        ArrayDeque<Integer> ingredients = new ArrayDeque<>(); //stack
        Arrays.stream(scanner.nextLine().split("\\s+"))
                .map(Integer::parseInt).forEach(ingredients::push);

        Map<String, Integer> foods = new TreeMap<>();
        foods.put("Bread", 0);
        foods.put("Cake", 0);
        foods.put("Pastry", 0);
        foods.put("Fruit Pie", 0);

        while (!liquids.isEmpty() && !ingredients.isEmpty()) {
            if (liquids.peek() + ingredients.peek() == 25) {
                int current = foods.get("Bread");
                foods.put("Bread", current + 1);
                liquids.poll();
                ingredients.pop();
            } else if (liquids.peek() + ingredients.peek() == 50) {
                int current = foods.get("Cake");
                foods.put("Cake", current + 1);
                liquids.poll();
                ingredients.pop();
            } else if (liquids.peek() + ingredients.peek() == 75) {
                int current = foods.get("Pastry");
                foods.put("Pastry", current + 1);
                liquids.poll();
                ingredients.pop();
            } else if (liquids.peek() + ingredients.peek() == 100) {
                int current = foods.get("Fruit Pie");
                foods.put("Fruit Pie", current + 1);
                liquids.poll();
                ingredients.pop();
            } else {
                liquids.poll();
                ingredients.push(ingredients.pop() + 3);
            }
        }

        boolean notCollectAllFoods = false;
        for (Integer foodValue : foods.values()) {
            if (foodValue == 0) {
                notCollectAllFoods = true;
                break;
            }
        }
        if (notCollectAllFoods) {
            System.out.println("Ugh, what a pity! You didn't have enough materials to cook everything.");
        } else {
            System.out.println("Wohoo! You succeeded in cooking all the food!");
        }

        System.out.printf("Liquids left: ");
        if (liquids.isEmpty()) {
            System.out.println("none");
        } else {
            List<String> leftLiquids = new ArrayList<>();
            for (Integer leftLiquid : liquids) {
                leftLiquids.add(String.valueOf(leftLiquid));
            }
            System.out.println(String.join(", ", leftLiquids));
        }

        System.out.print("Ingredients left: ");
        if (ingredients.isEmpty()) {
            System.out.println("none");
        } else {
            List<String> leftIngredients = new ArrayList<>();
            for (Integer leftIngredient : ingredients) {
                leftIngredients.add(String.valueOf(leftIngredient));
            }
            System.out.println(String.join(", ", leftIngredients));
        }

        foods.entrySet().forEach(f -> {
            System.out.printf("%s: %d%n", f.getKey(), f.getValue());
        });
    }
}
