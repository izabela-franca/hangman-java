package hangman.core;

import hangman.io.Input;
import hangman.io.Output;

public class Game {

    public void start() {

        Dictionary dictionary = Dictionary.instance();
        Word word = dictionary.nextWord();
        Output.writeToConsole(word);

        while (true) {
            char letter = readLetter();

            if (word.reveal(letter)) {
                onHit();
            } else {
                onMiss();
            }

            Output.writeToConsole(word);
        }
    }

    private char readLetter() {

        while (true) {
            try {
                return validLetter(Input.readFromKeyboard("Letter: "));
            } catch (InvalidLetterException e) {
                Output.writeToConsole("ERROR: " + e.getMessage());
            }
        }
    }

    private char validLetter (String text) throws InvalidLetterException{
        String trimmedText = text.trim();

        if (trimmedText.length() == 0) {
            throw new InvalidLetterException("Empty characters are not allowed");
        } else if (trimmedText.length() > 1) {
            throw new InvalidLetterException("Insert only one letter");
        }

        char letter = text.charAt(0);

        if (!Character.isLetter(letter)) {
            throw new InvalidLetterException("Only letters are allowed");
        }

        return letter;
    }

    private void onHit() {
        Output.writeToConsole("You got it!");
    }

    private void onMiss() {
        Output.writeToConsole("Ops...Try again!");
    }
}
