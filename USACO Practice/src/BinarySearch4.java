
public class BinarySearch4 {
	
	// inclusive of both low and high
	public static int bs(int low, int high) {
		while(high >= low + 1) {
			int mid = (low + high)/2;
			if(possible(mid)) high = mid;
			else low = mid+1;
		}
		return high;
	}
	
	public static boolean possible(int test) {
		return (test > 30);
	}
	
	public static void main(String[] args) {
		System.out.println(bs(29, 30));
	}
}
