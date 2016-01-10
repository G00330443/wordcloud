package ie.gmit.draw;


import java.awt.*;
import java.util.HashSet;
import java.util.Set;

import ie.gmit.collision.Position;
import ie.gmit.image.CollisionRaster;
import ie.gmit.wordcloud.Word;


//ªÊ÷∆Õº∆¨
public class Painting implements Paint {

    private static final Color PAINT_COLOR = Color.YELLOW;
    
    private RecPaint reCpaint=new RecPaint();
    
    //ªÊª≠
    public void paint(final Word word, final int pp) {
        if(pp <= 0) { return; }
        reCpaint.paint(word, pp);

        final CollisionRaster CR = word.getCollisionRaster();

        final Set<Position> toPaint = new HashSet<>();
        final int width = CR.getWidth();
        final int height = CR.getHeight();

        for(int y = 0; y < height; y++) {
            for(int x = 0; x < width; x++) {
                if(sPaint(CR, x, y, pp)) {
                    toPaint.add(new Position(x, y));
              //      System.out.print(x+"--"+y);
                }
            }
        }
        for(Position paintPoint : toPaint) {
        	CR.setRGB(paintPoint.getX(), paintPoint.getY(), PAINT_COLOR.getRGB());
        }
    }

    private boolean sPaint(final CollisionRaster Cr, final int cx, final int cy, final int painting) {
        if(!Cr.isTransparent(cx, cy)) { 
        	return false; 
        	
        }

        for(int y = cy - painting; y <= cy + painting; y++) {
            for(int x = cx - painting; x <= cx + painting; x++) {
                if(x == cx && y == cy) { 
                	continue; 
                	}
                
                if(inBounds(Cr, x, y)) {
                    if(!Cr.isTransparent(x, y)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    //≈–∂œ «∑Ò‘⁄ƒ⁄≤ø
    private boolean inBounds(final CollisionRaster collisionRaster, final int x, final int y) {
        return (x >= 0) && (y >= 0) && (x < collisionRaster.getWidth() )&&( y < collisionRaster.getHeight());                  
    }
    
}