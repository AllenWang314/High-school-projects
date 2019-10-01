import java.util.*;

public class DFS4 {

	public static boolean isPoss(int[] weights, ArrayList<int[]> edges, int K, int max) {
		HashSet<Integer> unvisited = new HashSet<Integer>();
		ArrayList<HashSet<Integer>> edg = new ArrayList<HashSet<Integer>>();
		for (int i = 0; i < weights.length; i++) {
			edg.add(new HashSet<Integer>());
			unvisited.add(i);
		}
		for (int i = 0; i < edges.size(); i++) {
			int[] curr = edges.get(i);
			edg.get(curr[0]-1).add(curr[1]-1);
			edg.get(curr[1]-1).add(curr[0]-1);	
		}
		int subtrees = 0;
		while (unvisited.isEmpty() == false) {
			int next = unvisited.iterator().next();
			dfs(next, unvisited, weights, edg, max);
			subtrees ++;
		}
		return (subtrees <= K + 1);
	}
	
	public static int dfs(int next, HashSet<Integer> unvisited, int[] weights, ArrayList<HashSet<Integer>> edg, int max) {
		return 1;
	}
	
	public static void main(String[] args) {
		int[] weights = new int[] {2,1,3,1,2};
		ArrayList<int[]> edges = new ArrayList<int[]>();
		edges.add(new int[] {1, 2});
		edges.add(new int[] {2, 3});
		edges.add(new int[] {2, 4});
		edges.add(new int[] {4, 5});		
		isPoss(weights, edges, 0, 0);
	}
}
