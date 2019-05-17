import java.io.*;
import java.util.*;

public class Dijkstra4 {

	private int dist[];
	private Set<Integer> settled;
	private PriorityQueue<Node> pq;
	private int V; // Number of vertices
	List<List<Node>> adj;

	// Class to represent a node in the graph
	class Node implements Comparator<Node> {
		public int node;
		public int cost;

		public Node() {
		}

		public Node(int node, int cost) {
			this.node = node;
			this.cost = cost;
		}

		@Override
		public int compare(Node node1, Node node2) {
			if (node1.cost < node2.cost)
				return -1;
			if (node1.cost > node2.cost)
				return 1;
			return 0;
		}
	}

	public Dijkstra4(int V) {
		this.V = V;
		dist = new int[V];
		settled = new HashSet<Integer>();
		pq = new PriorityQueue<Node>(V, new Node());
	}

	public int[] dijkstra(List<List<Node>> adj, int source) {
		this.adj = adj;

		for (int i = 0; i < V; i++) {
			dist[i] = Integer.MAX_VALUE;
		}

		// Add source node to the priority queue
		pq.add(new Node(source, 0));

		// Distance to the source is 0
		dist[source] = 0;

		while (settled.size() != V) {
			int u = pq.poll().node;
			if (!settled.contains(u)) {
				settled.add(u);
				eNeighbours(u);
			}
		}
		return dist;
	}

	public void eNeighbours(int u) {
		for (int i = 0; i < adj.get(u).size(); i++) {
			Node v = adj.get(u).get(i);
			if (!settled.contains(v.node)) {
				if (dist[u] + v.cost < dist[v.node]) {
					dist[v.node] = dist[u] + v.cost;
				}
				pq.add(new Node(v.node, dist[v.node]));
			}
		}
	}

	public static void makeTest() throws IOException {
		PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("dining.in")));
		pw.write("4 5 1\n" + "1 4 10\n" + "2 1 20\n" + "4 2 3\n" + "2 3 5\n" + "4 3 2\n" + "2 7");
		pw.close();
	}

	public static void main(String[] args) throws IOException {
//		makeTest();
		BufferedReader br = new BufferedReader(new FileReader("dining.in"));
		PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("dining.out")));

		StringTokenizer tkn = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(tkn.nextToken());
		int M = Integer.parseInt(tkn.nextToken());
		int K = Integer.parseInt(tkn.nextToken());

		List<List<Node>> adj = new ArrayList<List<Node>>();

		// Initialize list for every node
		for (int i = 0; i < N; i++) {
			adj.add(new ArrayList<Node>());
		}

		Dijkstra4 d = new Dijkstra4(N);

		for (int i = 0; i < M; i++) {
			tkn = new StringTokenizer(br.readLine());
			int vertex1 = Integer.parseInt(tkn.nextToken()) - 1;
			int vertex2 = Integer.parseInt(tkn.nextToken()) - 1;
			int cost = Integer.parseInt(tkn.nextToken());
			adj.get(vertex1).add(d.new Node(vertex2, cost));
			adj.get(vertex2).add(d.new Node(vertex1, cost));
		}

		int[] firstDist = d.dijkstra(adj, N - 1);

		adj.add(new ArrayList<Node>());

		for (int i = 0; i < K; i++) {
			tkn = new StringTokenizer(br.readLine());
			int vertex1 = Integer.parseInt(tkn.nextToken()) - 1;
			int yumminess = Integer.parseInt(tkn.nextToken());
			adj.get(N).add(d.new Node(vertex1, firstDist[vertex1] - yumminess));
			adj.get(vertex1).add(d.new Node(N, firstDist[vertex1] - yumminess));
		}

		Dijkstra4 d2 = new Dijkstra4(N + 1);
		int[] secondDist = d2.dijkstra(adj, N);

		System.out.println(Arrays.toString(firstDist));
		System.out.println(Arrays.toString(secondDist));
		for (int i = 0; i <= N-2; i++) {
			if (secondDist[i] <= firstDist[i]) {
				pw.write("" + 1);
			}
			else {
				pw.write("" + 0);
			}
			if (i != N-2) pw.write("\n");
		}

		br.close();
		pw.close();
	}
}
