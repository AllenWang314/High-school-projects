import java.util.*;

public class HRTemplate {
    public static void main(String args[] ) throws Exception {
        Scanner scanner = new Scanner(System.in);
        ArrayList<Integer> info = new ArrayList<Integer>();
        while (scanner.hasNext()){
            info.add(Integer.parseInt(scanner.next()));
        }
        scanner.close();
    }
}

