package core;

public class Word {

    private static final String HIDDEN_CHAR = "_ ";

    private final char[] letters;
    private final char[] hiddenLetters;


    public Word(String text) {
        letters = text.toCharArray();
        hiddenLetters = HIDDEN_CHAR.repeat(letters.length).toCharArray();
    }

    @Override
    public String toString() {
        return String.valueOf(hiddenLetters);
    }
}
