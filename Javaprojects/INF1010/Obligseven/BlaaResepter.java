

public class BlaaResepter extends Resepter {
	private double pris;

	BlaaResepter(int unikNr, String reseptType, int pasientNr, String legenavn,
			int legemiddelNr, int reit) {
		super(unikNr, reseptType, pasientNr, legenavn, legemiddelNr, reit);
		this.pris = pris;
	}

	public double hentPris() {
		return pris;
	}

	public String tilFile() {
		return super.tilFile();
	}
}
