package kilobolt1;

import java.awt.Rectangle;

public class Projectile {

	private int x, y, speedX;
	private boolean visible;
	private Rectangle rect;

	public Projectile(int x, int y) {

		this.x = x;
		this.y = y;
		speedX = 7;
		visible = true;
		rect = new Rectangle(0, 0, 0, 0);
	}

	public void update() {
		x += speedX;
		rect.setBounds(x, y, 50, 10);
		if (x > 800) {
			visible = false;
			rect = null;
		}

		if (x < 801) {
			checkCollission();
		}
	}

	private void checkCollission() {
		if (rect.intersects(StartingClass.hb1.r)) {
			visible = false;

			if (StartingClass.hb1.health > 0) {
				StartingClass.hb1.health -= 1;
			}

			if (StartingClass.hb1.health == 0) {
				StartingClass.hb1.setCenterX(-100);
				StartingClass.score += 5;
			}
		}
		if (rect.intersects(StartingClass.hb2.r)) {
			visible = false;

			if (StartingClass.hb2.health > 0) {
				StartingClass.hb2.health -= 1;
			}

			if (StartingClass.hb2.health == 0) {
				StartingClass.hb2.setCenterX(-100);
				StartingClass.score += 5;
			}
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
