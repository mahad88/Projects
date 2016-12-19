

public class TypeCpiller extends TypeC implements Piller {

	private int antallPiller;
	private int virkestoff;

	TypeCpiller(String navn, double pris, int antallPiller) {
		super(navn, pris);
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