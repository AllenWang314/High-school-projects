import java.io.*;


public class Solution {

	public static void makeTest() throws IOException {
		PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("file.in")));
		pw.write("4\n" + 
				"1 2 4 3");
		pw.close();
	}
	
	@SuppressWarnings({ "unused", "resource" })
	public static void main(String[] args) throws IOException {
		makeTest();
		BufferedReader br = new BufferedReader(new FileReader("file.in"));
		PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("file.out")));
		Solution s = new Solution();
		
	}
}
