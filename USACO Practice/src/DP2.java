import java.io.*;
import java.util.*;


public class DP2 {

	public static int N;
	public static int K;
	public static int[] skills;
	public static int[] maxSkills;
	
	public static void makeTest() throws IOException {
		PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("teamwork.in")));
		pw.write("7 3\n" + 
				"1\n" + 
				"15\n" + 
				"7\n" + 
				"9\n" + 
				"2\n" + 
				"5\n" + 
				"10");
		pw.close();
	}
	
	public static void main(String[] args) throws IOException {
//		makeTest();
		BufferedReader br = new BufferedReader(new FileReader("teamwork.in"));
		PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("teamwork.out")));

		StringTokenizer tkn = new StringTokenizer(br.readLine());
		N = Integer.parseInt(tkn.nextToken());
		K = Integer.parseInt(tkn.nextToken());
		skills = new int[N];
		maxSkills = new int[N];
		
		for (int i = 0; i < N; i++) {
			skills[i] = Integer.parseInt(br.readLine());
			maxSkills[i] = -1;
		}
		
		int max = skills[0];
		for (int i = 0; i < K; i++) {
			max = Math.max(max, skills[i]);
			maxSkills[i] = max * (i+1);
		}

		int answer = fillArray(N-1);
		
//		System.out.println(answer);
//		System.out.println(Arrays.toString(maxSkills));
		pw.write("" + answer);
		br.close();
		pw.close();
	}
	
	public static int fillArray(int top) {
		if (top < 0) return 0;
		if (maxSkills[top] > -1) return maxSkills[top];
		int currentMax = skills[top];
		int currentTot = 0;
		for (int i = 0; i < K; i++) {
			// update the max
			currentMax = Math.max(currentMax, skills[top-i]);
			currentTot = Math.max(currentTot, currentMax * (i+1) + fillArray(top-i-1));
		}
		return maxSkills[top] = currentTot;
	}
	
}
