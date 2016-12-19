

public class TypeApiller extends TypeA implements Piller {
	private int antallpiller;
	private int virkestoff;

	TypeApiller(int unikNr, String navn, double pris, int sterk,
			int virkestoff, int antallpiller) {
		super(unikNr, navn, pris, sterk);
		this.antallpiller = antallpiller;
		this.virkestoff = virkestoff;
	}

	@Override
	public int antallPillerIflaske() {

		return antallpiller;
	}

	@Override
	public int antallVirkeStoff() {

		return virkestoff;
	}

	public String hentForm() {
		return "pille";
	}
	public String hentType() {
		return "a";
	}

	public String tilFile() {
		return "" + super.hentUnikNr() + ", " + super.hentNavn() + ", "
				+ hentForm() + ", "+hentType()+", " + super.hentPris()+", "+super.hentSterkhet()+", "+antallVirkeStoff()+", "+
				antallPillerIflaske();
	}
}
