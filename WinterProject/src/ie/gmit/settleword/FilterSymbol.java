package ie.gmit.settleword;

import java.util.regex.Pattern;

//Ìæ»»µôËùÓÐµÄ·ûºÅ
public class FilterSymbol implements Setting {

    private static final Pattern DEFAULT_PATTERN = Pattern.compile("\\.|:|;|\\(|\\)|\"|,|\\?|,|!|<|>|/");

    private static final String DEFAULT_REPLACE_WITH = "";

    private final Pattern replacePattern;

    private final String replaceWith;

    public FilterSymbol() {
        replacePattern = DEFAULT_PATTERN;
        replaceWith = DEFAULT_REPLACE_WITH;
    }

    public FilterSymbol(final Pattern replacePattern, final String replaceWith) {
        this.replacePattern = replacePattern;
        this.replaceWith = replaceWith;
    }

    @Override
    public String setting(String text) {
        return DEFAULT_PATTERN.matcher(text).replaceAll(DEFAULT_REPLACE_WITH);
    }

}
