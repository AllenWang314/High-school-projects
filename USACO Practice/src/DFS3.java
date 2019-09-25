// maximum path sum through tree


 class TreeNode {
      int val;
      TreeNode left;
      TreeNode right;
      TreeNode(int x) { val = x; }
  }
 
public class DFS3 {
	public static int max;
	
    public static int maxPathSum(TreeNode root) {
        int a = dfs(root);
        return Math.max(a, max);
    }
    
    // returns the single maximum at that location
    // updates maximum to overall maximum if necessary
    public static int dfs(TreeNode root) {
    		if (root == null) return 0;
    		int lSingle = dfs(root.left);
    		int rSingle = dfs(root.right);
    		int ans = Math.max(root.val + lSingle, + root.val + rSingle);
    		ans = Math.max(ans, root.val);
    		max = Math.max(max, ans);
    		max = Math.max(max, root.val + lSingle + rSingle);
    		return ans;
    }
    
    public static void main(String[] args) {
    		TreeNode node1 = new TreeNode(2);
    		TreeNode node2 = new TreeNode(-1);
    		TreeNode node3 = new TreeNode(-2);
    		node1.right = node3;
    		node1.left = node2;
    		System.out.println(maxPathSum(node1));
    }
    
}