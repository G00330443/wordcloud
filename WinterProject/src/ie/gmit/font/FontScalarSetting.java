package ie.gmit.font;

public class FontScalarSetting implements FontScalar {

    private final int minFont;
    private final int maxFont;

    public FontScalarSetting(int minFont, int maxFont) {
        this.minFont = minFont;
        this.maxFont = maxFont;
    }

    @Override
    public float scale(int value, int minValue, int maxValue) {
        double leftSpan = Math.sqrt(maxValue) - Math.sqrt(minValue);
        double rightSpan = maxFont - minFont;

      
        double valueScaled = (Math.sqrt(value) - Math.sqrt(minValue)) / leftSpan;

        
        return (float)(minFont + (valueScaled * rightSpan));
    }
}
