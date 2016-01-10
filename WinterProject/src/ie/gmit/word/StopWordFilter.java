package ie.gmit.word;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

//¹ýÂËµô´Ê´ÓstopWord¡£txt
public class StopWordFilter extends Filter {

    private final Set<String> stopWords = new HashSet<>();

    public StopWordFilter(final Collection<String> stopWords) {
        this.stopWords.addAll(stopWords);
    }

    public boolean apply(String word) {
        return !this.stopWords.contains(word.toLowerCase());
    }

}

