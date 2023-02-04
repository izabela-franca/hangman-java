package hangman.core;

import hangman.io.Input;

import java.util.List;
import java.util.Random;

public class Dictionary {

    private static final String FILE_PATH = "/hangman/resources/dictionary.txt";
    private static final Dictionary INSTANCE = new Dictionary();

    private final List<String> words;
    private final Random random = new Random();

    private Dictionary() {
        words = Input.readLinesFromFile(FILE_PATH);

    }

    public Word nextWord() {
        int randomIndex = random.nextInt(0, words.size());
        String text =  words.get(randomIndex);
        return new Word(text);
    }

    //Singleton
    public static Dictionary instance() {
        return INSTANCE;
    }
}
