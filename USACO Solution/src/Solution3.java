import java.util.*;
import java.io.*;

public class Solution3 {
	static int N, M;
	static ArrayList<HashSet<Integer>> arr;
	static HashSet<Integer> needVisit;
	
	
	public static void makeTest() throws IOException {
		PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("fcolor.in")));
		pw.write("9 12\n" + 
				"1 2\n" + 
				"4 2\n" + 
				"5 8\n" + 
				"4 6\n" + 
				"6 9\n" + 
				"2 9\n" + 
				"8 7\n" + 
				"8 3\n" + 
				"7 1\n" + 
				"9 4\n" + 
				"3 5\n" + 
				"3 4");
		pw.close();
	}

	public static void BFS(int root) {
		
	}
	
	public static void main(String[] args) throws IOException {
		makeTest();
		BufferedReader br = new BufferedReader(new FileReader("fcolor.in"));
		PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("fcolor.out")));
		StringTokenizer tkn = new StringTokenizer(br.readLine());
		N = Integer.parseInt(tkn.nextToken());
		M = Integer.parseInt(tkn.nextToken());
		arr =  new ArrayList<HashSet<Integer>>();
		needVisit = new HashSet<Integer>();
		arr.add(new HashSet<Integer>());
		for (int i = 1; i <= N; i ++) {
			arr.add(new HashSet<Integer>());
			needVisit.add(i);
		}
		for (int i = 0; i < M; i++) {
			StringTokenizer tkn2 = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(tkn2.nextToken());
			int b = Integer.parseInt(tkn2.nextToken());
			arr.get(a).add(b);
		}
		
		while (needVisit.size() > 0) {
			int i = needVisit.iterator().next();
			needVisit.remove(i);
			BFS(i);
		}
		
		br.close();
		pw.close();
	}
}
