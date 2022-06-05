package Quadtree;
// this class contains the numbers of x abd y.. so we only need an array to make it
//Used to hold details of a point 
public class Point
{
	public int x;
	public int y;
	public Point(int x, int y)
	{
		this.x = x;
		this.y = y;
	}
	// this is used only for the quadtree
	public Point()
	{
		x = 0;
		y = 0;
	}
}