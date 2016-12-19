

public class Mus {
	private String navn;
	private boolean levende = true;

	// constructor
	public Mus(String navn, boolean levende) {
		this.navn = navn;
		this.levende = levende;
	}

	public String hentNavn() {
		return navn;
	}

	public boolean levende() {
		return levende;
	}

	public void dod() {
		this.levende = false;
	}

	/*Prints out if the mouse is alive or dead */
	public void musTilstand(Mus s) {

		if (s.levende())
			System.out.println("Musen " + s.hentNavn() + " er levende");

		else
			System.out.println("Musen " + s.hentNavn() + " er dod");

	}

	/*  prints out the when the mouse status changes from alive to dead */
	public void tilstandStatus(Mus t) {
		if (!t.levende())
			System.out.println("Musen " + t.hentNavn()
					+ "gikk fra aa vaere levende til aa vaere dod");
	}
}
