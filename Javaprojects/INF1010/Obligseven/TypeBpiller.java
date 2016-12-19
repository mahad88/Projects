

public class TypeBpiller extends TypeB implements Piller {
	private int antallPiller;
	private int virkestoff;

	TypeBpiller(int unikNr,String navn, double pris, int sterk,int virkestoff, int antallPiller) {
		super(unikNr,navn, pris, sterk);
		this.antallPiller = antallPiller;
		this.virkestoff= virkestoff;

	}

	@Override
	public int antallPillerIflaske() {

		return antallPiller;
	}

	@Override
	public int antallVirkeStoff() {
		
		return virkestoff;
	}
	public String hentForm(){
		return "pille";}
	public String hentType() {
		return "b";
	}
	public String tilFile() {
		return "" + super.hentUnikNr() + ", " + super.hentNavn() + ", "
				+ hentForm() +", "+hentType()+ ", " + super.hentPris()+", "+super.hentVanedannende()+", "+antallVirkeStoff()+", "+
				antallPillerIflaske();
	}
}