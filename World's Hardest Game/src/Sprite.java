public abstract class Sprite {

	private boolean visibility;
	private int xCoord;
	private int yCoord;
	private int width;
	private int height;
	
	public Sprite() {
		
	}
	
	public Sprite(int x, int y, int w, int h) {
		this.xCoord = x;
		this.yCoord = y;
		this.width = w;
		this.height = h;
		this.visibility = true;
	}
	
	public String toString() {
		return "location: (" +  xCoord + ", " + yCoord + "); visibility: " + visibility;
	}
	
	/**
	 * @return the width
	 */
	public int getWidth() {
		return width;
	}

	/**
	 * @param width the width to set
	 */
	public void setWidth(int width) {
		this.width = width;
	}

	/**
	 * @return the height
	 */
	public int getHeight() {
		return height;
	}

	/**
	 * @param height the height to set
	 */
	public void setHeight(int height) {
		this.height = height;
	}

	/**
	 * @return the visibility
	 */
	public boolean isVisible() {
		return visibility;
	}

	/**
	 * @param visibility the visibility to set
	 */
	public void setVisibility(boolean visibility) {
		this.visibility = visibility;
	}

	/**
	 * @return the xCoord
	 */
	public int getxCoord() {
		return xCoord;
	}

	/**
	 * @param xCoord the xCoord to set
	 */
	public void setxCoord(int xCoord) {
		this.xCoord = xCoord;
	}

	/**
	 * @return the yCoord
	 */
	public int getyCoord() {
		return yCoord;
	}

	/**
	 * @param yCoord the yCoord to set
	 */
	public void setyCoord(int yCoord) {
		this.yCoord = yCoord;
	}

	/**
	 * x y is the top left corner, 
	 * y increase going down the screen and x increase going to the right
	 * returns true if these collide
	 * @param x
	 * @param y
	 * @param width
	 * @param height
	 * @return
	 */
	public boolean isInBounds(int x, int y, int width, int height) {
		if(this.xCoord + this.width < x || this.yCoord + this.height < y) {
			return false;
		}
		else if (x + width < this.getxCoord() || y + height < this.getyCoord()) {
			return false;
		}
		return true;
		
	}
	
	public boolean isInBounds(int x, int y, int width, int height, Vector v) {
		if(this.xCoord + this.width + v.getX() < x || this.yCoord + this.height + v.getY() < y) {
			return false;
		}
		else if (x + width < this.getxCoord() + v.getX() || y + height < this.getyCoord() + v.getY()) {
			return false;
		}
		return true;
		
	}
}