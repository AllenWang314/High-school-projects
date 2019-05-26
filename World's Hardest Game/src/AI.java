import java.util.ArrayList;

public class AI extends Sprite {
	private boolean visibility;
	private int xCoord;
	private int yCoord;
	private int width;
	private int height;
	private ArrayList<Vector> directions;
	private int step;
	private int maxSteps;
	private double theta;
	
	public AI(int size) {
		super(200,200,10,10);
		maxSteps = size;
		directions = new ArrayList<Vector>();
		theta = Math.toRadians((int) (Math.random() * 360));
		for (int i = 0; i < size; i++) {
			//double theta = Math.toRadians((int) (Math.random() * 360));
			theta += -0.75 + (Math.random() * 1.5);
			int v1 = (int) (5 * Math.cos(theta));
			int v2 = (int) (5 * Math.sin(theta));
			//int v1 = -10 + (int) (Math.random() * 21);
			//int v2 = -10 + (int) (Math.random() * 21);
			directions.add(new Vector(v1,v2));
		}
	}
	public AI(int step, ArrayList<Vector> directions) {
		super(200,200,10,10);
		this.step = step;
		maxSteps = step;
		this.directions = new ArrayList<Vector>();
		//this.directions = directions;
		for (int i = 0; i < directions.size(); i++) {
			this.directions.add(directions.get(i));
		}
		for (int i = this.directions.size(); i < step; i++) {
			//double theta = Math.toRadians((int) (Math.random() * 360));
			theta += -0.75 + (Math.random() * 1.5);
			int v1 = (int) (5 * Math.cos(theta));
			int v2 = (int) (5 * Math.sin(theta));
			//int v1 = -10 + (int) (Math.random() * 21);
			//int v2 = -10 + (int) (Math.random() * 21);
			this.directions.add(new Vector(v1,v2));
		}
		this.step = 0;
	}
	
	private boolean isDead = false;
	private boolean reachedGoal = false;
	private boolean isBest = false;
	
	private double fitness = 0.0;
	
	public void mutate(double freq) {
		for (int i = 0; i < maxSteps; i++) {
			double chance = Math.random();
			if (chance < freq) {
				//System.out.println("Mutated");
				//double theta = Math.toRadians((int) (Math.random() * 360));
				theta += -0.75 + (Math.random() * 1.5);
				int v1 = (int) (5 * Math.cos(theta));
				int v2 = (int) (5 * Math.sin(theta));
				//int v1 = -10 + (int) (Math.random() * 21);
				//int v2 = -10 + (int) (Math.random() * 21);
				directions.set(i,new Vector(v1,v2));
			}
		}
	}
	
//	public Vector move() {
//		//Vector v = new Vector(-1 + (int) (Math.random() * 3),-1 + (int) (Math.random() * 3));
//		//return v;
//		
//		Vector v = new Vector(0,0);
//		if (directions.size() > step) {
//			v.setX(directions.get(step).getX());
//			v.setY(directions.get(step).getY());
//			step++;
//		} else {
//			isDead = true;
//		}
//		return v;
//	}
	
	public Vector getCurrentMove() {
		if (step < directions.size()) {
			return directions.get(step++);
		} else {
			isDead = true;
			return new Vector(0,0);
		}
	}
	
//	public void calculateFitness(Goal goal) {
//		if (reachedGoal) {
//			fitness = 10000;
//		} else {
//			double distance = Math.hypot(xCoord-goal.getxCoord(), yCoord-goal.getyCoord());
//			fitness = 9999 -(int)distance;
//		}
//		System.out.println(fitness);
//	}
	
	public ArrayList<Vector> getDirections() {
		return directions;
	}

	public void setDirections(ArrayList<Vector> directions) {
		this.directions = directions;
	}

	public int getStep() {
		return step;
	}

	public void setStep(int step) {
		this.step = step;
	}

	
	public int getMaxSteps() {
		return maxSteps;
	}
	public void setMaxSteps(int maxSteps) {
		this.maxSteps = maxSteps;
	}
	
	public boolean isBest() {
		return isBest;
	}

	public void setBest(boolean isBest) {
		this.isBest = isBest;
	}

	public double getFitness() {
		return fitness;
	}

	public void setFitness(double fitness) {
		this.fitness = fitness;
	}

	public void setDead(boolean isDead) {
		this.isDead = isDead;
	}

	public void setReachedGoal(boolean reachedGoal) {
		this.reachedGoal = reachedGoal;
	}

	public boolean getIsDead() {
		return isDead;
	}
	
	public void setIsDead(boolean b) {
		this.isDead = b;
	}
	
	public boolean getReachedGoal() {
		return reachedGoal;
	}
}
