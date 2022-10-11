package task_1;

import java.util.concurrent.atomic.AtomicInteger;

public class WordsChecker extends Thread {
    private final String[] array;
    private final int wordSize;
    private final AtomicInteger counter;
    private final TextTester firstTest;
    private final TextTester secondTest;
    private final TextTester thirdTest;

    public WordsChecker(String[] array, int wordSize, AtomicInteger counter, TextTester firstTest, TextTester secondTest, TextTester thirdTest) {
        this.array = array;
        this.wordSize = wordSize;
        this.counter = counter;
        this.firstTest = firstTest;
        this.secondTest = secondTest;
        this.thirdTest = thirdTest;
    }

    @Override
    public void run() {
        for (String s : array) {
            if (s.length() == wordSize) {
                if (firstTest.checkString(s) || secondTest.checkString(s) || thirdTest.checkString(s)) {
                    counter.incrementAndGet();
                }
            }
        }
    }
}