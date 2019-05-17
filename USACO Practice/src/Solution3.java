import java.util.ArrayList;
import java.util.Arrays;

public class Solution3 {

	public static void main(String[] args) {
//		ArrayList<Integer> a = new ArrayList<Integer>();
//		a.add(3);
//		a.add(4);
//		a.add(5);
//		a.add(5);
//		a.add(2);
//		a.add(1);
//		a.add(0);
//		insertSort(a);
//		System.out.println(a);
		
		int[] a = new int[] {3,1,2,1,5,9};
		quickSort(a);
	}

	@SuppressWarnings("unused")
	private static void insertSort(ArrayList<Integer> a) {
		for (int i = 0; i < a.size(); i++) {
			int spot = i;
			System.out.println("we are sorting index " + i);
			for (int j = 0; j < i; j++) {
				if (a.get(i) <= a.get(j)) {
					spot = j;
					if (a.get(i) == 2 && j == 0) System.out.println("STAPW");
					System.out.println(a.get(i)  + " " + a.get(j));
					j=i;
				}
			}
			System.out.println("hi the index spot is " + spot);
			a.add(spot, a.get(i));
			a.remove(i + 1);
		}
	}
	
	public static void quickSort(int[] a) {
		quickSort(a, 0, a.length);
	}
	
	public static void quickSort(int[] a, int start, int end) {
		if (end - start < 2) return;
		int pivote = (start+ end)/2;
		int hi = end-1;
		int low = start;
		while(hi > low) {
			if(a[hi] >= a[pivote]) {
				hi--;
			}
			else if (a[low] <= a[pivote]) {
				low++;
			}
			else {
				int tempe = a[hi];
				a[hi] = a[low];
				a[low] = tempe;
				low ++;
				hi --;
			}
		}
		System.out.println("index: " + pivote + " value: " + a[pivote]);
		int tempe = a[hi];
		a[hi] = a[pivote];
		a[pivote] = tempe;
		System.out.println(Arrays.toString(a));
		System.out.println("hi is " + hi);
		quickSort(a,start,hi);
		quickSort(a,hi+1,end);
	}
	
	
}
