package kilobolt1;

import java.awt.Image;

public class Tiles {

	private int tileX, tileY, speedX,type;
	private Image tileimage;
	private Background bg=StartingClass.getBg1();

	public Tiles(int x,int y,int type) {
		tileX=x*40;
		tileY=y*40;
		this.type=type;
		
		//5,8,2,4,6 numpad mid,top,bot,left,right
		if(type==5){
			tileimage=StartingClass.tileDirt;
		}else if(type==8){
			tileimage=StartingClass.tilegrassTop;
		}else if(type==2){
			tileimage=StartingClass.tilegrassBot;
		}else if(type==4){
			tileimage=StartingClass.tilegrassLeft;
		}else if(type==6){
			tileimage=StartingClass.tilegrassRight;
		}
	}
	
	public void update(){
		if(type==1){
			if(bg.getSpeedX()==0){
				speedX=-1;
			}else{
				speedX=-2;
			}
		}else{
			speedX=bg.getSpeedX()*5;
		}
		tileX+=speedX;
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
	 * @param tileX the tileX to set
	 */
	public void setTileX(int tileX) {
		this.tileX = tileX;
	}

	/**
	 * @param tileY the tileY to set
	 */
	public void setTileY(int tileY) {
		this.tileY = tileY;
	}

	/**
	 * @param speedX the speedX to set
	 */
	public void setSpeedX(int speedX) {
		this.speedX = speedX;
	}

	/**
	 * @param tileimage the tileimage to set
	 */
	public void setTileimage(Image tileimage) {
		this.tileimage = tileimage;
	}

}
