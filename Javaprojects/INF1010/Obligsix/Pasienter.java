

public class Pasienter {
	private String navn;
	private String fodselnummer;
	private String address;
	private int postnummer;
	private int tempnummer;
	private static int nummer = 0;
	 private YngsteForstReseptListe y_ForstReseptListe;

	Pasienter(String navn, String fodselnummer, String address, int postnummer) {
		this.navn = navn;
		this.fodselnummer = fodselnummer;
		this.address = address;
		this.postnummer = postnummer;
		nummer++;
		tempnummer = nummer;
		y_ForstReseptListe = new YngsteForstReseptListe();
	}

	public int hentNummer() {
		return nummer;
	}

	public String hentNavn() {

		return navn;
	}

	public String hentFodselnummer() {
		return fodselnummer;
	}

	public String hentAdress() {
		return address;
	}

	public int hentPostnummer() {
		return postnummer;
	}

	public int hentTempnummer() {
		return tempnummer;
	}
	public String toString(){
		return hentNavn();}
	public  YngsteForstReseptListe hentYngsteForstReseptListe(){
		return y_ForstReseptListe;}
}
