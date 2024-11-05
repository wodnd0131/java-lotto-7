package lotto.view;

public class OutputView {
    public void print(String string) {
        System.out.println(string);
    }

    public void print(OutputMessage message) {
        System.out.println(message.getMessage());
    }

    public void print(OutputMessage message, Object... args) {
        System.out.printf(message.getMessage(), args);
    }
}