public class Dinosaur extends Sprite {

	private int ground;
	private int jumpDelay = 0;
	private int jumpVelocity = 0;
	private int duckHeight =0;
	private boolean isDucking = false;
	
	public Dinosaur() {
		this(20, 270, 30, 55, 100, 20);
	}

	public Dinosaur(int x, int y, int w, int h, int jumpDelay, int duckHeight) {
		super(x, y, w, h);
		ground = y+h;
		this.jumpDelay = jumpDelay;
		this.duckHeight = duckHeight;
	}
	
	
	/**
	 * @return the ground
	 */
	public int getGround() {
		return ground;
	}

	/**
	 * @param ground the ground to set
	 */
	public void setGround(int ground) {
		this.ground = ground;
	}

	/**
	 * @return the duckHeight
	 */
	public int getDuckHeight() {
		return duckHeight;
	}

	/**
	 * @param duckHeight the duckHeight to set
	 */
	public void setDuckHeight(int duckHeight) {
		this.duckHeight = duckHeight;
	}

	/**
	 * @return the isDucking
	 */
	public boolean isDucking() {
		return isDucking;
	}

	/**
	 * @param isDucking the isDucking to set
	 */
	public void setDucking(boolean isDucking) {
		this.isDucking = isDucking;
	}

	/**
	 * @return the jumpDelay
	 */
	public int getJumpDelay() {
		return jumpDelay;
	}

	/**
	 * @param jumpDelay the jumpDelay to set
	 */
	public void setJumpDelay(int jumpDelay) {
		this.jumpDelay = jumpDelay;
	}

	/**
	 * @return the jumpVelocity
	 */
	public int getJumpVelocity() {
		return jumpVelocity;
	}

	/**
	 * @param jumpVelocity the jumpVelocity to set
	 */
	public void setJumpVelocity(int jumpVelocity) {
		this.jumpVelocity = jumpVelocity;
	}
	
	
	public void jump() {
		if(isTouchingGround()) {
			this.jumpVelocity = 100;
		}
	}
	
	public void startDuck() {
		if(!isDucking) {
			this.setHeight(this.getHeight() - duckHeight);
			this.setyCoord(this.getyCoord() + duckHeight);
			this.setWidth(this.getWidth() + duckHeight);
			isDucking = true;
		}
	}
	
	public void endDuck() {
		if(isDucking) {
			this.setHeight(this.getHeight() + duckHeight);
			this.setyCoord(this.getyCoord() - duckHeight);
			this.setWidth(this.getWidth() - duckHeight);
			isDucking = false;
		}
	}
	
	public boolean isTouchingGround() {
		return (this.getyCoord() + this.getHeight() >= this.ground);
	}

}