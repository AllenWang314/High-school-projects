import java.io.*;
import java.util.*;

public class DP4 {

	static int N;
	static int[] breakouts;
	static int[][] dp;
	
	public static void makeTest() throws IOException {
		PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("taming.in")));
		pw.write("6\n" + 
				"1 1 2 0 0 1");
		pw.close();
	}

	public static void main(String[] args) throws IOException {
//		makeTest();
		BufferedReader br = new BufferedReader(new FileReader("taming.in"));
		PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("taming.out")));

		N = Integer.parseInt(br.readLine());
		breakouts = new int[N];
		dp = new int[N+1][N+1];
		StringTokenizer tkn = new StringTokenizer(br.readLine());
		
		for (int i = 0; i < N; i++) {
			breakouts[i] = Integer.parseInt(tkn.nextToken());
		}
		
		for (int b = 0; b <= N; b++) {
			for(int d = 0; d < b; d++) {
				dp[b][d] = Integer.MAX_VALUE;
			}
		}
		
		// initialize the cases when there is only 1 breakout when d days remain
		for (int d = 1; d <= N; d++) {
			int total = 0;
			for (int i = N-d; i < N; i++) {
				if (breakouts[i] != i - (N-d)) {
					total++;
				}
			}
			dp[1][d] = total;
		}

		for (int b = 2; b <= N; b++) {
			for (int d = b; d <= N; d++) {
				dyn(b, d);
			}
		}

		for (int i = 1; i <=N; i++) {
			pw.println(dp[i][N]);
		}
		br.close();
		pw.close();
	}
//

	// we have that d days remain
	private static void dyn(int b, int d) {
		int min = Integer.MAX_VALUE;
		int total = 0;
		for (int i = 0; i < d; i++) {
			if (breakouts[N-d+i] != i) total++;
			if (dp[b-1][d-i-1] == Integer.MAX_VALUE) continue;
			min = Math.min(min, total + dp[b-1][d-i-1]);
		}
		dp[b][d] = min;
	}
}
