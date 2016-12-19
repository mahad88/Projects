

public class TypeAmikstur extends TypeA implements Mikstur {
	private double cm3;
	private int virkestoff;

	TypeAmikstur(String navn, double pris, int sterk, double cm3) {
		super(navn, pris, sterk);
		this.cm3 = cm3;
	}

	@Override
	public double antallCmIflaske() {

		return cm3;
	}

	@Override
	public int antallVirkeStoff() {

		return virkestoff;
	}

}
