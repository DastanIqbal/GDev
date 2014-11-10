package learning.kilobolt;

import java.applet.Applet;
import java.awt.Color;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.net.URL;

public class MainApp extends Applet implements Runnable, KeyListener {

	private Robot robot;
	private Image image, character, character2, character3, currentsprite;
	private URL base;
	private Graphics second;
	private MyAnimation canim;

	@Override
	public void init() {
		setSize(800, 480);
		setBackground(Color.WHITE);
		setFocusable(true);
		Frame f = new Frame();
		f.setTitle("Move Robot");

		addKeyListener(this);

		base = getDocumentBase();

		character = getImage(base, "data/character.png");
		character2 = getImage(base, "data/character2.png");
		character3 = getImage(base, "data/character3.png");

		canim = new MyAnimation();
		canim.addFrames(character, 500);
		canim.addFrames(character2, 500);
		canim.addFrames(character3, 500);

		currentsprite = canim.getImage();
	}

	@Override
	public void start() {
		robot = new Robot();
		Thread t = new Thread(this);
		t.start();
	}

	@Override
	public void paint(Graphics g) {
		g.drawImage(currentsprite, robot.getRoboX(), robot.getRoboY(), this);
	}

	@Override
	public void update(Graphics g) {
		if (image == null) {
			image = createImage(this.getWidth(), this.getHeight());
			second = image.getGraphics();
		}

		second.setColor(getBackground());
		second.fillRect(0, 0, getWidth(), getHeight());
		second.setColor(getForeground());
		paint(second);

		g.drawImage(image, 0, 0, this);

	}

	@Override
	public void stop() {

	}

	@Override
	public void destroy() {

	}

	@Override
	public void run() {
		while (true) {
			robot.update();
			currentsprite = canim.getImage();
			canim.update(10);
			repaint();
			try {
				Thread.sleep(17); // 1000/60 to acheive 60 FPS
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyPressed(KeyEvent e) {
		switch (e.getKeyCode()) {
		case KeyEvent.VK_LEFT:
			robot.moveLeft();
			break;
		case KeyEvent.VK_RIGHT:
			robot.moveRight();
			break;
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		switch (e.getKeyCode()) {
		case KeyEvent.VK_LEFT:
			robot.moveStop();
			break;
		case KeyEvent.VK_RIGHT:
			robot.moveStop();
			break;
		}

	}

}
