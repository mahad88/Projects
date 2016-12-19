
public class TypeCmikstur extends TypeC implements Mikstur {
	private int cm3;
	private int virkestoff;

	TypeCmikstur(int unikNr, String navn, double pris, int virkestoff, int cm3) {
		super(unikNr,navn, pris);
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
	public String hentType() {
		return "c";
	}
	public String hentForm(){
		return "misktur";}
	public String tilFile(){
		return "" + super.hentUnikNr() + ", " + super.hentNavn() + ", "
				+ hentForm() + ", "+hentType()+", " + super.hentPris()+", "+antallVirkeStoff()+", "+
				antallCmIflaske();}

}
