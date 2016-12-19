import java.io.*;
import java.util.Scanner;
 class Obligen
     {
			 public static void main(String []args)throws FileNotFoundException
			{
				 Fulge f=  new Fulge();

				 f.menu();
				 System.out.println("skrive 1 opp til 4");
				Scanner scan=new Scanner(System.in);
				System.out.println("system er avsluttet"); 
				 int akjon = scan.nextInt();
				 
				  
				  
					  switch (akjon){
					  case 1:
					  
					 f.register();
					 break;
					  case 2:
					  
					  f.skrivutnavn();
					 break;
					  case 3:
					  
					 f.skrivutsted();
					 break;
					  case 4:
					  System.out.println("system er avsluttet"); 
					  break;
					  default:System.out.println("oppgir en av det fire tall");
				 
				 
					}
				  
		    }// slutt av main metode
	}// slutt av main klasse
	
class Fulge

    {    

/*denne metode vil lager informasjon av hver fugle*/
	public void register()throws FileNotFoundException

	 {
		PrintWriter out = new PrintWriter( new File("minfil.txt"));
		Scanner scan=new Scanner(System.in);
		
		System.out.println("fulge type");
		String navn =scan.next();
		
		System.out.println("kjonn type");
		String kjonn = scan.next();
		
		System.out.println("hvor de  finnes");
	    String sted = scan.next();
		
		System.out.println("når vi fant");
		String dato = scan.next();
		
		out.println(navn +  " " + kjonn +  " " +   sted +" "  + dato);
		out.close();
	    }
		
		/*denne metode vil skrives når programme beginner*/
	public void menu()
  
		{
  
			 System.out.println("1. Registrer en fugleobservasjon");
			 System.out.println("2. Skriv ut alle observasjoner av en fugletype");
			 System.out.println("3. Skriv ut alle observasjonene på ett bestemt sted");
			 System.out.println("4. Avslutt systemet");
		}
 
 
 /*denne metode skriver spesifisk fugle observasjon*/
	public void skrivutnavn()throws FileNotFoundException
 
		{
			Scanner scan=new Scanner(new File("minfil.txt"));
			Scanner sc = new Scanner(System.in);
				 
			System.out.println("observasjon av en fulg");
				 
				 String fulgeNavn = sc.next();
				 while (scan.hasNext())
				 {
					String fil = scan.next();
					if(fil.contains(fulgeNavn))
						System.out.println(fil);
					
				
				}
		}
 /*denne metode skriver stedet hvor fulge finnes*/		
    public void skrivutsted()throws FileNotFoundException

		{
			Scanner scan=new Scanner(new File("minfil.txt"));
			Scanner sc = new Scanner(System.in);
				 System.out.println("observasjon på et sted ");
				 
				 String sted = sc.next();
				 while (scan.hasNext())
				 {
					String fil = scan.next();
					if(fil.contains(sted))
					System.out.println(fil);
				 
				 }
		}
   }
 
