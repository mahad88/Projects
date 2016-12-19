

abstract class Legemidler {
	protected int unikNr;
	protected String navn;
	protected static int nummer;
	private int tempnummer;
	protected double pris;
	protected int virkestoff;

	Legemidler(int unikNr, String navn, double pris) {
		this.navn = navn;
		this.pris = pris;
		nummer++;
		tempnummer = nummer;
	}

	public int hentUnikNr() {
		return unikNr;
	}

	public String hentNavn() {
		return navn;
	}

	public int hentVikrestoff() {
		return virkestoff;
	}

	public int hentAntlegemidler() {
		return nummer;
	}

	public double hentPris() {
		return pris;
	}

	public int hentTempnummer() {
		return tempnummer;
	}

	public String toString() {
		return hentNavn();
	}
	public void legemidletData(){
		System.out.println(hentUnikNr()+" "+hentNavn()+""+ hentVikrestoff()+""+ hentPris());
	}
	public String tilFile(){
		return ""+ unikNr+", "+ navn+", "+  pris;
		}
}
