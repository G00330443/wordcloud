package ie.gmit.background;


import javax.imageio.ImageIO;

import ie.gmit.collision.Collidable;
import ie.gmit.collision.Position;
import ie.gmit.image.CollisionRaster;

import java.awt.Dimension;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

public class RandomBackground implements Background {

    private final CollisionRaster CR;

    private final RecBackground RB;

    //����һ��ͼƬ
    //throws exception
    //
    public RandomBackground(final InputStream imageInputStream) throws IOException {
        final BufferedImage bufferedImage = ImageIO.read(imageInputStream);
        this.CR = new CollisionRaster(bufferedImage);
        this.RB = new RecBackground(bufferedImage.getWidth(), bufferedImage.getHeight());
    }
    
    //ʹ���������ص�ͼƬ
 
    public RandomBackground(final File file) throws IOException {
        this(new FileInputStream(file));
    }

   
    //ʹ�� ͼƬ·��ȥ ����ͼƬ
    
    public RandomBackground(final String filepath) throws IOException {
        this(new File(filepath));
    }

   //���ͼƬ�߽�
    public boolean checkPosition(Collidable Image) {
       
        if(!this.RB.checkPosition(Image)) {
            return false;
        }
        final Position position = Image.getPosition();
      //ѡ��ʼ������ĵ�
        int startX = Math.max(position.getX(), 0);
        int endX = Math.min(position.getX() + Image.getWidth(), CR.getWidth());

        int startY = Math.max(position.getY(), 0);
        int endY = Math.min(position.getY() + Image.getHeight(), CR.getHeight());

        for(int y = startY ; y < endY ; y++) {
            for(int x = startX ; x < endX ; x++) {
                
                if(CR.isTransparent(x - 0, y - 0) &&
                        !Image.getCollisionRaster().isTransparent(x - position.getX(), y - position.getY())) {
                    return false;
                }
            }
        }
        return true;
    }
}



