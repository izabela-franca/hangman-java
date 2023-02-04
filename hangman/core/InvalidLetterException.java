package hangman.core;

public class InvalidLetterException extends Exception{

    public InvalidLetterException(String message) {
        super(message);
    }
}
