import java.util.ArrayList;

public class Brain {
	public ArrayList<Vector> directions;
	public int step = 0;
	
	public Brain(int size) {
		directions = new ArrayList<Vector>();
		for (int i = 0; i < size; i++) {
			int v1 = -1 + (int) (Math.random() * 3);
			int v2 = -1 + (int) (Math.random() * 3);
			directions.add(new Vector(v1,v2));
		}
	}
	
	public ArrayList<Vector> getDirections() {
		return directions;
	}
	
	public int getStep() {
		return step;
	}
	
	public void incrementStep() {
		step++;
	}
}
