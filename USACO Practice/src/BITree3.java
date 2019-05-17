
import java.io.*;
import java.util.*;

public class BITree3 {

	private static int N;
	private static int K = 1;
	private static int[] perm;

	public static void makeTest() throws IOException {
		PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("sleepy.in")));
		pw.write("4\n" + 
				"1 2 4 3");
		pw.close();
	}
	
	public static void main(String[] args) throws IOException {
//		makeTest();
		BufferedReader br = new BufferedReader(new FileReader("sleepy.in"));
		PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("sleepy.out")));
		BITree3 s = new BITree3();
		
		N = Integer.parseInt(br.readLine());
		perm = new int[N];
		StringTokenizer tkn = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			perm[i] = Integer.parseInt(tkn.nextToken());
		}
		
		K = N-1;
		s.update(perm[K],1);
		while (K > 0 && perm[K] > perm[K-1]) {
			s.update(perm[K-1],1);
			K--;
		}
		
		String answer = K + "\n";
		for (int i = 0; i < K; i++) {
			s.update(perm[i],1);
			answer += (K - i - 2 + s.query(perm[i])) + " ";
		}

//		for (int i = 0; i < 4; i++) {
//			System.out.println(s.get(i));
//		}
//		System.out.println(answer);
		pw.write(answer.substring(0, answer.length()-1));
		br.close();
		pw.close();
	}
	
	public static final int maxSize = 100100;
	public int[] tree;
	

	public void update(int index, int val) {
		index++;
		while (index < tree.length) {
			tree[index] += val;
			index += index & -index;
		}
	}

	public int query(int index) {
		int ret = 0;
		index++;
		while (index > 0) {
			ret += tree[index];
			index -= index & -index;
		}
		return ret;
	}
	
	
	
	public BITree3() {
		tree = new int[maxSize];
	}


}
