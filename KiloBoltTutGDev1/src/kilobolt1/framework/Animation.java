package kilobolt1.framework;

import java.awt.Image;
import java.util.ArrayList;

public class Animation {

	private ArrayList frames;
	private int currentframe;
	private long animTime;
	private long totalDuration;

	public Animation() {
		frames = new ArrayList();
		totalDuration = 0;
		
		synchronized (this) {
			animTime = 0;
			currentframe = 0;
		}
	}

	public synchronized void addFrame(Image image, long duration) {
		totalDuration += duration;
		frames.add(new AnimFrame(image, totalDuration));
	}
	
	public synchronized void update(long elapsedTime) {

		if(frames.size()>1){
			// It will add elpased time with animtime in each update
			animTime+=elapsedTime;
			
			//If animTime > totalduration then it will module the animTime 
			//So it will be very less after module and reset to currentFrame=0
			if(animTime>=totalDuration){
				animTime=animTime%totalDuration;
				currentframe=0;
			}
			
			//If animTime > frame.duration time then increase currentFrame till < frame.duration
			while(animTime>getFrame(currentframe).duration){
				currentframe++;
			}
		}
		
		
	}

	public synchronized Image getImage() {
		if (frames.size() == 0) {
			return null;
		} else {
			return getFrame(currentframe).image;
		}
	}

	public AnimFrame getFrame(int i) {
		return (AnimFrame) frames.get(i);
	}

	class AnimFrame {
		private Image image;
		private long duration;

		public AnimFrame(Image image, long imageDuration) {

			this.image = image;
			duration = imageDuration;
		}

		/**
		 * @return the image
		 */
		public Image getImage() {
			return image;
		}

		/**
		 * @return the duration
		 */
		public long getDuration() {
			return duration;
		}

		/**
		 * @param image
		 *            the image to set
		 */
		public void setImage(Image image) {
			this.image = image;
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
