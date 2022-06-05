package Kd;


// now i test the main for the Kdtree
import java.util.Random;


public class Kdmain1 
{
	public static void main(String[] args) 
	{
		final int M = 50000; // this declares the number of data that i will have to use
		// create the random object
		final int timesOfsearch =100;
		
		
		Random rand = new Random();
		Kdtree tree = new Kdtree();
		int[] bigarray = new int[2*M]; // this array will be filled first to help me in the search
		int[] array = new int[2];
		
		for(int i=0; i<2*M; i++)
		{
			bigarray[i] = rand.nextInt((65536-10)-0)+1;
			System.out.println(bigarray[i]);
		}
		
		for(int j=0; j<2*M; j=j+2)
		{
			array[0] = bigarray[j];
			array[1] = bigarray[j+1];
			
			System.out.println(array[0]+"\t"+array[1]);
			// now i have to make the tree insertion
			
			tree.add(new int[] {array[0], array[1]});
		}
		
		int mo = 0;
		for(int counter=0; counter<timesOfsearch; counter++)
		{
			
			int number_even = (rand.nextInt(((M/2-0)-0)+1)) * 2; // make the number even... i know that with the way i put them into the search the even match will have the odd after it
			// because it is difficult to search a two array of that range and be successfull i choose to take specific numbers of the big array
			int[] seek =  {bigarray[number_even], bigarray[number_even+1]};
			System.out.println(seek[0] + "\t"+seek[1]);
			
			
			if(tree.search(seek))
			{
				mo += tree.getTimes();
				System.out.println("we found it");
			}
			else
			{
				System.out.println("The final subtree is empty.. so we could not find the comp");
			}
		}
		mo = mo/timesOfsearch;
		System.out.println(mo);
		
		mo=0;
		
		for(int counter=0; counter<timesOfsearch; counter++)
		{
			tree.setTimes(0);
			int[] seek = { rand.nextInt(), rand.nextInt()};
			
			if(tree.search(seek))
			{
				mo += tree.getTimes();
				System.out.println("we found it");
			}
			else
			{

				mo += tree.getTimes();
				System.out.println("The final subtree is empty.. so we could not find the comp");
			}
		}
		mo = mo/timesOfsearch;
		System.out.println(mo);
		
	}
}
