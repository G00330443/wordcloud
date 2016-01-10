package ie.gmit.collision;

import ie.gmit.image.CollisionRaster;

public class ImageChecker implements CollisionChecker {
	
	private static final RecChecker REC_CHECKER = new RecChecker();
	
	public boolean check(Collidable image, Collidable image2) {
		//检查边界
		
		
		if(!REC_CHECKER.check(image, image2)) {
			return false;
		}

		final Position position = image.getPosition();
		final Position position2 = image2.getPosition();
		final CollisionRaster collisionRaster = image.getCollisionRaster();
		final CollisionRaster collisionRaster2 = image2.getCollisionRaster();

		// 获取
		int startX = Math.max(position.getX(), position2.getX());
		int endX = Math.min(position.getX() + image.getWidth(), position2.getX() + image2.getWidth());

		int startY = Math.max(position.getY(), position2.getY());
		int endY = Math.min(position.getY() + image.getHeight(), position2.getY() + image2.getHeight());

		for(int y = startY ; y < endY ; y++) {
			for(int x = startX ; x < endX ; x++) {
				// 表面
				if((!collisionRaster2.isTransparent(x - position2.getX(), y - position2.getY()))
						&& (!collisionRaster.isTransparent(x - position.getX(), y - position.getY()))) {
					return true;
				}
			}
		}
		return false;
	}
	
	
}
