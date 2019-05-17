import java.io.*;
import java.util.*;

public class Dijkstra2 {


	private static int N;
	private static int M;
	private static int T;
	private static long[] cowList;
	private static int[][] adj;
	
	public static void makeTest() throws IOException {
		PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("shortcut.in")));
		pw.write("5 6 2\n" + 
				"1 2 3 4 5\n" + 
				"1 2 5\n" + 
				"1 3 3\n" + 
				"2 4 3\n" + 
				"3 4 5\n" + 
				"4 5 2\n" + 
				"3 5 7");
		pw.close();
	}

	public static void main(String[] args) throws IOException {
//		makeTest();
		BufferedReader br = new BufferedReader(new FileReader("shortcut.in"));
		PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("shortcut.out")));

		StringTokenizer tkn = new StringTokenizer(br.readLine());
		N = Integer.parseInt(tkn.nextToken());
		M = Integer.parseInt(tkn.nextToken());
		T = Integer.parseInt(tkn.nextToken());
		cowList = new long[N];
		adj = new int[N][N];
		tkn = new StringTokenizer(br.readLine());
		for (int i = 0; i< N; i++) {
			cowList[i] = Integer.parseInt(tkn.nextToken());
		}
		System.out.println(M);
		System.out.println(N);
		for (int i = 0; i < M; i++) {
			tkn = new StringTokenizer(br.readLine());
			int loc1 = Integer.parseInt(tkn.nextToken())-1;
			int loc2 = Integer.parseInt(tkn.nextToken())-1;
			int time = Integer.parseInt(tkn.nextToken());
			adj[loc1][loc2] = time;
			adj[loc2][loc1] = time;
		}

		long[][] result = dijkstra(adj, 0);
		long[] cowsPassing = new long[N];
		for(int i = 0; i < N; i++) {
			long current = (long) i;
			while (current != 0L) {
				cowsPassing[(int) current] += cowList[i];
				current = result[(int) current][1];
			}
		}
		
		long max = 0L;
		for (int i = 0; i < N; i++) {
			max = Math.max(max, cowsPassing[i] * ((result[i][0]) - T));
		}

		pw.write("" + max);
		br.close();
		pw.close();
	}

	public static int minDistance(long dist[][], Boolean visited[]) { 
        long min = Long.MAX_VALUE, min_index=-1; 
  
        for (int v = N-1; v >= 0; v--) {
        		if (visited[v] == false && dist[v][0] <= min) { 
                min = dist[v][0]; 
                min_index = v; 
            } 
        }
        return (int) min_index; 
    } 

    public static long[][] dijkstra(int graph[][], int src) { 
        long dist[][] = new long[N][2];
        Boolean visited[] = new Boolean[N]; 
  
        for (int i = 0; i < N; i++) { 
            dist[i] = new long[] {Long.MAX_VALUE,Long.MAX_VALUE}; 
            visited[i] = false; 
        } 
  
        dist[src] = new long[] {0,0}; 
        for (int count = 0; count < N-1; count++) { 
            int u = minDistance(dist, visited); 
            visited[u] = true; 
  
            for (int v = 0; v < N; v++) {
            		if (!visited[v] && graph[u][v] != 0 && 
                        dist[u][0] != Integer.MAX_VALUE && 
                        dist[u][0] + graph[u][v] == dist[v][0] &&
                        		u < dist[v][1]) {
                    dist[v][0] = dist[u][0] + graph[u][v]; 
                    dist[v][1] = u;
                }
            		else if (!visited[v] && graph[u][v] != 0 && 
                        dist[u][0] != Integer.MAX_VALUE && 
                        dist[u][0] + graph[u][v] < dist[v][0]) {
                    dist[v][0] = dist[u][0] + graph[u][v]; 
                    dist[v][1] = u;
                }
                
            }
        }
        return dist;
    } 
}
