
public class Dot extends Sprite {
	private boolean visibility;
	private int xCoord;
	private int yCoord;
	private int width;
	private int height;
	
	private Brain brain;
	
	public Dot() {
		super(200, 200, 5, 5);
		// brain
		brain = new Brain(300);
	}
	public Dot(int x, int y, int w, int h) {
		super(x, y, w, h);
	}
	
	private boolean isDead = false;
	private boolean reachedGoal = false;
	private boolean isBest = false;
	
	private double fitness = 0.0;
	
	public void update() {
		 if (!isDead && !reachedGoal) {
		      move();
//		      if (!super.isInBounds(0,0,390,390)) {
//		    	  isDead = true;
//		      }
		 }
	}
	
	public Vector move() {
		//Vector v = new Vector(-1 + (int) (Math.random() * 3),-1 + (int) (Math.random() * 3));
		//return v;
		
		Vector v = new Vector(0,0);
		if (brain.getDirections().size() > brain.getStep()) {
			v.setX(brain.getDirections().get(brain.getStep()).getX());
			v.setY(brain.getDirections().get(brain.getStep()).getY());
			brain.incrementStep();
		} else {
			isDead = true;
		}
		return v;
	}
	
	public void calculateFitness(int goalX, int goalY) {
		if (reachedGoal) {
			fitness = 10000;
		} else {
			double distance = Math.hypot(xCoord-goalX, yCoord-goalY);
		}
	}
	
	public boolean getIsDead() {
		return isDead;
	}
	
	public void setIsDead(boolean b) {
		isDead = b;
	}
	
	public boolean getReachedGoal() {
		return reachedGoal;
	}
	
}
