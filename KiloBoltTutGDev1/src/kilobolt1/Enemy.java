package kilobolt1;

public class Enemy {

	private int centerX, centerY, speedX, power, maxHealth, currentHealth;
	private Background bg1 = StartingClass.getBg1();

	public void update(){
		centerX+=speedX;
		speedX=bg1.getSpeedX();
	}

	public void die(){
		
	}
	
	public void attack(){
		
	}
	
	/**
	 * @return the centerX
	 */
	public int getCenterX() {
		return centerX;
	}

	/**
	 * @return the centerY
	 */
	public int getCenterY() {
		return centerY;
	}

	/**
	 * @return the speedX
	 */
	public int getSpeedX() {
		return speedX;
	}

	/**
	 * @return the power
	 */
	public int getPower() {
		return power;
	}

	/**
	 * @return the maxHealth
	 */
	public int getMaxHealth() {
		return maxHealth;
	}

	/**
	 * @return the currentHealth
	 */
	public int getCurrentHealth() {
		return currentHealth;
	}

	/**
	 * @return the bg1
	 */
	public Background getBg1() {
		return bg1;
	}

	/**
	 * @param centerX
	 *            the centerX to set
	 */
	public void setCenterX(int centerX) {
		this.centerX = centerX;
	}

	/**
	 * @param centerY
	 *            the centerY to set
	 */
	public void setCenterY(int centerY) {
		this.centerY = centerY;
	}

	/**
	 * @param speedX
	 *            the speedX to set
	 */
	public void setSpeedX(int speedX) {
		this.speedX = speedX;
	}

	/**
	 * @param power
	 *            the power to set
	 */
	public void setPower(int power) {
		this.power = power;
	}

	/**
	 * @param maxHealth
	 *            the maxHealth to set
	 */
	public void setMaxHealth(int maxHealth) {
		this.maxHealth = maxHealth;
	}

	/**
	 * @param currentHealth
	 *            the currentHealth to set
	 */
	public void setCurrentHealth(int currentHealth) {
		this.currentHealth = currentHealth;
	}

	/**
	 * @param bg1
	 *            the bg1 to set
	 */
	public void setBg1(Background bg1) {
		this.bg1 = bg1;
	}

}
