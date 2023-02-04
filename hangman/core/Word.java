package hangman.core;

public class Word {

    private static final char HIDDEN_CHAR = '_';

    private final char[] letters;
    private final char[] hiddenLetters;


    public Word(String text) {
        letters = text.toCharArray();
        hiddenLetters = String.valueOf(HIDDEN_CHAR).repeat(letters.length).toCharArray();
    }

    @Override
    public String toString() {
        return String.valueOf(hiddenLetters);
    }

    public boolean reveal(char letter) {
        boolean replaced = false;

        for (int i = 0; i < letters.length; i++) {
            if (letters[i] == letter) {
                hiddenLetters[i] = letter;
                replaced = true;
            }
        }
        return replaced;
    }
}
