
import java.io.*;
import java.util.Scanner;



class Obligfour 
{
	public static void main(String[]args)
	{
		if (args.length < 1) 
		{
			System.out.println("FILER SOM LESES ER :>java oblig4 hybeldata.txt");
		} 
		else 
		{ 
		 try{
			utsyn s = new utsyn();
			s.menylokke();
			s.HOVEDMENY();
		 }
		catch (FileNotFoundException e){
				return;
			}
		}
	} //slutt main metode
} // slutt main class

 

class student
{
    
	String navn;
	 
	

} // slutt class student

 class Hybel
 {
	String leietager;
	boolean toppetasje;
    char bokstav ;
	int etasjer;
	int saldo;
		   
	
	
  }// slutt class Hybel

 class utsyn
 {
	Hybel[][] hyblene =  new Hybel [3][6];
    //In tast = new In();
	Scanner in= new Scanner(System.in);
	//Out skjerm = new Out();
	//PrintWriter out = new PrintWriter( new File("minfil.txt"));
	   
	   int moned  ;
	   String leietager;
	   int or   ;
	   int monedpris ;
	   int Totalfortjeneste  ;
	   int Totalantallmoneder  ;
	   int monedleievanligmoneder  ;
	   int MonederleieToppetasjehybel ;
	   int depositum = 15000;
	   int monedfortjeneste;
	   int etgg;
	   int krav;
	   int gebir = 3000;
	   int vedlikeholdAS= 26700; // total utgifter
	   
	   
	   
	  
	 utsyn()throws FileNotFoundException // konstruktør
	{
		for(int etg = 0; etg < 3 ; etg++) // teller etasjer
		{
			for(int rom =0 ; rom < 6 ; rom++) // teller hybler
			{
				hyblene[etg][rom] = new Hybel(); // Hybel objekt 
			}
		}
		
		if(new File("hybeldata.txt").exists()) // sjekke hvis filer existere
		{
			// har leser jeg de førstlinje
			
			Scanner scan= new Scanner(new File("hybeldata.txt"));
			String line = scan.nextLine();
			String[] node = line.split(";");
			moned =Integer.parseInt(node[0]);
			or = Integer.parseInt(node[1]);
			Totalantallmoneder = Integer.parseInt(node[2]);
			Totalfortjeneste = Integer.parseInt(node[3]);
			monedleievanligmoneder=Integer.parseInt(node[4]);
			MonederleieToppetasjehybel= Integer.parseInt(node[5]);
				
                        for(int etg = 0; etg < 3; etg++)
						{
							for(int rom= 0; rom< 6; rom++)
							{
								String line2 = scan.nextLine();
								String[] node2 = line2.split(";");
							   int etgg = Integer.parseInt(node2[0]);
							   char []mm =node2[1].toCharArray() ;
							   char rm = mm[0];
							   int rom1 = (int) rm;
							    rom1=rom1 - 'A';
							   int saldo =Integer.parseInt(node2[2]);
							   String leietager =node2[3];
							   System.out.println(etgg +"  "+rm+" " +saldo+" "+leietager);
							   							   									       																					
								    hyblene[etg][rom].etasjer = etgg;
									hyblene[etg][rom].bokstav = rm;
									hyblene[etg][rom].leietager =leietager ;
									hyblene[etg][rom].saldo = saldo;
									
					        }
						} // slutt while løkker
						
								scan.close();
		}//slutt filer exists
							
						
    } // slutt konstruktor utsyn
	   
	   
    void menylokke() throws FileNotFoundException
	{
		int kommando = -1;
			
				while (kommando != 8)
				{
				
					
					HOVEDMENY();    //har kaller jeg meny metode
					kommando = in.nextInt();
						switch(kommando)
						{
							case 1: skrivOversikt();	    		    break;
							case 2: regNyLeietager();    				break;
							case 3: regBetaling();         				break;
							case 4: regFrivilligFlytting();			    break;
							case 5: Monedkjoring(); 	    			break;
							case 6: kastUtLeietagere();     			break;
							case 7: OkHusleien(); 						break;
							case 8: avslutt();			    			break;
							default: System.out.println(" VELG TALL MELLOM 1-7");    break;
							
						} // slutt switch

				} //slutt while løkker

	} // menyløkke metode

	void HOVEDMENY() 
	{
		System.out.println(" ");
		System.out.println(" HOVEDMENY");
		System.out.println("");
		System.out.println("1.Skriv oversikt");
		System.out.println("2.Registrer ny leietager");
		System.out.println("3.Register betaling fra leietager");
		System.out.println("4.Register frivilling utflytting");
		System.out.println("5.Monedskjoring av husleie");
		System.out.println("6.Kast ut leitagere ");
		System.out.println("7.ok husleien ");
		System.out.println("8.avslutt ");
		System.out.println(" ");
	}// slutt meny metode
			
	void skrivOversikt()
	{
		System.out.println("HYBEL" +"\t"+ "LEIETAGER" + "\t"+"SALDO");
		System.out.println("---------------------------------------");
			for (int etg = 0; etg < 3; etg++ )
			{
				for(int rom= 0; rom < 6; rom++)
					{
						char bokstav = (char)('A' + rom);
							if(hyblene[etg][rom].leietager.equals("TOM HYBEL"))
							{
									// har skal skrives ledig if de er  ingen bor på hybel	
							System.out.println(hyblene[etg][rom].etasjer +":"+ hyblene[etg][rom].bokstav+ "\t" + "(LEDIG)"+"\t" +"\t" +hyblene[etg][rom].saldo);
							
							} 
							else
							{
						         //har skal de skrives navn på de som bor på hybel.
								System.out.println(hyblene[etg][rom].etasjer +":"+ hyblene[etg][rom].bokstav +"\t"+ hyblene[etg][rom].leietager   +"\t" +hyblene[etg][rom].saldo);
								
							}
							
					} 
									
					   
			 } 
			   // det skrives på nederst linje på oversikt
			   System.out.println("");
				System.out.println("MONED OG OR    " +"\t"+ moned +";" + or);
				System.out.println("FORTJENESTE ER    " + Totalfortjeneste);
				System.out.println("TOTAL MONED I DRIFT    " + Totalantallmoneder);
			
			
		} // slutt overskrift metode
		
		void regNyLeietager()
		{
			boolean tom = false; 
				for(int etg=0; etg <3; etg++)
				{
					for(int rom=0; rom <6; rom++)
					{
						if(hyblene[etg][rom].leietager.equals("TOM HYBEL")) // sjekke hvis hybel er ledig.
						{
							tom = true;
						}
						
					}
				}					
					
					
					if(tom == false) 
					{
						System.out.println("INGEN LEDIG HYBEL");
					}	 
					else 
					{
					System.out.println("DET ER LEDIG  HYBLER");
						for(int etg = 0; etg <3; etg++)
						{
							for(int rom = 0; rom <6; rom++)
							{
								if(hyblene[etg][rom].leietager.equals("TOM HYBEL")) 
								{
									System.out.print( hyblene[etg][rom].etasjer);   // skriver  på skjerm navn av ledig etasjer
									System.out.println( hyblene[etg][rom].bokstav); // skrives på skjerm navn av ledig hybel
								}
							}
						}
				}
					
					System.out.println("VELG ET ETASJER");
					int etgg = in.nextInt();
					etgg -= 1; 
					System.out.println("VELG ET HYBEL");
				//	tast.skipWhite();
					char rm =in.next().charAt(0);
					int roms = (rm - 'A' );
					System.out.println("SKRIV NAVN OG ETTERNAVN AV LEIETAGER");
					String fornavn=in.next();
					String etternavn=in.next();
					String leietager =fornavn +" "+etternavn;
				
					int saldo = 0;
					int husleir = prisperetasje (etgg);
						if (etgg == 2)
							saldo = (depositum - husleir);
						else
							saldo = (depositum - husleir);
							
								hyblene[etgg][roms].leietager = leietager;
								hyblene[etgg][roms].etasjer = (etgg+1) ; 
								hyblene[etgg][roms].bokstav = rm;
								hyblene[etgg][roms].saldo = saldo;
								System.out.println("etasje " + (etgg + 1) + " saldo " + saldo + " leietager " + leietager);
								
		} // slutt  regNyLeietager metode
		
		public void regBetaling()
		{
			System.out.println("REGISTRER BETALING FRA LEIETAGER");
			
				System.out.println("SKRIV ETASJE");
				int etgg = in.nextInt();
				etgg -= 1;
				System.out.println("SKRIV HYBEL");
				char rm =in.next().charAt(0);
				int roms = (rm - 'A' );
					for(int etg = 0; etg < 3; etg++)
						{
							for( roms = 0 ; roms < 6; roms++)
							{
														
								if(hyblene[etg][roms].etasjer == (etgg+1))
								{
									if(hyblene[etg][roms].bokstav==rm)
									{
										if (hyblene[etg][roms].leietager.equals("TOM HYBEL")) // for å sjekke hvis hybel er ledig
										{ 
											System.out.println(" DENNE HYBEL ER LEDIG "+ "\n");
										}
															
									else  // hvis noen bor på hybel so han/hun kan betaler.
									{   
									System.out.println("HVOR MYE SKAL DU BETALER " + " "); 
									int  betaling  = in.nextInt();
									hyblene[etg][roms].saldo = hyblene[etg][roms].saldo += betaling;
									System.out.println(hyblene[etg][roms].leietager + "\t" + " SALDO DIN ER : " + hyblene[etg][roms].saldo);
								   }
								   }
								} 
							}	
						}
		} //slutt regBetaling metode.
		
		public void regFrivilligFlytting()
		{
			boolean treffer = false;
			
				System.out.println("NAVN AV LEIETAGER SOM FLYTTER");
				String fornavn=in.next();
					String etternavn=in.next();
					String stnavn =fornavn +" "+etternavn;
				
				
					for(int etgg = 0; etgg < 3; etgg++)
					{
						for(int roms = 0; roms < 6; roms++)
						{
							if(hyblene[etgg][roms].leietager.equals(stnavn))
							{
								treffer = true;
									if(hyblene[etgg][roms].saldo >0)
									{
										System.out.println("SALDO SOM GIS TILBAKE " + hyblene[etgg][roms].saldo);
										hyblene[etgg][roms].saldo=0;
										hyblene[etgg][roms].leietager = "TOM HYBEL";
									}
							}
						}
					}
		} // slutt regFrivilligFlytting meotde
		
		public void Monedkjoring()
		{
				if(moned==12)
				{
					moned = 1;
					or = or + 1;
					System.out.println("VIL DU KJORE FRA  " + moned +":"+ or  + "J/N");
				} 
				else
				{
					System.out.println("VIL DU KJORE FRA  " + (moned + 1)+ ":"+ or+ "J/N");
				}
				
					char losning =in.next().charAt(0);
		
					if(losning=='N')
					{
						if(moned==12) 
							{
								System.out.println("INGEN KJORING VAR GJORT "+ moned+ ":"+ or);
							}
							else 
							{
								System.out.println("INGEN KJORING VAR GJORT  "+ (moned+1)+ ":"+ or);
							}
					}
					else if(losning=='J')	
					{
						monedfortjeneste = 0;
							if (moned==12)
							{
								moned = 1;
								or++;
								Totalantallmoneder++;
							} 
							else
							{
								moned++;
								Totalantallmoneder++;
							}
								for(int etg = 0; etg <3;etg++)
								{
									for(int rom =0; rom < 6; rom++)
									{
										int huslei =prisperetasje (etg);
										if(!hyblene[etg][rom].leietager.equals("TOM HYBEL"))
										{
											if(hyblene[etg][rom].saldo< huslei)
											{
												if(hyblene[etg][rom].saldo<=0)
												{
													monedfortjeneste+=0;
												}
												else
												{
													monedfortjeneste +=hyblene[etg][rom].saldo;
												} 
												hyblene[etg][rom].saldo-=huslei;
											}	
											else
											{
												hyblene[etg][rom].saldo-=huslei;
												monedfortjeneste+=huslei;
											}
										}
									}
								}
								monedfortjeneste-=vedlikeholdAS;
								Totalfortjeneste+=monedfortjeneste;
								System.out.println("MONED OG OR KJORING FOR "+ moned + ":" + or);
								System.out.println("ANTALL MONED I DRIFT " + Totalantallmoneder);
								System.out.println(" MONEDFORTENESTE  " +  monedfortjeneste);
								System.out.println("TOTAL FORTJENESTE " + Totalfortjeneste);
								System.out.println(" GJENNOMSNITT FORTJENESTE "+ Totalfortjeneste/Totalantallmoneder);			
					}
					else
					{
					 System.out.println("TAST ENTEN N  ELLER  J");
					
				   }	
	} // slutt	månedkjlring metode	
			
		public void kastUtLeietagere()throws FileNotFoundException
		{
			System.out.println(" DE SOM SKAL KASTES UT ER : ");
			
			for(int etg =0; etg<3; etg++)
			{
				for(int rom=0; rom<6; rom++)
				{
					int husleir= prisperetasje(etgg);
						if(hyblene[etg][rom].saldo<=-husleir)
						{
							krav = (-1 * hyblene[etgg][rom].saldo) + (gebir/2);
							Totalfortjeneste+= krav;
						    leietager=hyblene[etgg][rom].leietager;
							hyblene[etgg][rom].leietager = "TOM HYBEL";
							hyblene[etg][rom].saldo=0;
							
					
							tilkallhole( etgg , rom ,leietager, krav); // kaller jeg tilkallhole merode
							krav =0;
						}
				}
			}	
	
	}	 // kast UtLeietagere metode
	
	public void tilkallhole( int etasjer,int rom, String leietager, int krav)throws FileNotFoundException
	{
		char bokstav = (char) ('A' + rom);
		System.out.print( hyblene[etgg][rom].etasjer+ ";" + bokstav +";"+ leietager  );
		System.out.println(krav);
		
			PrintWriter out = new PrintWriter( new File("torpedo.txt"));
			
			out.println(hyblene[etgg][rom].etasjer+";"+ hyblene[etgg][rom].bokstav+";"+  krav + leietager);
			out.close();
		    	    				
	} //  slutt tilkallhole
		
	void OkHusleien()
	{
		System.out.println("TREDJE ETASJE HUSLEIR ER: " + MonederleieToppetasjehybel + "\t" +"   OG DE ANDRE TO ER: " + monedleievanligmoneder);
				System.out.println("SKRIV HUSLEIRPRIS AV TREDJE ETASJER ");
				int nyhusleien = in.nextInt();
				MonederleieToppetasjehybel = nyhusleien;
				
				System.out.println("SKRIV HUSLEIRPRIS AV DE ANDRE TO ETASJER");
				int andrehusleien = in.nextInt();
				monedleievanligmoneder = andrehusleien;
				
				System.out.println("NYE HUSLEIRPRIS FOR TREDJE ETASJER ER:  " + MonederleieToppetasjehybel+"\t" + " OG DE ANDRE TO ER :  " + monedleievanligmoneder);
				
	} // slutt OkHusleien metode
			
	void avslutt() throws FileNotFoundException
	{			
		System.out.println(" DU HAR AVSLUTTET");
		PrintWriter out = new PrintWriter( new File("hybeldata.txt"));
		out.println(moned +";" + or  +";"+ Totalfortjeneste +";"+ Totalantallmoneder + ";"+ monedleievanligmoneder +";"+ MonederleieToppetasjehybel+ ";");
			for (int etg = 0; etg < 3; etg++) 
			{
				for (int rom = 0; rom < 6; rom++) 
				{
							
					out.println((etg+1) +";"+ hyblene[etg][rom].bokstav +";" + hyblene[etg][rom].saldo + ";" +hyblene[etg][rom].leietager );
							
				}
			}
					out.close();
	} // slutt avslutt metode

	public int  prisperetasje (int etgg)  // for gjore forkjell på pris mellom etasje 3 og de andre 2
	{
		if(etgg ==2) // for array beginner fra 0
		{
			monedpris = MonederleieToppetasjehybel ;
		}
		else
		{
		    monedpris = monedleievanligmoneder  ;
		}
	 return monedpris;
	}	
	
}	// slutt class utsyn
		
	
	
	
	
	
	
		
	
	
	
	
	
	
	
   

   

   























 
















