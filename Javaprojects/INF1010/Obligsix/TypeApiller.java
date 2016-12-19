

public class TypeApiller extends TypeA implements Piller {
	private int antallpiller;
	private int virkestoff;

	TypeApiller(String navn, double pris, int sterk, int antallpiller) {
		super(navn, pris, sterk);
		this.antallpiller = antallpiller;
	}

	@Override
	public int antallPillerIflaske() {

		return antallpiller;
	}

	@Override
	public int antallVirkeStoff() {

		return virkestoff;
	}

}
