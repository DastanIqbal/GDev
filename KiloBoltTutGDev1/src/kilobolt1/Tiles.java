package kilobolt1;

import java.awt.Image;
import java.awt.Rectangle;

public class Tiles {

	private int tileX, tileY, speedX, type;
	private Image tileimage;

	private Robot robot = StartingClass.getRobot();
	private Background bg = StartingClass.getBg1();

	private Rectangle rect;

	public Tiles(int x, int y, int type) {
		tileX = x * 40;
		tileY = y * 40;
		this.type = type;

		rect = new Rectangle();

		if (type == 5) {
			tileimage = StartingClass.tileDirt;
		} else if (type == 8) {
			tileimage = StartingClass.tilegrassTop;
		} else if (type == 4) {
			tileimage = StartingClass.tilegrassLeft;
		} else if (this.type == 6) {
			tileimage = StartingClass.tilegrassRight;
		} else if (type == 2) {
			tileimage = StartingClass.tilegrassBot;
		} else {
			this.type = 0;
		}

	}

	public void update() {
		speedX = bg.getSpeedX() * 5;
		tileX += speedX;
		rect.setBounds(tileX, tileY, 40, 40);

		if (rect.intersects(Robot.yellowRed) && type != 0) {
			checkVerticalCollision(Robot.rectUpper, Robot.rectLower);
			checkSideCollision(Robot.rectLeft, Robot.rectRight, Robot.footleft,
					Robot.footright);
		}
	}


	public void checkVerticalCollision(Rectangle rtop, Rectangle rbot) {
		if (rtop.intersects(rect)) {
			// Robot upper part collide
			System.out.println("Upper Collision");
		}

		if (rbot.intersects(rect) && type == 8) {
			robot.setJumped(false);
			robot.setSpeedY(0);
			robot.setCenterY(tileY - 63);
		}
	}

	public void checkSideCollision(Rectangle rleft, Rectangle rright,
			Rectangle lfoot, Rectangle rfoot) {
		if (type != 5 && type != 2 && type != 0) {

			if (rleft.intersects(rect)) {
				robot.setCenterX(tileX + 102);
				robot.setSpeedX(0);
			} else if (lfoot.intersects(rect)) {
				robot.setCenterX(tileX + 85);
				robot.setSpeedX(0);
			}

			if (rright.intersects(rect)) {
				robot.setCenterX(tileX - 62);
				robot.setSpeedX(0);
			} else if (rfoot.intersects(rect)) {
				robot.setCenterX(tileX - 45);
				robot.setSpeedX(0);
			}
		}
	}

	/**
	 * @return the tileX
	 */
	public int getTileX() {
		return tileX;
	}

	/**
	 * @return the tileY
	 */
	public int getTileY() {
		return tileY;
	}

	/**
	 * @return the speedX
	 */
	public int getSpeedX() {
		return speedX;
	}

	/**
	 * @return the tileimage
	 */
	public Image getTileimage() {
		return tileimage;
	}

	/**
	 * @param tileX
	 *            the tileX to set
	 */
	public void setTileX(int tileX) {
		this.tileX = tileX;
	}

	/**
	 * @param tileY
	 *            the tileY to set
	 */
	public void setTileY(int tileY) {
		this.tileY = tileY;
	}

	/**
	 * @param speedX
	 *            the speedX to set
	 */
	public void setSpeedX(int speedX) {
		this.speedX = speedX;
	}

	/**
	 * @param tileimage
	 *            the tileimage to set
	 */
	public void setTileimage(Image tileimage) {
		this.tileimage = tileimage;
	}

}
