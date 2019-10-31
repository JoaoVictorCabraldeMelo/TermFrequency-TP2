package unb.cic.tp2.styles.DeclaredIntentions;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;

import static java.util.Map.Entry.comparingByValue;

public class WordsController implements BookParser{

    private HashMap<String, Integer> counterOfWords;
    private String path;
    private List<String> Words;

    public WordsController(String path) {
        this.path = path;
        this.Words = Collections.emptyList();
        this.counterOfWords = new HashMap<>();

    }

    public String getPath() {
        return path;
    }

    public List<String> getWords() {
        return Words;
    }



    @Override
    public void getWordsFromSource() {

        ArrayList<String> newWords = new ArrayList<String>();

        try {
            List<String> lines = Files.readAllLines(Paths.get(path));
            for(String line: lines) {
                String[] split = line.replaceAll("[^a-zA-Z ]", "").toLowerCase().split("\\s+");
                newWords.addAll(Arrays.asList(split));
            }
        }
        catch(IOException e) {
            throw new RuntimeException(e.getMessage());
        }

        Words = newWords;
    }

    @Override
    public void prettyPrinter() {
        for(String word:getWords()){
            System.out.println(word);
        }
    }

    public void setWords(List<String> words) {
        Words = words;
    }

    //@assignable Words
    public void countWords(){
        for(String word:Words){
            if(!counterOfWords.containsKey(word)){
                counterOfWords.put(word,1);
            } else {
                counterOfWords.computeIfPresent(word, (k, v) -> v + 1);
            }
        }

        counterOfWords = counterOfWords.entrySet().stream().sorted(comparingByValue(Comparator.reverseOrder())).collect(Collectors.toMap(
                Map.Entry::getKey,Map.Entry::getValue, (e1, e2) -> e1, LinkedHashMap::new
        ));

    }

    public void prettyPrinterHashMap(){
        counterOfWords.entrySet().forEach(word -> {
            System.out.println(word.getKey() + " = " + word.getValue());
        });
    }
}
