import java.io.*;
import java.util.Scanner;

class Obligtre
{
	public static void main(String []args)
	{
		if (args.length < 1) {
			System.out.println("Bruk: > java Obligtre <tekstfil>");
		}
		else {

			Ordanalyser bc = new Ordanalyser(args[0]); 
			
			try{
				bc.ordlest();
				bc.unikerord();
				bc.unikeordfrekvens();
				bc.ordpar();
			}
			catch (FileNotFoundException e){
				return;
			}
			
		} 
   
	} //slutt main metode.
}// slutt class obligtre.

class Ordanalyser

{
	String antallorder [] = new String [46000]; // har anta jeg total ord pa filer alice.
	String unikeord [] = new String [5000];     //har anta jeg total unike ord pa file alice.
	int antallunikeord [] = new int [5000];
	 int bibi = 0 ;
	int i = 0;
	int alleord =0;
	int ordtelles = 0;
    int mimi[] = new int [1424];
	String tekstfil;
	public  Ordanalyser(String textfil){
		this.tekstfil=tekstfil;
	}
	
	// denne metoder teller alle ord pa alice file. 
	public void ordlest()throws FileNotFoundException
	{
		
		Scanner scan=new Scanner(new File("alice.txt"));
		int i = 0;
		int alleord = 0;
		while(scan.hasNext())
		{
			antallorder [i] = scan.next().toLowerCase();
			i++;
			alleord++;
		}
			PrintWriter out = new PrintWriter( new File("oppsummering.txt"));
			out.println("ANTALL ORD LEST ER:          "  + alleord );
			
			out.close();
		
			System.out.println("antall ord lest er   " + alleord);
		
	}
	
	//denne metoder teller alle unik ord
	public void unikerord()throws FileNotFoundException
	{

		int i = 0;
		for ( i = 0; antallorder[i]!= null; i++)
		{
			boolean treff= true;

			int a = 0;

			for ( a = 0; unikeord[a] != null; a++)
			{
				if (unikeord[a].contains(antallorder[i]))
					{
					antallunikeord[a]++;
					treff = false;

					break;
				}
			}
					
			if (treff)
			{
			unikeord[a] = antallorder [i];
			antallunikeord[a]++;
			ordtelles++;

			}


        }



		PrintWriter out = new PrintWriter( new File("oppsummering1.txt"));
			out.println("ANTALL UNIKEORD LEST ER:          "  + ordtelles );
			
		
		
		System.out.println("antall unike ord er       "  + ordtelles );
		
		for( int b =0;  unikeord[b]!=null;  b++)
		{	
			out.println(unikeord[b] +"                " + antallunikeord[b]);
				
		}	
		 out.close();

    }// slutt unikerord.



	public void unikeordfrekvens()throws FileNotFoundException
	{
		// denne methoder skriver alle ord som forekommer mest. 

	
		int fr = 0;
		
		for ( int i = 0; antallunikeord[i] !=0; i++)
		{ 
			if (antallunikeord[i] > fr)
			 fr =antallunikeord[i];
		}
		 
                //  utregning har.
		PrintWriter out = new PrintWriter( new File("oppsummering2.txt"));
		int sum =(fr/100)*10;
		for(  int i = 0; i < ordtelles; i++)
		{
			if(antallunikeord[i] >= sum)
			{
				out.println(unikeord[i]  + "   " + antallunikeord[i]);		
				System.out.println(unikeord[i]  + "   " + antallunikeord[i]);
			}
		}
		out.close();
		
	} //slutt ordfrekvens metode.

	/*denne metode finner ut all ord som er par med alice*/
	public void ordpar()
	{
		System.out.println(" ");
		System.out.println("ORDPAR ER");
		System.out.println(" ");
		
		String par1, par2 = "" ;
		
		int ordparer[][]= new int[ordtelles][ordtelles]; // 2 D array
		
		for (int x =0; antallorder[x] !=null; x++) // teller gjennom alle  total ord.
		{
			
			par1 = par2;
			par2 = antallorder[x];
			for(int y = 0; y < ordparer.length; y++)
			{
				if(par1.contains(unikeord[y]))
				{
					for(int z = 0; z < ordparer.length; z++)
					{
						if(par2.contains(unikeord[z]))
						ordparer[y][z]++;
					}
				}
			}
		}
		
		for( int m= 0; unikeord[m] !=null; m++)
		{
			if(unikeord[m].contains("alice"))
			{
				for( int n= 0 ; unikeord[n] != null; n++)
				{
					if(ordparer[m][n] > 0)
					{
					ordparer[m][n]++;
					System.out.println("alice-"  + unikeord[n]);
					}
				}
			}
		}
		
	} //slutt ordpar metode.
}// slutt ordanalyser class.
