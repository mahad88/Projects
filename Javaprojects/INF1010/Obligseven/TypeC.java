

public class TypeC extends Legemidler {

	TypeC(int unikNr, String navn, double pris) {
		super(unikNr, navn, pris);

	}

	public String hentType() {
		return "c";
	}
	public String tilFile(){
		return ""+super.tilFile() ;}
}
