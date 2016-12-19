

public class TypeBmikstur extends TypeB implements Mikstur {
	private double cm3;
	private int virkestoff;

	TypeBmikstur(String navn, double pris, int sterk, double cm3) {
		super(navn, pris, sterk);
		this.cm3 = cm3;
	}

	@Override
	public double antallCmIflaske() {
		// TODO Auto-generated method stub
		return cm3;
	}

	@Override
	public int antallVirkeStoff() {
		// TODO Auto-generated method stub
		return virkestoff;
	}
}