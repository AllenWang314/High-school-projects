
public class Enemy extends Sprite {
	private boolean visibility;
	private int xCoord;
	private int yCoord;
	private int width;
	private int height;
	
	private int x1;
	private int y1;
	private int x2;
	private int y2;
	private int speed;
	private boolean isForward = true;
	private Vector v;
	
	public Enemy(int x, int y, int w, int h, int x1, int y1, int x2, int y2, int speed) {
		super(x, y, w, h);
		this.x1 = x1;
		this.y1 = y1;
		this.x2 = x2;
		this.y2 = y2;
		this.speed = speed;
		double dist = Math.hypot(x1-x2, y1-y2);
		int deltaX = (int) (((x2 - xCoord)/(dist*0.1)*speed/10));
		int deltaY = (int) (((y2 - yCoord)/(dist*0.1)*speed/10));
		v = new Vector(deltaX, deltaY);
	}
	
	public Vector getVector() {
		return v;
	}
	
	public boolean isForward() {
		return isForward;
	}

	public void setForward(boolean isForward) {
		this.isForward = isForward;
	}

	public int getX1() {
		return x1;
	}

	public void setX1(int x1) {
		this.x1 = x1;
	}

	public int getY1() {
		return y1;
	}

	public void setY1(int y1) {
		this.y1 = y1;
	}

	public int getX2() {
		return x2;
	}

	public void setX2(int x2) {
		this.x2 = x2;
	}

	public int getY2() {
		return y2;
	}

	public void setY2(int y2) {
		this.y2 = y2;
	}

	public int getSpeed() {
		return speed;
	}

	public void setSpeed(int speed) {
		this.speed = speed;
		double dist = Math.hypot(x1-x2, y1-y2);
		int deltaX = (int) (((x2 - xCoord)/(dist*0.1)*speed/10));
		int deltaY = (int) (((y2 - yCoord)/(dist*0.1)*speed/10));
		v = new Vector(deltaX, deltaY);
	}
	
	
}
