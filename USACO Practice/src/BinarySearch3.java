
public class BinarySearch3 {

	public double findMedianSortedArrays(int[] nums1, int[] nums2) {
		return findIndex(nums1, nums2, 0, nums1.length + 1);
	}

	public int findIndex(int[] nums1, int[] nums2, int start, int end) {
		if (start + 1 == end) {
			return start;
		}
		int i = (start + end) / 2;
		int j = (nums1.length + nums2.length) / 2 - i;
		if (j < 0) {
			return findIndex(nums1, nums2, i, end);
		} else if (j > nums2.length) {
			return findIndex(nums1, nums2, start, i);
		} else if (i > 0 && j < nums2.length && nums1[i - 1] > nums2[j]) {
			return findIndex(nums1, nums2, i, end);
		} else {
			return findIndex(nums1, nums2, start, i);
		}
	}
}
