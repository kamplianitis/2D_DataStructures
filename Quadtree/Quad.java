package Quadtree;
// The main quadtree class 
public class Quad
{
	// Hold details of the bounds of this node 
	private Point topLeft = new Point();
	private Point bottomRight = new Point();

	// Node factor 
	private Node n;

	// Children of this tree .. each quadsubtree has to have 4 quadsubtree 
	private Quad topLeftTree; // the names of the quadtrees are given by the position that they are subdivided
	private Quad topRightTree;
	private Quad bottomLeftTree;
	private Quad bottomRightTree;
	private static int times;  // i make times static 'cause i want it to change only when it is called by the class itself
	private static int times1;
	public Quad()
	{
		n = null; // give the value of the node that we create... this is cause i want to have the root of the first quadtree
		topLeft = new Point(0, 0);
		bottomRight = new Point(0, 0);
		topLeftTree = null;
		bottomLeftTree = null;
		topRightTree = null;
		bottomRightTree = null;
		times =0;
		times1 =0;
	}
	public Quad(Point topLeft, Point botRight)
	{
		n = null; // same thing again
		this.topLeft=topLeft;
		this.bottomRight=botRight;
		topLeftTree = null;
		topRightTree = null;
		bottomLeftTree = null;
		bottomRightTree = null;
		times = 0;
	}

	// Insert a node into the quadtree at the proper place
	public final void insertNode(Node node)
	{
		if (!inBoundary(node.pos)) // if the node is out of bounds of the tree that we are
		{
			System.out.println("Cannot be added");
			return;
		}

		if (Math.abs(topLeft.x - bottomRight.x) <= 1 && Math.abs(topLeft.y - bottomRight.y) <= 1) // the abs function gives me the absolute value.. this prevents it from going to negative values
		{
			if(n == null) // if the node is empty it puts the node that has to insert
			{
				n = node;
				System.out.println("Node Added");
			}
			return;
		}

		if ((bottomRight.x + topLeft.x) / 2 >= node.pos.x) // make the proper calculations so that the move we make is the proper move inside the tree 
		{
			if ((topLeft.y + bottomRight.y) / 2 >= node.pos.y) // if we went in the check for the x factor now its time to check for the y axis
			{
				if (topLeftTree == null)
				{
					topLeftTree = new Quad(new Point(topLeft.x, topLeft.y), new Point((topLeft.x + bottomRight.x) / 2, (topLeft.y + bottomRight.y) / 2)); // in case the node we have reached is null this means that we have to create a new one
				// it basically creates a sub quadtree.
				}
				topLeftTree.insertNode(node); // either way i have to insert the node that i have to the proper position
			}
			// same procedure for the next three sub quads.
			else
			{
				if (bottomLeftTree == null)
				{
					bottomLeftTree = new Quad(new Point(topLeft.x, (topLeft.y + bottomRight.y) / 2), new Point((topLeft.x + bottomRight.x) / 2, bottomRight.y));
				}
				bottomLeftTree.insertNode(node);
			}
		}
		else
		{
			if ((topLeft.y + bottomRight.y) / 2 >= node.pos.y)
			{
				if (topRightTree == null)
				{
					topRightTree = new Quad(new Point((topLeft.x + bottomRight.x) / 2, topLeft.y), new Point(bottomRight.x, (topLeft.y + bottomRight.y) / 2));
				}
				topRightTree.insertNode(node);
			}
			else
			{
				if (bottomRightTree == null)
				{
					bottomRightTree = new Quad(new Point((topLeft.x + bottomRight.x) / 2, (topLeft.y + bottomRight.y) / 2), new Point(bottomRight.x, bottomRight.y));
				}
				bottomRightTree.insertNode(node);
			}
		}
	}


	// now we have to give a node in order to find it in the big tree
	public final Node search(Point p)
	{

		if (n != null) // takes the case that the first Node is null that means that threre is no tree
		{
			return n; 
		}
		
		if (!inBoundary(p)) // first step is to check the bounds 
		{
			return null;
		}
		
		if ((topLeft.x + bottomRight.x) / 2 >= p.x) //  the check to go to the sub top-left tree
		{
			if ((topLeft.y + bottomRight.y) / 2 >= p.y)
			{
				
				if (topLeftTree == null)
				{
					return null;
				}
				times++;
				return topLeftTree.search(p);
			}
			
			else // bottom-left tree
			{
				if (bottomLeftTree == null)
				{
					return null;
				}
				times++;
				return bottomLeftTree.search(p);
			}
		}
		else // now i take the right side of the tree
		{
			if ((bottomRight.y + topLeft.y) / 2 >= p.y)
			{
				if (topRightTree == null)
				{
					return null;
				}
				times++;
				return topRightTree.search(p);
			}
			else
			{
				if (bottomRightTree == null)
				{
					return null;
				}
				times++;
				return bottomRightTree.search(p);
			}
		}
	}
	
	public final boolean inBoundary(Point p) // this function is responsible to check if the node values are between the values of the rectangle that is created each time
	{
		return (p.x >= topLeft.x && p.x <= bottomRight.x && p.y >= topLeft.y && p.y <= bottomRight.y);
	}
	public int getTimes() {
		return times;
	}
	public void setTimes(int times) {
		this.times = times;
	}
	public int getTimes1() {
		return times1;
	}
	public int setTimes1(int a)
	{
		return this.times1 = a;
	}

}