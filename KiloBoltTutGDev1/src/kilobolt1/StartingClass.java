package kilobolt1;

import java.applet.Applet;
import java.awt.Color;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.net.URL;
import java.util.ArrayList;

import kilobolt1.framework.Animation;
import IqbalUtils.Logging;

/**
 * Kilobolt tutorials
 * 
 * @author sinbad
 *
 */

public class StartingClass extends Applet implements Runnable, KeyListener {

	private Robot robot;
	private Heliboy hb1, hb2;
	private Image image, character, character2, character3, background,
			currentSprite, characterJumped, characterDown, heliboy, heliboy2,
			heliboy3, heliboy4, heliboy5;
	public static Image tileOcean, tileDirt;
	private Graphics second;
	private URL base;
	private static Background bg1, bg2;
	private Animation anim, hanim;
	private ArrayList<Tiles> tilearray = new ArrayList<Tiles>();

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.applet.Applet#init()
	 */
	@Override
	public void init() {

		System.out.println("Init");

		setSize(800, 480);
		setBackground(Color.black);
		setFocusable(true);
		Frame frame = (Frame) this.getParent().getParent();
		frame.setTitle("Q-Bot Alpha");

		addKeyListener(this);

		try {
			base = getDocumentBase();
		} catch (Exception e) {
			// TODO: handle exception
		}

		character = getImage(base, "data/character.png");
		character2 = getImage(base, "data/character2.png");
		character3 = getImage(base, "data/character3.png");

		characterDown = getImage(base, "data/down.png");
		characterJumped = getImage(base, "data/jumped.png");

		heliboy = getImage(base, "data/heliboy.png");
		heliboy2 = getImage(base, "data/heliboy2.png");
		heliboy3 = getImage(base, "data/heliboy3.png");
		heliboy4 = getImage(base, "data/heliboy4.png");
		heliboy5 = getImage(base, "data/heliboy5.png");

		background = getImage(base, "data/background.png");

		tileOcean = getImage(base, "data/tileocean.png");
		tileDirt = getImage(base, "data/tiledirt.png");

		anim = new Animation();
		anim.addFrame(character, 1250);
		anim.addFrame(character2, 50);
		anim.addFrame(character3, 50);
		anim.addFrame(character2, 50);

		hanim = new Animation();
		hanim.addFrame(heliboy, 100);
		hanim.addFrame(heliboy2, 100);
		hanim.addFrame(heliboy3, 100);
		hanim.addFrame(heliboy4, 100);
		hanim.addFrame(heliboy5, 100);
		hanim.addFrame(heliboy4, 100);
		hanim.addFrame(heliboy3, 100);
		hanim.addFrame(heliboy2, 100);

		currentSprite = anim.getImage();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.applet.Applet#start()
	 */
	@Override
	public void start() {

		System.out.println("Start");

		bg1 = new Background(0, 0);
		bg2 = new Background(2160, 0);

		// Intialize Tiles

		for (int i = 0; i < 200; i++) {
			for (int j = 0; j < 12; j++) {
				if (j == 11) {
					Tiles tile = new Tiles(i, j, 2);
					tilearray.add(tile);
				} else if (j == 10) {
					Tiles tile = new Tiles(i, j, 1);
					tilearray.add(tile);
				}
			}
		}
		hb1 = new Heliboy(340, 360);
		hb2 = new Heliboy(700, 360);

		robot = new Robot();

		Thread thread = new Thread(this);
		thread.start();

	}

	public void updateTiles() {
		for (int i = 0; i < tilearray.size(); i++) {
			Tiles t = tilearray.get(i);
			t.update();
		}
	}

	public void paintTiles(Graphics g) {
		for (int i = 0; i < tilearray.size(); i++) {
			Tiles t = tilearray.get(i);
			g.drawImage(t.getTileimage(), t.getTileX(), t.getTileY(), this);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.applet.Applet#stop()
	 */
	@Override
	public void stop() {

		System.out.println("Stop");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.applet.Applet#destroy()
	 */
	@Override
	public void destroy() {

		System.out.println("Destroy");
	}

	/**
	 * Heartbeat of the game which main task to -update character, -update
	 * environment, -repaint the scene
	 */

	@Override
	public void run() {
		System.out.println("Run");
		while (true) {
			robot.update();
			if (robot.isJumped()) {
				currentSprite = characterJumped;
			} else if (robot.isJumped() == false && robot.isDucked() == false) {
				currentSprite = anim.getImage();
			}
			

			ArrayList<Projectile> projList = robot.getProjlist();
			for (int i = 0; i < projList.size(); i++) {
				Projectile projectile = projList.get(i);
				if (projectile.isVisible() == true) {
					projectile.update();
				} else {
					projList.remove(i);
				}
			}
			
			updateTiles();
			hb1.update();
			hb2.update();
			bg1.update();
			bg2.update();
			animate();
			repaint();
			try {
				Thread.sleep(17);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public void animate() {
		System.out.println("Animate");
		anim.update(10);
		hanim.update(50);
	}

	@Override
	public void update(Graphics g) {
		System.out.println("Update");
		if (image == null) {
			image = createImage(this.getWidth(), this.getHeight());
			second = image.getGraphics();
		}

		second.setColor(getBackground());
		second.fillRect(0, 0, this.getWidth(), this.getHeight());
		second.setColor(getForeground());
		paint(second);

		g.drawImage(image, 0, 0, this);
	}

	@Override
	public void paint(Graphics g) {
		System.out.println("paint");
		g.drawImage(background, bg1.getBgX(), bg1.getBgY(), this);
		g.drawImage(background, bg2.getBgX(), bg2.getBgY(), this);

		paintTiles(g);
		
		ArrayList<Projectile> projlist = robot.getProjlist();
		for (int i = 0; i < projlist.size(); i++) {
			Projectile p = projlist.get(i);
			g.setColor(Color.YELLOW);

			g.fillRect(p.getX(), p.getY(), 20, 10);
		}
		g.drawImage(currentSprite, robot.getCenterX() - 61,
				robot.getCenterY() - 63, this);

		g.drawImage(hanim.getImage(), hb1.getCenterX() - 48,
				hb1.getCenterY() - 48, this);
		g.drawImage(hanim.getImage(), hb2.getCenterX() - 48,
				hb2.getCenterY() - 48, this);

	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyPressed(KeyEvent e) {
		switch (e.getKeyCode()) {
		case KeyEvent.VK_SPACE:
			robot.jump();
			break;
		case KeyEvent.VK_LEFT:
			robot.moveLeft();
			robot.setMovingLeft(true);
			break;
		case KeyEvent.VK_RIGHT:
			robot.moveRight();
			robot.setMovingRight(true);
			break;
		case KeyEvent.VK_UP:
			robot.moveTop();
			// Logging.Log("Pressed moving up Stop");
			break;
		case KeyEvent.VK_DOWN:
			currentSprite = characterDown;
			if (robot.isJumped() == false) {
				robot.setDucked(true);
				robot.setSpeedX(0);
			}
			break;
		case KeyEvent.VK_CONTROL:
			if (robot.isDucked() == false && robot.isJumped() == false) {
				robot.shoot();
			}
			break;

		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		switch (e.getKeyCode()) {
		case KeyEvent.VK_SPACE:
			Logging.Log("Released Jumping Stop");
			break;
		case KeyEvent.VK_LEFT:
			robot.stopLeft();
			break;
		case KeyEvent.VK_RIGHT:
			robot.stopRight();
			break;
		case KeyEvent.VK_UP:

			Logging.Log("Released moving up Stop");
			break;
		case KeyEvent.VK_DOWN:
			currentSprite = anim.getImage();
			robot.setDucked(false);
			break;
		}
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

}
