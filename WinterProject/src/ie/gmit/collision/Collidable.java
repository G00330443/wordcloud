package ie.gmit.collision;

import ie.gmit.image.CollisionRaster;

public interface Collidable{
    boolean check(Collidable collidable);
    Position getPosition();
    int getWidth();
    int getHeight();
    CollisionRaster getCollisionRaster();
}
