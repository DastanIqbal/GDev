package kilobolt1;

import java.awt.Rectangle;
import java.util.ArrayList;

public class Robot {

	// Constants are Here
	final int JUMPSPEED = -15;
	final int MOVESPEED = 5;

	private int centerX = 100;
	private int centerY = 377;
	
	private boolean jumped = false;
	private boolean movingLeft = false;
	private boolean movingRight = false;
	private boolean ducked = false;
	private boolean readyToFire = true;

	private static Background bg1 = StartingClass.getBg1();
	private static Background bg2 = StartingClass.getBg2();

	private int speedX = 0;
	private int speedY = 0;
	
	public static Rectangle rectUpper = new Rectangle(0, 0, 0, 0);	//Upper
	public static Rectangle rectLower = new Rectangle(0, 0, 0, 0);	//Lower
	public static Rectangle rectLeft = new Rectangle(0, 0, 0, 0);	//Left
	public static Rectangle rectRight = new Rectangle(0, 0, 0, 0);	//Right
	public static Rectangle yellowRed = new Rectangle(0, 0, 0, 0);	// Character Area 

	public static Rectangle footleft = new Rectangle(0, 0, 0, 0);	//left
	public static Rectangle footright = new Rectangle(0, 0, 0, 0);	//right

	
	
	private ArrayList<Projectile> projlist = new ArrayList<Projectile>();

	public void update() {

		// Character Moving
		if (speedX < 0) {
			// if speedX goes negative then it will set to positive around ~100
			centerX += speedX;
		}

		if (speedX == 0 || speedX < 0) {
			bg1.setSpeedX(0);
			bg2.setSpeedX(0);
		}

		if (centerX <= 200 && speedX > 0) {
			centerX += speedX;
		}

		if (speedX > 0 && centerX > 200) {
			bg1.setSpeedX(-MOVESPEED / 5);
			bg2.setSpeedX(-MOVESPEED / 5);
		}

		// Update Y Position
		centerY += speedY;

		// Handle Jump

		// Add 1 when character in air it will take the charachter downward
		
		speedY += 1;

		if (speedY > 3) {
			jumped = true;
		}

		// Prevents character don't go outside the screen
		if (centerX + speedX <= 60) {
			// Fix character x position
			centerX = 61;
		}

		//
		rectUpper.setRect(centerX - 34, centerY - 63, 68, 63);
		rectLower.setRect(rectUpper.getX(), rectUpper.getY() + 63, 68, 63);
		rectLeft.setRect(rectUpper.getX() - 26, rectUpper.getY() + 32, 26, 20);
		rectRight.setRect(rectUpper.getX() + 68, rectUpper.getY() + 32, 26, 20);
		yellowRed.setRect(centerX- 110, centerY - 110, 180, 180);
		
		footleft.setRect(centerX - 50, centerY + 20, 50, 15);
		footright.setRect(centerX, centerY + 20, 50, 15);

		
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
		if (isReadyToFire()) {
			Projectile projectile = new Projectile(centerX + 50, centerY - 25);
			projlist.add(projectile);
		}
	}

	/**
	 * @return the projlist
	 */
	public ArrayList<Projectile> getProjlist() {
		return projlist;
	}

	/**
	 * @return the readyToFire
	 */
	public boolean isReadyToFire() {
		return readyToFire;
	}

	/**
	 * @param readyToFire
	 *            the readyToFire to set
	 */
	public void setReadyToFire(boolean readyToFire) {
		this.readyToFire = readyToFire;
	}

}
