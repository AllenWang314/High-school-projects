import java.util.*;
import java.io.*;

public class Solution {
	
	static int N, M;
	
	public static void makeTest() throws IOException {
		PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("exercise.in")));
		pw.write("5 1000000007");
		pw.close();
	}

	public static void main(String[] args) throws IOException {
//		makeTest();
		BufferedReader br = new BufferedReader(new FileReader("exercise.in"));
		PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("exercise.out")));
		StringTokenizer tkn = new StringTokenizer(br.readLine());
		N = Integer.parseInt(tkn.nextToken());
		M = Integer.parseInt(tkn.nextToken());
		int[] num = new int[N+1];
		ArrayList<Integer> primes = new ArrayList<Integer>();

		for (int i=2; i<=N; ++i) {
		    if (num[i] == 0) {
		        num[i] = i;
		        primes.add(i);
		    }
		    for (int j=0; j< primes.size() && primes.get(j) <= num[i] && i*primes.get(j) <= N; ++j)
		        num[i * primes.get(j)] = primes.get(j);
		}
				
//		// need a table of logs
//		int[][] log = new int[N+1][primes.size()]; // for each prime, int[i][j] gives j^k where j^k is the greatest power <= i
//		for (int j = 0; j < primes.size(); j++) {
//			int pwr = 1;
//			for (int i = 1; i < N+1; i++) {
//				if (pwr*primes.get(j) <= i) {
//					pwr *= primes.get(j);
//					log[i][j] = 	pwr;
//				} else log[i][j] = pwr;
//			}
//		}
		
		
		long [][] dp = new long[N+1][primes.size()];

		for (int i = 0; i < primes.size(); i++) {
			dp[0][i] = 1;
		}
		
		long largest_p = primes.get(primes.size()-1);
		dp[0][primes.size()-1] = dp[1][primes.size()-1] = 1;
		long res = 1;
		for(int i = 1; i < N+1; i++) {
			// sum of all prime powers up to i
			if (res*largest_p <= i) {
				res *= largest_p;
				dp[i][primes.size()-1] = ((dp[i-1][primes.size()-1] + res) % M );
			} else {
				dp[i][primes.size()-1] = (dp[i-1][primes.size()-1] % M);
			}
		}
				
		for (int i = 0; i <= N; i++) { 
			for (int p = primes.size()-2; p >= 0; p--) {
				int curr_p = primes.get(p);
				long sum = (dp[i][p+1]) % M;
				for (int j = curr_p; j <= i; j*=curr_p) {
					sum += (j*dp[i-j][p+1]) % M;
				}
				dp[i][p] = sum % M;
			}
		}
//		System.out.println(Arrays.deepToString(dp));
//		
		
		pw.write("" + (dp[N][0] % M));
		br.close();
		pw.close();
	}
	
}
