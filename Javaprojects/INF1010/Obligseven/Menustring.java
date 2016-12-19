

import java.io.*;
import java.util.Scanner;

public class Menustring {
	/* These are the containers that will contain the objects */
	Tabell<Legemidler> l_Tabell = new Tabell<Legemidler>(120);
	Tabell<Pasienter> p_Tabell = new Tabell<Pasienter>(120);
	SortertEnkelListe<Leger> s_EnkelListe = new SortertEnkelListe<Leger>();
	EnkelReseptListe e_ReseptListe = new EnkelReseptListe();

	/*
	 * This method reads the file, create the object, leger , legemiddel,
	 * pasient and resepter and put them in their specific containers
	 */
	@SuppressWarnings("resource")
	public void readFile(String input) throws FileNotFoundException {
		Scanner scan = null;
		String line = "";
		String[] temp = null;
		scan = new Scanner(new File(input));

		while (scan.hasNext()) {
			line = scan.nextLine();
			/* create person and put to beholder tabell */
			if (line.toLowerCase().contains("personer")) {
				line = scan.nextLine();

				while (!(line.equals(""))) {
					temp = line.split(", ");
					Pasienter pars = new Pasienter(Integer.parseInt(temp[0]),
							temp[1], temp[2], temp[3],
							Integer.parseInt(temp[4]));
					/* this loop is to make sure no overwriting takes place */
					for (int j = 0; j < p_Tabell.hentstorrelse(); j++) {
						if (p_Tabell.inholder(j) == null) {
							p_Tabell.setInn(pars, j);
							line = scan.nextLine();
							break;
						}
					}

				}
				/* create legemidler and put to container */
			} else if (line.contains("Legemidler")) {

				line = scan.nextLine();

				while (!(line.equals(""))) {

					temp = line.split(", ");
					if (temp[3].equals("a")) {
						if (temp[2].equals("pille")) {

							for (int j = 0; j < l_Tabell.hentstorrelse(); j++) {
								if (l_Tabell.inholder(j) == null) {
									l_Tabell.setInn(
											(new TypeApiller(
													Integer.parseInt(temp[0]),
													temp[1],
													Double.parseDouble(temp[4]),
													Integer.parseInt(temp[5]),
													Integer.parseInt(temp[6]),
													Integer.parseInt(temp[7]))),
											j);
									line = scan.nextLine();
									break;
								}
							}
						} else if (temp[2].equals("mikstur")) {
							for (int j = 0; j < l_Tabell.hentstorrelse(); j++) {
								if (l_Tabell.inholder(j) == null) {
									l_Tabell.setInn(
											(new TypeAmikstur(
													Integer.parseInt(temp[0]),
													temp[1],
													Double.parseDouble(temp[4]),
													Integer.parseInt(temp[5]),
													Integer.parseInt(temp[6]),
													Integer.parseInt(temp[7]))),
											j);
									line = scan.nextLine();
									break;
								}
							}
						}
					}

					else if (temp[3].equals("b")) {
						if (temp[2].equals("pille")) {

							for (int j = 0; j < l_Tabell.hentstorrelse(); j++) {
								if (l_Tabell.inholder(j) == null) {
									l_Tabell.setInn(
											(new TypeBpiller(
													Integer.parseInt(temp[0]),
													temp[1],
													Double.parseDouble(temp[4]),
													Integer.parseInt(temp[5]),
													Integer.parseInt(temp[6]),
													Integer.parseInt(temp[7]))),
											j);
									line = scan.nextLine();
									break;
								}
							}
						} else if (temp[2].equals("mikstur")) {
							for (int j = 0; j < l_Tabell.hentstorrelse(); j++) {
								if (l_Tabell.inholder(j) == null) {
									l_Tabell.setInn(
											(new TypeBmikstur(
													Integer.parseInt(temp[0]),
													temp[1],
													Double.parseDouble(temp[4]),
													Integer.parseInt(temp[5]),
													Integer.parseInt(temp[6]),
													Integer.parseInt(temp[7]))),
											j);
									line = scan.nextLine();
									break;
								}
							}
						}

					}

					else if (temp[3].equals("c")) {
						if (temp[2].equals("pille")) {

							for (int j = 0; j < l_Tabell.hentstorrelse(); j++) {
								if (l_Tabell.inholder(j) == null) {
									l_Tabell.setInn(
											(new TypeCpiller(
													Integer.parseInt(temp[0]),
													temp[1],
													Double.parseDouble(temp[4]),
													Integer.parseInt(temp[5]),
													Integer.parseInt(temp[6]))),
											j);
									line = scan.nextLine();
									break;
								}
							}
						} else if (temp[2].equals("mikstur")) {
							for (int j = 0; j < l_Tabell.hentstorrelse(); j++) {
								if (l_Tabell.inholder(j) == null) {
									l_Tabell.setInn(
											(new TypeCmikstur(
													Integer.parseInt(temp[0]),
													temp[1],
													Double.parseDouble(temp[4]),
													Integer.parseInt(temp[5]),
													Integer.parseInt(temp[6]))),
											j);
									line = scan.nextLine();
									break;
								}
							}
						}

					}

				}

			}
			/* create leger and put to beholder */
			else if (line.toLowerCase().contains("leger")) {
				line = scan.nextLine();

				while (!(line.equals(""))) {

					temp = line.split(", ");

					Leger leger = new Leger(temp[0], Integer.parseInt(temp[1]));
					Fastlege fleger = new Fastlege(temp[0],
							Integer.parseInt(temp[1]));
					if (Integer.parseInt(temp[1]) == 0) {
						s_EnkelListe.setInn(fleger);
						line = scan.nextLine();
					} else
						s_EnkelListe.setInn(leger);

				}

			}
			/* create resepter and put to beholder */
			else if (line.toLowerCase().contains("resepter")) {
				line = scan.nextLine();

				while (!(line.equals(""))) {

					temp = line.split(", ");
					Pasienter par = p_Tabell
							.inholder(Integer.parseInt(temp[2]));
					Leger l1 = s_EnkelListe.inholder(temp[3]);
					Resepter rr = new HviteResepter(Integer.parseInt(temp[0]),
							temp[1], Integer.parseInt(temp[2]), temp[3],
							Integer.parseInt(temp[4]),
							Integer.parseInt(temp[5]));

					if (temp[1].equals("hvit")) {
						e_ReseptListe.settInn(rr);

						par.hentYngsteForstReseptListe().settInn(rr);
						l1.hentEldsteForstReseptListe().settInn(rr);
						line = scan.nextLine();

					}

					else if (temp[1].equals("blaa")) {
						Resepter re = new BlaaResepter(
								Integer.parseInt(temp[0]), temp[1],
								Integer.parseInt(temp[2]), temp[3],
								Integer.parseInt(temp[4]),
								Integer.parseInt(temp[5]));
						e_ReseptListe.settInn(re);
						par.hentYngsteForstReseptListe().settInn(re);

						l1.hentEldsteForstReseptListe().settInn(re);
						line = scan.nextLine();

					}

				}

			}

		}

	}

	/* Read the the file and write to another file */
	public void skrivTilFile(String f) throws FileNotFoundException {

		File fil = new File(f);
		PrintWriter out = new PrintWriter(fil);

		out.println("# Personer (nr, navn, fnr, adresse, postnr)");
		for (Pasienter p : p_Tabell) {
			if (p != null)
				out.println(p.tilFile());
		}
		out.println("");
		out.println("# Legemidler (nr, navn, form, type, pris, antall/mengde, virkestoff [, styrke])");
		for (Legemidler l : l_Tabell) {
			if (l != null){
				if(l instanceof TypeA){
					if(l instanceof TypeApiller  )
				  out.println(l.tilFile());
				else if(l instanceof TypeAmikstur)
					 out.println(l.tilFile());
			}
				else if(l instanceof TypeB){
					if(l instanceof TypeBpiller  )
				  out.println(l.tilFile());
				else if(l instanceof TypeBmikstur)
					 out.println(l.tilFile());
			}
				else if(l instanceof TypeC){
					if(l instanceof TypeCpiller  )
				  out.println(l.tilFile());
				else if(l instanceof TypeCmikstur)
					 out.println(l.tilFile());
			}
				
			}
		}
		out.println("");
		out.println("# Leger (navn, avtalenr / 0 hvis ingen avtale)");
		for(Leger l:s_EnkelListe ){
			out.println(l.tilFile());
		}
		out.println("");
		out.println("# Resepter (nr, hvit/blÃ¥, persNummer, legeNavn, legemiddelNummer, reit)");
		for(Resepter r:e_ReseptListe){
			if(r instanceof HviteResepter)
				out.println(r.tilFile());
			else if(r instanceof BlaaResepter)
			out.println(r.tilFile());
		}
		out.println("");
		out.println("");
		out.println("	#  Slutt");
		out.close();
	}

	/* This method is for finding the content of every container */
	public void skrivUt_Beholder_Inhold() {
		System.out
				.println("p for Pasienter, l for leger, le for legemidler, r for resepter");
		Scanner scan = new Scanner(System.in);
		String word = scan.next();
		/* checking and prings out pasient container */
		if (word.equalsIgnoreCase("p")) {
			for (Pasienter p : p_Tabell) {
				if (p != null)
					System.out.println("Pasienter	" + p.hentunikNr() + " " + p);
			}
		}
		/* checking and print out leger container */
		else if (word.equalsIgnoreCase("l")) {
			for (Leger l : s_EnkelListe) {

				System.out.println("Leger	" + l);
			}
		}
		/* checking and prints out legemidler container */
		else if (word.equalsIgnoreCase("le")) {
			for (Legemidler l : l_Tabell) {
				if (l != null)
					System.out.println("Legemilder	" + l);
			}
		}
		/* checking and prints out resepter container */
		else if (word.equalsIgnoreCase("r")) {
			for (Resepter r : e_ReseptListe) {
				System.out.println("Resepter	" + "unikNr " + r.hentUnikNr()
						+ ", " + r);

			}
		} else
			System.out.println("ukjent foresporsel");
	}

	/* This method creates new legemidler */
	public void opprettLegemidler() {
		System.out
				.println("Skriv nr, navn, form, type, pris, antall/mengde, virkestoff [, styrke]");
		Scanner scan = new Scanner(System.in);
		String line = scan.nextLine();
		String[] temp = line.split(", ");
		if (temp[3].equalsIgnoreCase("a")) {
			if (temp[2].equals("pille")) {

				for (int j = 0; j < l_Tabell.hentstorrelse(); j++) {
					if (l_Tabell.inholder(j) == null) {
						l_Tabell.setInn(
								(new TypeApiller(Integer.parseInt(temp[0]),
										temp[1], Double.parseDouble(temp[4]),
										Integer.parseInt(temp[5]), Integer
												.parseInt(temp[6]), Integer
												.parseInt(temp[7]))), j);

						break;
					}
				}
			} else if (temp[2].equals("mikstur")) {
				for (int j = 0; j < l_Tabell.hentstorrelse(); j++) {
					if (l_Tabell.inholder(j) == null) {
						l_Tabell.setInn(
								(new TypeAmikstur(Integer.parseInt(temp[0]),
										temp[1], Double.parseDouble(temp[4]),
										Integer.parseInt(temp[5]), Integer
												.parseInt(temp[6]), Integer
												.parseInt(temp[7]))), j);

						break;
					}
				}
			}

		} else if (temp[3].equalsIgnoreCase("b")) {
			if (temp[2].equals("pille")) {

				for (int j = 0; j < l_Tabell.hentstorrelse(); j++) {
					if (l_Tabell.inholder(j) == null) {
						l_Tabell.setInn(
								(new TypeBpiller(Integer.parseInt(temp[0]),
										temp[1], Double.parseDouble(temp[4]),
										Integer.parseInt(temp[5]), Integer
												.parseInt(temp[6]), Integer
												.parseInt(temp[7]))), j);

						break;
					}
				}
			} else if (temp[2].equals("mikstur")) {
				for (int j = 0; j < l_Tabell.hentstorrelse(); j++) {
					if (l_Tabell.inholder(j) == null) {
						l_Tabell.setInn(
								(new TypeBmikstur(Integer.parseInt(temp[0]),
										temp[1], Double.parseDouble(temp[4]),
										Integer.parseInt(temp[5]), Integer
												.parseInt(temp[6]), Integer
												.parseInt(temp[7]))), j);

						break;
					}
				}
			}

		} else if (temp[3].equals("c")) {
			if (temp[2].equals("pille")) {

				for (int j = 0; j < l_Tabell.hentstorrelse(); j++) {
					if (l_Tabell.inholder(j) == null) {
						l_Tabell.setInn(
								(new TypeCpiller(Integer.parseInt(temp[0]),
										temp[1], Double.parseDouble(temp[4]),
										Integer.parseInt(temp[5]), Integer
												.parseInt(temp[6]))), j);

						break;
					}
				}
			} else if (temp[2].equals("mikstur")) {
				for (int j = 0; j < l_Tabell.hentstorrelse(); j++) {
					if (l_Tabell.inholder(j) == null) {
						l_Tabell.setInn(
								(new TypeCmikstur(Integer.parseInt(temp[0]),
										temp[1], Double.parseDouble(temp[4]),
										Integer.parseInt(temp[5]), Integer
												.parseInt(temp[6]))), j);

						break;
					}
				}
			}

		}

	}

	/* This method creates new leger */
	public void opprettLeger() {
		System.out.println("Skriv leganavn, avtale");
		Scanner scan = new Scanner(System.in);
		String line = scan.nextLine();
		String[] tmp = line.split(", ");
		if (Integer.parseInt(tmp[1]) == 0) {

			s_EnkelListe.setInn(new Leger(tmp[0], Integer.parseInt(tmp[1])));
		} else

			s_EnkelListe.setInn(new Fastlege(tmp[0], Integer.parseInt(tmp[1])));

	}

	/* This method creates new pasienter */
	public void opprettPasient() {
		System.out.println("Skriv nr, navn, fnr, adresse, postnr");
		Scanner scan = new Scanner(System.in);
		String line = scan.nextLine();
		String[] temp = line.split(", ");
		Pasienter pars = new Pasienter(Integer.parseInt(temp[0]), temp[1],
				temp[2], temp[3], Integer.parseInt(temp[4]));

		for (int j = 0; j < p_Tabell.hentstorrelse(); j++) {
			if (p_Tabell.inholder(j) == null) {
				p_Tabell.setInn(pars, j);

				break;
			}

		}

	}

	/* This method creates new Resepter */
	public void opprettResept() {
		System.out
				.println("Skriv nr, hvit/blå, persNummer, legeNavn, legemiddelNummer, reit");
		Scanner scan = new Scanner(System.in);
		String line = scan.nextLine();
		String[] temp = line.split(", ");

		if (temp[1].equals("hvit")) {
			Resepter rr = new HviteResepter(Integer.parseInt(temp[0]), temp[1],
					Integer.parseInt(temp[2]), temp[3],
					Integer.parseInt(temp[4]), Integer.parseInt(temp[5]));
			e_ReseptListe.settInn(rr);
			return;
		}

		else if (temp[1].equals("blaa")) {
			Resepter re = new BlaaResepter(Integer.parseInt(temp[0]), temp[1],
					Integer.parseInt(temp[2]), temp[3],
					Integer.parseInt(temp[4]), Integer.parseInt(temp[5]));
			e_ReseptListe.settInn(re);
			return;
		} else
			System.out.println("fail input");

	}

	/* This method get the legemiddel that is still valid */
	public void hentLegemidler() {
		Legemidler ll = null;
		int c = 0;
		Pasienter p = null;
		int antall = 0;
		int virkestoff = 0;
		System.out.println("Tas in person uniknumber");
		Scanner scan = new Scanner(System.in);
		int res = scan.nextInt();
		p = p_Tabell.inholder(res);

		if (p == null) {
			System.out.println("found nothing ");
			return;
		} else

			for (Resepter rr : p.hentYngsteForstReseptListe()) {
				if (rr != null && rr.hentReit() > 0) {

					ll = l_Tabell.inholder(rr.hentLegemiddelNr());
					rr.girResepterUt();
					System.out.println("Leger er " + rr.hentLegenavn());
					System.out.println("pasienter er " + p.hentNavn());
					System.out.println("legemilder er " + ll.hentNavn());
					System.out.println("priser er " + ll.hentPris());
					if (ll instanceof Mikstur) {
						virkestoff = ((Mikstur) ll).antallVirkeStoff();
						antall = ((Mikstur) ll).antallCmIflaske();
						System.out.println("total virkestoff " + virkestoff
								* antall);
						System.out.println("flakse volume " + antall);
					}
					if (ll instanceof Piller) {
						virkestoff = ((Piller) ll).antallVirkeStoff();
						antall = ((Piller) ll).antallPillerIflaske();
						System.out.println("total virkestoff  " + virkestoff
								* antall);
						System.out.println("antall piller  " + antall);
					}
					System.out.println("reit er " + rr.hentReit());
					System.out.println("");

				}
			}

	}

	/* This method gives several statisks . To go out this method write out */
	public void statistisk() {
		System.out.println("skriv van for vanedannende resepter");
		System.out.println("skriv blaa for blaa resepter");
		System.out.println("skriv mix for total virkestoff");
		System.out.println("skriv nl  for narkotika per leger");
		System.out.println("skriv np  for narkotika pasienter");
		System.out.println("skriv out  for gaa ut ava disse metoder");
		Scanner scan = new Scanner(System.in);
		String input = "";
		while (scan.hasNextLine()) {
			input = scan.next();
			if (input.equals("van"))
				hentVanedannende();

			else if (input.equals("blaa"))
				hentblaaResepter();

			else if (input.equals("mix"))
				hentMiksturResept();

			else if (input.equals("nl"))
				narkotikaPerLege();

			else if (input.equals("np"))
				narkotkaPerPasienter();

			else if (input.equals("out"))
				return;

			else
				System.out.println("ukjent foresporsel");
		}

	}

	public void menu() {
		System.out.println("Welcome");
		System.out.println("Tas 1 for aa lese fra file");
		System.out.println("Tas 2 for aa skrive til file");
		System.out.println("Tas 3 for aa skrive out beholdere inhold");
		System.out.println("Tas 4 for aa opprette  en ny legemider ");
		System.out.println("Tas 5 for aa opprette  en ny leger ");
		System.out.println("Tas 6 for aa opprette  en ny resept ");
		System.out.println("Tas 7 for aa opprette  en ny pasient ");
		System.out.println("Tas 8 for aa hente legemilder ");
		System.out.println("Tas 9 for aa  skriver ut menu ");
		System.out.println("Tas 10 for aa  skriver ut statistisk ");
		System.out.println("Tas 11 for aa avslutte ");

	}

	/*
	 * pring out the total number of vanedannende and those given to pasient in
	 * oslo
	 */
	public void hentVanedannende() {
		int vanTeller = 0;
		int pasientTeller = 0;
		for (Resepter r : e_ReseptListe) {
			if (r != null) {
				Legemidler re = l_Tabell.inholder(r.hentLegemiddelNr());
				Pasienter p = p_Tabell.inholder(r.hentPaseintNr());

				if (re instanceof TypeBpiller || re instanceof TypeBmikstur) {
					vanTeller++;
					if (p.hentPostnummer() < 1300)
						pasientTeller++;

				}

			}
		}

		System.out
				.println("antall er vanedannende resepter er    " + vanTeller);
		System.out.println("pasienter i Oslo  " + pasientTeller);
	}

	/* print out the blue resepter for specific person */
	public void hentblaaResepter() {
		System.out.println("Tas in person uniknumber");
		Scanner scan = new Scanner(System.in);
		int nr = scan.nextInt();
		Pasienter p = p_Tabell.inholder(nr);
		if (p == null) {
			System.out.println("finnes ikke denne pasienter");
			return;
		}

		System.out.println("pasienter " + p.hentNavn());
		for (Resepter r : p.hentYngsteForstReseptListe()) {
			if (r instanceof BlaaResepter) {
				Legemidler l = l_Tabell.inholder(r.hentLegemiddelNr());
				System.out.println("Blaa resepter er " + l.hentNavn()
						+ " og leger er  " + r.hentLegenavn());
			}

		}

	}

	/*
	 * print the total virkestoff of all resepter prescribed by a specific
	 * doctor and the amount thats is mikstur and piller
	 */
	public void hentMiksturResept() {
		int miksVirkestoff = 0;
		int antPiller = 0;
		int antmikstur = 0;
		Leger l = null;
		Legemidler ll = null;
		System.out.println("Skriv leger navn");
		Scanner scan = new Scanner(System.in);
		String navn = scan.nextLine();
		l = s_EnkelListe.inholder(navn);
		if (l == null) {
			System.out.println(" finnes ikke denne lege");
			return;
		}

		for (Resepter r : l.hentEldsteForstReseptListe()) {
			if (r != null) {
				ll = l_Tabell.inholder(r.legemiddelNr);

				if (ll instanceof Mikstur) {
					miksVirkestoff += ((Mikstur) ll).antallVirkeStoff();

					System.out.println("Miksturpreparater  " + ll.hentNavn());

				}
				if (ll instanceof Piller) {
					antPiller += ((Piller) ll).antallVirkeStoff();
				}
			}
		}
		int totalvirkerstoff = miksVirkestoff + antPiller;
		System.out.println("Total virkestoff	" + totalvirkerstoff);
		System.out.println("mikstur virkestoff	" + miksVirkestoff);
		System.out.println("piller virkestoff	" + antPiller);

	}

	/*
	 * prints the nartika legemiddel(TypeA) per doctor and the total resept per
	 * doctor
	 */
	public void narkotikaPerLege() {
		int i = 0;
		Legemidler le = null;
		for (Leger l : s_EnkelListe) {
			System.out.println("Leger er   " + l.hentNavn());
			System.out.println("-------------------------");
			for (Resepter r : l.hentEldsteForstReseptListe()) {
				if (r != null) {

					le = l_Tabell.inholder(r.hentLegemiddelNr());

					if (le instanceof TypeApiller || le instanceof TypeAmikstur) {
						i++;
						System.out.println("Resepter  " + r.hentPaseintNr());
						System.out.println("legemidler er  " + le.hentNavn()
								+ ", pasientnr er  " + r.hentPaseintNr());

					}
				}
			}
			System.out.println("Total narkotika  resepter " + i);
			System.out.println("");
			i = 0;
		}

	}

	/*
	 * prints the nartika legemiddel(TypeA) per pasient and the total resept per
	 * pasient
	 */
	public void narkotkaPerPasienter() {
		int i = 0;
		Legemidler le = null;
		for (Pasienter p : p_Tabell) {
			if (p != null) {
				System.out.println("Pasienter er   " + p.hentNavn());
				System.out.println("-------------------------");
				for (Resepter r : p.hentYngsteForstReseptListe()) {
					if (r != null && r.hentReit() > 0) {

						le = l_Tabell.inholder(r.hentLegemiddelNr());

						if (le instanceof TypeAmikstur
								|| le instanceof TypeApiller) {
							i++;
							System.out.println("Resepter gis av  "
									+ r.hentLegenavn());
							System.out.println("legemidler er  "
									+ le.hentNavn() + " pris " + le.hentPris());

						}
					}

				}
				if (i != 0) {
					System.out.println("Total narkotika  " + i);
					System.out.println("");
					i = 0;
				} else
					System.out.println("ingen gyldig resept");
				System.out.println("");

			}
		}
	}
}
