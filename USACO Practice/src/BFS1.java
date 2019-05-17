import java.io.*;
import java.util.*;

public class BFS1 {

	public static boolean[][][][] edges;
	public static boolean[][] cows;
	public static int[][] cowsOrdered;
	public static int N;
	public static int K;
	public static int R;
	public static int totalPairs;

	public static void makeTest() throws IOException {
		PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("countcross.in")));
		pw.write("3 3 3\n" + "2 2 2 3\n" + "3 3 3 2\n" + "3 3 2 3\n" + "3 3\n" + "2 2\n" + "2 3");
		pw.close();
	}

	public static void main(String[] args) throws IOException {
//		makeTest();
		BufferedReader br = new BufferedReader(new FileReader("countcross.in"));
		PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("countcross.out")));

		StringTokenizer tkn = new StringTokenizer(br.readLine());
		N = Integer.parseInt(tkn.nextToken());
		K = Integer.parseInt(tkn.nextToken());
		R = Integer.parseInt(tkn.nextToken());
		edges = new boolean[N][N][N][N];
		cows = new boolean[N][N];
		cowsOrdered = new int[K][2];

		for (int i = 0; i < R; i++) {
			tkn = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(tkn.nextToken()) - 1;
			int b = Integer.parseInt(tkn.nextToken()) - 1;
			int c = Integer.parseInt(tkn.nextToken()) - 1;
			int d = Integer.parseInt(tkn.nextToken()) - 1;
			edges[a][b][c][d] = true;
			edges[c][d][a][b] = true;
		}

		for (int i = 0; i < K; i++) {
			tkn = new StringTokenizer(br.readLine());
			int e = Integer.parseInt(tkn.nextToken()) - 1;
			int f = Integer.parseInt(tkn.nextToken()) - 1;
			cows[e][f] = true;
			cowsOrdered[i] = new int[] { e, f };
		}

		for (int i = 0; i < K; i++) {
			BFS(cowsOrdered[i]);
//			System.out.println("i is " + i + " and totalPairs is " + totalPairs);
		}

//		System.out.println(totalPairs);
		int nondistant = (totalPairs - K)/2;
		int distant = K * (K-1) / 2 - nondistant;
//		System.out.println(distant);
		pw.write("" + distant);
		br.close();
		pw.close();
	}

	public static void BFS(int[] root) {
		LinkedList<int[]> toProcess = new LinkedList<int[]>();
		boolean[][] visited = new boolean[N][N];
		toProcess.add(root);
		while (toProcess.peek() != null) {
			int[] cow = toProcess.poll();
			int x = cow[0];
			int y = cow[1];
			if (visited[x][y])
				continue;
			if (cows[x][y] == true) {
				totalPairs++;
//				System.out.println(x + " " + y);
			}
			visited[x][y] = true;
			if (x + 1 < N && x + 1 >= 0 && y < N && y >= 0 && !edges[x + 1][y][x][y]) {
				toProcess.add(new int[] { x + 1, y });
			}
			if (x - 1 < N && x - 1 >= 0 && y < N && y >= 0 && !edges[x - 1][y][x][y]) {
				toProcess.add(new int[] { x - 1, y });
			}
			if (x < N && x >= 0 && y + 1 < N && y + 1 >= 0 && !edges[x][y][x][y + 1]) {
				toProcess.add(new int[] { x, y + 1 });
			}
			if (x < N && x >= 0 && y - 1 < N && y - 1 >= 0 && !edges[x][y][x][y - 1]) {
				toProcess.add(new int[] { x, y - 1 });
			}

		}
	}

}
