;

public class Oblig6 {
	public static void main(String[] args) {

		HviteResepter br = new HviteResepter("noor", "havor", 15, 23);
		HviteResepter br1 = new HviteResepter("bile", "skjel", 55, 53);
		HviteResepter br2 = new HviteResepter("asma", "per", 65, 73);
		BlaaResepter baa1 = new BlaaResepter("moha", "jib", 33, 0);

		TypeA a = new TypeA("CHAPACHOAK", 12, 234);
		TypeB b = new TypeB("brufen", 12, 234);
		TypeC c = new TypeC("asprin", 12);

		Leger lege = new Leger("ben carson");
		Leger yy = new Leger("yonis carson");
		Fastlege ff = new Fastlege("zidane", 232);
		Leger ll = new Leger("adane carson");
		Leger leg = new Leger("toutre carson");
		Fastlege fl = new Fastlege("kjetil", 232);

		Pasienter p = new Pasienter("keni", "23457522701", "bispe gata 1044",
				234);
		Pasienter p1 = new Pasienter("ask", "589759795", "bispe gata 5444", 234);
		Pasienter p2 = new Pasienter("mark", "28489758975", "bispe gata 6883",
				234);
		Pasienter p3 = new Pasienter("david", "984877487", "bispe gata 8578",
				234);

		TypeApiller tAp = new TypeApiller("panadol", 12, 2434, 452);
		TypeAmikstur tAm = new TypeAmikstur("hedex", 12, 234, 452);
		TypeBpiller tBp = new TypeBpiller("ginger", 12, 2434, 452);
		TypeBmikstur tBm = new TypeBmikstur("action", 12, 234, 452);
		TypeCpiller tCp = new TypeCpiller("valium", 12, 2434);
		TypeCmikstur tCm = new TypeCmikstur("spirit", 12, 234);

		// lager beholdere
		Tabell<Legemidler> l_Tabell = new Tabell<Legemidler>(120);
		Tabell<Pasienter> p_Tabell = new Tabell<Pasienter>(120);
		SortertEnkelListe<Leger> s_EnkelListe = new SortertEnkelListe<Leger>();
		EnkelReseptListe e_ReseptListe = new EnkelReseptListe();

		// settinn legemidler paa tabeller
		l_Tabell.setInn(tAp, 3);
		l_Tabell.setInn(tBm, 13);
		l_Tabell.setInn(tAm, 8);
		l_Tabell.setInn(a, 66);
		l_Tabell.setInn(tCp, 14);
		l_Tabell.setInn(c, 58);
		l_Tabell.setInn(tCm, 4);
		l_Tabell.setInn(b, 82);
		
			System.out.println("legemidler paa tabell beholder");
			
		for (Legemidler h : l_Tabell) {
			if (h != null)
				System.out.println("tabell		" + h);
		}
		int position = 3;
		System.out.println();
		System.out.println(" leger legemidler at position " + position + " is "
				+ l_Tabell.inholder(position).hentNavn());

		// settinn pasienter paa tabeller

		p_Tabell.setInn(p, 5);
		p_Tabell.setInn(p1, 23);
		p_Tabell.setInn(p2, 85);
		p_Tabell.setInn(p3, 60);

		System.out.println();
		System.out.println("Pasienter  legemidler paa tabell beholder");
		for (Pasienter h : p_Tabell) {
			if (h != null)
				System.out.println("Pasienter		" + h);
		}
		System.out.println();

		System.out.println();
		// sett inn leger paa beholder
		s_EnkelListe.setInn(ll);
		s_EnkelListe.setInn(fl);
		s_EnkelListe.setInn(leg);
		s_EnkelListe.setInn(lege);
		s_EnkelListe.setInn(yy);
		s_EnkelListe.setInn(ff);

		System.out.println();
		//legger inn resepter paa en pasienter beholder
		p.hentYngsteForstReseptListe().settInn(br);
		p.hentYngsteForstReseptListe().settInn(br1);
		p.hentYngsteForstReseptListe().settInn(br2);

		//legger inn resepter paa en legers beholder
		lege.hentEldsteForstReseptListe().settInn(br);
		lege.hentEldsteForstReseptListe().settInn(br1);
		lege.hentEldsteForstReseptListe().settInn(br2);
		
		// skrivut eldste resepter
		for (Resepter rr : lege.hentEldsteForstReseptListe()) {

			System.out.println("eldesteresept		" + rr);
		}
		System.out.println("");
		System.out.println("j vi har denne respesper 		"
				+ p.hentYngsteForstReseptListe().finnResepter(1));

		// skrivut yngste resepter

		for (Resepter n : p.hentYngsteForstReseptListe()) {
			System.out.println("yngesteresept		" + n.hentLegenavn());
		}
		// skriv ut leger paa sortet maate
		System.out.println();
		for (Leger l : s_EnkelListe) {

			System.out.println(l.hentNavn());
		}

	}
}