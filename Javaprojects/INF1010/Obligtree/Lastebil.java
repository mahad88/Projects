

public class Lastebil extends Fossilbil {

	private double nyttevekt;

	Lastebil(String bilnummer, double utslipp, double nyttevekt) {
		super(bilnummer, utslipp);
		this.nyttevekt = nyttevekt;

	}

	public double hentNyttevekt() {
		return nyttevekt;
	}
}
