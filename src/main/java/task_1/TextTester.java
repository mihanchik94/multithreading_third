package task_1;

import java.util.function.Predicate;

public class TextTester {
    private final Predicate<String> condition;

    public TextTester(Predicate<String> condition) {
        this.condition = condition;
    }

    public boolean checkString(String str) {
        return condition.test(str);
    }
}