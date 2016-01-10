package ie.gmit.word;


//�����ƵĴ�С����
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
