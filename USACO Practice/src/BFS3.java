import java.util.*;

public class BFS3 {
	public static int numSquares(int n) {
		LinkedList<int[]> sums = new LinkedList<int[]>();
		HashSet<Integer> visited = new HashSet<Integer>();
		sums.add(new int[] {0,0});
        ArrayList<Integer> a = new ArrayList<Integer>();
        for (int i = 1; i <= Math.sqrt(n); i++) {
        		if (i*i <= n) a.add(i*i);
        }
        System.out.println(a);
        while(sums.peek()[0] != n) {
        		bfs(sums, visited, a, n);
        }
        return sums.peek()[1];
	}
	
	public static void bfs(LinkedList<int[]> sums, HashSet<Integer> visited, ArrayList<Integer> a, int n) {
		int[] curr = sums.poll();
		if (curr[0] > n) return;
		if (visited.contains(curr[0])) return;
		visited.add(curr[0]);
		for (int x: a) {
			sums.add(new int[] {curr[0] + x, curr[1] + 1});
		}
	}
	
	public static void main(String[] args) {
		System.out.println(numSquares(43));
	}
	
}
