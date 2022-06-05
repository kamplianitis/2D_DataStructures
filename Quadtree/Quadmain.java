package Quadtree;

import java.util.Random;

public class Quadmain 
{
	public static void main(String[] args) 
	{
		final int M =10000;
		Random rand = new Random();
		int[] buff = new int[2*M];
		final int timesOfsearch =100;
		int axis = 65536;
		
		
		Quad center = new Quad(new Point(0,0), new Point(axis,axis)); // create the main tree
		// change the second point
		
		// do the same with the buffer
		
		for(int i=0; i<2*M; i++)
		{
			buff[i] = rand.nextInt((axis-10)-0)+1;
			//System.out.println(buff[i]);
		}
		
		for(int i=0; i<2*M; i=i+2)
		{
			Node a = new Node(new Point(buff[i], buff[i+1]));
			center.insertNode(a);
		}
		
		// now the successfull searches
		
		int mo2 =0;
		System.out.println("");
		
		for(int counter =0; counter < timesOfsearch; counter++)
		{

			int a = (rand.nextInt(((M-1) - 1)-0)+1)*2;
			center.setTimes(0);
			Node node = center.search(new Point(buff[a], buff[a+1]));
			mo2 += center.getTimes();
			if(node== null)
			{
				System.out.println("Not found");
			}
			else
				System.out.println("found");
		}
		
		System.out.println("Mo is: "+ mo2/timesOfsearch);

	 	int mo=0;
		System.out.println(mo);
		System.out.println("");
		// now the unsuccessful searches
		for(int counter=0; counter<timesOfsearch; counter++)
		{
			center.setTimes(0);
			System.out.println(center.getTimes());
			Node a = center.search(new Point((rand.nextInt(axis-10)-0)+1, (rand.nextInt(axis-10)-0)+1));
			System.out.println(center.getTimes());
			if(a!=null)
			{
				mo += center.getTimes();
				System.out.println("we found it");
			}
			else
			{
				mo += center.getTimes();
				System.out.println("The final subtree is empty.. so we could not find the comp");
			}
		}

		System.out.print("Mo is: ");
		System.out.print(mo/timesOfsearch);
	
		

	}
		
}
