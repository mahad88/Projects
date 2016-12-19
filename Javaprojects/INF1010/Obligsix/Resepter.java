

abstract class Resepter {
	protected Legemidler ld;
	protected static int nummer;
	protected String legenavn;
	protected String pasientnavn;
	private int tempnummer;
	protected int reit;

	Resepter(String legenavn, String pasientnavn, int reit) {
		this.legenavn = legenavn;
		this.pasientnavn = pasientnavn;
		this.reit = reit;
		nummer++;
		tempnummer = nummer;

	}

	public int hentAntresepter() {
		return nummer;
	}

	public String hentLegenavn() {
		return legenavn;
	}

	public String hentPaseintnavn() {
		return pasientnavn;
	}

	public int hentReit() {

		return reit;

	}

	public Legemidler hentLegemidler() {
		return ld;
	}
	public int hentTempnummer(){
		return tempnummer;
	}
	public String toString(){
		return hentLegenavn();}

}
