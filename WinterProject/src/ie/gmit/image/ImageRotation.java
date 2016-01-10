package ie.gmit.image;

import java.awt.*;
import java.awt.image.BufferedImage;


//翻转图片
public class ImageRotation {

    private ImageRotation() {}

    public static BufferedImage rotate90(final BufferedImage bufferedImage) {
        return rotate(bufferedImage, Math.PI / 2);
    }

    public static BufferedImage rotateMinus90(final BufferedImage bufferedImage) {
        return rotate(bufferedImage, -Math.PI / 2);
    }

    
    //缓冲一个图片   有不同的角度
    public static BufferedImage rotate(BufferedImage bufferedImage, double theta) {
        if(theta == 0) { return bufferedImage; }

        final double sin = Math.abs(Math.sin(theta)), cos = Math.abs(Math.cos(theta));
        final int w = bufferedImage.getWidth();
        final int h = bufferedImage.getHeight();
        final int neww = (int) Math.floor(w * cos + h * sin);
        final int newh = (int) Math.floor(h * cos + w * sin);
        BufferedImage result = new BufferedImage(neww, newh, bufferedImage.getType());
        Graphics2D g = result.createGraphics();
        g.translate((neww - w) / 2, (newh - h) / 2);
        g.rotate(theta, w / 2, h / 2);
        g.drawRenderedImage(bufferedImage, null);
        g.dispose();

        return result;
    }

}
