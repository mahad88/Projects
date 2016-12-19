

public class Rotte {
	private String navn;
	private boolean levende = true;
	private boolean skadet = false;
	
	// constructor
	public Rotte(String navn, boolean levende) {
		this.navn = navn;
		this.levende = levende;
	}

	public String hentNavn() {
		return navn;
	}

	public boolean levende() {
		return levende;
	}

	public boolean skadet() {
		
		return skadet;
	}

	public void dod() {
	
		this.levende = false;
		
	}
	public void  erSkadet()
	{
	this.skadet=true;
	}


	
	/*Prints out if the rat is alive or dead */
	public void rotteTilstand(Rotte s) {

		if (s.levende())
			System.out.println("Rotta "+s.hentNavn() + " er levende");

		else
			System.out.println("Rotta "+s.hentNavn() + " er dod");

	}

	/*Prints out the when the rat status changes from alive to injured and from injured to dead */
	public void tilstandStatus(Rotte t) {
		if (skadet()&&levende()) {
			System.out.println("Rotta "+t.hentNavn()
					+ " gikk fra aa vaere levende til aa vaere skadet");
		} else if (skadet()) {

			System.out.println("Rotta "+t.hentNavn() + " gikk fra aa vaere skadet til aa vaere dod");
		}
	}
}
