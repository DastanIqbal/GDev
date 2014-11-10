package learning.kilobolt;

import java.awt.Image;
import java.util.ArrayList;

import kilobolt1.framework.Animation;

public class MyAnimation {

	ArrayList<AnimFrame> frames = new ArrayList<AnimFrame>();
	long totalduration;
	long animtime;
	int currentframe;

	MyAnimation() {
		synchronized (this) {
			animtime = 0;
			currentframe = 0;
		}
	}

	public void addFrames(Image img, long duration) {
		totalduration += duration;
		frames.add(new AnimFrame(img, totalduration));

	}

	public synchronized void update(long elapsedtime) {
		animtime += elapsedtime;

		if (animtime > totalduration) {
			animtime = animtime % totalduration;
			currentframe = 0;
		}

		while (animtime > frames.get(currentframe).duration) {
			currentframe++;
		}
	}

	public synchronized Image getImage() {
		return frames.get(currentframe).img;
	}

	class AnimFrame {
		Image img;
		long duration;

		public AnimFrame(Image img2, long duration2) {
			img = img2;
			duration = duration2;
		}

		/**
		 * @return the img
		 */
		public Image getImg() {
			return img;
		}

		/**
		 * @return the duration
		 */
		public long getDuration() {
			return duration;
		}

		/**
		 * @param img
		 *            the img to set
		 */
		public void setImg(Image img) {
			this.img = img;
		}

		/**
		 * @param duration
		 *            the duration to set
		 */
		public void setDuration(long duration) {
			this.duration = duration;
		}
	}
}
