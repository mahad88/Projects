

public class TypeCpiller extends TypeC implements Piller {

	private int antallPiller;
	private int virkestoff;

	TypeCpiller(int unikNr,String navn, double pris, int virkestoff, int antallPiller) {
		super(unikNr,navn, pris);
		this.antallPiller = antallPiller;
		this.virkestoff=virkestoff;

	}

	@Override
	public int antallPillerIflaske() {

		return antallPiller;
	}

	@Override
	public int antallVirkeStoff() {

		return virkestoff;
	}
	public String hentType() {
		return "c";
	}
	public String hentForm(){
		return "pille";}
	public String tilFile(){
		return "" + super.hentUnikNr() + ", " + super.hentNavn() + ", "
				+ hentForm() +", "+hentType()
				+", " + super.hentPris()+", "+antallVirkeStoff()+", "+
				antallPillerIflaske();}
}