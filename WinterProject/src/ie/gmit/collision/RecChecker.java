package ie.gmit.collision;


//±ß½ç¼ì²é
public class RecChecker implements CollisionChecker {

    public boolean check(final Collidable image, Collidable image2) {
        final Position position = image.getPosition();
        final Position position2 = image2.getPosition();

        if((position.getX() + image.getWidth() < position2.getX()) || (position2.getX() + image2.getWidth() < position.getX())) {
            return false;
        }
        if((position.getY() + image.getHeight() < position2.getY()) || (position2.getY() + image2.getHeight() < position.getY())) {
            return false;
        }
        return true;
    }

}
