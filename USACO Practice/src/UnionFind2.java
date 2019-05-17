import java.io.*;
import java.util.*;

public class UnionFind2 {

	static int N;
	static int[][] cows;
	static ArrayList<Edge> edges;
	static int[] parent;

	public static void makeTest() throws IOException {
		PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("moocast.in")));
		pw.write("4\n" + "1 3\n" + "5 4\n" + "7 2\n" + "6 1\n");
		pw.close();
	}

	public static void main(String[] args) throws IOException {
//		makeTest();
		BufferedReader br = new BufferedReader(new FileReader("moocast.in"));
		PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("moocast.out")));

		UnionFind2 u = new UnionFind2();
		N = Integer.parseInt(br.readLine());
		cows = new int[N][2];
		edges = new ArrayList<Edge>();
		parent = new int[N];

		for (int i = 0; i < N; i++) {
			StringTokenizer tkn = new StringTokenizer(br.readLine());
			cows[i] = new int[] { Integer.parseInt(tkn.nextToken()), Integer.parseInt(tkn.nextToken()) };
			parent[i] = i;
		}

		for (int i = 0; i < N; i++) {
			for (int j = i + 1; j < N; j++) {
				edges.add(u.new Edge(i, j, dist(i, j)));
			}
		}
		EdgeComparator e = u.new EdgeComparator();
		Collections.sort(edges, e);
		System.out.println(edges);
		
		int numEdges = 0;
		int distance = 0;
		for (int i = 0; i < edges.size() && numEdges != N-1; i++) {
			Edge current = edges.get(i);
			if (find(current.node1) != find(current.node2)) {
				numEdges++;
				distance = current.distance;
				merge(current.node1, current.node2);
			}
		}
		pw.write("" + distance);
		br.close();
		pw.close();
	}

	private static int dist(int i, int j) {
		return (cows[i][0] - cows[j][0]) * (cows[i][0] - cows[j][0])
				+ (cows[i][1] - cows[j][1]) * (cows[i][1] - cows[j][1]);
	}

	public class Edge {
		int node1;
		int node2;
		int distance;

		public Edge(int node1, int node2, int distance) {
			this.node1 = node1;
			this.node2 = node2;
			this.distance = distance;
		}

		public String toString() {
			return "node 1: " + node1 + ", node 2: " + node2;
		}
	}

	public class EdgeComparator implements Comparator<Edge> {

		@Override
		public int compare(Edge arg0, Edge arg1) {
			return arg0.distance - arg1.distance;
		}

	}

	public static int find(int curr) {
		return parent[curr] == curr ? curr : (parent[curr] = find(parent[curr]));
	}
	
	public static void merge(int x, int y) {
		parent[find(x)] = find(y);
	}

}
