import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.function.BiFunction;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class HangmanModel {

    List<List<String>> field = new LinkedList<>();
    String[] words = {"INFORMATIK", "PROGRAMMIEREN", "PRAKTIKUM", "HELLO WORLD", "PIZZA", "ACER", "DOENER", "LOS ANGELES", "LUEBECK", "RECHNERSTRUKTUREN", "HAARWAX", "KRAFTFAHRZEUGHAFTPFLICHTVERSICHERUNG", "DONAUDAMPFSCHIFFFAHRTSGESELLSCHAFT", "PROTEINBIOSYNTHESE", "JAVA"};
    String word = randomWord();
    String maskedWord = maskedRandomWord();
    String correctChars = "";
    String falseChars = "";
    int life = 7;


    public HangmanModel() {
        int i = 0;

        for (int row = 0; row < 5; row++) {
            field.add(new LinkedList<>());
            for (int col = 0; col < 6; col++) {
                field.get(row).add("" + (char) ('A' + i++));
            }
        }

        field.get(field.size() - 1).set(field.get(field.size() - 1).size() - 1, " ");
        field.get(field.size() - 1).set(field.get(field.size() - 1).size() - 2, " ");
        field.get(field.size() - 1).set(field.get(field.size() - 1).size() - 3, " ");
        field.get(field.size() - 1).set(field.get(field.size() - 1).size() - 4, " ");
    }

    public String toString() {
        return field.stream()
                .map(row -> row.stream().collect(Collectors.joining("\t")))
                .collect((Collectors.joining("\n")));
    }

    public int getHeight() {
        return this.field.size();
    }

    public int getWidth() {
        return this.field.get(0).size();
    }

    public String get(int row, int col) {
        return this.field.get(row).get(col);
    }

    public String getWord() {
        return this.word;
    }

    public String getMaskedWord() {
        return this.maskedWord;
    }

    public String getCorrectChars() {
        return this.correctChars;
    }

    public String getFalseChars() {
        return this.falseChars;
    }

    public int getLife() {
        return this.life;
    }

    public String randomWord() {
        return this.words[(int) (Math.random() * this.words.length)];
    }

    public String maskedRandomWord() {
        return this.word.replaceAll(".", "*");
    }

    public String guessMaskedWord(String c) {
        if (!this.word.contains(c)) {
            this.life--;
            this.falseChars += c;
            return this.maskedWord;
        }

        this.correctChars += c;

        BiFunction<String, String, String> check = (s, buchstaben) -> buchstaben.contains(s) ? s : "*";

        maskedWord = Stream.of(word.split(""))
                .map(s -> check.apply(s, this.correctChars))
                .collect(Collectors.joining(""));

        return maskedWord;
    }

    public boolean isGuessed() {
        return Arrays.deepEquals(Stream.of(word.split("")).collect(Collectors.toList()).toArray(), Stream.of(maskedWord.split("")).collect(Collectors.toList()).toArray());
    }

    public boolean isDead() {
        return this.life == 0;
    }

    public void clear() {
        this.life = 7;
        this.correctChars = "";
        this.falseChars = "";
        this.word = randomWord();
        this.maskedWord = maskedRandomWord();
    }
}