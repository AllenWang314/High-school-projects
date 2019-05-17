
/*
 * bit business
 */
public class BitManipulation {
   
    
    public static void main(String[] args) {
    		int a = 8; // 1000
    		int b = 9; // 1001
    		System.out.println(a & b); // prints 8
    		System.out.println(a | b); // prints 9
    		System.out.println(a ^ b); // prints 1
    		System.out.println(a >> 1); // prints 4
    		System.out.println(a >> 2); // prints 2
    		System.out.println(a << 1); // prints 16
    		System.out.println(a << 2); // prints 32
    		System.out.println(9 >> 1); // prints 4
    		System.out.println(9 + (9 & (-9))); // prints 10 (find parent)

    		System.out.println(1 ^ 4 ^ 16);


    }
    
}
