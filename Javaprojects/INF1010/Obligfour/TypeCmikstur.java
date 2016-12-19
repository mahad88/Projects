

public class TypeCmikstur extends TypeC implements Mikstur {
	private double cm3;
	private int virkestoff;

	TypeCmikstur(String navn, double pris, double cm3) {
		super(navn, pris);
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
