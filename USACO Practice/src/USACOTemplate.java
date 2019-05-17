import java.io.*;

public class USACOTemplate {

	public static void makeTest() throws IOException {
		PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("file.in")));
		pw.write("");
		pw.close();
	}

	public static void main(String[] args) throws IOException {
		makeTest();
		BufferedReader br = new BufferedReader(new FileReader("file.in"));
		PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("file.out")));

		pw.write("");
		br.close();
		pw.close();
	}

}
