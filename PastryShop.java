package FirstExamPreparation;

import java.util.*;

public class PastryShop {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        ArrayDeque<Integer> liquids = new ArrayDeque<>(); //queue
        ArrayDeque<Integer> ingredients = new ArrayDeque<>(); //stack

        int[] liquidsNumbers = Arrays.stream(scanner.nextLine().split("\\s+"))
                .mapToInt(Integer::parseInt)
                .toArray();

        for (int number : liquidsNumbers) {
            liquids.offer(number);
        }

        int[] ingredientsNumbers = Arrays.stream(scanner.nextLine().split("\\s+"))
                .mapToInt(Integer::parseInt)
                .toArray();

        for (int number : ingredientsNumbers) {
            ingredients.push(number);
        }

        Map<String, Integer> pastriesAndCakes = new LinkedHashMap<>();
        pastriesAndCakes.put("Biscuit", 0);
        pastriesAndCakes.put("Cake", 0);
        pastriesAndCakes.put("Pie", 0);
        pastriesAndCakes.put("Pastry", 0);

        while (!liquids.isEmpty() && !ingredients.isEmpty()) {
            if (liquids.peek() + ingredients.peek() == 25) {
                if (!pastriesAndCakes.containsKey("Biscuit")) {
                    pastriesAndCakes.put("Biscuit", 1);
                } else {
                    int currentValue = pastriesAndCakes.get("Biscuit");
                    pastriesAndCakes.put("Biscuit", currentValue + 1);
                }
                liquids.poll();
                ingredients.pop();

            } else if (liquids.peek() + ingredients.peek() == 50) {
                if (!pastriesAndCakes.containsKey("Cake")) {
                    pastriesAndCakes.put("Cake", 1);
                } else {
                    int currentValue = pastriesAndCakes.get("Cake");
                    pastriesAndCakes.put("Cake", currentValue + 1);
                }
                liquids.poll();
                ingredients.pop();

            } else if (liquids.peek() + ingredients.peek() == 75) {
                if (!pastriesAndCakes.containsKey("Pastry")) {
                    pastriesAndCakes.put("Pastry", 1);
                } else {
                    int currentValue = pastriesAndCakes.get("Pastry");
                    pastriesAndCakes.put("Pastry", currentValue + 1);
                }
                liquids.poll();
                ingredients.pop();

            } else if (liquids.peek() + ingredients.peek() == 100) {
                if (!pastriesAndCakes.containsKey("Pie")) {
                    pastriesAndCakes.put("Pie", 1);
                } else {
                    int currentValue = pastriesAndCakes.get("Pie");
                    pastriesAndCakes.put("Pie", currentValue + 1);
                }
                liquids.poll();
                ingredients.pop();

            } else {
                liquids.poll();
                ingredients.push(ingredients.pop() + 3);
            }
        }

//        if (pastriesAndCakes.entrySet().stream().filter(x -> x.getValue() == 0).findAny().isPresent()) {
//            System.out.println("What a pity! You didn't have enough materials to cook everything.");
//        } else {
//            System.out.println("Great! You succeeded in cooking all the food!");
//        }

        if (pastriesAndCakes.entrySet().stream().anyMatch(x -> x.getValue() == 0)) {
            System.out.println("What a pity! You didn't have enough materials to cook everything.");
        } else {
            System.out.println("Great! You succeeded in cooking all the food!");
        }


        List<String> liquidsAsString = new ArrayList<>();
        System.out.print("Liquids left: ");
        if (liquids.isEmpty()) {
            System.out.println("none");
        } else {
            for (int number : liquids) {
                liquidsAsString.add(String.valueOf(number));
            }
            System.out.println(String.join(", ", liquidsAsString));
        }

        List<String> ingredientsAsString = new ArrayList<>();
        System.out.print("Ingredients left: ");
        if (ingredients.isEmpty()) {
            System.out.println("none");
        } else {
            for (int number : ingredients) {
                ingredientsAsString.add(String.valueOf(number));
            }
            System.out.println(String.join(", ", ingredientsAsString));
        }

        pastriesAndCakes.forEach((key, value) -> System.out.printf("%s: %d%n", key, value));
    }
}
