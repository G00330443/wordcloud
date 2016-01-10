package ie.gmit.font;

import java.awt.*;


//ÔÆ×ÖÌå
public class CloudFont {

    private static final int DEFAULT_WEIGHT = 10;

    private final Font font;

    public CloudFont(String type, FontWeight weight) {
        this.font = new Font(type, weight.getWeight(), DEFAULT_WEIGHT);
    }
    
    public CloudFont(String type, int style, int weight) {
        this.font = new Font(type, style, weight);
    }
    
    public CloudFont(Font font) {
        this.font = font;
    }

	public Font getFont() {
        return this.font;
    }

}
