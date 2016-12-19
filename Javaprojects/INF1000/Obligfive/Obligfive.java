import java.io.*;
import java.util.*;
class Obligfive
{
	public static void main(String[]args)
	{
			
	
		try{
			planlegger dp = new planlegger();
			dp.lestilogfra();
		
		 }
		catch (FileNotFoundException e){
				return;
			}
		
		
		
	
	} //slutt metode
} // slutt main class

class planlegger
 {

		Stasjon stas = null;
		Linje ll = null;
		Overgang overgg = null;
		
		
		//for alle linjer og stasjoner.
		HashMap <String, Stasjon> stasjons= new HashMap< String, Stasjon>(); 
		HashMap<Integer, Linje> linjer = new HashMap<Integer, Linje>();
		
		planlegger() throws FileNotFoundException// konstruktør
		{
		
			  // leser filer
			Scanner scan= new Scanner(new File("TrikkOgTbane.txt"));
			while (scan.hasNext())
			{
				String st = scan.nextLine();
			
				if(st.contains("*Linje*"))
				{
					
					int linjenummer = Integer.parseInt(st.split(" ")[1]);
					
					ll= new Linje(linjenummer); //opprettet linje objekt
					linjer.put(linjenummer, ll);
					
					
				}
					else
					{
						if (ll != null) 
						{
							
							if (stasjons.containsKey(st)) 
							{
								 stas = stasjons.get(st); // hvis stasjon var registrert fra før.
								

							}    
								else
								{
									
									stas = new Stasjon(st); // opprettet nye Stasjon objekt hvis de ikke finnes fra før.
									stasjons.put(st,stas);
									
								}
									ll.regnyStasjon(stas);  //legger stasjon til arraylist i classer Linje
									stas.regnylinje(ll);  //legger Linje til arraylist i classer Stasjon
						}
				
					}
			} // slutt while
			
			scan.close();
		
			
		} // slutt konstruktør
		
		
		void lestilogfra()throws FileNotFoundException
		{
			boolean sjekke = false; 
				Scanner in= new Scanner(System.in);
				do 
				{
				    System.out.println(" ");
					System.out.println("REISER FRA: ");
					String frastasjon = in.next();
					
					System.out.println("REISER TIL: ");
					String tilstasjon = in.next();
					System.out.println("");
										
						if ( stasjons.containsKey(frastasjon)  && stasjons.containsKey(tilstasjon) ) // sjekke hvis stasjon finnes.
						{
							Stasjon frast = stasjons.get(frastasjon);
							Stasjon tilst= stasjons.get(tilstasjon);
							
							beregnruter(frast,tilst); // kaller metode beregnruter
							sjekke = true;
						}
							else
							{
								System.out.println("DU HAR TAST FEIL PROV IGJEN");
							}	
						
				} 
				
					while(sjekke !=true); // slutter ikke hvis du skriver feil .

		     
			
		} // slutt lestilogfra metode
		
		void beregnruter(Stasjon frast,  Stasjon tilst)
		{
			
			Iterator <Linje> it = frast.linjer.iterator(); // finer Linje som  går fra  denne stasjon.
				while (it.hasNext())
				{
					Linje ll = it.next();
						if (ll.innholder(tilst)) // finner hvis stasjon du skal til finnes i denne Linje
						{
							String retninger = ll.retning(frast, tilst);
							double tid = ll.tid(frast, tilst);
							String lln =ll.type(frast, tilst);
							System.out.println("Ta "+ lln+" linje "+ll.linjenummer+" fra :"+frast.stnav+" til "+
							tilst.stnav+" i retning "+retninger + ".Estimert reisetid "+  tid+" min");
							
							return;  // returner hvis vi  kan reise direkt og trenger vi ikke overgang
						}
				}
				
					Iterator <Linje> td = frast.linjer.iterator(); //finer Linje som  går fra  denne stasjon.
						while (td.hasNext())
						{	
							Linje fraln= td.next();
							
								Iterator <Stasjon> t = fraln.stasjons.iterator(); // finner alle Stasjon som Linjer går gjennom
									while (t.hasNext())
									{
										Stasjon stas = t.next();
										
											Iterator <Linje> tm = tilst.linjer.iterator(); // finner alle linje som til  destinasjon.
												while (tm.hasNext())	
												{
													Linje tilln= tm.next();
								
													if (tilln.innholder(stas)) 
													{
														
														double reisetm = fraln.tid(frast, stas)+
														tilln.tid(stas, tilst)+
														tilln.ventetid() + 3.0;
														String retninger1 = fraln.retning(frast, stas);
														String retninger2 = tilln.retning(stas, tilst);
														String TBANE1 = fraln.type( frast,stas);
														String TBANE2 = tilln.type( stas,tilst);
														
														
															System.out.println("Ta "+ TBANE1+" linje "+fraln.linjenummer+" fra :"+frast.stnav+" til "+
															stas.stnav +" i retning "+retninger1 +" og deretter ta "+ TBANE2+" linje "+tilln.linjenummer+ " i retning " +
															retninger2+" til  "+tilst.stnav+".Estimert reisetid: "+ reisetm+" min");
															System.out.println(" ");
															System.out.println(" eller");
															
															String sts =("Ta "+TBANE1+" linje "+fraln.linjenummer+" fra :"+frast.stnav+" til "+
															stas.stnav +" i retning "+retninger1 +" og deretter ta "+ TBANE2+" linje "+tilln.linjenummer+ " i retning " +
															retninger2+" til "+tilst.stnav+".Estimert reisetid: "+ reisetm + " min");
															
														   if (overgg == null || overgg.reisetm > reisetm )
															
															overgg = new Overgang( sts, reisetm); //lager objekt Overgang.
															
													}
											}
									}
						}	
							if (overgg != null)
							System.out.println(" " );
							System.out.println("KORTEST REISETID ER" );
							System.out.print( overgg.skrivord);
							System.out.println( "" );
				
	}	
		
		void skrivUt()
		{
			Iterator <Linje> it = linjer.values().iterator();
				while (it.hasNext())
				{
					Linje l = it.next();
					l.skrivLine();
				}
		}
		
} //slutt class planellger

class Stasjon
{
	String stnav;

	ArrayList <Linje> linjer = new ArrayList<Linje>();
	
	Stasjon(String stnav) // konstruktør
	{
		this.stnav=stnav;
	}
	
	
	void regnylinje(Linje ll) 
	{
		linjer.add(ll);
	}

} // slutt class Stasjon

class Linje
{
	String type;
	double minuter;
	double tid;
	ArrayList <Stasjon> stasjons = new ArrayList<Stasjon>();
	
		int linjenummer;
		
		Linje( int linjenum) // konstruktør
		{
			this.linjenummer= linjenum;
		}
	
		void regnyStasjon(Stasjon stas) 
		{
			stasjons.add(stas);
		}
		
		void skrivLine()
		{ 
			
			for(int i=0; i<stasjons.size(); i++)
			{	
				Stasjon s = stasjons.get(i);
				
			}	
		}
		
		
		String type(Stasjon frast, Stasjon tilst)
		{
		
			if(linjenummer > 10)
			{
				type="trikk";
			
			}
			else
			{
				type= "tbane";
				
			}
			return type;
		}
		
		public double ventetid()
		{
			if(linjenummer > 10)
			{
				minuter = 7.5  ;
			}
			else
			{ 
				minuter = 5.0 ;
			}
		return minuter;
		}
		
		public double reisetid(Stasjon frast,Stasjon tilst)
		{
			if(linjenummer > 10)
			{
				tid =1.4;
			}	
			else
			{
				tid = 1.8;
			}
		return tid;
				
		}  //slutt doule reisetid metode
		
		
		public String retning(Stasjon frast, Stasjon tilst) 
		{
			if (stasjons.indexOf(frast) < stasjons.indexOf(tilst))
			{
				return stasjons.get(stasjons.size() - 1).stnav;
			}
			else
			{
				return stasjons.get(0).stnav;
			}
			
		} // slutt retning metode
		
		
		public double tid(Stasjon frast, Stasjon tilst) 
		{
			int stasjonnummer= Math.abs(stasjons.indexOf(frast) -stasjons.indexOf(tilst));
			return stasjonnummer*reisetid( frast, tilst);
		
		} // slutt tid  metode
		
		
		
		boolean innholder(Stasjon stas) 
		{
			return stasjons.contains(stas);
		}
			
		
} // slutt class linje



class Overgang
{
	
	double reisetm;
	String skrivord;
	Overgang( String skrivord, double reisetm )
	{

		this.reisetm = reisetm;
		this.skrivord = skrivord;
	}

} // slutt class overgang
			
		
		
		
		
		
		
		
		
		
		
		
