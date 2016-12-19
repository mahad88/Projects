
public class Fossilbil extends Bil {
	
	private double utslipp;

	Fossilbil(String bilnummer, double utslipp) {
		super(bilnummer);
		this.utslipp = utslipp;
	}

	public double hentUtslipp() {
		return utslipp;
	}
}
