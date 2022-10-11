package task_1;

import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Predicate;


public class Main {
    private static final AtomicInteger counterForThree = new AtomicInteger();
    private static final AtomicInteger counterForFour = new AtomicInteger();
    private static final AtomicInteger counterForFive = new AtomicInteger();

    private static final int SMALL_WORDS_SIZE = 3;
    private static final int MIDDLE_WORDS_SIZE = 4;
    private static final int BIG_WORDS_SIZE = 5;

    public static void main(String[] args) throws InterruptedException {
        TextGenerator generator = new TextGenerator();
        Random random = new Random();

        String[] texts = new String[100_000];
        for (int i = 0; i < texts.length; i++) {
            texts[i] = generator.generateText("abc", 3 + random.nextInt(3));
        }

        Predicate<String> firstCondition = s -> s.equals(new StringBuilder(s).reverse().toString());
        Predicate<String> secondCondition = s -> s.chars().allMatch(ch -> ch == s.charAt(0));
        Predicate<String> thirdCondition = s -> {
            for (int i = 0; i < s.length() - 1; i++) {
                if (s.charAt(i) > s.charAt(i + 1)) {
                    return false;
                }
            }
            return true;
        };

       TextTester tester1 = new TextTester(firstCondition);
       TextTester tester2 = new TextTester(secondCondition);
       TextTester tester3 = new TextTester(thirdCondition);

       WordsChecker checkerForThree = new WordsChecker(texts, SMALL_WORDS_SIZE, counterForThree, tester1, tester2, tester3);
       WordsChecker checkerForFour = new WordsChecker(texts, MIDDLE_WORDS_SIZE, counterForFour, tester1, tester2, tester3);
       WordsChecker checkerForFive = new WordsChecker(texts, BIG_WORDS_SIZE, counterForFive, tester1, tester2, tester3);

       checkerForThree.start();
       checkerForFour.start();
       checkerForFive.start();
       checkerForThree.join();
       checkerForFour.join();
       checkerForFive.join();


       System.out.printf("Красивых слов с длиной - %d: %s шт. %n", SMALL_WORDS_SIZE, counterForThree);
       System.out.printf("Красивых слов с длиной - %d: %s шт. %n", MIDDLE_WORDS_SIZE, counterForFour);
       System.out.printf("Красивых слов с длиной - %d: %s шт. %n", BIG_WORDS_SIZE, counterForFive);

    }
}