import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class Population {

	private ArrayList<AI> population;
	private double mutationFreq;
	private double randomFreq;
	private double bestFreq;
	private double topPerc;
	private int maxPop;
	
	public Population(int maxPop, double mutationFreq, double randomFreq, double bestFreq, double topPerc) {
		this.maxPop = maxPop;
		this.mutationFreq = mutationFreq;
		this.randomFreq = randomFreq;
		this.bestFreq = bestFreq;
		this.topPerc = topPerc;
		this.population = new ArrayList<AI>();
		for (int i = 0; i< maxPop; i++) {
			this.population.add(new AI(25));
		}
	}
	
//	public Population(int maxPop, double mutationFreq, double randomFreq, double bestFreq, ArrayList<AI> population) {
//		this.maxPop = maxPop;
//		this.mutationFreq = mutationFreq;
//		this.bestFreq = bestFreq;
//		this.randomFreq = randomFreq;
//		this.population = population;
//	}

	public ArrayList<AI> getPopulation() {
		return population;
	}
	
	public boolean isDone() {
		for (int i = 0; i < population.size(); i++) {
			if (!population.get(i).getIsDead() && !population.get(i).getReachedGoal()) {
				return false;
			}
		}
		return true;
	}

	public void setPopulation(ArrayList<AI> population) {
		this.population = population;
	}

	public double getMutationFreq() {
		return mutationFreq;
	}

	public void setMutationFreq(double mutationFreq) {
		this.mutationFreq = mutationFreq;
	}

	public int getMaxPop() {
		return maxPop;
	}

	public void setMaxPop(int maxPop) {
		this.maxPop = maxPop;
	}
	
//	public void calcFitness(Goal goal) {
//		for (AI square: population) {
//			square.calculateFitness(goal);
//		}
//	}
	
	/**
	 * WARNING this only returns the reference to the best square
	 * @return
	 */
	public AI selectBest() {
		Collections.sort(population, new Comparator<AI>() {
	        public int compare(AI square1, AI square2) {
	            return (int) ((square2.getFitness() - square1.getFitness()) * 1000); // descending order
	        }

	    });
		AI best = population.get(0);
//		for (AI current : population) {
//			if (current.getFitness() > best.getFitness()) {
//				best = current;
//			}
//		}

//	    return this.population.get(0);
		System.out.println("Best Fitness: "+best.getFitness());
		return best;
	}
	
	public AI naturalSelect() {
		double fitnessSum = 0;
		for (int i = 0; i < population.size(); i++) {
			fitnessSum += population.get(i).getFitness();
		}
		
		double rand = Math.random() * fitnessSum;
		double runningSum = 0;
		
		for (int i = 0; i < population.size(); i++) {
			runningSum += population.get(i).getFitness();
			if (runningSum > rand) {
				System.out.println("Naturally Selected: "+population.get(i).getFitness());
				return population.get(i);
			}
		}
		
		System.out.println("Error returned null");
		return null;
	}
	
	/**
	 * takes the bestSquare and breeds enough to fill to maxPop
	 * generates random moves for the rest
	 * @param bestSquare
	 */
	public void breed(AI bestSquare) {
		//double nfreq = Math.sqrt(mutationFreq);
		
		ArrayList<AI> oldPop = new ArrayList<AI>();
		int topAmount = (int) (topPerc*maxPop);
		for (int i = 1; i <= topAmount; i++) {
			oldPop.add(population.get(i));
		}

	
		int randRange = (int) (randomFreq*maxPop);
		int bestRange = randRange + (int) (bestFreq*maxPop);
		
		System.out.println("randRange: "+randRange+" bestRange: "+bestRange);
		
		population.set(0, new AI(bestSquare.getMaxSteps()+20, bestSquare.getDirections()));
		for (int i = 1; i < randRange; i++) {
			population.set(i, new AI(bestSquare.getMaxSteps()+20));
		}
		for (int i = randRange; i < bestRange; i++) {
			AI temp = new AI(bestSquare.getMaxSteps()+20, bestSquare.getDirections());
			temp.mutate(mutationFreq);
			population.set(i, temp);
		}
		for (int i = bestRange; i < maxPop; i++) {
			int randomTop = (int) (Math.random() * topAmount);
			AI temp = new AI(oldPop.get(randomTop).getMaxSteps()+20, oldPop.get(randomTop).getDirections());
			temp.mutate(mutationFreq);
			population.set(i, temp);
		}
	}
}