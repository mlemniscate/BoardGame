package ir.maktab;
import java.util.Scanner;

/*
* You can use Input class to get your input from console. When you use getInput() method of this class
* inputMessage shows in the console. If your value was no valid value warningMessage showed in the console and ask
* again for input value.
*/
public class Input {
    private final Scanner scanner;
    private String inputMessage;
    private final String warningMessage;
    private final int maxValue, minValue;
    private final int[] unAllowedValues;
    private int input;

    // Constructor of Input that take : inputMessage, warningMessage, maxValue, minValue
    public Input(String inputMessage, String warningMessage, int maxValue, int minValue, int[] unAllowedValues) {
        this.inputMessage = inputMessage;
        this.warningMessage = warningMessage;
        this.maxValue = maxValue;
        this.minValue = minValue;
        this.unAllowedValues = unAllowedValues;
        scanner = new Scanner(System.in);
    }

    // Constructor of Input that take : inputMessage, maxValue, minValue, warningInput has default value.
    public Input(String inputMessage, int maxValue, int minValue, int[] unAllowedValues) {
        this(inputMessage, "Input value is not valid.\nPlease try again!", maxValue, minValue, unAllowedValues);
    }

    // When we need an input we use this method to get an input from user
    public int getInputFromConsole() {
        setInput();
        return input;
    }

    // This method for set input but its private and we use it in getInput() method of Input class
    private void setInput() {
        while(true) {
            System.out.print(inputMessage);
            this.input = scanner.nextInt();
            if(this.input >= minValue &&
                    this.input <= maxValue &&
                    !hasNotAllowedValues())
                break;
            System.out.println(warningMessage);
        }
    }

//    Check that input equals to any of the unAllowedValues values.
    private boolean hasNotAllowedValues() {
        if (unAllowedValues != null)
            for (int unAllowedValue : unAllowedValues) if (input == unAllowedValue) return true;
        return false;
    }

    // set a new inputMessage
    public void setInputMessage(String inputMessage) {
        this.inputMessage = inputMessage;
    }
}
