

public class Personbil extends Fossilbil {

	private int sete;

	Personbil(String bilnummer, double utslipp, int sete) {
		super(bilnummer, utslipp);
		this.sete = sete;
	}

	public int hentSete() {
		return sete;
	}
}
