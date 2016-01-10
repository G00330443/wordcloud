package ie.gmit.collision;

import ie.gmit.image.CollisionRaster;

//±ß½çÅö×²
public class ImageCollidable implements Collidable {

    private static final ImageChecker IMAGE_CHECKER = new ImageChecker();

    private final Position position;

    private final CollisionRaster collisionRaster;

    public ImageCollidable(CollisionRaster collisionRaster, int x, int y) {
        this.collisionRaster = collisionRaster;
        this.position = new Position(x, y);
    }

    public int getX() {
        return position.getX();
    }

    public int getY() {
        return position.getY();
    }

    @Override
    public boolean check(Collidable collidable) {
        return IMAGE_CHECKER.check(this, collidable);
    }

    @Override
    public Position getPosition() {
        return position;
    }

    @Override
    public int getWidth() {
        return collisionRaster.getWidth();
    }

    @Override
    public int getHeight() {
        return collisionRaster.getHeight();
    }

    @Override
    public CollisionRaster getCollisionRaster() {
        return collisionRaster;
    }

}
