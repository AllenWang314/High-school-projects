import java.io.*;
import java.util.*;

public class DP1 {

	public static long MOD = 1000000007L;

	
	public static void makeTest() throws IOException {
		PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("poetry.in")));
		pw.write("3 3 10\n" + 
				"3 1\n" + 
				"4 1\n" + 
				"3 2\n" + 
				"A\n" + 
				"B\n" + 
				"A");
		pw.close();
	}
	
	public static void main(String[] args) throws IOException {
//		makeTest();
		BufferedReader br = new BufferedReader(new FileReader("poetry.in"));
		PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("poetry.out")));
		
		StringTokenizer tkn = new StringTokenizer(br.readLine());
		// N, M, K
		
		int N = Integer.parseInt(tkn.nextToken()); // num of words
		int M = Integer.parseInt(tkn.nextToken()); // num of lines
		int K = Integer.parseInt(tkn.nextToken()); // num of syllables per line
		Word[] words = new Word[N];
		DP1 s = new DP1();
		
		// first we compute the dictionary
		for (int i = 0; i < N; i++) {
			tkn = new StringTokenizer(br.readLine());
			words[i] = s. new Word(Integer.parseInt(tkn.nextToken()), Integer.parseInt(tkn.nextToken()));
		}
		
		// we now calculate the frequencies
		HashMap<String, Integer> h = new HashMap<String, Integer>();
		for (int i = 0; i < M; i++) {
			tkn = new StringTokenizer(br.readLine());
			String rhy = tkn.nextToken();
			if (h.containsKey(rhy)) {
				h.put(rhy, h.get(rhy) + 1);
			}
			else {
				h.put(rhy, 1);
			}
		}
		
	    //dp[x] = the number of ways to make a line with x syllables.
	    long[] dp = new long[K+1];
	    dp[0] = 1L;
	    
	    //r[x] = the number of ways to form a full line that ends with rhyme scheme x
	    long[] r = new long[N+1];
	    
		for(int i = 0; i <= K; i++) {
	         for(int j = 0; j < N; j++){
	             if(words[j].syl + i > K) continue;
	             if(words[j].syl + i == K){
	                r[words[j].rhy] = (r[words[j].rhy] + dp[i]) % MOD;
	             }
	             dp[words[j].syl + i] = (dp[words[j].syl + i] + dp[i]) % MOD;
	          }
		}
		
		// we now have to compute the actual product
		
	    long answer = 1L;
	    for (String str : h.keySet()){
	         int freq = h.get(str);
	         long sum = 0L;
	         for(int i = 0; i < r.length; i++){
	            if(r[i] == 0) continue;
	            sum = (sum + exp(r[i],freq)) % MOD;
	         }
	      
	         answer = (answer * sum) % MOD;
	      }

		System.out.println(answer);
		pw.write("" + answer);
		br.close();
		pw.close();
	}
	
	public static long exp(long base, int exponent) {
		if (exponent == 0) {
			return 1;
		}
		if (exponent % 2 == 0) {
			long a = exp(base, exponent/2);
			return (a * a) % MOD;
		}
		long a = (exp(base, (exponent - 1)/2));
		return ((a * base % MOD) * a) % MOD;

	}
		   
	
	class Word {
		int syl;
		int rhy;
		
		Word(int syl, int rhy) {
			this.syl = syl;
			this.rhy = rhy;
		}
	}
}
