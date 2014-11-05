package kilobolt1;

public class Projectile {

	private int x, y, speedX;
	private boolean visible;

	public Projectile(int x, int y) {

		this.x = x;
		this.y = y;
		speedX = 7;
		visible=true;
	}

	public void update() {
		x += speedX;
		if (x > 800) {
			visible = false;
		}
	}

	/**
	 * @return the x
	 */
	public int getX() {
		return x;
	}

	/**
	 * @return the y
	 */
	public int getY() {
		return y;
	}

	/**
	 * @return the speedX
	 */
	public int getSpeedX() {
		return speedX;
	}

	/**
	 * @return the visible
	 */
	public boolean isVisible() {
		return visible;
	}

	/**
	 * @param x
	 *            the x to set
	 */
	public void setX(int x) {
		this.x = x;
	}

	/**
	 * @param y
	 *            the y to set
	 */
	public void setY(int y) {
		this.y = y;
	}

	/**
	 * @param speedX
	 *            the speedX to set
	 */
	public void setSpeedX(int speedX) {
		this.speedX = speedX;
	}

	/**
	 * @param visible
	 *            the visible to set
	 */
	public void setVisible(boolean visible) {
		this.visible = visible;
	}

}
