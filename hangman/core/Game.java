package hangman.core;

import hangman.io.Input;
import hangman.io.Output;

import java.util.HashSet;
import java.util.Set;

public class Game {

    private static final int MAX_CHANCES = 7;

    private int chancesLeft = MAX_CHANCES;

    private final Set<Character> lettersUsed = new HashSet<>();

    public void start() {

        Dictionary dictionary = Dictionary.instance();
        Word word = dictionary.nextWord();
        printWord(word);

        while (!word.revealed() && chancesLeft > 0) {
            char letter = readLetter();

            if (word.reveal(letter)) {
                onHit();
            } else {
                onMiss();
            }
            lettersUsed.add(letter);
            printWord(word);
        }
        onEnd(word);
    }

    private void printWord(Word word) {
        Output.writeToConsole();
        Output.writeToConsole(word);
        Output.writeToConsole();
    }

    private char readLetter() {

        while (true) {
            try {
                return validLetter(Input.readFromKeyboard("Letter"));
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

        char letter = trimmedText.charAt(0);

        if (!Character.isLetter(letter)) {
            throw new InvalidLetterException("Only letters are allowed");
        }

        char upperLetter = Character.toUpperCase(letter);

        if (lettersUsed.contains(upperLetter)) {
            throw new InvalidLetterException("You already tried this letter");
        }

        return upperLetter;
    }

    private void onHit() {
        Output.writeToConsole("You got it!");
    }

    private void onMiss() {
        chancesLeft--;
        Output.writeToConsole("Ops...Try again! " + chancesLeft + " guess(es) left.");
    }

    private void onEnd(Word word) {
        if (word.revealed()) {
            Output.writeToConsole("You won!");
        } else {
            Output.writeToConsole("The word was: " + word.show());
            Output.writeToConsole("Better luck next time :)");
        }

    }
}
