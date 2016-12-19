

public class Katt {
	private String navn;

	// constructor
	public Katt(String navn) {
		this.navn = navn;
	}

	public String hentNavn() {
		return navn;
	}

	/*
	 * the cat first attacks mouse .if their is no mouse it attacks the rat. if
	 * both are not available .print out nothing available.
	 */
	public void jakt(Bol<Mus> musebol, Bol<Rotte> rottebol) {
		Mus mus = musebol.getBeboer();
		Rotte rotte = rottebol.getBeboer();
		if (!musebol.erTom() && mus.levende()) {
			System.out.println("Katten " + hentNavn() + " har agrepet musen "
					+ mus.hentNavn());
			mus.dod();// change the status to dead
			mus.tilstandStatus(mus);

		} else if (musebol.erTom() || !mus.levende())
			if (!rottebol.erTom() &&(rotte.levende()&& !rotte.skadet())) {
				System.out.println("Katten " + hentNavn() + " har agrepet rotta "
						+ rotte.hentNavn());
				
				rotte.erSkadet();// change the status
				rotte.tilstandStatus(rotte);
			}
			else if(!rottebol.erTom() && (rotte.levende()&&rotte.skadet())){
				System.out.println("Katten " + hentNavn() + " har agrepet rotta "
						+ rotte.hentNavn());
				
				rotte.dod();// change the status
				rotte.tilstandStatus(rotte);
				
			
				

			}

			else if (rottebol.erTom() || !rotte.levende()) {
				System.out.println("Katten " + hentNavn()
						+ " fant ingen gnagnen.Jakten avsluttes");
			}

	}
}
