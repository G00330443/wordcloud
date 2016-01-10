package ie.gmit.wordcloud;

import java.awt.*;
import java.awt.image.BufferedImage;

import ie.gmit.collision.Collidable;
import ie.gmit.collision.CollisionChecker;
import ie.gmit.collision.Position;
import ie.gmit.image.CollisionRaster;

//文字类   颜色  绘制
public class Word implements Collidable {

    private final CollisionChecker CC;

    private final String word;

    private final Color color;

    private Position position = new Position(0, 0);

    private BufferedImage bufferedImage;

    private CollisionRaster collisionRaster;
    
    public int getX() {
        return position.getX();
    }

    public void setX(int x) {
        position.setX(x);
    }

    public int getY() {
        return position.getY();
    }

    public void setY(int y) {
        position.setY(y);
    }
    

    public int getWidth() {
        return bufferedImage.getWidth();
    }

    public int getHeight() {
        return bufferedImage.getHeight();
    }

    public Word(String word, Color color, FontMetrics fontMetrics, CollisionChecker collisionChecker) {
        this.word = word;
        this.color = color;
        this.CC = collisionChecker;
       
        final int width = fontMetrics.stringWidth(word);
        
        final int maxDescent = fontMetrics.getMaxDescent();
        final int maxAscent = fontMetrics.getMaxAscent();

        fontMetrics.getHeight();

        this.bufferedImage = new BufferedImage(width, maxAscent, BufferedImage.TYPE_INT_ARGB);
        
        //图片
        final Graphics2D graphics = (Graphics2D) this.bufferedImage.getGraphics();
        
        graphics.setRenderingHint(
                RenderingHints.KEY_TEXT_ANTIALIASING,
                RenderingHints.VALUE_TEXT_ANTIALIAS_LCD_HRGB);
        
        // 设置颜色
        graphics.setColor(color);
        
        //设置字体
        graphics.setFont(fontMetrics.getFont());
        graphics.drawString(word, 0, maxAscent - maxDescent);

        this.collisionRaster = new CollisionRaster(this.bufferedImage);
    }

    public BufferedImage getBufferedImage() {
        return bufferedImage;
    }

    public void setBufferedImage(BufferedImage bufferedImage) {
        this.bufferedImage = bufferedImage;
        this.collisionRaster = new CollisionRaster(bufferedImage);
    }

    public String getWord() {
        return word;
    }

    public Position getPosition() {
        return position;
    }

    @Override
    public CollisionRaster getCollisionRaster() {
        return collisionRaster;
    }

    @Override
    public boolean check(Collidable collidable) {
        return CC.check(this, collidable);
    }

    public void draw(CollisionRaster collisionRaster) {
        collisionRaster.mask(collisionRaster, position.getX(), position.getY());
    }

}
