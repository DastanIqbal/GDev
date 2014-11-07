package kilobolt1;

import java.applet.Applet;
import java.awt.Color;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
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

	private static Robot robot;
	public static int score = 0;
	public static Heliboy hb1, hb2;
	private Font font = new Font(null, Font.BOLD, 30);
	private Image image, character, character2, character3, background,
			currentSprite, characterJumped, characterDown, heliboy, heliboy2,
			heliboy3, heliboy4, heliboy5;
	public static Image tilegrassTop, tilegrassBot, tilegrassLeft,
			tilegrassRight, tileDirt;
	private Graphics second;
	private URL base;
	private static Background bg1, bg2;
	private Animation anim;
	public static Animation hanim;
	private ArrayList<Tiles> tilearray = new ArrayList<Tiles>();
	private ArrayList<Heliboy> enemyarray = new ArrayList<Heliboy>();

	enum GameState {
		RUNNING, DEAD
	}

	GameState state = GameState.RUNNING;
	private Thread thread;

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

		tilegrassTop = getImage(base, "data/tilegrasstop.png");
		tilegrassBot = getImage(base, "data/tilegrassbot.png");
		tilegrassLeft = getImage(base, "data/tilegrassleft.png");
		tilegrassRight = getImage(base, "data/tilegrassright.png");
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
		robot = new Robot();

		// Intialize Tiles

		try {
			loadMap("data/map1.txt");
		} catch (Exception e) {
			e.printStackTrace();
		}
//
//		hb1 = new Heliboy(340, 360);
//		hb2 = new Heliboy(700, 360);

		thread = new Thread(this);
		thread.start();

	}

	private void loadMap(String string) {
		ArrayList lines = new ArrayList();
		int width = 0;
		int height = 0;

		try {
			BufferedReader breader = new BufferedReader(new FileReader(string));
			while (true) {
				String line = breader.readLine();

				if (line == null) {
					breader.close();
					break;
				}

				if (!line.startsWith("!")) {
					lines.add(line);
					width = Math.max(width, line.length());
				}
			}

			height = lines.size();
			for (int i = 0; i < height; i++) {
				String line = (String) lines.get(i);
				for (int j = 0; j < width; j++) {
					if (j < line.length()) {
						char ch = line.charAt(j);
						if (Character.getNumericValue(ch) == 9) {
							Heliboy heli = new Heliboy(j, i);
							enemyarray.add(heli);
						} else {
							Tiles t = new Tiles(j, i,
									Character.getNumericValue(ch));
							tilearray.add(t);
						}
					}
				}
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void updateEnemy() {
		for (int i = 0; i < enemyarray.size(); i++) {
			Heliboy heli = enemyarray.get(i);
			heli.update();
		}
	}

	public void paintEnemy(Graphics g) {
		for (int i = 0; i < enemyarray.size(); i++) {
			Heliboy heli = enemyarray.get(i);
			g.drawImage(heli.getImage().getImage(), heli.getCenterX()-48,
					heli.getCenterY()-48, this);
		}
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

		if (state == GameState.RUNNING) {
			while (true) {
				robot.update();
				if (robot.isJumped()) {
					currentSprite = characterJumped;
				} else if (robot.isJumped() == false
						&& robot.isDucked() == false) {
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
				updateEnemy();
//				hb1.update();
//				hb2.update();
				bg1.update();
				bg2.update();
				animate();
				repaint();
				if (robot.getCenterY() > 500) {
					state = GameState.DEAD;

				}
				try {
					Thread.sleep(17);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
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
		if (state == GameState.RUNNING) {
			g.drawImage(background, bg1.getBgX(), bg1.getBgY(), this);
			g.drawImage(background, bg2.getBgX(), bg2.getBgY(), this);

			paintTiles(g);
			paintEnemy(g);
			
			ArrayList<Projectile> projlist = robot.getProjlist();
			for (int i = 0; i < projlist.size(); i++) {
				Projectile p = projlist.get(i);
				g.setColor(Color.YELLOW);

				g.fillRect(p.getX(), p.getY(), 20, 10);
			}

			g.drawRect((int) Robot.rectUpper.getX(),
					(int) Robot.rectUpper.getY(),
					(int) Robot.rectUpper.getWidth(),
					(int) Robot.rectUpper.getHeight());
			g.drawRect((int) Robot.rectLower.getX(),
					(int) Robot.rectLower.getY(),
					(int) Robot.rectLower.getWidth(),
					(int) Robot.rectLower.getHeight());
			g.drawRect((int) Robot.rectLeft.getX(),
					(int) Robot.rectLeft.getY(),
					(int) Robot.rectLeft.getWidth(),
					(int) Robot.rectLeft.getHeight());
			g.drawRect((int) Robot.rectRight.getX(),
					(int) Robot.rectRight.getY(),
					(int) Robot.rectRight.getWidth(),
					(int) Robot.rectRight.getHeight());
			g.drawRect((int) Robot.yellowRed.getX(),
					(int) Robot.yellowRed.getY(),
					(int) Robot.yellowRed.getWidth(),
					(int) Robot.yellowRed.getHeight());
			g.drawRect((int) Robot.footleft.getX(),
					(int) Robot.footleft.getY(),
					(int) Robot.footleft.getWidth(),
					(int) Robot.footleft.getHeight());
			g.drawRect((int) Robot.footright.getX(),
					(int) Robot.footright.getY(),
					(int) Robot.footright.getWidth(),
					(int) Robot.footright.getHeight());

			// currentSprite 122x126
			g.drawImage(currentSprite, robot.getCenterX() - 61,
					robot.getCenterY() - 63, this);
//			g.drawImage(hanim.getImage(), hb1.getCenterX() - 48,
//					hb1.getCenterY() - 48, this);
//			g.drawImage(hanim.getImage(), hb2.getCenterX() - 48,
//					hb2.getCenterY() - 48, this);

//			g.drawRect((int) hb1.r.getX(), (int) hb1.r.getY(),
//					(int) hb1.r.getWidth(), (int) hb1.r.getHeight());
//			g.drawRect((int) hb2.r.getX(), (int) hb2.r.getY(),
//					(int) hb2.r.getWidth(), (int) hb2.r.getHeight());

			g.setFont(font);
			g.setColor(Color.WHITE);
			g.drawString(score + "", 750, 30);
		} else if (state == GameState.DEAD) {
			g.setColor(Color.BLACK);
			g.fillRect(0, 0, 800, 480);
			g.setColor(Color.WHITE);
			g.drawString("DEAD", 340, 240);
		}

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
			// robot.moveTop();
			Logging.Log("Pressed moving up Stop");
			break;
		case KeyEvent.VK_DOWN:
			currentSprite = characterDown;
			if (robot.isJumped() == false) {
				robot.setDucked(true);
				robot.setSpeedX(0);
			}
			break;
		case KeyEvent.VK_CONTROL:
			if (robot.isDucked() == false && robot.isJumped() == false
					&& robot.isReadyToFire()) {
				robot.shoot();
				robot.setReadyToFire(false);
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
		case KeyEvent.VK_CONTROL:
			robot.setReadyToFire(true);
			break;
		case KeyEvent.VK_R:
			Logging.Log("Code for Restart");
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

	/**
	 * @return the robot
	 */
	public static Robot getRobot() {
		return robot;
	}

}
