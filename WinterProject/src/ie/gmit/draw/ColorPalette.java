package ie.gmit.draw;

import java.awt.*;
import java.util.Random;

//ÑÕÉ«±à¼­
public class ColorPalette {

    private static final Random RANDOM = new Random();

    protected Color[] colors = {};

    private int next = 0;

    public ColorPalette(Color... colors) {
        this.colors = colors;
    }

    public Color next() {
        return colors[next++ % colors.length];
    }

    public Color randomNext() {
        return colors[RANDOM.nextInt(colors.length)];
    }

}
