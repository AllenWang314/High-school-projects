public class DinoGene {
	
	private double wallWeight;
	private double birdWeight;
	private Dinosaur dino;
	private int fitness;
	/**
	 * @return the distToNextWall
	 */
	public int getDistToNextWall() {
		return distToNextWall;
	}

	/**
	 * @param distToNextWall the distToNextWall to set
	 */
	public void setDistToNextWall(int distToNextWall) {
		this.distToNextWall = distToNextWall;
	}

	/**
	 * @return the distToNextBird
	 */
	public int getDistToNextBird() {
		return distToNextBird;
	}

	/**
	 * @param distToNextBird the distToNextBird to set
	 */
	public void setDistToNextBird(int distToNextBird) {
		this.distToNextBird = distToNextBird;
	}

	private double evaluation;
	private int distToNextWall;
	private int distToNextBird;

	public DinoGene() {
		wallWeight = Math.random();
		birdWeight = Math.random();
		dino = new Dinosaur();
	}
	
	public DinoGene(Dinosaur d) {
		wallWeight = Math.random();
		birdWeight = Math.random();
		dino =d;
	}
	
	public DinoGene(double wallWeight, double birdWeight, Dinosaur d) {
		this.wallWeight = wallWeight;
		this.birdWeight = birdWeight;
		dino =d;
	}

	/**
	 * @return the wallWeight
	 */
	public double getWallWeight() {
		return wallWeight;
	}

	/**
	 * @param wallWeight the wallWeight to set
	 */
	public void setWallWeight(double wallWeight) {
		this.wallWeight = wallWeight;
	}

	/**
	 * @return the birdWeight
	 */
	public double getBirdWeight() {
		return birdWeight;
	}

	/**
	 * @param birdWeight the birdWeight to set
	 */
	public void setBirdWeight(double birdWeight) {
		this.birdWeight = birdWeight;
	}

	/**
	 * @return the dino
	 */
	public Dinosaur getDino() {
		return dino;
	}

	/**
	 * @param dino the dino to set
	 */
	public void setDino(Dinosaur dino) {
		this.dino = dino;
	}
	
	/**
	 * @return the fitness
	 */
	public int getFitness() {
		return fitness;
	}

	/**
	 * @param fitness the fitness to set
	 */
	public void setFitness(int fitness) {
		this.fitness = fitness;
	}

	/**
	 * @return the evaluation
	 */
	public double getEvaluation() {
		return evaluation;
	}

	/**
	 * @param evaluation the evaluation to set
	 */
	public void setEvaluation(double evaluation) {
		this.evaluation = evaluation;
	}

	public void calculateFitness(int distTraveled) {
		this.fitness = distTraveled;
	}
	
	/**
	 * if evaluation < 0.3 then duck, if > 0.7 then jump, else do nothing
	 * @param distToWall
	 * @param distToBird
	 */
	public void evaluate() {
		if ((double)distToNextBird/600 < this.birdWeight){
			this.dino.startDuck();
		}
		else if ((double)distToNextWall/600 < this.wallWeight){
			this.dino.endDuck();
			this.dino.jump();
		}
		else {
			this.dino.endDuck();
		}
	}

	public DinoGene cross(DinoGene d) {
		DinoGene baby = new DinoGene((wallWeight + d.getWallWeight())/2, (birdWeight + d.getBirdWeight())/2, new Dinosaur());
		return baby;
	}
	
	public DinoGene mutate() {
		double mut1 = (Math.random()-0.5);
		double mut2 = (Math.random()-0.5);
		return new DinoGene(wallWeight + mut1, wallWeight + mut2, new Dinosaur());
	}
	
}