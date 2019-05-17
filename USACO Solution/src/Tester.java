
import java.io.*;

public class Tester {

	public static void main(String[] args) throws IOException {
		for(int i = 1; i<= 10; i++) {
			BufferedReader br = new BufferedReader(new FileReader(i + ".in"));
			PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter("question.in")));
			int c = br.read();
			while(c != -1) {
				pw.write(c);
				c = br.read();
			}
			pw.close();
//			Solution.main();
			
			// at this point question.out will contain the answer
			
			BufferedReader br2 = new BufferedReader(new FileReader("question.out"));
			BufferedReader br3 = new BufferedReader(new FileReader(i + ".out"));

			System.out.println("My answer for question #" + i + ":");
			String line = null;
			 while ((line = br2.readLine()) != null) {
			   System.out.println(line);
			 }
			System.out.println("The correct answer is:");
			String line2 = null;
			 while ((line2 = br3.readLine()) != null) {
			   System.out.println(line2);
			 }
			System.out.println();
			br.close();
			br2.close();
			br3.close();
		}
	}
}

