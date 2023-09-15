package Sudoku;

public class Builder {

    private static String message;

    public Builder(String message) {
        this.message = message;
    }

    public static String getMessage() {
        return message;
    }
}
