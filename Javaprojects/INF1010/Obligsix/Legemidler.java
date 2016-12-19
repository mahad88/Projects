

abstract class Legemidler {
	protected String navn;
	protected static int nummer;
	private int tempnummer;
	protected double pris;
	protected double virkestoff;

	Legemidler(String navn, double pris) {
		this.navn = navn;
		this.pris = pris;
		nummer++;
		tempnummer=nummer;
	}

	public String hentNavn() {
		return navn;
	}

	public int hentAntlegemidler() {
		return nummer;
	}

	public double hentPris() {
		return pris;
	}
	public int hentTempnummer(){
		return tempnummer;
	}
	public String toString(){
		return   hentNavn() ;
	}

}
