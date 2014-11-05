package kilobolt1;

import java.util.ArrayList;

import IqbalUtils.Logging;

public class Robot {

	// Constants are Here
	final int JUMPSPEED = -15;
	final int MOVESPEED = 5;
	final int GROUND = 382;

	private int centerX = 100;
	private int centerY = GROUND;

	private boolean jumped = false;
	private boolean movingLeft = false;
	private boolean movingRight = false;
	private boolean ducked = false;

	private static Background bg1 = StartingClass.getBg1();
	private static Background bg2 = StartingClass.getBg2();

	private int speedX = 0;
	private int speedY = 1;

	private ArrayList<Projectile> projlist = new ArrayList<Projectile>();

	public void update() {

		// Character Moving
		if (speedX < 0) {
			// if speedX goes negative then it will set to positive around ~100
			centerX += speedX;
		}

		if (speedX == 0 && speedX < 0) {
			bg1.setSpeedX(0);
			bg2.setSpeedX(0);
		}

		if (centerX <= 200 && speedX > 0) {
			centerX += speedX;
		}

		if (speedX > 0 && centerX > 200) {
			bg1.setSpeedX(-MOVESPEED);
			bg2.setSpeedX(-MOVESPEED);
		}

		// Update Y Position
		centerY += speedY;
		if (centerY + speedY >= GROUND) {
			centerY = GROUND;
		}

		// Handle Jump
		if (jumped) {
			// Add 1 when character in air it will take the charachter downward
			speedY += 1;

			if (centerY + speedY >= GROUND) {
				centerY = GROUND;
				jumped = false;
				speedY = 0;
			}
		}

		// Prevents character don't go outside the screen
		if (centerX + speedX <= 60) {
			// Fix character x position
			centerX = 61;
		}
	}

	public void moveRight() {
		if (ducked == false) {
			speedX = MOVESPEED;
		}
	}

	public void moveLeft() {
		if (ducked == false) {
			speedX = -MOVESPEED;
		}
	}

	public void stopRight() {
		setMovingRight(false);
		stop();
	}

	public void stopLeft() {
		setMovingLeft(false);
		stop();
	}

	public void moveTop() {
		speedY += -6;
	}

	public void moveDown() {
		speedY += 6;
	}

	/**
	 * @return the centerX
	 */
	public int getCenterX() {
		return centerX;
	}

	/**
	 * @return the jumped
	 */
	public boolean isJumped() {
		return jumped;
	}

	/**
	 * @return the speedX
	 */
	public int getSpeedX() {
		return speedX;
	}

	/**
	 * @return the speedY
	 */
	public int getSpeedY() {
		return speedY;
	}

	/**
	 * @param jumped
	 *            the jumped to set
	 */
	public void setJumped(boolean jumped) {
		this.jumped = jumped;
	}

	/**
	 * @param speedX
	 *            the speedX to set
	 */
	public void setSpeedX(int speedX) {
		this.speedX = speedX;
	}

	/**
	 * @param speedY
	 *            the speedY to set
	 */
	public void setSpeedY(int speedY) {
		this.speedY = speedY;
	}

	/**
	 * @param centerX
	 *            the centerX to set
	 */
	public void setCenterX(int centerX) {
		this.centerX = centerX;
	}

	/**
	 * @return the centerY
	 */
	public int getCenterY() {
		return centerY;
	}

	/**
	 * @param centerY
	 *            the centerY to set
	 */
	public void setCenterY(int centerY) {
		this.centerY = centerY;
	}

	private void stop() {
		if (isMovingRight() == false && isMovingLeft() == false) {
			speedX = 0;
		}

		if (isMovingRight() == false && isMovingLeft() == true) {
			moveLeft();
		}

		if (isMovingRight() == true && isMovingLeft() == false) {
			moveRight();
		}

	}

	/**
	 * @return the jUMPSPEED
	 */
	public int getJUMPSPEED() {
		return JUMPSPEED;
	}

	/**
	 * @return the mOVESPEED
	 */
	public int getMOVESPEED() {
		return MOVESPEED;
	}

	/**
	 * @return the gROUND
	 */
	public int getGROUND() {
		return GROUND;
	}

	/**
	 * @return the movingLeft
	 */
	public boolean isMovingLeft() {
		return movingLeft;
	}

	/**
	 * @return the movingRight
	 */
	public boolean isMovingRight() {
		return movingRight;
	}

	/**
	 * @return the ducked
	 */
	public boolean isDucked() {
		return ducked;
	}

	/**
	 * @return the bg1
	 */
	public static Background getBg1() {
		return bg1;
	}

	/**
	 * @return the bg2
	 */
	public static Background getBg2() {
		return bg2;
	}

	/**
	 * @param movingLeft
	 *            the movingLeft to set
	 */
	public void setMovingLeft(boolean movingLeft) {
		this.movingLeft = movingLeft;
	}

	/**
	 * @param movingRight
	 *            the movingRight to set
	 */
	public void setMovingRight(boolean movingRight) {
		this.movingRight = movingRight;
	}

	/**
	 * @param ducked
	 *            the ducked to set
	 */
	public void setDucked(boolean ducked) {
		this.ducked = ducked;
	}

	/**
	 * @param bg1
	 *            the bg1 to set
	 */
	public static void setBg1(Background bg1) {
		Robot.bg1 = bg1;
	}

	/**
	 * @param bg2
	 *            the bg2 to set
	 */
	public static void setBg2(Background bg2) {
		Robot.bg2 = bg2;
	}

	public void jump() {
		if (!jumped) {
			speedY = JUMPSPEED;
			jumped = true;
		}
	}

	public void shoot() {
		Projectile projectile = new Projectile(centerX + 50, centerY - 25);
		projlist.add(projectile);
	}

	/**
	 * @return the projlist
	 */
	public ArrayList<Projectile> getProjlist() {
		return projlist;
	}
	
	
}
