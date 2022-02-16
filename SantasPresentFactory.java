package FirstExamPreparation;

import java.util.*;
import java.util.stream.Collectors;

public class SantasPresentFactory {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ArrayDeque<Integer> materials = new ArrayDeque<>(); //stack
        Arrays.stream(scanner.nextLine().split("\\s+")).map(Integer::parseInt)
                .forEach(materials::push);

        ArrayDeque<Integer> magicValues = Arrays.stream(scanner.nextLine().split("\\s+")) //queue
                .map(Integer::parseInt).collect(Collectors.toCollection(ArrayDeque::new));

        Map<String, Integer> presents = new TreeMap<>();
        presents.put("Doll", 0);
        presents.put("Wooden train", 0);
        presents.put("Teddy bear", 0);
        presents.put("Bicycle", 0);

        while (!materials.isEmpty() && !magicValues.isEmpty()) {
            if (materials.peek() == 0 || magicValues.peek() == 0) {
                if (materials.peek() == 0) {
                    materials.pop();
                }
                if (magicValues.peek() == 0) {
                    magicValues.poll();
                }
                continue;
            }
            int result = materials.peek() * magicValues.peek();
            if (result == 150) {
                int current = presents.get("Doll");
                presents.put("Doll", current + 1);
                materials.pop();
                magicValues.poll();
            } else if (result == 250) {
                int current = presents.get("Wooden train");
                presents.put("Wooden train", current + 1);
                materials.pop();
                magicValues.poll();
            } else if (result == 300) {
                int current = presents.get("Teddy bear");
                presents.put("Teddy bear", current + 1);
                materials.pop();
                magicValues.poll();
            } else if (result == 400) {
                int current = presents.get("Bicycle");
                presents.put("Bicycle", current + 1);
                materials.pop();
                magicValues.poll();
            } else if (result < 0) {
                int sumResult = materials.pop() + magicValues.poll();
                materials.push(sumResult);
            } else if (result > 0) {
                magicValues.poll();
                materials.push(materials.pop() + 15);
            }
        }

        if ((presents.get("Doll") > 0 && presents.get("Wooden train") > 0) ||
                (presents.get("Teddy bear") > 0 && presents.get("Bicycle") > 0)) {
            System.out.println("The presents are crafted! Merry Christmas!");
        } else {
            System.out.println("No presents this Christmas!");
        }

        if (!materials.isEmpty()) {
            List<String> leftMaterials = new ArrayList<>();
            for (Integer material : materials) {
                leftMaterials.add(String.valueOf(material));
            }
            System.out.print("Materials left: ");
            System.out.println(String.join(", ", leftMaterials));
        }

        if (!magicValues.isEmpty()) {
            List<String> leftMagicValues = new ArrayList<>();
            for (Integer magicValue : magicValues) {
                leftMagicValues.add(String.valueOf(magicValue));
            }
            System.out.print("Magic left: ");
            System.out.println(String.join(", ", leftMagicValues));
        }

        presents.entrySet().stream().filter(t -> t.getValue() > 0).forEach(t -> {
            System.out.printf("%s: %d%n", t.getKey(), t.getValue());
        });
    }
}
