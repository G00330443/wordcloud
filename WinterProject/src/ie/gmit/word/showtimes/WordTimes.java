package ie.gmit.word.showtimes;

import ch.lambdaj.Lambda;
import ie.gmit.settleword.DelBothSideSymbol;
import ie.gmit.settleword.FilterSymbol;
import ie.gmit.settleword.LowerCaseSet;
import ie.gmit.settleword.Setting;
import ie.gmit.word.AllFilter;
import ie.gmit.word.Filter;
import ie.gmit.word.StopWordFilter;
import ie.gmit.word.WordSizeSetting;
import ie.gmit.wordcloud.WordFrequency;
import ie.gmit.wordedit.SpaceWordEditer;
import ie.gmit.wordedit.WordEditer;

import org.apache.commons.io.IOUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;


import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import static ch.lambdaj.Lambda.on;
import static ch.lambdaj.Lambda.sort;

//统计出现次数
public class WordTimes {

    public static final String DEFAULT_ENCODING = "UTF-8";

   public static final int MAX_LENGTH = 32;

   public static final int MIN_LENGTH = 3;

    public static final int RETURN_WORD = 50;
 
    public static final long LOAD_TIMEOUT = 3000; 
    
    //把stopword放入Hashset里

    private final Set<String> stopWords = new HashSet<>();

    private WordEditer wordEditer = new SpaceWordEditer();

    
    //list 过滤的列表   
    
    private final List<Filter> filters = new ArrayList<>();

    private final List<Setting> sett = new ArrayList<>();

    //
    
    private int wordFrequencesToReturn = RETURN_WORD;

    private int maxWordLength = MAX_LENGTH;

    private int minWordLength = MIN_LENGTH;

    private String characterEncoding = DEFAULT_ENCODING;

    private long urlLoadTimeout = LOAD_TIMEOUT;

    public WordTimes() {
        this.sett.add(new DelBothSideSymbol());
        this.sett.add(new FilterSymbol());
        this.sett.add(new LowerCaseSet());
    }

    public List<WordFrequency> load(InputStream fileInputStream) throws IOException {
        return load(IOUtils.readLines(fileInputStream, characterEncoding));
    }

    public List<WordFrequency> load(File file) throws IOException {
        return this.load(new FileInputStream(file));
    }

    public List<WordFrequency> load(String filePath) throws IOException {
        return this.load(new File(filePath));
    }

    public List<WordFrequency> load(URL url) throws IOException {
        final Document doc = Jsoup.parse(url, (int) urlLoadTimeout);
        return load(Collections.singletonList(doc.body().text()));
    }

    public List<WordFrequency> load(final List<String> texts) {
        final List<WordFrequency> wordFrequencies = new ArrayList<>();

        final Map<String, Integer> cloud = buildWordFrequencies(texts, wordEditer);
        for(Map.Entry<String, Integer> wordCount : cloud.entrySet()) {
            wordFrequencies.add(new WordFrequency(wordCount.getKey(), wordCount.getValue()));
        }
        return takeTopFrequencies(wordFrequencies);
    }
    
    public List<WordFrequency> loadWordFrequencies(final List<WordFrequency> wflist) {
        return takeTopFrequencies(wflist);
    }
    
    private Map<String, Integer> buildWordFrequencies(List<String> texts, WordEditer wordEdit) {
        final Map<String, Integer> wordFrequencies = new HashMap<>();
        for(final String text : texts) {
            final List<String> words = filter(wordEdit.edit(text));

            for(final String word : words) {
                final String normalized = normalize(word);
                if (!wordFrequencies.containsKey(normalized)) {
                    wordFrequencies.put(normalized, 1);
                }
                wordFrequencies.put(normalized, wordFrequencies.get(normalized) + 1);
            }
        }
        return wordFrequencies;
    }

    private List<String> filter(final List<String> words) {
        final List<Filter> allFilters = new ArrayList<>();
        allFilters.add(new StopWordFilter(stopWords));
        allFilters.add(new WordSizeSetting(minWordLength, maxWordLength));
        allFilters.addAll(filters);
        final AllFilter compositeFilter = new AllFilter(allFilters);
        return Lambda.filter(compositeFilter, words);
    }

    private String normalize(final String word) {
        String s = word;
        for(Setting normalizer : sett) {
            s = normalizer.setting(s);
        }
        return s;
    }

    private List<WordFrequency> takeTopFrequencies(Collection<WordFrequency> wordCloudEntities) {
        if(wordCloudEntities.isEmpty()) { return Collections.emptyList(); }
        final List<WordFrequency> sorted = sort(wordCloudEntities, on(WordFrequency.class).getFrequency());
        Collections.reverse(sorted);
        return sorted.subList(0, Math.min(sorted.size(), wordFrequencesToReturn));
    }

    public void setStopWords(Collection<String> stopWords) {
        this.stopWords.clear();
        this.stopWords.addAll(stopWords);
    }

    public void setWordFrequencesToReturn(int wordFrequencesToReturn) {
        this.wordFrequencesToReturn = wordFrequencesToReturn;
    }

    public void setMinWordLength(int minWordLength) {
        this.minWordLength = minWordLength;
    }

    public void setMaxWordLength(final int maxWordLength) {
        this.maxWordLength = maxWordLength;
    }

    public void setWordEditer(WordEditer wordEditer) {
        this.wordEditer = wordEditer;
    }

    public void clearFilters() {
        this.filters.clear();
    }

    public void addFilter(final Filter filter) {
        this.filters.add(filter);
    }

    public void setFilter(final Filter filter) {
        this.filters.clear();
        this.filters.add(filter);
    }

    public void clearNormalizers() {
        this.sett.clear();
    }

    public void addNormalizer(final Setting normalizer) {
        this.sett.add(normalizer);
    }

    public void set(final Setting settt) {
        this.sett.clear();
        this.sett.add(settt);
    }

    public void setCharacterEncoding(String characterEncoding) {
        this.characterEncoding = characterEncoding;
    }

    public void setUrlLoadTimeout(final long urlLoadTimeout) {
        this.urlLoadTimeout = urlLoadTimeout;
    }
}
