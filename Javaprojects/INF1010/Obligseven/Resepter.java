

abstract class Resepter {
	protected int unikNr;
	protected String reseptType;
	protected int pasientNr;
	protected String legenavn;
	protected int legemiddelNr;
	protected int reit;
	protected Legemidler ld;
	protected static int nummer;
	private int tempnummer;

	Resepter(int unikNr, String reseptType, int pasientNr, String legenavn,
			int legemiddelNr, int reit) {
		this.unikNr = unikNr;
		this.reseptType = reseptType;
		this.pasientNr = pasientNr;
		this.legenavn = legenavn;
		this.legemiddelNr = legemiddelNr;
		this.reit = reit;
		nummer++;
		tempnummer = nummer;

	}

	public int hentUnikNr() {
		return unikNr;
	}

	public String hentReseptType() {
		return reseptType;
	}

	public int hentPaseintNr() {
		return pasientNr;
	}

	public String hentLegenavn() {
		return legenavn;
	}

	public int hentLegemiddelNr() {
		return legemiddelNr;
	}

	public int hentAntresepter() {
		return nummer;
	}

	public int hentReit() {

		return reit;

	}

	public Legemidler hentLegemidler() {
		return ld;
	}

	public int hentTempnummer() {
		return tempnummer;
	}

	public String toString() {
		return  " Type "+hentReseptType() +", legernavn   "+  hentLegenavn() ;
	}
	public void  girResepterUt(){
		 reit--;
	}
	public String tilFile(){
		return""+ unikNr+", "+ reseptType+", "+ pasientNr+", "+ legenavn+", "+
				
				legemiddelNr+", "+ reit;
	}
	

}
