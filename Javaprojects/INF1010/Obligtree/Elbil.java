

public class Elbil extends Bil {

	
	private int kw;

	Elbil(String bilnummer, int kw) {
		
		super(bilnummer);
		
		this.kw = kw;
	}

	public int hentbatteriStorrelse() {
		return kw;
	}
}
