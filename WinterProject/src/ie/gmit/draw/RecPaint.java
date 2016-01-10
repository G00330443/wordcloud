package ie.gmit.draw;

import java.awt.*;
import java.awt.image.BufferedImage;

import ie.gmit.wordcloud.Word;

public class RecPaint implements Paint {

 
    public void paint(Word word, int paint) {
        if(paint <= 0) { 
        	return; 
        	}

        final BufferedImage bufferedImage = word.getBufferedImage();
        final int width = bufferedImage.getWidth() + paint * 2;
        final int height = bufferedImage.getHeight() + paint * 2;

        final BufferedImage newBufferedImage = new BufferedImage(width, height, bufferedImage.getType());
        final Graphics graphics = newBufferedImage.getGraphics();
        graphics.drawImage(bufferedImage, paint, paint, null);

        word.setBufferedImage(newBufferedImage);
    }

}
