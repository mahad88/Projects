import easyIO.*;
class obligtre
{
	public static void main(String []args)
	{
	if (args.length < 1)
	{
	System.out.println("jeg bruker > java obligtre <alice.txt>");
    }
	else {
 
		ordanalyser bc = new ordanalyser(); 
		//bc.ordlest();
		 bc.totalord();
		bc.unikerord();
		bc.unikeordfrekvens();
		bc.ordpar();
		 
   }
} //slutt main metode.
}// slutt class obligtre.
class ordanalyser

{


		String antallorder [] = new String [46000]; // har anta jeg total ord på filer alice.
		String unikeord [] = new String [5000];     //har anta jeg total unike ord på file alice.
		int antallunikeord [] = new int [5000];
		 int bibi = 0 ;
		int i = 0;
		int alleord =0;
		int ordtelles = 0;
         int mimi[] = new int [1424];
	void ordlest()
	{

		// denn methoder skrvier alle ord på skjerm.
		In filer =new In("alice.txt");  // har leser fra fil
		int i = 0;
		String antallorder[] =  new String[56000];
		while(!filer.lastItem())
		{
		 

		for( i =0; i<antallorder.length; i++)
		{
		antallorder[i] = filer.inWord();
		if(antallorder[i] !=null){

		System.out.println(antallorder[i]);
		}
		}
	    }

		filer.close(); 

	}// slutt av ordlest metode.
	void totalord()
	{
		// denne metoder teller alle ord på alice file. 
		In filer = new In("alice.txt");
		int i = 0;
		int alleord = 0;
		while(!filer.lastItem()){

		antallorder [i] = filer.inWord().toLowerCase();
		i++;

		  alleord++;


		}
		Out id = new Out("oppsummering.txt", true);
			id.outln("ANTALL ORD LEST ER:          "  + alleord );
			id.outln("      "  );
			id.outln("      "  );
			id.outln("      "  );
			
			
			id.close();
		System.out.println("");
		System.out.println("");
		System.out.println("");

		System.out.println("antall ord lest er   " + alleord);
		System.out.println("");
		System.out.println("");
		System.out.println("");

	}
	
	void unikerord()
	{

		//denne metoder teller alle unik ord

		int i = 0;


		for ( i = 0; antallorder[i]!= null; i++)
		{

		boolean treffer = true;

		int a = 0;

		for ( a = 0; unikeord[a] != null; a++){
		 
		if (unikeord[a].contains(antallorder[i])) {
		antallunikeord[a]++;

		treffer = false;

		break;
		}
		}
				
		if (treffer)
		{
		unikeord[a] = antallorder [i];

		antallunikeord[a]++;

		ordtelles++;


		}


        }



		Out aid = new Out("oppsummering.txt", true);
		aid.outln("ANTALL UNIKE ORD ER:         "  + ordtelles );
		aid.outln(" " );
		aid.outln("    ");
		aid.close();

		
		System.out.println("antall unike ord er       "  + ordtelles );
		System.out.println(" ");
		System.out.println(" ");
		System.out.println("ORD SOM FOREKOMMER ER MER ENN TI PROSENT" );
		
		for( int b =0;  unikeord[b]!=null;  b++)
		{
				
		Out kaid = new Out("oppsummering.txt", true);
				
		kaid.outln(unikeord[b] +"                " + antallunikeord[b]);
				 
		kaid.close();
		 
		
 
		}	

    }// slutt unikerord.



	void unikeordfrekvens()
	{
		// denne methoder skriver alle ord som forekommer mest. 

		int i = 0;

		int fr = 0;
		int sum ;
		for ( i = 0; antallunikeord[i] !=0; i++){ 
		if (antallunikeord[i] > fr){
		 fr =antallunikeord[i];
		 
		}
		}
		 

                //  utregning har.
		sum =(fr/100)*10;
		for(  i = 0; i < ordtelles; i++)
		{
		if(antallunikeord[i] >= sum)
		{
						   
		Out didi = new Out("oppsummeringB.txt", true);

		didi.outln(unikeord[i]  + "   " + antallunikeord[i]);
		didi.close();

					
		System.out.println(unikeord[i]  + "   " + antallunikeord[i]);
		
		
		}
		}
	} //slutt ordfrekvens metode.

	void ordpar()
	{
   

		System.out.println(" ");
		System.out.println(" ");
		System.out.println("ORDPAR ER");
		System.out.println(" ");
		
		
		String par1, par2 = "" ;
		
		
		int ordparer[][]= new int[ordtelles][ordtelles]; // har lager jeg 2 D array
		
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
		{
		ordparer[y][z]++;
		}
		}
		}
		}
		}
		
		for( int m= 0; unikeord[m] !=null; m++){
		if(unikeord[m].contains("alice")){
		for( int n= 0 ; unikeord[n] != null; n++){
		if(ordparer[m][n] > 0){
		ordparer[m][n]++;
	
		System.out.println("alice-"  + unikeord[n]);
		}
		}
		}
		}
		
	} //slutt ordpar metode.
}// slutt ordanalyser class.
