package unb.cic.tp2.styles.DeclaredIntentions;

import java.util.stream.Collectors;


public class WordsManager {
    public static void main(String[] args) {
        try {
            WordsController manager = new WordsController("/home/joao/IdeaProjects/Studies/src/main/resources/AliceInWonderLand.txt");
            manager.getWordsFromSource();
            StopWordsController managerStopWords = new StopWordsController("/home/joao/IdeaProjects/Studies/src/main/resources/StopWords.txt");
            managerStopWords.getStopWordsFromSource();

            manager.setWords(manager.getWords().stream()
                                               .filter(word -> !managerStopWords.getStopWords().contains(word)  )
                                               .collect(Collectors.toList()));


            manager.countWords();
            manager.prettyPrinterHashMap();

        } catch (Exception ex){
            ex.printStackTrace();
        }
    }
}
