package kilobolt1;

import java.awt.Rectangle;

import IqbalUtils.Logging;

public class Enemy {

	private int centerX, centerY, speedX, power;
	private Background bg1 = StartingClass.getBg1();
	public int health=5;
	public Rectangle r = new Rectangle(0, 0, 0, 0);

	public void update() {
		centerX += speedX;
		speedX = bg1.getSpeedX() * 5;
		r.setBounds(centerX - 25, centerY - 25, 50, 60);

		if (r.intersects(Robot.yellowRed)) {
			checkCollosion();
		}
	}

	private void checkCollosion() {
		if (r.intersects(Robot.footleft) || r.intersects(Robot.footright)
				|| r.intersects(Robot.rectLeft)
				|| r.intersects(Robot.rectRight)
				|| r.intersects(Robot.rectLower)
				|| r.intersects(Robot.rectUpper)) {

			Logging.Log("Collision");
		}
	}

	public void die() {

	}

	public void attack() {

	}

	/**
	 * @return the centerX
	 */
	public int getCenterX() {
		return centerX;
	}

	/**
	 * @return the centerY
	 */
	public int getCenterY() {
		return centerY;
	}

	/**
	 * @return the speedX
	 */
	public int getSpeedX() {
		return speedX;
	}

	/**
	 * @return the power
	 */
	public int getPower() {
		return power;
	}

	/**
	 * @return the bg1
	 */
	public Background getBg1() {
		return bg1;
	}

	/**
	 * @param centerX
	 *            the centerX to set
	 */
	public void setCenterX(int centerX) {
		this.centerX = centerX;
	}

	/**
	 * @param centerY
	 *            the centerY to set
	 */
	public void setCenterY(int centerY) {
		this.centerY = centerY;
	}

	/**
	 * @param speedX
	 *            the speedX to set
	 */
	public void setSpeedX(int speedX) {
		this.speedX = speedX;
	}

	/**
	 * @param power
	 *            the power to set
	 */
	public void setPower(int power) {
		this.power = power;
	}

	
	/**
	 * @param bg1
	 *            the bg1 to set
	 */
	public void setBg1(Background bg1) {
		this.bg1 = bg1;
	}

}
