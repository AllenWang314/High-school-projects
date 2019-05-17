
public class Solution7 {
	    public static int reverse(int x) {
	        if (x < 0) return (-1) * Integer.parseInt(reverseII( "" + (-1)*x));
	        return Integer.parseInt(reverseII("" + x));
	    }
	    
	    public static String reverseII(String x){
	        if (x.length() <= 1) return x;
	        return reverseII(x.substring(1)) + x.substring(0,1);
	    }
	    
	    public static void main(String[] args) {
	    	System.out.println(reverse(1534236469));
	    }
}
