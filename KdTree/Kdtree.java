package Kd;

public class Kdtree 
{
	private static class TreeNode
	{
		private int array[];
		private TreeNode left;
		private TreeNode right;
		
		private TreeNode(int array[], TreeNode left, TreeNode right)
		{
			this.array = array; // the dimensional ...i put the array so that i can extend it to more dimensions easier
			this.left = left; // the left node
			this.right = right; // the right node
		}
	}
	
	public static int times;
	public TreeNode root; // need it so the root before the first node will be null

	
	public Kdtree()
	{
		this.root = null;
		times =0; // need for the search
	}
	
	// i have to set a standard vector size as a var
	
	
	
	
	// now i have to create the method to add the vectors
	private static TreeNode insertNode(int array[], TreeNode subtree,int depth)
	{
		int level = 0;
		// now all i have to do is to diverse the array to the x and y in order to fill the node
		
		if(subtree == null) // the empty root case
		{
			System.out.println("Node Added");
			return new TreeNode(array, null, null);
		}// else missing
		else
		{
			// now i have to take cases of  the level so i will know how the tree will do the comparison
			level = depth % 2; // with co
			if(array[level]<subtree.array[level])
			{
				subtree.left = insertNode(array,subtree.left,depth+1);
			}
			else
				subtree.right = insertNode(array,subtree.right,depth+1);
		}
		return subtree;
	}
	
	public void add(int array[])
	{
		root = insertNode(array, root, 0);
	}
	
	
	
	// now i have to search for a node in the tree
	
	// same i will need 2 functions
	
	private static boolean searchNode (TreeNode subtree, int[] array, int depth)
	{
		int factor = depth % 2;
		if(subtree == null)
		{
			return false;
		}
		if(subtree.array[0] == array[0] && subtree.array[1] == array[1])
		{
			return true;
		}
		
		
		if(array[factor] < subtree.array[factor])
		{
			times++;
			return searchNode(subtree.left, array, depth+1);
		}
		times++;
		// not in the else bracket cause i want my function to be recursive so i want to have the return status 
		return searchNode(subtree.right, array, depth+1);
	}
	
	public boolean search(int array[])
	{
		times =0;
		return searchNode(root, array,0);
	}

	public int getTimes() 
	{
		return times;
	}	
	
	public void setTimes(int times)
	{
		this.times = times;
	}
}
