package ie.gmit.word;


//对字云的大小控制
public class WordSizeSetting extends Filter {

    private final int minLength;

    private final int maxLength;

    public WordSizeSetting(final int minLength, final int maxLength) {
        this.minLength = minLength;
        this.maxLength = maxLength;
    }

  
    public boolean apply(String word) {
        return (word!=null) && (word.length() >= minLength )&&( word.length() < maxLength);
    }

}
