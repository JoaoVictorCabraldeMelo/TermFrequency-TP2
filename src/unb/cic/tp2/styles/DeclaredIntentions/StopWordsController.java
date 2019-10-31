package unb.cic.tp2.styles.DeclaredIntentions;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

public class StopWordsController implements StopWordsParser {

    private List<String> StopWords;
    private String path;

    public StopWordsController(String path) {
        this.path = path;
        this.StopWords = Collections.emptyList();
    }

    public List<String> getStopWords() {
        return StopWords;
    }

    public String getPath() {
        return path;
    }


    @Override
    public void getStopWordsFromSource() {
        ArrayList<String> newStopWords = new ArrayList<String>();
        try{
            List<String> lines = Files.readAllLines(Paths.get(path));
            for(String line:lines){
                String[] split = line.replaceAll("[^a-zA-z]","").toLowerCase().split("\\s+");
                newStopWords.addAll(Arrays.asList(split));
            }
        }catch (IOException ex){
            ex.getMessage();
        }

        StopWords = newStopWords;
    }


    @Override
    public void prettyPrinter() {
        for(String stopWord:StopWords){
            System.out.println(stopWord);
        }

    }
}
