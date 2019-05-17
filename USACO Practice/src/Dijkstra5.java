import java.io.*;
import java.util.*;

public class Dijkstra5 {

	static int N, M, K;
	static HashMap<Integer, HashMap<Integer, Integer>> edges;
	static HashMap<Integer, Integer> hays;

	@SuppressWarnings("resource")
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new FileReader("dining.in"));
		PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("dining.out")));

		StringTokenizer tkn = new StringTokenizer(br.readLine());
		N = Integer.parseInt(tkn.nextToken());
		M = Integer.parseInt(tkn.nextToken());
		K = Integer.parseInt(tkn.nextToken());
		edges = new HashMap<>();
		hays = new HashMap<>();

		for (int i = 0; i < M; i++) {
			tkn = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(tkn.nextToken());
			int b = Integer.parseInt(tkn.nextToken());
			int w = Integer.parseInt(tkn.nextToken());
			if (!edges.containsKey(a)) {
				edges.put(a, new HashMap<>());
			}
			if (!edges.containsKey(b)) {
				edges.put(b, new HashMap<>());
			}
			edges.get(a).put(b, w);
			edges.get(b).put(a, w);
		}

		for (int i = 0; i < K; i++) {
			tkn = new StringTokenizer(br.readLine());
			int p = Integer.parseInt(tkn.nextToken());
			int y = Integer.parseInt(tkn.nextToken());
			hays.put(p, y);
		}

		// get the distance between barn and each field
		HashMap<Integer, Integer> dist = new HashMap<>();
		dist.put(N, 0);
		dijkstra(N, dist);

		HashMap<Integer, Integer> dist2 = new HashMap<>();

		edges.put(N + 1, new HashMap<>());
		for (int f : hays.keySet()) {
			int w = dist.get(f) - hays.get(f);
			edges.get(N + 1).put(f, w);
		}
		dist2.put(N + 1, 0);
		dijkstra(N + 1, dist2);

		for (int i = 1; i <= N - 1; i++) {
			int v = dist2.get(i) <= dist.get(i) ? 1 : 0;
			pw.println(v);
		}
		
		pw.close();
		
	}

	static void dijkstra(int src, HashMap<Integer, Integer> dist) {
		PriorityQueue<Integer> front = new PriorityQueue<>(N, new Comparator<Integer>() {
			@Override
			public int compare(Integer o1, Integer o2) {
				return dist.get(o1) - dist.get(o2);
			}
		});

		front.add(src);
		while (!front.isEmpty()) {
			int a = front.poll();
			for (int b : edges.get(a).keySet()) {
				if (!dist.containsKey(b) || dist.get(b) > dist.get(a) + edges.get(a).get(b)) {
					dist.put(b, dist.get(a) + edges.get(a).get(b));
					front.offer(b);
				}
			}
		}
	}

	static void dijkstra2(int src, HashMap<Integer, Integer> dist) {
		LinkedList<Integer> front = new LinkedList<>();
		front.add(src);
		while (!front.isEmpty()) {
			int a = front.removeFirst();
			for (int b : edges.get(a).keySet()) {
				if (!dist.containsKey(b) || dist.get(b) > dist.get(a) + edges.get(a).get(b)) {
					dist.put(b, dist.get(a) + edges.get(a).get(b));
					front.addLast(b);
				}
			}
		}
	}
}