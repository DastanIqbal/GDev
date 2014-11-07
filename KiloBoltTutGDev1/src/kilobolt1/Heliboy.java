package kilobolt1;

import kilobolt1.framework.Animation;

public class Heliboy extends Enemy {

	private Animation image;

	public Heliboy(int centerX, int centerY) {
		setCenterX(centerX*40);
		setCenterY(centerY*40);
		setImage(StartingClass.hanim);
	}

	/**
	 * @return the image
	 */
	public Animation getImage() {
		return image;
	}

	/**
	 * @param image
	 *            the image to set
	 */
	public void setImage(Animation image) {
		this.image = image;
	}

}
