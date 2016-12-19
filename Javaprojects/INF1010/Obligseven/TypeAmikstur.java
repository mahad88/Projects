

public class TypeAmikstur extends TypeA implements Mikstur {
	private int cm3;
	private int virkestoff;

	TypeAmikstur(int unikNr,String navn, double pris, int sterk,int virkestoff, int cm3) {
		super(unikNr, navn, pris, sterk);
		this.cm3 = cm3;
		this.virkestoff=virkestoff;
	}

	@Override
	public int antallCmIflaske() {

		return cm3;
	}

	@Override
	public int antallVirkeStoff() {

		return virkestoff;
	}

	public String hentForm(){
		return "misktur";}
	public String hentType() {
		return "a";
	}
	public String tilFile(){
		return "" + super.hentUnikNr() + ", " + super.hentNavn() + ", "
				+ hentForm() + ", "+ hentType()+", "+ hentPris()+", "+super.hentSterkhet()+", "+antallVirkeStoff()+", "+
				antallCmIflaske();
}
}