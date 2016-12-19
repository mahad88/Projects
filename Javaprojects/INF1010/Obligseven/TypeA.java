

public class TypeA extends Legemidler {
	protected int sterk;

	TypeA(int unikNr, String navn, double pris, int sterk) {
		super(unikNr, navn, pris);
		this.sterk = sterk;
	}

	public int hentSterkhet() {
		return sterk;
	}

	public String hentForm() {
		return "pille";
	}

	public String hentType() {
		return "a";
	}

	public String tilFile() {
		return "" + super.hentUnikNr() + ", " + super.hentNavn() + ", "
				 + hentForm() + ", " + hentType()+", "+ hentSterkhet();
	}
}
