

public class TypeBpiller extends TypeB implements Piller {
	private int antallPiller;
	private int virkestoff;

	TypeBpiller(String navn, double pris, int sterk, int antallPiller) {
		super(navn, pris, sterk);
		this.antallPiller = antallPiller;

	}

	@Override
	public int antallPillerIflaske() {

		return antallPiller;
	}

	@Override
	public int antallVirkeStoff() {
		
		return virkestoff;
	}
}