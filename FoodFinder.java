package FirstExamPreparation;

import java.util.*;
import java.util.stream.Collectors;

public class FoodFinder {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String[] vowelsArr = scanner.nextLine().split("\\s+");
        ArrayDeque<String> vowels = new ArrayDeque<>(); //queue

        for (String vowel : vowelsArr) {
            if (!vowels.contains(vowel)) {
                vowels.offer(vowel);
            }
        }

        String[] consonantsArr = scanner.nextLine().split("\\s+");
        ArrayDeque<String> consonants = new ArrayDeque<>(); //stack

        for (String consonant : consonantsArr) {
            if (!consonants.contains(consonant)) {
                consonants.push(consonant);
            }
        }

        Map<String, Integer> words = new LinkedHashMap<>();
        words.put("pear", 0);
        words.put("flour", 0);
        words.put("pork", 0);
        words.put("olive", 0);

        while (!consonants.isEmpty()) {
            String  currentVowel = vowels.peek();
            String currentConsonant = consonants.peek();
            vowels.poll();
            vowels.offer("-");
            for (String word : words.keySet()) {
                if (word.contains(currentConsonant)) {
                    int currentValue = words.get(word);
                    words.put(word, currentValue + 1);
                }
                if (word.contains(currentVowel)) {
                    int currentValue = words.get(word);
                    words.put(word, currentValue + 1);
                }
            }
            consonants.pop();
        }

        List<String> foundWords = words.entrySet().stream().filter(w -> w.getKey().length() == w.getValue())
                .map(w -> w.getKey())
                .collect(Collectors.toList());

        System.out.printf("Words found: %d%n", foundWords.size());
        for (String foundWord : foundWords) {
            System.out.println(foundWord);
        }
    }
}
