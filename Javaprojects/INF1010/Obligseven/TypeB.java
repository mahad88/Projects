

public class TypeB extends Legemidler {
	protected int vanedannende;

	TypeB(int unikNr, String navn, double pris, int vanedannende) {
		super(unikNr, navn, pris);
		this.vanedannende = vanedannende;
	}

	public int hentVanedannende() {
		return vanedannende;
	}

	public String hentType() {
		return "b";
	}
	
	
	public String tilFile(){
		return "" + super.hentUnikNr() + ", " + super.hentNavn() + ", "
				  + hentType()+", "+  hentVanedannende();
}
}