package ie.gmit.background;

import java.awt.Dimension;
import java.awt.Point;
import java.awt.Rectangle;

import ie.gmit.collision.Position;


public class RecBackground implements Background {

    private final int x;
    
    private final int y;
    
    private final int width;

    private final int height;

  //创建一个图片
    public RecBackground(int width, int height) {
        this(0, 0, width, height);
    }
    
   //创建一个有高度长度 位置的长方形
    public RecBackground(int x, int y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }
    
    //使用 point 去创建
    public RecBackground(Point startingPoint, Dimension dimensions) {
        this(startingPoint.x, startingPoint.y, dimensions.width, dimensions.height);
    }
    
    
    public RecBackground(Rectangle rectangle) {
        this(rectangle.x, rectangle.y, rectangle.width, rectangle.height);
    }

    public boolean checkPosition(ie.gmit.collision.Collidable collidable) {
        final Position position = collidable.getPosition();
        return position.getX() >= x && 
               position.getX() + collidable.getWidth() < (x + width)  && 
               position.getY() >= y && 
               position.getY() + collidable.getHeight() < (y + height);
    }

}
