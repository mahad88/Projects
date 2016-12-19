

public class Fastlege extends Leger implements Avtale {
	private int avtallnummer;

	Fastlege(String navn, int avtallnummer) {
		super(navn);
		this.avtallnummer = avtallnummer;

	}

	@Override
	public int AvtaleNummer() {

		return avtallnummer;
	}
}
