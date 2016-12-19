

public class Fastlege extends Leger implements Avtale {
	private int avtallnummer;

	Fastlege(String navn, int avtallnummer ) {
		super(navn,avtallnummer);
		

	}

	@Override
	public int AvtaleNummer() {

		return avtallnummer;
	}
	public String tilFile(){
		return""+super.tilFile();
	}
}
