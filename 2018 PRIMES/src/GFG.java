// Java program to generate all unique
import java.util.ArrayList;
 
class GFG 
{
    // Function to print an array p[] of size n
    static Partition printArray(int p[], int n)
    {
        return new Partition(p, n);
    }
     
    // Function to generate all unique partitions of an integer
    static ArrayList<Partition> allPartitions(int n)
    {
    		ArrayList<Partition> answer = new ArrayList<Partition>();
        int[] p = new int[n]; // An array to store a partition
        int k = 0;  // Index of last element in a partition
        p[k] = n;  // Initialize first partition as number itself
  
        // This loop first prints current partition, then generates next
        // partition. The loop stops when the current partition has all 1s
        while (true)
        {
            // print current partition
            answer.add(printArray(p, k+1));
  
            // Generate next partition
  
            // Find the rightmost non-one value in p[]. Also, update the
            // rem_val so that we know how much value can be accommodated
            int rem_val = 0;
            while (k >= 0 && p[k] == 1)
            {
                rem_val += p[k];
                k--;
            }
  
            // if k < 0, all the values are 1 so there are no more partitions
            if (k < 0)
            		break;
  
            // Decrease the p[k] found above and adjust the rem_val
            p[k]--;
            rem_val++;
  
  
            // If rem_val is more, then the sorted order is violeted.  Divide
            // rem_val in differnt values of size p[k] and copy these values at
            // different positions after p[k]
            while (rem_val > p[k])
            {
                p[k+1] = p[k];
                rem_val = rem_val - p[k];
                k++;
            }
  
            // Copy rem_val to next position and increment position
            p[k+1] = rem_val;
            k++;
        }
        return answer;
    }
    
    // Driver program
    public static void main (String[] args) 
    {
    		System.out.println(allPartitions(1));
    		System.out.println(allPartitions(2));
    		System.out.println(allPartitions(3));
    		System.out.println(allPartitions(4));
    		System.out.println(allPartitions(5));
    }
    
}