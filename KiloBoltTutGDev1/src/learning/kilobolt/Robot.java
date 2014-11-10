package learning.kilobolt;

import IqbalUtils.Logging;

public class Robot {

	int roboX = 100, roboY = 350, roboSpeedX = 0, roboSpeedY;

	public void update() {

		if (roboX < 0) {
			roboSpeedX = 0;
			roboX=0;
		}
		
		if (roboX>680) {
			roboSpeedX = 0;
			roboX=680;
		}
		
		// if (roboX < 800)
		roboX += roboSpeedX;
		Logging.Log(roboX + "");
	}

	void moveRight() {
		roboSpeedX = 6;
	}

	void moveLeft() {
		roboSpeedX = -6;
	}

	void moveStop() {
		roboSpeedX = 0;
	}

	/**
	 * @return the roboX
	 */
	public int getRoboX() {
		return roboX;
	}

	/**
	 * @return the roboY
	 */
	public int getRoboY() {
		return roboY;
	}

	/**
	 * @return the roboSpeedX
	 */
	public int getRoboSpeedX() {
		return roboSpeedX;
	}

	/**
	 * @return the roboSpeedY
	 */
	public int getRoboSpeedY() {
		return roboSpeedY;
	}

	/**
	 * @param roboX
	 *            the roboX to set
	 */
	public void setRoboX(int roboX) {
		this.roboX = roboX;
	}

	/**
	 * @param roboY
	 *            the roboY to set
	 */
	public void setRoboY(int roboY) {
		this.roboY = roboY;
	}

	/**
	 * @param roboSpeedX
	 *            the roboSpeedX to set
	 */
	public void setRoboSpeedX(int roboSpeedX) {
		this.roboSpeedX = roboSpeedX;
	}

	/**
	 * @param roboSpeedY
	 *            the roboSpeedY to set
	 */
	public void setRoboSpeedY(int roboSpeedY) {
		this.roboSpeedY = roboSpeedY;
	}

}
