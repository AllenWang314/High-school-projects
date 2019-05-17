import java.io.*;
import java.util.*;

public class TopoSort2 {

	static int N;
	static int M;
	static ArrayList<ArrayList<Integer>> pref = new ArrayList<ArrayList<Integer>>();
	static int[] result;

	public static void makeTest() throws IOException {
		PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("milkorder.in")));
		pw.write("4 3\n" + "3 1 2 3\n" + "2 4 2\n" + "3 3 4 1\n" + "");
		pw.close();
	}

	public static void main(String[] args) throws IOException {
		 makeTest();
		BufferedReader br = new BufferedReader(new FileReader("milkorder.in"));
		PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("milkorder.out")));

		StringTokenizer tkn = new StringTokenizer(br.readLine());
		N = Integer.parseInt(tkn.nextToken());
		M = Integer.parseInt(tkn.nextToken());

		for (int i = 0; i < M; i++) {
			tkn = new StringTokenizer(br.readLine());
			pref.add(new ArrayList<Integer>());
			int numPref = Integer.parseInt(tkn.nextToken());
			for (int j = 0; j < numPref; j++) {
				pref.get(i).add(Integer.parseInt(tkn.nextToken()) - 1);
			}
		}

		// now we conduct the binary search
		int lo = 0;
		int hi = M + 1;
		while (hi > lo + 1) {
			int mid = (lo + hi) / 2;
			if (doableII(mid)) {
				lo = mid;
			} else {
				hi = mid;
			}
		}
		doable(lo);
		String answer = "";
		for (int i : result) {
			answer += (i + 1) + " ";
		}
		pw.write(answer.substring(0, answer.length() - 1));
		br.close();
		pw.close();
	}

	/*
	 * method checks whether the value of mid is valid
	 */
	private static boolean doable(int mid) {
		int[] pred = new int[N];
		ArrayList<ArrayList<Integer>> succ = new ArrayList<ArrayList<Integer>>();
		for (int i = 0; i < N; i++) {
			succ.add(new ArrayList<Integer>());
		}
		for (int j = 0; j < mid; j++) {
			ArrayList<Integer> connections = pref.get(j);
			for (int i = 0; i < connections.size() - 1; i++) {
				pred[connections.get(i + 1)]++;
				succ.get(connections.get(i)).add(connections.get(i + 1));
			}
		}
		return topologicalSort(succ, pred);
	}
	
	private static boolean doableII(int mid) {
		int[] pred = new int[N];
		ArrayList<ArrayList<Integer>> succ = new ArrayList<ArrayList<Integer>>();
		for (int i = 0; i < N; i++) {
			succ.add(new ArrayList<Integer>());
		}
		for (int j = 0; j < mid; j++) {
			ArrayList<Integer> connections = pref.get(j);
			for (int i = 0; i < connections.size() - 1; i++) {
				pred[connections.get(i + 1)]++;
				succ.get(connections.get(i)).add(connections.get(i + 1));
			}
		}
		return topologicalSortII(succ, pred);
	}

	public static boolean topologicalSort(ArrayList<ArrayList<Integer>> succ, int[] pred) {
		result = new int[N];
		PriorityQueue<Integer> p = new PriorityQueue<Integer>();
		boolean visited[] = new boolean[N];
		for (int i = 0; i < N; i++) {
			visited[i] = false;
		}
		for (int i = 0; i < N; i++) {
			if (pred[i] == 0) {
				p.add(i);
			}
		}
		for (int i = 0; i < N; i++) {
			if (p.isEmpty()) {
				return false;
			}
			result[i] = p.poll();
			for (int next : succ.get(result[i])) {
				if (--pred[next] == 0) {
					p.add(next);
				}
			}
		}
		return true;
	}

	public static boolean topologicalSortII(ArrayList<ArrayList<Integer>> succ, int[] pred) {
		result = new int[N];
		LinkedList<Integer> p = new LinkedList<Integer>();
		boolean visited[] = new boolean[N];
		for (int i = 0; i < N; i++) {
			visited[i] = false;
		}
		for (int i = 0; i < N; i++) {
			if (pred[i] == 0) {
				p.add(i);
			}
		}
		for (int i = 0; i < N; i++) {
			if (p.isEmpty()) {
				return false;
			}
			result[i] = p.poll();
			for (int next : succ.get(result[i])) {
				if (--pred[next] == 0) {
					p.add(next);
				}
			}
		}
		return true;
	}

}
