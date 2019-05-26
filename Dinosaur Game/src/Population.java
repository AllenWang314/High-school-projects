import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class Population {

	private ArrayList<DinoGene> population;
	private double mutationFreq;
	private int maxPop;
	
	public Population(double mutationFreq, int maxPop) {
		this.maxPop = maxPop;
		this.population = new ArrayList<DinoGene>();
		for (int i = 0; i< maxPop; i++) {
			this.population.add(new DinoGene());
		}
	}


	public Population(double mutationFreq, int maxPop, ArrayList<DinoGene> pop) {
		this.maxPop = maxPop;
		this.population = pop;
	}
	
	/**
	 * @return the population
	 */
	public ArrayList<DinoGene> getPopulation() {
		return population;
	}

	/**
	 * @param population the population to set
	 */
	public void setPopulation(ArrayList<DinoGene> population) {
		this.population = population;
	}

	/**
	 * @return the mutationFreq
	 */
	public double getMutationFreq() {
		return mutationFreq;
	}

	/**
	 * @param mutationFreq the mutationFreq to set
	 */
	public void setMutationFreq(double mutationFreq) {
		this.mutationFreq = mutationFreq;
	}

	/**
	 * @return the maxPop
	 */
	public int getMaxPop() {
		return maxPop;
	}

	/**
	 * @param maxPop the maxPop to set
	 */
	public void setMaxPop(int maxPop) {
		this.maxPop = maxPop;
	}
	
	/**
	 * this method is dumb
	 * @param distTraveled
	 */
	public void calcFitness(int distTraveled) {
		for (DinoGene d: population) {
			d.calculateFitness(distTraveled);
		}
	}
	
	/**
	 * @return the top 10% of the population maxPop/4
	 */
	public ArrayList<DinoGene> naturalSelection() {
	    Collections.sort(population, new Comparator<DinoGene>() {
	        public int compare(DinoGene d1, DinoGene d2) {
	            return d2.getFitness() - d1.getFitness(); // descending order
	        }

	    });
	    ArrayList<DinoGene> pros = new ArrayList<DinoGene>();
	    for (int i = 0; i <= maxPop/2; i++) {
	    	pros.add(this.population.get(i));
	    }
	    Collections.shuffle(pros);
	    return pros;
	}
	
	/**
	 * takes parents sets population to the children with mutations
	 * assumes parents have been shuffled
	 * @param parents
	 */
	public void breed(ArrayList<DinoGene> parents) {
		ArrayList<DinoGene> children = new ArrayList<DinoGene>();
		for (int i = 0; i<= mutationFreq*maxPop; i++) {
			children.add(parents.get(i).mutate());
		}
		for (int j = 0; j< parents.size(); j++) {
			for(int k = j+1; k<parents.size(); k++) {
				if (children.size() == maxPop) {
					break;
				}
				children.add(parents.get(j).cross(parents.get(k)));
			}
		}
		System.out.println(children.size());
		setPopulation(children);
	}
		
}