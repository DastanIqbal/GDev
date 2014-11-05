package kilobolt1;

import java.applet.Applet;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.HeadlessException;
import java.util.Random;

public class TileMap extends Applet {

	static int row, column;
	static int[][] tilemaps;

	@Override
	public void init() {
		setSize(800, 480);
		setBackground(Color.WHITE);

		createTilmap();
	}

	private void createTilmap() {
		tilemaps = new int[50][30];
		row = tilemaps.length;
		column = tilemaps[49].length;
		Random r = new Random();

		for (int i = 0; i < row; i++) {
			for (int j = 0; j < column; j++) {
				tilemaps[i][j] = r.nextInt(5);
			}
		}
	}

	@Override
	public void start() {
		// TODO Auto-generated method stub
		super.start();
	}

	@Override
	public void paint(Graphics g) {
		for (int i = 0; i < row; i++) {
			for (int j = 0; j < column; j++) {
				int mod_i = 20 * i;
				int mod_j = 20 * j;

				switch (tilemaps[i][j]) {
				case 0:
					g.setColor(Color.RED);
					g.fillRect(mod_i, mod_j, 20, 20);
					break;
				case 1:
					g.setColor(Color.BLACK);
					g.fillRect(mod_i, mod_j, 20, 20);
					break;
				case 2:
					g.setColor(Color.GREEN);
					g.fillRect(mod_i, mod_j, 20, 20);
					break;
				case 3:
					g.setColor(Color.CYAN);
					g.fillRect(mod_i, mod_j, 20, 20);
					break;
				case 4:
					g.setColor(Color.MAGENTA);
					g.fillRect(mod_i, mod_j, 20, 20);
					break;
				case 5:
					g.setColor(Color.GREEN);
					g.fillRect(mod_i, mod_j, 20, 20);
					break;
				}
			}
		}
	}

	public void stop() {

	};

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		super.destroy();
	}
}
