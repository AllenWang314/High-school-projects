import java.util.*;

public class Solution5 {

	public static int solution(int[] S, int[] E) {
		Solution5 s = new Solution5();
		ArrayList<Node> guests = new ArrayList<Node>();
		for (int i = 0; i < E.length; i++) {
			guests.add(s.new Node(S[i], E[i]));
		}
		guests.sort(s.new nodeComparator());
        return solution(S, E, guests, 1, 500);
    }
	
	public static int solution(int[] S, int[] E, ArrayList<Node> guests, int min, int max) {
		if (min >= max) return max;
		if (!possible(guests, (min + max)/2)) return solution(S, E, guests, min, (min + max)/2);
		return solution (S, E, guests, (min + max)/2, max);
	}
	
	public static boolean possible(ArrayList<Node> guests, int numChairs) {

//		for (int i = 0; int guests) {
//			
//		}
		return false;
	}
	
	public class Node {
	
	public int arrival;
	public int leave;
	
	public Node(int arrival, int leave){
		this.arrival = arrival;
		this.leave = leave;
	}
}

class nodeComparator implements Comparator<Node> {
    
     public int compare(Node a, Node b) {
        return a.arrival - b.arrival;
    }
}
	
	public static void main(String[] args) {
		
	}
}
