
public class BinarySearch {
	public static void main(String[] args) {

		System.out.println(search(new int[] { 0, 1, 2, 3, 4, 5, 6 }, 3));

	}

	public static int search(int[] list, int target) {

		int start = 0;
		int end = list.length;
		int index = end / 2;

		while (start <= end) {
			if (list[index] == target)
				return index;
			else if (list[index] < target)
				start = index + 1;
			else
				end = index - 1;
			index = (start + end) / 2;
		}
		return -1;

	}
	
	/*
	 * 
	 *   // Binary search
  // Invariant: lo <= ans < hi
  int lo = 0;
  int hi = m+1;
  while (hi > lo+1) {
    int mid = (lo + hi) / 2;
    if (doable(mid)) {
      lo = mid;
    } else {
      hi = mid;
    }
  }
  int ans = lo;
	 * 
	 */
}
